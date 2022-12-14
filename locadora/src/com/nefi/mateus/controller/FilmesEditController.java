package com.nefi.mateus.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.nefi.mateus.entity.Filme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FilmesEditController implements Initializable {

	@FXML
	private AnchorPane pnlPrincipal;

	@FXML
	private GridPane pnlDados;

	@FXML
	private Label lblNome;

	@FXML
	private TextField txtNome;

	@FXML
	private Label lblLancamento;

	@FXML
	private Label lblProdutora;

	@FXML
	private TextField txtProdutora;

	@FXML
	private DatePicker dtpLancamento;

	@FXML
	private HBox pnlBotoes;

	@FXML
	private Button btnOK;

	@FXML
	private Button btnCancela;

	private Stage janelaFilmeEdit;

	private Filme filme;

	private boolean okClick = false;

	@FXML
	void onClickBtnCancela(ActionEvent event) {
		this.getJanelaFilmeEdit().close();
	}

	@FXML
	void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.filme.setNome(this.txtNome.getText());
			this.filme.setProdutora(this.txtProdutora.getText());
			this.filme.setLancamento(Date.valueOf(this.dtpLancamento.getValue()));

			this.okClick = true;
			this.getJanelaFilmeEdit().close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public Stage getJanelaFilmeEdit() {
		return janelaFilmeEdit;
	}

	public void setJanelaFilmeEdit(Stage janelaFilmeEdit) {
		this.janelaFilmeEdit = janelaFilmeEdit;
	}

	public void populaTela(Filme filme) {
		this.filme = filme;
		
		if (this.filme.getLancamento() != null) {
			this.dtpLancamento.setValue(this.filme.getLancamento().toLocalDate());
		}

		this.txtNome.setText(filme.getNome());
		
		this.txtProdutora.setText(filme.getProdutora());
	}

	public boolean isOkClick() {
		return okClick;
	}

	private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
			mensagemErros += "Informe o nome!\n";
		}

		if (this.txtProdutora.getText() == null || this.txtProdutora.getText().trim().length() == 0) {
			mensagemErros += "Informe a empresa produtora!\n";
		}

		if (this.dtpLancamento.getValue() == null) {
			mensagemErros += "Informe a data de lançamento!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaFilmeEdit);
			alerta.setTitle("Dados inválidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informações:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}

}
