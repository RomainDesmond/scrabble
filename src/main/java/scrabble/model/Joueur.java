package scrabble.model;

import java.util.List;



public class Joueur {
	private String nom;
	private int score;
	private List<Lettre> chevalet;
	
	public Joueur(String nom, int score, List<Lettre> chevalet) {
		this.nom=nom;
		this.score=score;
		this.chevalet=chevalet;
	}
	
	public void echangerLettre(int nombreAEchanger) {
		
	}
}
