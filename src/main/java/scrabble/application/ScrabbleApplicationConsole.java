package scrabble.application;

import java.util.Scanner;

import scrabble.console.Console;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.Lettre;
import scrabble.model.ValeurLettre;

public class ScrabbleApplicationConsole {
	Console console;



	public static void main(String[] args) {
		
		System.out.println("---------------------------------------------------------");
		System.out.println("--  Bienvenue dans notre magnifique jeu de scrabble !  --");
		System.out.println("--  developpé par Noa                                  --");
		System.out.println("--  et par Hippolyte                                   --");
		System.out.println("--  ainsi que par Romain                               --");
		System.out.println("---------------------------------------------------------");
		System.out.println();

		

		Jeu plateau = new Jeu();
		Joueur j1 = new Joueur("test");
		Scanner testInput = new Scanner(System.in);
		
		plateau.afficherPlateau();
		plateau.ajouteTypeCase();
		plateau.afficherPlateau();

		System.out.println("le plateau est vide :"+plateau.sacDeLettreEstVide());
		plateau.afficherSacDeLettre();
		plateau.remplirSacDeLettre();
		System.out.println("le plateau est vide :"+plateau.sacDeLettreEstVide());
		plateau.afficherSacDeLettre();
		System.out.println(j1.getNom());
		distribution(plateau, j1);
		plateau.afficherSacDeLettre();

		System.out.println("affichage du Chevalet :");
		j1.afficherChevalet();
		
		affichageMenu();
		int choix=testInput.nextShort();
		System.out.println(choix);
		switch(choix) {
		case 1:
			echanger(plateau,j1);
			break;
			//TODO
		case 2:
			break;

			//TODO
		case 3:
			
			jouerMot(plateau,j1);
			break;

		case 4:

			break;
			//TODO
		}
		plateau.afficherSacDeLettre();
		plateau.afficherPlateau();
		j1.afficherChevalet();



		}
		
	public static void affichageMenu() {
		System.out.println("---------------------------------------------------------");
		System.out.println("--  1-Échanger                                         --");
		System.out.println("--  2-Passer son tour                                  --");
		System.out.println("--  3-Jouer Lettre                                     --");
		System.out.println("--  4-Quitter                                          --");
		System.out.println("---------------------------------------------------------");
	}
	public static void echanger(Jeu plateau,Joueur j) {
		
		//ajouter une vérification pour ne pas porcéder à l'échange si le sacDeLettre est vide
		
		ValeurLettre lettreTemp=null;
		int nombreTemporaire=-1;
		int[] positionsEchanger=new int[7];
		System.out.println("Combien de lettre voulez vous échanger : ");
		Scanner inputNombreEchange = new Scanner(System.in);
		int choix=inputNombreEchange.nextShort();
		int compteur=0;
		if (choix>0 && choix<8) {
			for (compteur=0;compteur<choix;compteur++) {
				System.out.println("Position de la lettre à échanger :");
				int choixPosition=inputNombreEchange.nextShort();
				positionsEchanger[compteur]=choixPosition;}			

		}
			for (compteur=0;compteur<choix;compteur++) {
				plateau.ajouterLettrePremierePositionDisponible(j.supprimerLettre(positionsEchanger[compteur]-1));
				
				distribuerUneLettre(plateau,j);
					
				}
			}
			
		
			
		



	
	public static void distribution(Jeu plateau,Joueur j) {
		for (int i=0;i<8;i++) {

			distribuerUneLettre(plateau,j);

		}
	}
	public static void distribuerUneLettre(Jeu plateau,Joueur j) {
		ValeurLettre lettreTemp=null;
		int nombreTemporaire=-1;
		while (lettreTemp==null){
		nombreTemporaire=plateau.choixNombreAleatoire();
		lettreTemp=plateau.distribuerLettre(nombreTemporaire);
		}
		if (j.ajouterLettre(lettreTemp)){
			plateau.supprimerLettre(nombreTemporaire);
			
			lettreTemp=null;
		}
		
		
	}
	
	public static void jouerLettre(int[] listeNb) {
		System.out.println("Donnez une lettre à jouer");
		Scanner inputNombreEchange = new Scanner(System.in);
		int choixPosition=inputNombreEchange.nextShort();
		int positionsLettre;
		positionsLettre=choixPosition;
		for (int cpt=0;cpt<listeNb.length;cpt++) {
			System.out.println(listeNb[cpt]);
			if (listeNb[cpt]==0) {
			
			
				if (listeNb[cpt]==positionsLettre) {
					break;
				}
				else {
					listeNb[cpt]=positionsLettre;

					break;
				}
			}
			
		}
		
	}
	public static void jouerMot(Jeu plateau,Joueur j) {
		int[] positionDeLaPremiereLettre=new int[2];
		int[] listeDeNombre= new int[7];  //à modifier
		int nombreLettreAPlacer=0;
		int choix;
		
		System.out.println("1:ajout en ligne");
		System.out.println("2:ajout en colonne");
		choix=demanderNombre();

		
		System.out.println("Donnez le nombre de lettre que vouz voulez placer : ");
		nombreLettreAPlacer=demanderNombre();
		
		for (int compteur=0;compteur<nombreLettreAPlacer;compteur++) {
			jouerLettre(listeDeNombre);
		}
		//for qui met les lettres dans l'ordre dans la liste
		
		//positionner le mot
		System.out.println("Donnez la ligne de la première lettre du mot :");
		positionDeLaPremiereLettre[0]=demanderNombre();
		System.out.println("Donnez la colonne de la première lettre du mot :");
		positionDeLaPremiereLettre[1]=demanderNombre();

		for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
			System.out.println();
			plateau.afficherPlateau();

			//System.out.println("Liste des positions :"+listeDeNombre[cpt]+j.donnerLettre(listeDeNombre[cpt]));

			if (choix == 1) {
					System.out.println("Lettre : "+j.donnerLettre(listeDeNombre[cpt]-1));
					plateau.placerLettreJoue(positionDeLaPremiereLettre[0], positionDeLaPremiereLettre[1]+cpt,j.donnerLettre(listeDeNombre[cpt]-1));
			}
			else {
				
					System.out.println("test"+choix);
					plateau.placerLettreJoue(positionDeLaPremiereLettre[0]+cpt, positionDeLaPremiereLettre[1], j.donnerLettre(listeDeNombre[cpt]-1));



		}
		}
		//supprimer le mot dans le jeu du joueur et appeler une fonction qui remplit le sac du joueur
		
		
	}
	
	public static int demanderNombre() {
		//donnez des instructions avant cette méthode
		Scanner inputNombre = new Scanner(System.in);
		int choixPosition=inputNombre.nextShort();
		int entier;
		entier=choixPosition;
		return choixPosition;
	}

	
}
