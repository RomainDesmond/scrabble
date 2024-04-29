package scrabble.application;

import scrabble.model.Jeu;

public class ScrabbleApplicationConsole {

	public static void main(String[] args) {
		System.out.println("---------------------------------------------------------");
		System.out.println("--  Bienvenue dans notre magnifique jeu de scrabble !  --");
		System.out.println("--  developpé par Noa                                  --");
		System.out.println("--  et par Hippolyte                                   --");
		System.out.println("--  ainsi que par Romain                               --");
		System.out.println("---------------------------------------------------------");
		
		Jeu plateau = new Jeu();
		plateau.afficherPlateau();

	}

}
