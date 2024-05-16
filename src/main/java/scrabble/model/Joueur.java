package scrabble.model;

import java.util.List;

import scrabble.console.Console;



public class Joueur {
	private String nom;
	private int score;
	static ValeurLettre [] chevalet;

	//private List<Lettre> chevalet;
	private int valeurEchange;
	
	
	Console console;
	
	public Joueur(String nom, int score, List<Lettre> chevalet) {
		this.nom=nom;
		this.score=score;
		this.chevalet = new ValeurLettre[7];
	}
	public Joueur(String nom) {
		this.nom=nom;
		this.score=0;
		this.chevalet = new ValeurLettre[7];
	}
	public void afficherChevalet() {
		System.out.println("1 2 3 4 5 6 7");
		System.out.println();
		for (int compteur=0;compteur<7;compteur++) {
			if (chevalet[compteur]!=null) {
				System.out.print(chevalet[compteur].AffichageLettre()+" ");
		}
		
		
	}
	System.out.println();
	System.out.println();

	for (int compteur1=0;compteur1<7;compteur1++) {
		if (chevalet[compteur1]!=null) {
		System.out.print(chevalet[compteur1].getPoint()+" ");
		//gérer l'espace si la valeur des points est égal à 10 (affichage décalé sinon)
		}
	}
	System.out.println();
	System.out.println();


	}
	
	public void echangerLettre(int nombreAEchanger) {
		//valeurEchange=keyboard.nextLine()
		//TODO

		
	}
	public boolean sacDeLettreEstVide() {
		boolean booleenEstVide = true;
		for (int cpt = 0; cpt<7; cpt++) {
			if (!(chevalet[cpt]== null)) {
				booleenEstVide=false;
				break;		
			}
		}
        return booleenEstVide;
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
	public boolean ajouterLettre(ValeurLettre lettreTemp) {
		int position=0;
		boolean booleenAjoutLettre=false;
		while (position<7){
			if (placeVideChevalet(position)) {
				chevalet[position]=lettreTemp;
				booleenAjoutLettre=true;
				break;
			}
			position++;
		}
		return booleenAjoutLettre;

	}
	public ValeurLettre supprimerLettre(int position) {
		ValeurLettre tempValeurLettre = chevalet[position];
		chevalet[position]=null;
		return tempValeurLettre;
	}
	public ValeurLettre donnerLettre(int position) {
		
		ValeurLettre lettre=chevalet[position];

		return lettre;
	}
	public boolean placeVideChevalet(int position){
		boolean booleenEstVide = true;
			if (!(chevalet[position]== null)) {
				booleenEstVide=false;			}
		
        return booleenEstVide;
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

	public int getValeurEchange() {
		return valeurEchange;
	}

	public void setValeurEchange(int valeurEchange) {
		this.valeurEchange = valeurEchange;
	}
	
}
