package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
	private int tour;
	private List<String> motsValides;
	private List<Lettre> sacDeLettre;
	private ArrayList<Case> plateau;
	private List<Joueur> listeJoueur;
	
	plateau = new ArrayList<Case> (225);
	for (int i = 0; i < 225; i++) {
		plateau.add(ValeurLettre.VIDE);
	}
	plateau.set(112,DEPART);

	public Jeu(int tour, List<String> motsValides, List<Lettre> sacDeLettre, ArrayList<Case> plateau, List<Joueur> listeJoueur) {
		this.tour=tour;
		this.motsValides=motsValides;
		this.sacDeLettre=sacDeLettre;
		this.plateau=plateau;
		this.listeJoueur=listeJoueur;
	}
	
	public Boolean verifierMot() {
		//a compléter
	}
	
	public int compterPoint() {
		//a compléter
	}
	
	public Lettre distribuerLettre() {
		//a compléter
	}
	
	public String joueurQuiJoue() {
		//a compléter
	}
	
	public List<List<Case>> afficherPlateau() {
		//a compléter
	}
	
	public String finPartie() {
		//a compléter
	}
	
}
