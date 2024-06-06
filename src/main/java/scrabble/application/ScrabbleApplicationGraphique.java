package scrabble.application;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scrabble.model.Jeu;
import scrabble.model.Joueur;

public class ScrabbleApplicationGraphique extends Application {

	@Override
	public void start(Stage primaryStage)throws Exception {
		Joueur j=new Joueur("J1");
		Jeu plateau = new Jeu();
		plateau.ajouteTypeCase();

		
		//Parent root;
		//FXMLLoader loader = new FXMLLoader( );
		//loader.setLocation(getClass().getResource("ScrabbleAffichage.fxml"));
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScrabbleAffichage.fxml"));
        Parent root = loader.load();

		
        ScrabbleApplicationGraphiqueController controller = loader.getController();
        GridPane idGrilleScrabble = controller.getIdGrilleScrabble();
        
        
		ScrabbleApplicationGraphiqueController.actualiserAffichage(plateau,idGrilleScrabble);


		
		Scene scene = new Scene (root,1000,900);
		primaryStage.setScene(scene);
		primaryStage.show();	
		System.out.println("fin");
		ScrabbleApplicationGraphiqueController.menuChoixJeu(j, plateau,idGrilleScrabble);
		//menuChoix(j,plateau);
		//ScrabbleApplicationGraphiqueController.actualiserAffichage(plateau);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
