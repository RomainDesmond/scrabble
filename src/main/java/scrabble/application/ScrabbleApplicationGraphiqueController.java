package scrabble.application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.ValeurLettre;

public class ScrabbleApplicationGraphiqueController {
	Joueur j;
	Jeu plateau;
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
	
	@FXML
	private Button idBtnValider;
	
	@FXML
	private BorderPane idBorderPane;

	
	@FXML
	private void btnValiderAppuyer() {
        System.out.println("Bouton 'Valider' pressé");
		ScrabbleApplicationGraphiqueController.menuChoixJeu(j, plateau,idGrilleScrabble,idGrilleChevaletJ1,idLbScore,idLbTour);
	}
	
	@FXML void initialize() {
	    //BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
	    //Background background = new Background(backgroundFill);
		//Background background = new Background(new BackgroundFill(Color.SANDYBROWN, CornerRadii.EMPTY, Insets.EMPTY));
		Background background = new Background(new BackgroundFill(Color.web("#F5DEB3"), CornerRadii.EMPTY, Insets.EMPTY));

		idBorderPane.setBackground(background);
		j=new Joueur("J1");
		plateau = new Jeu();
		plateau.ajouteTypeCase();
		
		plateau.remplirSacDeLettre();
		plateau.afficherPlateau();
		ScrabbleApplicationConsole.distribution(plateau, j);		
	}

	public Joueur getJoueur() {
		return this.j;
	}

	public Jeu getPlateau() {
		return this.plateau;
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
	public Button getIdBtnValider() {
		return idBtnValider;
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
    		
    					
    					choixLigneColonne=demandeJoueurAffichage("ChoixLigneColonne","Choisissez si vous voulez jouer en ligne ou en colonne","1 - Ajout en ligne\n2 - Ajout en colonne");
    					//choixLigneColonne=ScrabbleApplicationConsole.choixLigneColonne();
    		
    					nombreLettreAPlacer=demandeJoueurAffichage("NombreLettre","Donnez le nombre de lettre que vous voulez jouer","Nombre de lettre (entre 1 et 7)");
    					//nombreLettreAPlacer=ScrabbleApplicationConsole.demanderNBLettre();
    					
    		
    					//ScrabbleApplicationConsole.demanderMot(plateau,j1,listeDeNombre,nombreLettreAPlacer,positionLigneColonneMot,listePosition,choixLigneColonne);    					
    					demanderMotFX(plateau,j1,listeDeNombre,nombreLettreAPlacer,positionLigneColonneMot,listePosition,choixLigneColonne);
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
    
    
    public static void demanderMotFX(Jeu plateau,Joueur jeu,int[] listeDeNombre,int nombreLettreAPlacer,int[] positionLigneColonneMot,int[] listePosition,int choix) {
		int nbLettreJoue=0;
		
		for (int compteur=0;compteur<nombreLettreAPlacer;compteur++) {
			nbLettreJoue=jouerLettre(plateau,listeDeNombre,nbLettreJoue);
		}
		if(nombreLettreAPlacer==nbLettreJoue) {
				if (choix == 1) {
					//positionLigneColonneMot[0]=demanderNombre("Donnez la ligne du mot :");
					positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Donnez la ligne du mot :","");
					while ((positionLigneColonneMot[0]<0) || (positionLigneColonneMot[0]>14)){
						positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Erreur ","Donnez la ligne du mot");
						//positionLigneColonneMot[0]=demanderNombre("Donnez la ligne du mot :");
					}
					for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						positionLigneColonneMot[1]=demandeJoueurAffichage("Colonne du mot","Donnez la Colonne du mot :","");
						//positionLigneColonneMot[1]=demanderNombre("Donnez la colonne de la lettre à placer :");
						while((positionLigneColonneMot[1]<0)||(positionLigneColonneMot[1]>14)) {
							positionLigneColonneMot[1]=demandeJoueurAffichage("Colonne du mot","Erreur ","Donnez la Colonne du mot");
							//positionLigneColonneMot[1]=demanderNombre("Erreur vos valeurs sont hors du tableau :");
						}
						listePosition[cpt]=positionLigneColonneMot[1];
					}
				}
				else if (choix == 2) {
					positionLigneColonneMot[1]=demandeJoueurAffichage("Colonne du mot","Donnez la Colonne du mot :","");
					//positionLigneColonneMot[1]=demanderNombre("Donnez la colonne du mot :");
					while ((positionLigneColonneMot[1]<0) || (positionLigneColonneMot[1]>14)){
						positionLigneColonneMot[1]=demandeJoueurAffichage("Ligne du mot","Erreur ","Donnez la Colonne du mot");
						//positionLigneColonneMot[1]=demanderNombre("Donnez la colonne du mot :");
					}
					for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						//positionLigneColonneMot[0]=demanderNombre("Donnez la ligne de la lettre à placer :");
						positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Donnez la ligne du mot :","");

						while((positionLigneColonneMot[0]<0)||(positionLigneColonneMot[1]>14)) {
							//positionLigneColonneMot[0]=demanderNombre("Erreur vos valeurs sont hors du tableau :");
							positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Erreur ","Donnez la ligne du mot");

						}
						listePosition[cpt]=positionLigneColonneMot[0];
					}
				}
		}
		//return true;
	}
    
	public static int jouerLettre(Jeu plateau,int[] listeNb,int nbLettreJoue) {
		//System.out.println("Donnez une lettre à jouer");
		//Scanner inputNombreEchange = new Scanner(System.in);
		//int choixPosition=inputNombreEchange.nextShort();
		int choixPosition=demandeJoueurAffichage("lettre à jouer","Donnez une leetre à jouer :","");
		int positionsLettre;
		positionsLettre=choixPosition;
		if (positionsLettre>0 && positionsLettre<8){
			for (int cpt=0;cpt<listeNb.length;cpt++) {
				System.out.println(listeNb[cpt]==(positionsLettre));
				if (listeNb[cpt]==positionsLettre) {
					
					System.out.println("lettre invalide");
					break;
				}
				else {
					
					if (listeNb[cpt]==0) {
						listeNb[cpt]=positionsLettre;
						nbLettreJoue++;
						break;
					}
				}
				
			}
		}
		return nbLettreJoue;
	}
    
    private static int demandeJoueurAffichage(String demande,String nomFenetre,String contenu) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(nomFenetre);
        dialog.setHeaderText(demande);
        dialog.setContentText(contenu);

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
        
        
        
    }/*
	public static int choixLigneColonne() {
		System.out.println("1:ajout en ligne");
		System.out.println("2:ajout en colonne");
		return demanderNombre("");
	}*/

}
