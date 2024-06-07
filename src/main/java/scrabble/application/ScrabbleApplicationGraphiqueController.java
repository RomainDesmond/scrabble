package scrabble.application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.ValeurLettre;

public class ScrabbleApplicationGraphiqueController {

	@FXML
	private GridPane idGrilleScrabble;

	@FXML
	private GridPane idGrilleChevaletJ1;
	
	@FXML
	private GridPane idGrilleChevaletJ2;
	
	@FXML
	private Label idLbScore;
	
	@FXML
	private Label idLbTour;
	
	@FXML void initialize() {
		Joueur j=new Joueur("J1");
		Jeu plateau = new Jeu();
		plateau.ajouteTypeCase();
		
		plateau.remplirSacDeLettre();
		plateau.afficherPlateau();
		ScrabbleApplicationConsole.distribution(plateau, j);		
	}

	

	public GridPane getIdGrilleScrabble() {
	    return idGrilleScrabble;
	}
	
	public GridPane getIdGrilleChevaletJ1() {
	    return idGrilleChevaletJ1;
	}
	
	public Label getIdLbScore() {
	    return idLbScore;
	}
	
	public Label getIdLbTour() {
		return idLbTour;
	}
	
	public static void actualiserAffichage(Joueur j,Jeu plateau, GridPane idGrilleScrabble,GridPane idGrilleChevaletJ1,Label lbScore,Label lbTour) {
		lbScore.setText(""+j.getScore());
		lbTour.setText(""+plateau.getTour());

		for(int cpt1=0;cpt1<15;cpt1++) {
			for(int cpt2=0;cpt2<15;cpt2++) {
                Rectangle tile = new Rectangle(50, 50);
                Text text = new Text(plateau.casePosition(cpt2,cpt1).affichageCase());
                
                text.setFont(Font.font(20));
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);

				idGrilleScrabble.add(new StackPane(tile, text),cpt1,cpt2);
			}
		}
		for(int cpt=0;cpt<7;cpt++) {
            Rectangle tile = new Rectangle(55, 55);
            Text text = new Text(j.donnerLettre(cpt).AffichageLettre());

            text.setFont(Font.font(20));
            tile.setFill(Color.BURLYWOOD);
            tile.setStroke(Color.BLACK);
            
            idGrilleChevaletJ1.add(new StackPane(tile, text),cpt,0);
		}
	}
	
	public static void menuChoixJeu(Joueur j1,Jeu plateau,GridPane GP,GridPane idGrilleChevaletJ1,Label idLbScore,Label idLbTour) {
		boolean joue = true;

		System.out.println("oui");
		System.out.println((j1.sacDeLettreEstVide()));
		while (joue) {
            if (!(j1.sacDeLettreEstVide())) {
                ScrabbleApplicationConsole.affichageMenu();

                int choix = demanderChoixUtilisateur();

                switch (choix) {
                    case 1:
                        ScrabbleApplicationConsole.echanger(plateau, j1);
                        break;
                    case 2:
    					joue=false;

                        break;
            		case 3:
    					int[] positionLigneColonneMot=new int[2];
    					int[] listeDeNombre= new int[7];  
    					int[] listePosition= new int[7];
    					int nombreLettreAPlacer;
    					int choixLigneColonne;
    		
    					choixLigneColonne=ScrabbleApplicationConsole.choixLigneColonne();
    		
    					nombreLettreAPlacer=ScrabbleApplicationConsole.demanderNBLettre();
    					
    		
    					ScrabbleApplicationConsole.demanderMot(plateau,j1,listeDeNombre,nombreLettreAPlacer,positionLigneColonneMot,listePosition,choixLigneColonne);    					
    					ScrabbleApplicationConsole.jouerMot(plateau,j1,positionLigneColonneMot,listeDeNombre,listePosition,nombreLettreAPlacer,choixLigneColonne);
    					ScrabbleApplicationConsole.distribution(plateau,j1);
    					plateau.modificationCasePlacable();

    					ScrabbleApplicationGraphiqueController.actualiserAffichage(j1,plateau, GP,idGrilleChevaletJ1,idLbScore,idLbTour);
    					break;
                    case 4:
    					joue=false;
                		System.exit(0);
                }

                plateau.afficherPlateau();
                j1.afficherChevalet();
                System.out.println("Score:" + j1.getScore());
                System.out.println("Tour :" + plateau.getTour());

            }
        }
	}
    private static int demanderChoixUtilisateur() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Choix de l'action");
        dialog.setHeaderText("Entrez le numéro de l'action que vous souhaitez effectuer :");
        dialog.setContentText("1 - Echanger des lettres\n2 - Passer au tour suivant\n3 - Jouer un mot\n4 - Quitter le jeu");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                return Integer.parseInt(result.get());
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un numéro valide.");
                return -1;
            }
        } else {
            return -1;
        }
    }

    private static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
