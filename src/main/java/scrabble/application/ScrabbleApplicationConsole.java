package scrabble.application;

import java.util.Scanner;

import scrabble.console.Console;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.Lettre;

public class ScrabbleApplicationConsole {
	Console console;



	public static void main(String[] args) {
		
		System.out.println("---------------------------------------------------------");
		System.out.println("--  Bienvenue dans notre magnifique jeu de scrabble !  --");
		System.out.println("--  developpé par Noa                                  --");
		System.out.println("--  et par Hippolyte                                   --");
		System.out.println("--  ainsi que par Romain                               --");
		System.out.println("---------------------------------------------------------");
		
		Jeu plateau = new Jeu();
		Joueur j1 = new Joueur("test");
		Scanner testInput = new Scanner(System.in);
		
		plateau.afficherPlateau();
		System.out.println("le plateau est vide :"+plateau.sacDeLettreEstVide());
		plateau.remplirSacDeLettre();
		System.out.println("le plateau est vide :"+plateau.sacDeLettreEstVide());
		System.out.println(j1.getNom());
		distribution(plateau, j1);
		
		System.out.println("affichage du Chevalet :");
		j1.afficherChevalet();
		
		affichageMenu();
		int choix=testInput.nextShort();
		System.out.println(choix);
		switch(choix) {
		case 1:
			echanger();
			//TODO
		case 2:
			//TODO
		case 3:
			//TODO
		case 4:
			//TODO
		}

		}
		
	public static void affichageMenu() {
		System.out.println("---------------------------------------------------------");
		System.out.println("--  1-Échanger                                         --");
		System.out.println("--  2-Passer son tour                                  --");
		System.out.println("--  3-Jouer Lettre                                     --");
		System.out.println("--  4-Quitter                                          --");
		System.out.println("---------------------------------------------------------");
	}
	public static void echanger() {
		//TODO
	}
	public static void distribution(Jeu plateau,Joueur j) {
		for (int i=0;i<8;i++) {
			Lettre lettreTemp=null;
			int nombreTemporaire=-1;
			while (lettreTemp==null){
			nombreTemporaire=plateau.choixNombreAleatoire();
			lettreTemp=plateau.distribuerLettre(nombreTemporaire);
			}
			if (j.ajouterLettre(lettreTemp)){
				plateau.supprimerLettre(nombreTemporaire);
			}
			lettreTemp=null;

		}
	}

	
}
