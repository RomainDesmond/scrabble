package scrabble.application;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScrabbleAffichage.fxml"));
        Parent root = loader.load();
		
        ScrabbleApplicationGraphiqueController controller = loader.getController();
        GridPane idGrilleScrabble = controller.getIdGrilleScrabble();
        GridPane idGrilleChevaletJ1 = controller.getIdGrilleChevaletJ1();
        Label idLbScore = controller.getIdLbScore();
        Label idLbTour = controller.getIdLbTour();
        
		ScrabbleApplicationGraphiqueController.actualiserAffichage(j,plateau,idGrilleScrabble,idGrilleChevaletJ1,idLbScore,idLbTour);
		
		Scene scene = new Scene (root,1000,900);
		primaryStage.setScene(scene);
		primaryStage.show();	
		ScrabbleApplicationGraphiqueController.menuChoixJeu(j, plateau,idGrilleScrabble,idGrilleChevaletJ1,idLbScore,idLbTour);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
