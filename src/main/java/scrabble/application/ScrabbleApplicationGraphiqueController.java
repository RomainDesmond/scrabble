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

	@FXML void initialize() {
		Joueur j=new Joueur("J1");
		Jeu plateau = new Jeu();
		plateau.ajouteTypeCase();
		/*
		for(int cpt1=0;cpt1<15;cpt1++) {
			for(int cpt2=0;cpt2<15;cpt2++) {
                Rectangle tile = new Rectangle(50, 50);
                Text text = new Text(plateau.casePosition(cpt1,cpt2).affichageCase());

                
                
                text.setFont(Font.font(20));
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);
                
				idGrilleScrabble.add(new StackPane(tile, text),cpt1,cpt2);
			}
		}*/
		plateau.remplirSacDeLettre();
		plateau.afficherPlateau();
		ScrabbleApplicationConsole.distribution(plateau, j);
		//actualiserAffichage(plateau)
		//menuChoix(j,plateau);
		System.out.println("test0");
	}

	

	public GridPane getIdGrilleScrabble() {
	    return idGrilleScrabble;
	}
	public static void actualiserAffichage(Jeu plateau, GridPane idGrilleScrabble) {

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
	}
	
	
	
	public static void menuChoixJeu(Joueur j1,Jeu plateau,GridPane GP) {
		boolean joue = true;

		System.out.println("oui");
		System.out.println((j1.sacDeLettreEstVide()));
		while (joue) {
			//System.out.println("oui");
            if (!(j1.sacDeLettreEstVide())) {
                ScrabbleApplicationConsole.affichageMenu();

                // Demander à l'utilisateur de faire un choix
                int choix = demanderChoixUtilisateur();

                switch (choix) {
                    case 1:
                        ScrabbleApplicationConsole.echanger(plateau, j1);
                        break;
                    case 2:
    					joue=false;

                        // Quitter le jeu
                        break;
                    // Continuer avec les autres cas...
            		case 3:
    					int[] positionLigneColonneMot=new int[2];
    					int[] listeDeNombre= new int[7];  
    					int[] listePosition= new int[7];
    					int nombreLettreAPlacer;
    					int choixLigneColonne;
    		
    					choixLigneColonne=ScrabbleApplicationConsole.choixLigneColonne();
    		
    					nombreLettreAPlacer=ScrabbleApplicationConsole.demanderNBLettre();
    					
    		
    					ScrabbleApplicationConsole.demanderMot(plateau,j1,listeDeNombre,nombreLettreAPlacer,positionLigneColonneMot,listePosition,choixLigneColonne);
    					System.out.println("??");
    					
    					ScrabbleApplicationConsole.jouerMot(plateau,j1,positionLigneColonneMot,listeDeNombre,listePosition,nombreLettreAPlacer,choixLigneColonne);
    					System.out.println("??");
    					ScrabbleApplicationConsole.distribution(plateau,j1);
    					System.out.println("??");
    					plateau.modificationCasePlacable();
    					System.out.println("??");

    					ScrabbleApplicationGraphiqueController.actualiserAffichage(plateau, GP);
    					break;
                    case 4:
    					joue=false;
                		System.exit(0);
                	
                }

                // Afficher les éléments de l'interface utilisateur
                plateau.afficherPlateau();
                j1.afficherChevalet();
                System.out.println("Score:" + j1.getScore());
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
                return -1; // Valeur invalide pour gérer l'erreur
            }
        } else {
            return -1; // L'utilisateur a annulé la saisie
        }
    }

    private static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
