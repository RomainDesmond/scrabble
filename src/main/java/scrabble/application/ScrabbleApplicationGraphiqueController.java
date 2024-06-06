package scrabble.application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import scrabble.model.Jeu;
import scrabble.model.ValeurLettre;

public class ScrabbleApplicationGraphiqueController {

	@FXML
	private GridPane idGrilleScrabble;
	
	@FXML void initialize() {
		Jeu plateau = new Jeu();
		plateau.ajouteTypeCase();
		
		for(int cpt1=0;cpt1<15;cpt1++) {
			for(int cpt2=0;cpt2<15;cpt2++) {
                Rectangle tile = new Rectangle(50, 50);
                Text text = new Text(plateau.casePosition(cpt1,cpt2).affichageCase());

                
                
                text.setFont(Font.font(20));
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);
                
				idGrilleScrabble.add(new StackPane(tile, text),cpt1,cpt2);
			}
		}
		
		plateau.afficherPlateau();
		actualiserAffichage(plateau);
		System.out.println("test0");
	}


	
	public void actualiserAffichage(Jeu plateau) {
		for(int cpt1=0;cpt1<15;cpt1++) {
			for(int cpt2=0;cpt2<15;cpt2++) {
                Rectangle tile = new Rectangle(50, 50);
                Text text = new Text(plateau.casePosition(cpt1,cpt2).affichageCase());
                
                text.setFont(Font.font(20));
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);
                
				idGrilleScrabble.add(new StackPane(tile, text),cpt1,cpt2);
			}
		}
	}
}
