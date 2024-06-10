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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.ValeurLettre;

public class ScrabbleApplicationGraphiqueController {
	int[]positionsX =new int[7];
	int[]positionsY = new int[7];

	int nombreLettrePose=0;
	ValeurLettre[] listeValeurLettre=new ValeurLettre[7];
	Joueur j;
	Joueur autreJ;
	Joueur j1;
	Joueur j2;
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
	private Label idLbScore2;
	
	@FXML
	private Label idLbTour;
	
	@FXML
	private Button idBtnValider;
	
	@FXML
	private BorderPane idBorderPane;
	
	@FXML
	private Button idBtnEchanger;
	
	@FXML
	private Button idBtnPasserSonTour;
	
	@FXML
	private void btnValiderAppuyer() {
		System.out.println(plateau.casePosition(7, 0).estUtilisable());
		System.out.println(plateau.casePosition(7, 0).estUtilise());

		int[] positionLigneColonneMot= new int[2];
		if(toutesValeursSontEgalsX()) {
			positionLigneColonneMot[0]=positionsX[0];
			System.out.println("ligne");
			if(verifierLigneContientPasEspace()) {
				System.out.println("ligne2");
				if (plateau.getTour()%2==0) {
					jouerMotFX(plateau,j,positionLigneColonneMot,listeValeurLettre,positionsY,nombreLettrePose,1);
				}
				else {
					jouerMotFX(plateau,j2,positionLigneColonneMot,listeValeurLettre,positionsY,nombreLettrePose,1);
				}
			}
			else {
				for (int cpt=0;cpt<nombreLettrePose;cpt++){
					if (plateau.getTour()%2==0) {
						j.ajouterLettre(plateau.getLettre(positionsX[cpt],positionsY[cpt]));
					}
					else {
						j2.ajouterLettre(plateau.getLettre(positionsX[cpt],positionsY[cpt]));
					}
					plateau.supprimerLettreEmplacement(positionsX[cpt],positionsY[cpt]);
				}
			}
		}
		else if (toutesValeursSontEgalsY()) {
			positionLigneColonneMot[1]=positionsY[0];
			System.out.println("vérification : "+verifierColonneContientPasEspace());
			if(verifierColonneContientPasEspace()) {
				if (plateau.getTour()%2==0) {
					jouerMotFX(plateau,j,positionLigneColonneMot,listeValeurLettre,positionsX,nombreLettrePose,2);
				}
				else {
					jouerMotFX(plateau,j2,positionLigneColonneMot,listeValeurLettre,positionsX,nombreLettrePose,2);
				}
			}
			else {
				for (int cpt=0;cpt<nombreLettrePose;cpt++){
					if (plateau.getTour()%2==0) {
						j.ajouterLettre(plateau.getLettre(positionsX[cpt],positionsY[cpt]));
					}
					else {
						j2.ajouterLettre(plateau.getLettre(positionsX[cpt],positionsY[cpt]));
					}
					plateau.supprimerLettreEmplacement(positionsX[cpt],positionsY[cpt]);
				}
			}
		}
		else {
			System.out.println("autre");
			for (int cpt=0;cpt<nombreLettrePose;cpt++){
				System.out.println("Ligne a supprimer"+positionsX[cpt]);
				if (plateau.getTour()%2==0) {
					j.ajouterLettre(plateau.getLettre(positionsX[cpt]-1,positionsY[cpt]-1));
				}
				else {
					j.ajouterLettre(plateau.getLettre(positionsX[cpt]-1,positionsY[cpt]-1));
				}
				plateau.supprimerLettreEmplacement(positionsX[cpt]-1,positionsY[cpt]-1);
			}
			
	}
		positionsX =new int[7];
		actualisationPositionsX();
		positionsY=new int[7];
		actualisationPositionsY();
		nombreLettrePose=0;
		listeValeurLettre=new ValeurLettre[7];
		plateau.modificationCasePlacable();
		ScrabbleApplicationConsole.distribution(plateau, j);
		ScrabbleApplicationConsole.distribution(plateau, j2);
		actualiserAffichage(j, plateau, idGrilleScrabble, idGrilleChevaletJ1, idLbScore, idLbTour);
		
	}
	
	@FXML
	private void btnEchanger() {
		echanger(plateau,j);
		plateau.ajouterUnTour();
		actualiserAffichage(j, plateau, idGrilleScrabble, idGrilleChevaletJ1, idLbScore, idLbTour);
	}
	
	@FXML
	private void btnPasserSonTour() {
		plateau.ajouterUnTour();
		//menuChoixJeu(j, plateau,idGrilleScrabble,idGrilleChevaletJ1,idLbScore,idLbTour);
	}
	
	@FXML void initialize() {
		Background background = new Background(new BackgroundFill(Color.web("#F5DEB3"), CornerRadii.EMPTY, Insets.EMPTY));

		idBorderPane.setBackground(background);
		j=new Joueur("J1");
		j2=new Joueur("J2");
		plateau = new Jeu();
		plateau.ajouteTypeCase();
		
		plateau.remplirSacDeLettre();
		plateau.afficherPlateau();
		ScrabbleApplicationConsole.distribution(plateau, j);
		j.afficherChevalet();
		j2.afficherChevalet();
		System.out.println(j2.sacDeLettreEstVide());
		ScrabbleApplicationConsole.distribution(plateau, j2);		
		j2.afficherChevalet();

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
	
	public void ajouterPositionX(int nombreX) {
		nombreX++;
		for (int i = 0; i<7;i++) {
			if (positionsX[i]==-1) {
				positionsX[i]=nombreX;
				break;
			}
		}
	}
	public void ajouterPositionY(int nombreY) {
		nombreY++;
		for (int i = 0; i<7;i++) {
			if (positionsY[i]==-1) {
				positionsY[i]=nombreY;
				break;
			}
		}
	}
	
	public void ajouterListeValeurLettre(ValeurLettre valeurLettre) {
		System.out.println(valeurLettre.AffichageLettre());
		for (int i = 0; i<7;i++) {
			if (listeValeurLettre[i]==null) {
				System.out.println("Ajout");
				listeValeurLettre[i]=valeurLettre;
				break;
			}
		}
	}
	
	public int premiereLettreAjoute(int[] positionLettre) {
		int minimum=100;
		for (int i =0;i<7;i++) {
			System.out.println((positionLettre[i]));
			if ((positionLettre[i]<minimum)&&(positionLettre[i]!=-1)){
				minimum=positionLettre[i];
			}
		}
		return minimum;
	}
	
	public int derniereLettreAjoute(int[] positionLettre) {
		int maximum=-1;
		for (int i =0;i<7;i++) {
			if (positionLettre[i]>maximum){
				maximum=positionLettre[i];
			}
		}
		return maximum;
	}
	public void actualisationPositionsX() {
		for (int cpt =0; cpt<positionsX.length;cpt++) {
			positionsX[cpt]=-1;
		}
	}
	public void actualisationPositionsY() {
		for (int cpt =0; cpt<positionsY.length;cpt++) {
			positionsY[cpt]=-1;
		}
	}
	public Boolean verifierLigneContientPasEspace() {
		
		int posPremiereLettreAjoute=premiereLettreAjoute(positionsY);
		int posDerniereLettreAjoute=derniereLettreAjoute(positionsY);
		int cpt=0;
		System.out.println(posPremiereLettreAjoute +" "+posDerniereLettreAjoute);
		if (posPremiereLettreAjoute!=100) {
			while (plateau.getLettre(positionsX[0]-1,posPremiereLettreAjoute+cpt-1)!=null) {
				if (posPremiereLettreAjoute+cpt==posDerniereLettreAjoute) {
					return true;
				}
				cpt++;
			}
		}
		System.out.println("non");
		return false;
	}
	
	public boolean verifierColonneContientPasEspace() {
		int posPremiereLettreAjoute=premiereLettreAjoute(positionsX);
		int posDerniereLettreAjoute=derniereLettreAjoute(positionsX);
		int cpt=0;
		if (posPremiereLettreAjoute!=100) {
			while (plateau.getLettre(posPremiereLettreAjoute+cpt-1,positionsY[0]-1)!=null) {
				if (posPremiereLettreAjoute+cpt==posDerniereLettreAjoute) {
					return true;
				}
				cpt++;
			}
		}
		return false;
	}
	
	
	
	public void afficher() {
		System.out.println("afficheeee");
		for (int i = 0; i<7;i++) {
			System.out.println("affichage:"+listeValeurLettre[i].AffichageLettre());
				//listeValeurLettre[i]=valeurLettre;
		}
	}
	public Boolean toutesValeursSontEgalsX() {
		for(int i=0;i<7;i++) {
			System.out.println(positionsX[0]+"v"+positionsX[i]);
			if((positionsX[0]!=positionsX[i])&&(positionsX[i]!=-1)){
				System.out.println(positionsX[0]+" "+positionsX[1]);
				return false;
			}
		}
		System.out.println("posée en ligne");
		return true;
	}
	
	
	public Boolean toutesValeursSontEgalsY() {
		for(int i=0;i<7;i++) {
			if((positionsY[0]!=positionsY[i])&&(positionsX[i]!=-1)) {
				System.out.println(positionsY[0]+" "+positionsY[1]);
				return false;
			}
		}

		return true;
	}

	public void actualiserAffichage(Joueur j,Jeu plateau, GridPane idGrilleScrabble,GridPane idGrilleChevaletJ1,Label lbScore,Label lbTour) {
		System.out.println("Afficher Chevalet");
		j.afficherChevalet();
		lbScore.setText(""+j.getScore());
		idLbScore2.setText(""+j2.getScore());
		lbTour.setText(""+plateau.getTour());
	    idGrilleScrabble.getChildren().clear();
	    idGrilleChevaletJ1.getChildren().clear();


		for(int cpt1=0;cpt1<15;cpt1++) {
			for(int cpt2=0;cpt2<15;cpt2++) {
                Rectangle tile = new Rectangle(50, 50);
                Text text = new Text(plateau.casePosition(cpt2,cpt1).affichageCase());
                //Text textPoint = new Text(String.valueOf(plateau.casePosition(cpt2, cpt1).getContenu().getPoint()));
                //System.out.printl
                /*Text textPoint = new Text();
                VBox vb;
    	        text.setFont(Font.font(20));
    	        tile.setFill(Color.BURLYWOOD);
    	        tile.setStroke(Color.BLACK);
    	        textPoint.setFont(Font.font(10));

    	        vb=new VBox(text,textPoint);
    	        vb.setPrefWidth(50);
    	        vb.setPrefHeight(50);
    	        vb.setMaxWidth(50);
    	        vb.setMaxHeight(50);*/
                
                text.setFont(Font.font(20));
                if (plateau.casePosition(cpt2, cpt1).affichageTypeCase()!="") {
                	if (plateau.casePosition(cpt2, cpt1).affichageTypeCase()=="MT"){
    	                tile.setFill(Color.RED);
                	}
                	else if (plateau.casePosition(cpt2, cpt1).affichageTypeCase()=="MD") {
    	                tile.setFill(Color.PINK);
                	}
                	else if (plateau.casePosition(cpt2, cpt1).affichageTypeCase()=="LT") {
    	                tile.setFill(Color.BLUE);
                	}
                	else if (plateau.casePosition(cpt2, cpt1).affichageTypeCase()=="LD") {
    	                tile.setFill(Color.LIGHTBLUE);
                	}
                	else if (plateau.casePosition(cpt2, cpt1).affichageTypeCase()=="*") {
    	                tile.setFill(Color.PINK);
                	}
                }
                else {
	                tile.setFill(Color.BURLYWOOD);
                }
                

  
                tile.setStroke(Color.BLACK);
                StackPane sP=new StackPane (tile,text);
                //StackPane sP=new StackPane (tile,vb);

                sP.setOnDragOver(event -> {
                    if (event.getGestureSource() != sP && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });
                int i1=cpt2;
                int i2=cpt1;

                sP.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if(plateau.getTour()%2==0) {
	                    if (db.hasString()) {
	                        String[] parts = db.getString().split(",");
	                        int ligne = Integer.parseInt(parts[0]);
	                        int colonne = Integer.parseInt(parts[1]);
	        				if (j.donnerLettre(colonne)==ValeurLettre.JOKER) {
	        					choixJoker(j,listeValeurLettre,colonne);
	        				}
	                        
	                        if(plateau.placerLettreJoue(i1, i2, j.donnerLettre(colonne))) {
	    
	                        	ajouterPositionX(i1);
	                        	ajouterPositionY(i2);
	                        	ajouterListeValeurLettre(j.donnerLettre(colonne));
	                        	j.afficherChevalet();
	                        	nombreLettrePose++;
	                        	//afficher();
	                        	j.supprimerLettre(colonne);
	                        	actualiserAffichage(j, plateau, idGrilleScrabble, idGrilleChevaletJ1, lbScore, lbTour);
	                        }
	
	                        success = true;
	                    }
                    }
                    else {
	                    if (db.hasString()) {
	                        String[] parts = db.getString().split(",");
	                        int ligne = Integer.parseInt(parts[0]);
	                        int colonne = Integer.parseInt(parts[1]);
	        				if (j2.donnerLettre(colonne)==ValeurLettre.JOKER) {
	        					choixJoker(j2,listeValeurLettre,colonne);
	        				}
	                        
	                        if(plateau.placerLettreJoue(i1, i2, j2.donnerLettre(colonne))) {
	    
	                        	ajouterPositionX(i1);
	                        	ajouterPositionY(i2);
	                        	ajouterListeValeurLettre(j2.donnerLettre(colonne));
	                        	j.afficherChevalet();
	                        	nombreLettrePose++;
	                        	//afficher();
	                        	j2.supprimerLettre(colonne);
	                        	actualiserAffichage(j, plateau, idGrilleScrabble, idGrilleChevaletJ1, lbScore, lbTour);
	                        }
	
	                        success = true;
	                    }
                    }


                    event.setDropCompleted(success);
                    event.consume();
                });

				idGrilleScrabble.add(sP,cpt1,cpt2);

			
			}
		}
	    for (int cpt = 0; cpt < 7; cpt++) {
	        ValeurLettre lettre = j.donnerLettre(cpt);
	        Rectangle tile = new Rectangle(55, 55);
	        VBox vb= new VBox();
	        Text text;
	        Text textPoint;
	        if (lettre != null) {
	            text = new Text(lettre.AffichageLettre());
	            textPoint= new Text(String.valueOf(lettre.getPoint()));
	        } else {
	            text = new Text("");
		        textPoint= new Text("");

	        }
	        text.setFont(Font.font(20));
	        tile.setFill(Color.BURLYWOOD);
	        tile.setStroke(Color.BLACK);
	        textPoint.setFont(Font.font(10));

	        vb=new VBox(text,textPoint);;
	        vb.setPrefWidth(50);
	        vb.setPrefHeight(50);
	        vb.setMaxWidth(50);
	        vb.setMaxHeight(50);
	        StackPane sP = new StackPane(tile, vb);
	        idGrilleChevaletJ1.add(sP, cpt, 0);

	        final int i = cpt;
	        if (plateau.getTour()%2==0) {
	        sP.setOnDragDetected(event -> {
	            if (lettre != null) {
	                Dragboard db = sP.startDragAndDrop(TransferMode.MOVE);
	                ClipboardContent content = new ClipboardContent();
	                content.putString("1," + i);
	                db.setContent(content);
	                event.consume();
	            }
	        });
	        
	        sP.setOnDragOver(event -> {
                if (event.getGestureSource() != sP && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }); 

            sP.setOnDragDropped(event -> {
                ValeurLettre temp;
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String[] split = db.getString().split(",");
                    int sourceIndex = Integer.parseInt(split[1]);
                    
                    System.out.println(sourceIndex+" "+i);
                    if (sourceIndex<i) {
	                    temp=j.supprimerLettre(sourceIndex);
	                    j.ajouterLettre(j.supprimerLettre(i));
	                    j.ajouterLettre(temp);}
                    else {
	                    temp=j.supprimerLettre(i);
	                    j.ajouterLettre(j.supprimerLettre(sourceIndex));
	                    j.ajouterLettre(temp);
	                    }

                    
                    
                    actualiserAffichage(j, plateau, idGrilleScrabble, idGrilleChevaletJ1, lbScore, lbTour);


                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            });
	        }
		}
	    
	    for (int cpt = 0; cpt < 7; cpt++) {
	    	System.out.println("Chevalet j2:" );
	    	j2.afficherChevalet();
	        ValeurLettre lettre = j2.donnerLettre(cpt);
	        Rectangle tile = new Rectangle(55, 55);
	        VBox vb= new VBox();
	        Text text;
	        Text textPoint;
	        if (lettre != null) {
	            text = new Text(lettre.AffichageLettre());
	            textPoint= new Text(String.valueOf(lettre.getPoint()));
	        } else {
	            text = new Text("");
		        textPoint= new Text("");

	        }
	        text.setFont(Font.font(20));
	        tile.setFill(Color.BURLYWOOD);
	        tile.setStroke(Color.BLACK);
	        textPoint.setFont(Font.font(10));

	        vb=new VBox(text,textPoint);;
	        vb.setPrefWidth(50);
	        vb.setPrefHeight(50);
	        vb.setMaxWidth(50);
	        vb.setMaxHeight(50);
	        StackPane sP = new StackPane(tile, vb);
	        idGrilleChevaletJ2.add(sP, cpt, 0);

	        final int i = cpt;
	        if (plateau.getTour()%2==1) {
	        sP.setOnDragDetected(event -> {
	            if (lettre != null) {
	                Dragboard db = sP.startDragAndDrop(TransferMode.MOVE);
	                ClipboardContent content = new ClipboardContent();
	                content.putString("1," + i);
	                db.setContent(content);
	                event.consume();
	            }
	        });
	        
	        sP.setOnDragOver(event -> {
                if (event.getGestureSource() != sP && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }); 

            sP.setOnDragDropped(event -> {
                ValeurLettre temp;
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String[] split = db.getString().split(",");
                    int sourceIndex = Integer.parseInt(split[1]);
                    
                    System.out.println(sourceIndex+" "+i);
                    if (sourceIndex<i) {
	                    temp=j2.supprimerLettre(sourceIndex);
	                    j2.ajouterLettre(j2.supprimerLettre(i));
	                    j2.ajouterLettre(temp);}
                    else {
	                    temp=j2.supprimerLettre(i);
	                    j2.ajouterLettre(j2.supprimerLettre(sourceIndex));
	                    j2.ajouterLettre(temp);
	                    }

                    
                    
                    actualiserAffichage(j, plateau, idGrilleScrabble, idGrilleChevaletJ1, lbScore, lbTour);


                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            });
	        }
		}
	}
	
	public void menuChoixJeu(Joueur j1,Jeu plateau,GridPane GP,GridPane idGrilleChevaletJ1,Label idLbScore,Label idLbTour) {
		boolean joue = true;

		//System.out.println("oui");
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

    					actualiserAffichage(j1,plateau, GP,idGrilleChevaletJ1,idLbScore,idLbTour);
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
					positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Donnez la ligne du mot :","");
					while ((positionLigneColonneMot[0]<0) || (positionLigneColonneMot[0]>14)){
						positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Erreur ","Donnez la ligne du mot");
					}
					for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						positionLigneColonneMot[1]=demandeJoueurAffichage("Colonne du mot","Donnez la Colonne du mot :","");
						while((positionLigneColonneMot[1]<0)||(positionLigneColonneMot[1]>14)) {
							positionLigneColonneMot[1]=demandeJoueurAffichage("Colonne du mot","Erreur ","Donnez la Colonne du mot");
						}
						listePosition[cpt]=positionLigneColonneMot[1];
					}
				}
				else if (choix == 2) {
					positionLigneColonneMot[1]=demandeJoueurAffichage("Colonne du mot","Donnez la Colonne du mot :","");
					while ((positionLigneColonneMot[1]<0) || (positionLigneColonneMot[1]>14)){
						positionLigneColonneMot[1]=demandeJoueurAffichage("Ligne du mot","Erreur ","Donnez la Colonne du mot");
					}
					for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Donnez la ligne du mot :","");

						while((positionLigneColonneMot[0]<0)||(positionLigneColonneMot[1]>14)) {
							positionLigneColonneMot[0]=demandeJoueurAffichage("Ligne du mot","Erreur ","Donnez la ligne du mot");
						}
						listePosition[cpt]=positionLigneColonneMot[0];
					}
				}
		}
		//return true;
	}
    
	public static int jouerLettre(Jeu plateau,int[] listeNb,int nbLettreJoue) {
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
        
        
        
    }
    
    
    
	public static void echanger(Jeu plateau,Joueur j) {
		
		ValeurLettre lettreTemp=null;
		int nombreTemporaire=-1;
		int[] positionsEchanger=new int[7];

		int choix=demandeJoueurAffichage("Nombre lettre à échanger","Donnez le nombre de lettre à échanger","Nombre de lettre à échanger :");
		
		int compteur=0;
		if (choix>0 && choix<8) {
			if (choix<7) {
				for (compteur=0;compteur<choix;compteur++) {
					int choixPosition=demandeJoueurAffichage("Position de la lettre à échanger","Donnez la position de la lettre à échanger","Lettre : "+compteur);
					positionsEchanger[compteur]=choixPosition;
					}			
			
				for (compteur=0;compteur<choix;compteur++) {
					plateau.ajouterLettrePremierePositionDisponible(j.supprimerLettre(positionsEchanger[compteur]-1));
					ScrabbleApplicationConsole.distribuerUneLettre(plateau,j);
				}
			}
			else {
				for (compteur=0;compteur<choix;compteur++) {
					int choixPosition=compteur;
					positionsEchanger[compteur]=(choixPosition+1);
					}			
			
				for (compteur=0;compteur<choix;compteur++) {
					plateau.ajouterLettrePremierePositionDisponible(j.supprimerLettre(positionsEchanger[compteur]-1));
					ScrabbleApplicationConsole.distribuerUneLettre(plateau,j);
				}
			}
		}
	
		
	}
	
	public static boolean jouerMotFX(Jeu plateau,Joueur j,int[] positionLigneColonneMot,ValeurLettre[] listeValeurLettre,int[] listePosition,int nombreLettreAPlacer,int choix) {
		System.out.println("nb et choix "+nombreLettreAPlacer+" "+choix);

		boolean motEstJoue=true;
		if (choix == 1) {
			for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				/*
				if (listeValeurLettre[cpt]==ValeurLettre.JOKER) {
					choixJoker(j,listeValeurLettre,cpt);
				}*/
				//if(!(plateau.placerLettreJoue(positionLigneColonneMot[0], listePosition[cpt],j.donnerLettre(listeDeNombre[cpt]-1)))) {
					//motEstJoue=false;
					//break;
				//}
			}
			plateau.afficherPlateau();
			if ((plateau.motEstPlacableLigne(positionLigneColonneMot[0], listePosition,nombreLettreAPlacer))&&(motEstJoue)) {
				compterLesPointsLigneFX(j,plateau,nombreLettreAPlacer,listeValeurLettre,positionLigneColonneMot[0],listePosition);
			}					
			else if (motEstJoue){
				System.out.println("non placable");
				motEstJoue=false;
				for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
					System.out.println(positionLigneColonneMot[0]+" "+listePosition[cpt]);
					plateau.supprimerLettreEmplacement(positionLigneColonneMot[0]-1, listePosition[cpt]-1);
					
				}
			}
		}
		else if(choix == 2) {
			for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				if (listeValeurLettre[cpt]==ValeurLettre.JOKER) {
					choixJoker(j,listeValeurLettre,cpt);
				}
				//if (!(plateau.placerLettreJoue(listePosition[cpt], positionLigneColonneMot[1],j.donnerLettre(listeDeNombre[cpt]-1)))) {
					//motEstJoue=false;
				//	break;
				//}
				}
			
			if ((plateau.motEstPlacableColonne(listePosition,positionLigneColonneMot[1],nombreLettreAPlacer))&&(motEstJoue)) {
				compterLesPointsColonneFX(j,plateau,nombreLettreAPlacer,listeValeurLettre,listePosition,positionLigneColonneMot[1]);
			}					
			else if (motEstJoue){
				plateau.afficherPlateau();

				motEstJoue=false;
				for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
					plateau.supprimerLettreEmplacement(positionLigneColonneMot[0]-1, listePosition[cpt]-1);

					//plateau.placerLettreJoue(listePosition[cpt],positionLigneColonneMot[1],null);
				}
			}
		}
		if (motEstJoue) {
			plateau.ajouterUnTour();
		}
		plateau.modificationCasePlacable();
		plateau.afficherPlateau();
		return motEstJoue;
	}
	
	public static void compterLesPointsLigneFX(Joueur j,Jeu plateau,int nombreLettreAPlacer,ValeurLettre[] listeValeurLettre,int positionsLigne,int[] positionsColonne) {
		//WIP
		int multiplicateurMot=1;
		int scoreMot=0;
		int[] lettreCompleteCompteColonne= new int[15];
		int[] lettreCompleteCompteLigne= new int[15];
		System.out.println(nombreLettreAPlacer);

		for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {

			int multiplicateurLettre=1;
			scoreMot=scoreMot+ScrabbleApplicationConsole.ajoutMotCompleteLigne(j,plateau,positionsLigne,positionsColonne[cpt],positionsColonne,lettreCompleteCompteLigne,lettreCompleteCompteColonne);
			multiplicateurMot=multiplicateurMot*plateau.typeCasePosition(positionsLigne,positionsColonne[cpt]).multiplicateurCaseMot();
			multiplicateurLettre=multiplicateurLettre*plateau.typeCasePosition(positionsLigne, positionsColonne[cpt]).multiplicateurCaseLettre();
			j.afficherChevalet();
			System.out.println("cpt"+cpt);
			System.out.println(listeValeurLettre[cpt]);
			scoreMot=scoreMot+listeValeurLettre[cpt].getPoint()*multiplicateurLettre;
		}
		if (nombreLettreAPlacer==7){
			scoreMot=scoreMot+50;
		}
		j.setScore(j.getScore()+(scoreMot*multiplicateurMot));
	}

	
	public static void compterLesPointsColonneFX(Joueur j,Jeu plateau,int nombreLettreAPlacer,ValeurLettre[] listeValeurLettre,int[] positionsLigne,int positionsColonne) {
		//WIP
		int multiplicateurMot=1;
		int scoreMot=0;
		int[] lettreCompleteCompteColonne= new int[15];
		int[] lettreCompleteCompteLigne= new int[15];
		
		for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
			int multiplicateurLettre=1;
			scoreMot=scoreMot+ScrabbleApplicationConsole.ajoutMotCompleteColonne(j,plateau,positionsLigne[cpt],positionsColonne,positionsLigne,lettreCompleteCompteLigne,lettreCompleteCompteColonne);
			multiplicateurMot=multiplicateurMot*plateau.typeCasePosition(positionsLigne[cpt],positionsColonne).multiplicateurCaseMot();
			multiplicateurLettre=multiplicateurLettre*plateau.typeCasePosition(positionsLigne[cpt], positionsColonne).multiplicateurCaseLettre();
			scoreMot=scoreMot+listeValeurLettre[cpt].getPoint()*multiplicateurLettre;
		}
		if (nombreLettreAPlacer==7){
			scoreMot=scoreMot+50;
		}
		j.setScore(j.getScore()+(scoreMot*multiplicateurMot));
	}
	
	   private static String demandeJoueurAffichageString(String demande, String nomFenetre, String contenu) {
	        Dialog<String> dialog = new Dialog<>();
	        dialog.setTitle(nomFenetre);
	        dialog.setHeaderText(demande);

	        ChoiceBox<String> cBoxAffichageJoker =new ChoiceBox<String>();
	        ChoiceBox<String> choiceBox = new ChoiceBox<>();
	        int cpt = 0;
	        String[] listeVL = new String[30];
	        String temp ;
	        for (ValeurLettre valeur : ValeurLettre.values()) {
	        	temp=valeur.AffichageLettre();
	            if (!" ".equals(temp)) {
	            	choiceBox.getItems().add(temp);
	            }
	            cpt++;
	        }

	        if (!choiceBox.getItems().isEmpty()) {
	            choiceBox.setValue(choiceBox.getItems().get(0));
	        }

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(new Label(contenu), choiceBox);
	        dialog.getDialogPane().setContent(vbox);

	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        dialog.setResultConverter(dialogButton -> {
	            if (dialogButton == ButtonType.OK) {
	                return choiceBox.getValue();
	            }
	            return null;
	        });

	        Optional<String> result = dialog.showAndWait();
	        if (result.isPresent()) {
	            return result.get();
	        } else {
	            return "Erreur";
	        }
	    }
	private static void choixJoker(Joueur j, ValeurLettre[] listeValeurLettre, int cpt) {
		String affichageJoker=demandeJoueurAffichageString("donnezlettre joker","donnezlettre joker","donnezlettre joker");
		j.donnerLettre(cpt).modifierAffichageJoker(affichageJoker);
	}

}
