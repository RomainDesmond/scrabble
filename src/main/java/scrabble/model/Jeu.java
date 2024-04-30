package scrabble.model;

import java.util.ArrayList;
import java.util.List;

import scrabble.console.Console;

public class Jeu {
	
	Console console;

	
	private int tour;
	private List<String> motsValides;
	private ArrayList<Case> plateau;
	private List<Joueur> listeJoueur;
	
	private Lettre e=new Lettre(ValeurLettre.E,1);
	private Lettre a=new Lettre(ValeurLettre.A,1);
	private Lettre i=new Lettre(ValeurLettre.I,1);
	private Lettre n=new Lettre(ValeurLettre.N,1);
	private Lettre o=new Lettre(ValeurLettre.O,1);
	private Lettre r=new Lettre(ValeurLettre.R,1);
	private Lettre s=new Lettre(ValeurLettre.S,1);
	private Lettre t=new Lettre(ValeurLettre.T,1);
	private Lettre u=new Lettre(ValeurLettre.U,1);
	private Lettre l=new Lettre(ValeurLettre.L,1);
	private Lettre d=new Lettre(ValeurLettre.D,2);
	private Lettre g=new Lettre(ValeurLettre.G,2);
	private Lettre m=new Lettre(ValeurLettre.M,2);
	private Lettre b=new Lettre(ValeurLettre.B,3);
	private Lettre c=new Lettre(ValeurLettre.C,3);
	private Lettre p=new Lettre(ValeurLettre.P,3);
	private Lettre f=new Lettre(ValeurLettre.F,4);
	private Lettre h=new Lettre(ValeurLettre.H,4);
	private Lettre v=new Lettre(ValeurLettre.V,4);
	private Lettre j=new Lettre(ValeurLettre.J,8);
	private Lettre q=new Lettre(ValeurLettre.Q,8);
	private Lettre k=new Lettre(ValeurLettre.K,10);
	private Lettre w=new Lettre(ValeurLettre.W,10);
	private Lettre x=new Lettre(ValeurLettre.X,10);
	private Lettre y=new Lettre(ValeurLettre.Y,10);
	private Lettre z=new Lettre(ValeurLettre.Z,10);
	private Lettre joker=new Lettre(ValeurLettre.JOKER,0);



	static Lettre [] sacDeLettre;
	static Case [][] plateauDeJeu;
	
	public Jeu(){
		this.sacDeLettre = new Lettre[101];
		this.plateauDeJeu = new Case[15][15];
		plateauDeJeu[7][7]=new Case(7,7,"DEPART",null);
	}
	
	public void remplirSacDeLettre() {
		//TODO
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
	public boolean estVide() {
		boolean booleenEstVide = true;
		for (int cpt = 0; cpt<101; cpt++) {
			if (!(sacDeLettre[cpt]== null)) {
				booleenEstVide=false;
				break;		
			}
		}
        return booleenEstVide;
	}
	
	public String finPartie() {
		return null;
		//TODO
	}
	
}
