package com.nefi.mateus.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.nefi.mateus.dao.EmprestimoDAO;
import com.nefi.mateus.entity.Emprestimo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmprestimosListaController implements Initializable {

	@FXML
    private AnchorPane pnlPrincipal;

    @FXML
    private SplitPane pnlDivisao;

    @FXML
    private AnchorPane pnlEsquerda;

    @FXML
    private TableView<Emprestimo> tbvEmprestimos;

    @FXML
    private TableColumn<Emprestimo, Long> tbcCodigo;

    @FXML
    private TableColumn<Emprestimo, Date> tbcVencimento;

    @FXML
    private AnchorPane pnlDireita;

    @FXML
    private Label lblDetalhes;

    @FXML
    private GridPane pnlDetalhes;

    @FXML
    private Label lblEmprestimoValor;

    @FXML
    private Label lblVencimentoValor;

    @FXML
    private Label lblClienteValor;

    @FXML
    private Label lblFilmeValor;

    @FXML
    private ButtonBar barBotoes;

    @FXML
    private Button btnInclur;

    @FXML
    private Tooltip tlpIncluir;

    @FXML
    private Button btnEditar;

    @FXML
    private Tooltip tlpEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Tooltip tlpExcluir;

	private List<Emprestimo> listaEmprestimos;
	private ObservableList<Emprestimo> observableListaEmprestimos = FXCollections.observableArrayList();
	private EmprestimoDAO EmprestimoDAO;

	public static final String EMPRESTIMO_EDITAR = " - Editar";
	public static final String EMPRESTIMO_INCLUIR = " - Incluir";

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Emprestimo Emprestimo = this.tbvEmprestimos.getSelectionModel().getSelectedItem();

		if (Emprestimo != null) {
			boolean btnConfirmarClick = this.onShowTelaEmprestimoEditar(Emprestimo, EmprestimosListaController.EMPRESTIMO_EDITAR);

			if (btnConfirmarClick) {
				this.getEmprestimoDAO().update(Emprestimo, null);
				this.carregarTableViewEmprestimos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma Emprestimo na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Emprestimo Emprestimo = this.tbvEmprestimos.getSelectionModel().getSelectedItem();

		if (Emprestimo != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do emprestimo?\n Cliente " + Emprestimo.getCliente() + " e Filme " + Emprestimo.getFilme());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getEmprestimoDAO().delete(Emprestimo);
				this.carregarTableViewEmprestimos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma Emprestimo na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Emprestimo Emprestimo = new Emprestimo();

		boolean btnConfirmarClic = this.onShowTelaEmprestimoEditar(Emprestimo, EmprestimosListaController.EMPRESTIMO_INCLUIR);

		if (btnConfirmarClic) {
			this.getEmprestimoDAO().save(Emprestimo);
			this.carregarTableViewEmprestimos();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setEmprestimoDAO(new EmprestimoDAO());
		this.carregarTableViewEmprestimos();
		this.selecionarItemTableViewEmprestimos(null);

		this.tbvEmprestimos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewEmprestimos(newValue));
	}

	public EmprestimoDAO getEmprestimoDAO() {
		return EmprestimoDAO;
	}

	public void setEmprestimoDAO(EmprestimoDAO EmprestimoDAO) {
		this.EmprestimoDAO = EmprestimoDAO;
	}

	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
		this.listaEmprestimos = listaEmprestimos;
	}

	public ObservableList<Emprestimo> getObservableListaEmprestimos() {
		return observableListaEmprestimos;
	}

	public void setObservableListaEmprestimos(ObservableList<Emprestimo> observableListaEmprestimos) {
		this.observableListaEmprestimos = observableListaEmprestimos;
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do cadastro de Emprestimo?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public void carregarTableViewEmprestimos() {
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcVencimento.setCellValueFactory(new PropertyValueFactory<>("vencimento"));

		this.setListaEmprestimos(this.getEmprestimoDAO().getAll());
		this.setObservableListaEmprestimos(FXCollections.observableArrayList(this.getListaEmprestimos()));
		this.tbvEmprestimos.setItems(this.getObservableListaEmprestimos());
	}

	public void selecionarItemTableViewEmprestimos(Emprestimo Emprestimo) {
		if (Emprestimo != null) {
			this.lblEmprestimoValor.setText(Emprestimo.getEmprestimoFormatado());
			this.lblVencimentoValor.setText(Emprestimo.getVencimentoFormatado());
			this.lblClienteValor.setText(Emprestimo.getCliente().getNome());
			this.lblFilmeValor.setText(Emprestimo.getFilme().getNome());
		} else {
			this.lblEmprestimoValor.setText("");
			this.lblVencimentoValor.setText("");
			this.lblClienteValor.setText("");
			this.lblFilmeValor.setText("");
		}
	}

	public boolean onShowTelaEmprestimoEditar(Emprestimo Emprestimo, String operacao) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nefi/mateus/view/EmprestimosEdit.fxml"));
			Parent EmprestimoEditXML = loader.load();

			// criando uma janela nova
			Stage janelaEmprestimoEditar = new Stage();
			janelaEmprestimoEditar.setTitle("Cadastro de Emprestimo" + operacao);
			janelaEmprestimoEditar.initModality(Modality.APPLICATION_MODAL);
			janelaEmprestimoEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene EmprestimoEditLayout = new Scene(EmprestimoEditXML);
			janelaEmprestimoEditar.setScene(EmprestimoEditLayout);

			// carregando o controller e a scene
			EmprestimosEditController EmprestimoEditController = loader.getController();
			EmprestimoEditController.setJanelaEmprestimoEdit(janelaEmprestimoEditar);
			EmprestimoEditController.populaTela(Emprestimo);

			// mostrando a tela
			janelaEmprestimoEditar.showAndWait();

			return EmprestimoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
