package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
	private int tour;
	private List<String> motsValides;
	private List<Lettre> sacDeLettre;
	private ArrayList<Case> plateau;
	private List<Joueur> listeJoueur;
	
	static Case [][] plateauDeJeu;
	public Jeu(){
		this.plateauDeJeu = new Case[15][15];
		plateauDeJeu[7][7]=new Case(7,7,"DEPART",null);
	}
	
	//plateau = new ArrayList<Case> (225);
	//for (int i = 0; i < 225; i++) {
	//	plateau.add(new Case());
	//}
	//plateau.set(112,DEPART);

	public Jeu(int tour, List<String> motsValides, List<Lettre> sacDeLettre, ArrayList<Case> plateau, List<Joueur> listeJoueur) {
		this.tour=tour;
		this.motsValides=motsValides;
		this.sacDeLettre=sacDeLettre;
		this.plateau=plateau;
		this.listeJoueur=listeJoueur;
	}
	
	public Boolean verifierMot() {
		return null;
		//TODO
	}
	
	public int compterPoint() {
		return tour;
		//TODO
	}
	
	public Lettre distribuerLettre() {
		return null;
		//TODO
	}
	
	public String joueurQuiJoue() {
		return null;
		//TODO
	}
	
	public static void afficherPlateau() {
		for (int compteur = 0; compteur<15; compteur++) {
			System.out.print("|");
			for (int compteur2 = 0; compteur2<15; compteur2++) {
				if (plateauDeJeu[compteur][compteur2]!=null){
					System.out.print(plateauDeJeu[compteur][compteur2].getContenu()+" "+ "|"+" ");
				}
				else {
				//System.out.print(plateauDeJeu[compteur][compteur2]+" "+"|");
				System.out.print("  "+ "|"+ " ");
				}
			}
			System.out.println("");
			System.out.println("----------------------------------------------------------");
			
			
		}
	}
	
	public String finPartie() {
		return null;
		//TODO
	}
	
}
