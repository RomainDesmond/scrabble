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
		valeurEchange=keyboard.nextLine()
	}
}
