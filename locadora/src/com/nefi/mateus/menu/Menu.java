package com.nefi.mateus.menu;

import com.nefi.mateus.controller.MenuController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Menu extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		
		
		
		
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nefi/mateus/view/Menu.fxml"));
			Parent menuXML = loader.load();

			// carregando o controller e a scene
			MenuController menuController = loader.getController();
			Scene menuLayout = new Scene(menuXML);

			Stage menuJanela = new Stage();
			menuJanela.initModality(Modality.APPLICATION_MODAL);
			menuJanela.resizableProperty().setValue(Boolean.FALSE);
			menuJanela.setScene(menuLayout);
			menuJanela.setTitle("Menu");

			// atribuindo evento para fechar janela
			menuJanela.setOnCloseRequest(e -> {
				if (menuController.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}
			});

			menuJanela.show();

			// posicionando a janela no centro da tela do computador
			Rectangle2D posicaoJanela = Screen.getPrimary().getVisualBounds();
			menuJanela.setX((posicaoJanela.getWidth() - menuJanela.getWidth()) / 2);
			menuJanela.setY((posicaoJanela.getHeight() - menuJanela.getHeight()) / 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
