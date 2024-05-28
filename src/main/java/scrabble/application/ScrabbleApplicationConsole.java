package scrabble.application;

import java.util.Scanner;

import scrabble.console.Console;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.Lettre;
import scrabble.model.TypeCase;
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
		Boolean joue=true;
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
		
		while (joue) {
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
			distribution(plateau,j1);
			plateau.modificationCasePlacable();
			break;

		case 4:
			joue=false;
			break;
			//TODO
		}
		//plateau.afficherSacDeLettre();
		plateau.afficherPlateau();
		j1.afficherChevalet();
		System.out.println("Score:"+j1.getScore());
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
		for (int i=0;i<7;i++) {
			if (j.donnerLettre(i)==null){
				distribuerUneLettre(plateau,j);
			}
			

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
	
	public static int jouerLettre(int[] listeNb,int nbLettreJoue) {
		System.out.println("Donnez une lettre à jouer");
		Scanner inputNombreEchange = new Scanner(System.in);
		int choixPosition=inputNombreEchange.nextShort();
		int positionsLettre;
		positionsLettre=choixPosition;
		if (positionsLettre>0 && positionsLettre<8){
			for (int cpt=0;cpt<listeNb.length;cpt++) {
				System.out.println(listeNb[cpt]==(positionsLettre));
				if (listeNb[cpt]==positionsLettre) {
					System.out.println("lettre invalide");
					
					break;
				}
				else {
				if (listeNb[cpt]==0) {
					

					//else {
					System.out.println("test28");
					listeNb[cpt]=positionsLettre;
					nbLettreJoue++;
					break;
					}
				}
				
			}
		}
		return nbLettreJoue;
	}
	public static void jouerMot(Jeu plateau,Joueur j) {
		int[] positionLigneColonneMot=new int[2];
		int[] listeDeNombre= new int[7];  //à modifier
		int[] listePosition= new int[7];
		int nombreLettreAPlacer=0;
		int choix;
		int nbLettreJoue=0;
		String choixJoker;
		
		
		System.out.println("1:ajout en ligne");
		System.out.println("2:ajout en colonne");
		choix=demanderNombre("");

		
		nombreLettreAPlacer=demanderNombre("Donnez le nombre de lettre que vouz voulez placer : ");
		
		for (int compteur=0;compteur<nombreLettreAPlacer;compteur++) {
			nbLettreJoue=jouerLettre(listeDeNombre,nbLettreJoue);
			
		}
		if(nombreLettreAPlacer==nbLettreJoue) {

	
				if (choix == 1) {
					positionLigneColonneMot[0]=demanderNombre("Donnez la ligne du mot :");
					
					for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {


						if (j.donnerLettre(listeDeNombre[cpt]-1)==ValeurLettre.JOKER) {
							choixJoker(j,listeDeNombre,cpt);
						}
						

						positionLigneColonneMot[1]=demanderNombre("Donnez la colonne de la lettre à placer :");
						plateau.placerLettreJoue(positionLigneColonneMot[0], positionLigneColonneMot[1],j.donnerLettre(listeDeNombre[cpt]-1));
						listePosition[cpt]=positionLigneColonneMot[1];
					}
					if (plateau.motEstPlacableLigne(positionLigneColonneMot[0], listePosition,nombreLettreAPlacer)) {
						System.out.println("Oui");
						compterLesPointsLigne(j,plateau,nombreLettreAPlacer,listeDeNombre,positionLigneColonneMot[0],listePosition);
							//TODO
					}
					else {
						System.out.println("Non");
						for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						plateau.placerLettreJoue(positionLigneColonneMot[0], listePosition[cpt],null);
						}
					}
						//for (int compteurVerification=0;compteurVerification<nombreLettreAPlacer;compteurVerification++) {
							
						//}
					
				}
				else {
					
					positionLigneColonneMot[1]=demanderNombre("Donnez la colonne du mot :");
					
					for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {


						if (j.donnerLettre(listeDeNombre[cpt]-1)==ValeurLettre.JOKER) {
							choixJoker(j,listeDeNombre,cpt);
						}
						

						positionLigneColonneMot[0]=demanderNombre("Donnez la ligne de la lettre à placer :");
						plateau.placerLettreJoue(positionLigneColonneMot[0], positionLigneColonneMot[1],j.donnerLettre(listeDeNombre[cpt]-1));
						listePosition[cpt]=positionLigneColonneMot[0];
					}
					if (plateau.motEstPlacableColonne( listePosition,positionLigneColonneMot[1],nombreLettreAPlacer)) {
						compterLesPointsColonne(j,plateau,nombreLettreAPlacer,listeDeNombre,listePosition,positionLigneColonneMot[1]);
							//TODO
					}
					else {
						for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						plateau.placerLettreJoue( listePosition[cpt],positionLigneColonneMot[1],null);
						}
					}	
				}
		}
		else {
			System.out.println("Erreur, vous avez joué plusieurs fois la même lettre");
		}
		
	}
	
	public static String demanderLettre(String instruction) {
		System.out.println(instruction);
		Scanner inputChaîneCaractere = new Scanner(System.in);
		String choixPosition=inputChaîneCaractere.nextLine();
		String lettre;
		lettre=choixPosition;
		return choixPosition;
	}
	
	public static int demanderNombre(String instruction) {
		System.out.println(instruction);
		Scanner inputNombre = new Scanner(System.in);
		int choixPosition=inputNombre.nextShort();
		int entier;
		entier=choixPosition;
		return choixPosition;
	}

	public static void choixJoker(Joueur j,int listeDeNombre[],int cpt) {
		j.donnerLettre(listeDeNombre[cpt]-1).modifierAffichageJoker(demanderLettre("Donnez le nouvel affichage du joker"));
	}
	
	public static void compterLesPointsLigne(Joueur j,Jeu plateau,int nombreLettreAPlacer,int positionsLettreChevalet[],int positionsLigne,int[] positionsColonne) {
		//WIP
		int multiplicateurMot=1;
		int scoreMot=0;
		
		for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
			int multiplicateurLettre=1;
			multiplicateurMot=multiplicateurMot*plateau.typeCasePosition(positionsLigne,positionsColonne[cpt]).multiplicateurCaseMot();
			multiplicateurLettre=multiplicateurLettre*plateau.typeCasePosition(positionsLigne, positionsColonne[cpt]).multiplicateurCaseLettre();
			
			scoreMot=scoreMot+j.donnerLettre(positionsLettreChevalet[cpt]-1).getPoint()*multiplicateurLettre;
			
			j.supprimerLettre(cpt);
		}
		j.setScore(j.getScore()+(scoreMot*multiplicateurMot));
	}
	
	public static void compterLesPointsColonne(Joueur j,Jeu plateau,int nombreLettreAPlacer,int positionsLettreChevalet[],int[] positionsLigne,int positionsColonne) {
		//WIP
		int multiplicateurMot=1;
		int scoreMot=0;
		
		for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
			int multiplicateurLettre=1;
			multiplicateurMot=multiplicateurMot*plateau.typeCasePosition(positionsLigne[cpt],positionsColonne).multiplicateurCaseMot();
			multiplicateurLettre=multiplicateurLettre*plateau.typeCasePosition(positionsLigne[cpt], positionsColonne).multiplicateurCaseLettre();
			j.afficherChevalet();
			scoreMot=scoreMot+j.donnerLettre(positionsLettreChevalet[cpt]-1).getPoint()*multiplicateurLettre;
			
			j.supprimerLettre(cpt);
		}
		j.setScore(j.getScore()+(scoreMot*multiplicateurMot));
	}
	
}
