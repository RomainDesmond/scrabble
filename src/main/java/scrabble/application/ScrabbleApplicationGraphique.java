package scrabble.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrabbleApplicationGraphique extends Application {

	@Override
	public void start(Stage primaryStage)throws Exception {
		
		Parent root;
		FXMLLoader loader = new FXMLLoader( );
		loader.setLocation(getClass().getResource("ScrabbleAffichage.fxml"));
		root=loader.load();
		
		VBox root1 = new VBox();
		
		Scene scene = new Scene (root,900,900);
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println("etset");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
