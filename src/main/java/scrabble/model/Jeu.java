package scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import scrabble.console.Console;

public class Jeu {
	
	Console console;

	private int longueur=102;
	private int tailleXY=15;
	private int caseMilieuXY=7;
	private int tour;
	private List<String> motsValides;
	private ArrayList<Case> plateau;
	private List<Joueur> listeJoueur;
	


	private static Random rand = new Random();


	static ValeurLettre[] sacDeLettre;
	static Case [][] plateauDeJeu;
	
	public Jeu(){
		this.sacDeLettre = new ValeurLettre[longueur];
		this.plateauDeJeu = new Case[tailleXY][tailleXY];
		plateauDeJeu[caseMilieuXY][caseMilieuXY]=new Case(caseMilieuXY,caseMilieuXY,TypeCase.DEPART,null);
	}
	
	public void remplirSacDeLettre() {
		int longueur=0;
		//WIP
		if (sacDeLettreEstVide()) {
			for (ValeurLettre valeur : ValeurLettre.values()) {
				longueur=ajouterLettre(longueur,valeur,valeur.getRecurrence());
			}
			
		}
	}
	public int ajouterLettre(int longueur,ValeurLettre valeur,int nombre) {
		int temp=longueur+nombre;
		int ajout=0;
		for (int cpt=0;cpt<nombre; cpt++){
			ajout=longueur+cpt;
			sacDeLettre[ajout]=valeur;
		}
		return (longueur+nombre);
	}
	public void ajouterLettrePremierePositionDisponible(ValeurLettre lettre) {
		for (int i=0;i<longueur;i++) {
			if (sacDeLettre[i]==null){
			sacDeLettre[i]=lettre;
			break;
			}

		}
	}
	public void supprimerLettre(int position) {
		sacDeLettre[position]=null;
		//for (int i=position+1; i<103;i++) {
		//	if (sacDeLettre[i-1]==null) {
		//		sacDeLettre[i-1]=sacDeLettre[i];
		//	}
		//}
	}
	
	public Boolean verifierMot() {
		return null;
		//TODO
	}
	
	public int compterPoint() {
		return tour;
		//TODO
	}
	
	public ValeurLettre distribuerLettre(int position) {
		
		ValeurLettre lettre=sacDeLettre[position];

		return lettre;
	}
	public int choixNombreAleatoire() {
		int nombreAleatoire = rand.nextInt(longueur);
		return nombreAleatoire;
	}
	public String joueurQuiJoue() {
		return null;
		//TODO
	}
	
	public void afficherPlateau() {
		System.out.println("------------------------------------------------------------");
		for (int compteur = 0; compteur<15; compteur++) {
			System.out.print("|");
			for (int compteur2 = 0; compteur2<15; compteur2++) {
				if ((plateauDeJeu[compteur][compteur2]!=null)){
					System.out.print(plateauDeJeu[compteur][compteur2].affichageTypeCase()+" "+ "|"+" ");
				}
				//TODO affichage des lettres qaund nécessaire et non du type de case

				else {
				//System.out.print(plateauDeJeu[compteur][compteur2]+" "+"|");
				System.out.print("  "+ "|"+ " ");
				}
			}
			System.out.println("");
			System.out.println("------------------------------------------------------------");
			
			
		}
	}
	public boolean sacDeLettreEstVide() {
		boolean booleenEstVide = true;
		for (int cpt = 0; cpt<longueur; cpt++) {
			if (!(sacDeLettre[cpt]== null)) {
				booleenEstVide=false;
				break;		
			}
		}
        return booleenEstVide;
	}
	public void afficherSacDeLettre() {
		for (int compteur=0;compteur<longueur;compteur++) {
			if (sacDeLettre[compteur]!=null) {
				System.out.print(sacDeLettre[compteur].AffichageLettre()+" ");
			}
		}
		System.out.println();

	}
	public String finPartie() {
		return null;
		//TODO
	}
	
}
