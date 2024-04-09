package scrabble.model;

import java.util.List;

import scrabble.console.Console;



public class Joueur {
	private String nom;
	private int score;
	private List<Lettre> chevalet;
	private int valeurEchange;
	
	
	Console console;
	
	public Joueur(String nom, int score, List<Lettre> chevalet) {
		this.nom=nom;
		this.score=score;
		this.chevalet=chevalet;
	}
	
	public void echangerLettre(int nombreAEchanger) {
		//valeurEchange=keyboard.nextLine()
		
	}

	public void jouerLettre() {
		//TODO
	}
	public void proposerUnNouveauMot() {
		//TODO
	}

	public void passerSontour() {
		//TODO
	}

	public void quitterLaPartie() {
		//TODO
	}

	public void entrerNom() {
		//TODO
	}
	public void placeVideChevalet(){
		//TODO
	}
	public void afficherJoueurEtScore(){
		//TODO
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Lettre> getChevalet() {
		return chevalet;
	}

	public void setChevalet(List<Lettre> chevalet) {
		this.chevalet = chevalet;
	}

	public int getValeurEchange() {
		return valeurEchange;
	}

	public void setValeurEchange(int valeurEchange) {
		this.valeurEchange = valeurEchange;
	}
	
}
