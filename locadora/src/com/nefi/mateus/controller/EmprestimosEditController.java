package com.nefi.mateus.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.nefi.mateus.entity.Cliente;
import com.nefi.mateus.entity.Emprestimo;
import com.nefi.mateus.entity.Filme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EmprestimosEditController implements Initializable {

	@FXML
    private AnchorPane pnlPrincipal;

    @FXML
    private GridPane pnlDados;

    @FXML
    private DatePicker dtpVencimento;

    @FXML
    private ComboBox<Cliente> cbxCliente;

    @FXML
    private ComboBox<Filme> cbxFilme;

    @FXML
    private DatePicker dtpEmprestimo;

    @FXML
    private HBox pnlBotoes;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancela;

	private Stage janelaEmprestimoEdit;

	private Emprestimo Emprestimo;

	private boolean okClick = false;

	private ClientesListaController ClientesListaController;
	private FilmesListaController FilmesListaController;

	@FXML
	void onClickBtnCancela(ActionEvent event) {
		this.getJanelaEmprestimoEdit().close();
	}

	@FXML
	void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.Emprestimo.setEmprestimo(Date.valueOf(this.dtpEmprestimo.getValue()));
			this.Emprestimo.setVencimento(Date.valueOf(this.dtpVencimento.getValue()));
			this.Emprestimo.setCliente(this.cbxCliente.getSelectionModel().getSelectedItem());
			this.Emprestimo.setFilme(this.cbxFilme.getSelectionModel().getSelectedItem());

			this.okClick = true;
			this.getJanelaEmprestimoEdit().close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.ClientesListaController = new ClientesListaController();
		this.FilmesListaController = new FilmesListaController();

		this.carregarComboBoxClientes();
		this.carregarComboBoxFilmes();
	}

	public Stage getJanelaEmprestimoEdit() {
		return janelaEmprestimoEdit;
	}

	public void setJanelaEmprestimoEdit(Stage janelaEmprestimoEdit) {
		this.janelaEmprestimoEdit = janelaEmprestimoEdit;
	}

	public void populaTela(Emprestimo Emprestimo) {
		this.Emprestimo = Emprestimo;

		if (this.Emprestimo.getEmprestimo() != null) {
			this.dtpEmprestimo.setValue(this.Emprestimo.getEmprestimo().toLocalDate());
		}

		if (this.Emprestimo.getVencimento() != null) {
			this.dtpVencimento.setValue(this.Emprestimo.getVencimento().toLocalDate());
		}

		if (this.Emprestimo.getCliente() != null) {
			this.cbxCliente.setValue(this.Emprestimo.getCliente());
		}

		if (this.Emprestimo.getFilme() != null) {
			this.cbxFilme.setValue(this.Emprestimo.getFilme());
		}
	}

	public boolean isOkClick() {
		return okClick;
	}

	private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.dtpEmprestimo.getValue() == null) {
			mensagemErros += "Informe a data do empréstimo!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaEmprestimoEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}

	public void carregarComboBoxClientes() {
		ObservableList<Cliente> observableListaCliente = FXCollections
				.observableArrayList(this.ClientesListaController.retornaListagemCliente());

		this.cbxCliente.setItems(observableListaCliente);
	}

	public void carregarComboBoxFilmes() {
		ObservableList<Filme> observableListaFilme = FXCollections
				.observableArrayList(this.FilmesListaController.retornaListagemFilme());

		this.cbxFilme.setItems(observableListaFilme);
	}
}
