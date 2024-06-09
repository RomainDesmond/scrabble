package scrabble.application;

import java.util.Scanner;

import scrabble.console.Console;
import scrabble.model.Jeu;
import scrabble.model.Joueur;
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
		//plateau.afficherPlateau();
		plateau.ajouteTypeCase();
		plateau.afficherPlateau();

		//System.out.println("le plateau est vide :"+plateau.sacDeLettreEstVide());
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
			if (!(j1.sacDeLettreEstVide())) {
				affichageMenu();
				int choix=testInput.nextShort();
				switch(choix) {
				case 1:
					echanger(plateau,j1);
					break;
				case 2:
					joue=false;
					break;
		
					//TODO
				case 3:
					int[] positionLigneColonneMot=new int[2];
					int[] listeDeNombre= new int[7];  
					int[] listePosition= new int[7];
					int nombreLettreAPlacer;
					int choixLigneColonne;
		
					//demanderMot(Jeu plateau,Joueur jeu,int[] listeDeNombre,int nombreLettreAPlacer,int[] positionLigneColonneMot,int[] listePosition,int choix) {
					//demanderMot(plateau,j1,); demanderNBLettre
					choixLigneColonne=choixLigneColonne();
		
					nombreLettreAPlacer=demanderNBLettre();
					
		
					demanderMot(plateau,j1,listeDeNombre,nombreLettreAPlacer,positionLigneColonneMot,listePosition,choixLigneColonne);
					
					//jouerMot(Jeu plateau,Joueur j,int[] positionLigneColonneMot,int[] listeDeNombre,int[] listePosition,int nombreLettreAPlacer,int choix) {
		
					jouerMot(plateau,j1,positionLigneColonneMot,listeDeNombre,listePosition,nombreLettreAPlacer,choixLigneColonne);
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
	
	public static int jouerLettre(Jeu plateau,int[] listeNb,int nbLettreJoue) {
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
						listeNb[cpt]=positionsLettre;
						nbLettreJoue++;
						break;
					}
				}
				
			}
		}
		return nbLettreJoue;
	}
	
	public boolean demanderPosition() {
		return false;
	}
	
	public static int demanderNBLettre() {
		return demanderNombre("Donnez le nombre de lettre que vouz voulez placer : ");
	}
	
	public static int choixLigneColonne() {
		System.out.println("1:ajout en ligne");
		System.out.println("2:ajout en colonne");
		return demanderNombre("");
	}

	public static void demanderMot(Jeu plateau,Joueur jeu,int[] listeDeNombre,int nombreLettreAPlacer,int[] positionLigneColonneMot,int[] listePosition,int choix) {
		int nbLettreJoue=0;
		
		for (int compteur=0;compteur<nombreLettreAPlacer;compteur++) {
			nbLettreJoue=jouerLettre(plateau,listeDeNombre,nbLettreJoue);
		}
		if(nombreLettreAPlacer==nbLettreJoue) {
				if (choix == 1) {
					positionLigneColonneMot[0]=demanderNombre("Donnez la ligne du mot :");
					while ((positionLigneColonneMot[0]<0) || (positionLigneColonneMot[0]>14)){
						positionLigneColonneMot[0]=demanderNombre("Donnez la ligne du mot :");
					}
					for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						positionLigneColonneMot[1]=demanderNombre("Donnez la colonne de la lettre à placer :");
						while((positionLigneColonneMot[1]<0)||(positionLigneColonneMot[1]>14)) {
							positionLigneColonneMot[1]=demanderNombre("Erreur vos valeurs sont hors du tableau :");
						}
						listePosition[cpt]=positionLigneColonneMot[1];
					}
				}
				else if (choix == 2) {
					positionLigneColonneMot[1]=demanderNombre("Donnez la colonne du mot :");
					while ((positionLigneColonneMot[1]<0) || (positionLigneColonneMot[1]>14)){
						positionLigneColonneMot[1]=demanderNombre("Donnez la colonne du mot :");
					}
					for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
						positionLigneColonneMot[0]=demanderNombre("Donnez la ligne de la lettre à placer :");
						while((positionLigneColonneMot[0]<0)||(positionLigneColonneMot[1]>14)) {
							positionLigneColonneMot[0]=demanderNombre("Erreur vos valeurs sont hors du tableau :");
						}
						listePosition[cpt]=positionLigneColonneMot[0];
					}
				}
		}
		//return true;
	}
	
	
	public static boolean jouerMot(Jeu plateau,Joueur j,int[] positionLigneColonneMot,int[] listeDeNombre,int[] listePosition,int nombreLettreAPlacer,int choix) {
		boolean motEstJoue=true;
		if (choix == 1) {
			for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				if (j.donnerLettre(listeDeNombre[cpt]-1)==ValeurLettre.JOKER) {
					choixJoker(j,listeDeNombre,cpt);
				}
				if(!(plateau.placerLettreJoue(positionLigneColonneMot[0], listePosition[cpt],j.donnerLettre(listeDeNombre[cpt]-1)))) {
					motEstJoue=false;
					break;
				}
			}
			plateau.afficherPlateau();
			if ((plateau.motEstPlacableLigne(positionLigneColonneMot[0], listePosition,nombreLettreAPlacer))&&(motEstJoue)) {
				compterLesPointsLigne(j,plateau,nombreLettreAPlacer,listeDeNombre,positionLigneColonneMot[0],listePosition);
			}					
			else if (motEstJoue){
				motEstJoue=false;
				for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
					plateau.supprimerLettreEmplacement(positionLigneColonneMot[0], listePosition[cpt]);
					
				}
			}
		}
		else if(choix == 2) {
			for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				if (j.donnerLettre(listeDeNombre[cpt]-1)==ValeurLettre.JOKER) {
					choixJoker(j,listeDeNombre,cpt);
				}
				if (!(plateau.placerLettreJoue(listePosition[cpt], positionLigneColonneMot[1],j.donnerLettre(listeDeNombre[cpt]-1)))) {
					motEstJoue=false;
					break;
				} 
				}

			if ((plateau.motEstPlacableColonne(listePosition,positionLigneColonneMot[1],nombreLettreAPlacer))&&(motEstJoue)) {
				compterLesPointsColonne(j,plateau,nombreLettreAPlacer,listeDeNombre,listePosition,positionLigneColonneMot[1]);
			}					
			else if (motEstJoue){
				motEstJoue=false;
				for(int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
					plateau.supprimerLettreEmplacement(positionLigneColonneMot[0], listePosition[cpt]);

					//plateau.placerLettreJoue(listePosition[cpt],positionLigneColonneMot[1],null);
				}
			}
		}
		if (motEstJoue) {
			plateau.ajouterUnTour();
		}
		return motEstJoue;
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
		int[] lettreCompleteCompteColonne= new int[15];
		int[] lettreCompleteCompteLigne= new int[15];


		for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {

			int multiplicateurLettre=1;
			scoreMot=scoreMot+ajoutMotCompleteLigne(j,plateau,positionsLigne,positionsColonne[cpt],positionsColonne,lettreCompleteCompteLigne,lettreCompleteCompteColonne);
			multiplicateurMot=multiplicateurMot*plateau.typeCasePosition(positionsLigne,positionsColonne[cpt]).multiplicateurCaseMot();
			multiplicateurLettre=multiplicateurLettre*plateau.typeCasePosition(positionsLigne, positionsColonne[cpt]).multiplicateurCaseLettre();
			j.afficherChevalet();
			scoreMot=scoreMot+j.donnerLettre(positionsLettreChevalet[cpt]-1).getPoint()*multiplicateurLettre;

			j.supprimerLettre(positionsLettreChevalet[cpt]-1);

		}
		if (nombreLettreAPlacer==7){
			scoreMot=scoreMot+50;
		}
		j.setScore(j.getScore()+(scoreMot*multiplicateurMot));
	}
	public static int ajoutMotCompleteLigne(Joueur j,Jeu plateau,int positionsLigneDuMot,int positionColonneDuMot,int[]positionColonneAutreMot,int[]lettreCompleteCompteLigne,int[]lettreCompleteCompteColonne) {
		int cpt=1;
		int point=0;
		//positionsLigneDuMot--;
		//positionColonneDuMot--;
		int[] positionsLettreDuMotComplete;
		boolean ajoutLigne=false;
		boolean ajoutColonne=false;
		System.out.println("ligne plus:"+(positionsLigneDuMot+cpt)+" "+positionColonneDuMot);
		System.out.println("puntos1"+point);
		if (positionsLigneDuMot>0) {
			System.out.println("positions0:"+(positionsLigneDuMot-cpt)+(positionColonneDuMot-cpt));
			System.out.println(plateau.getLettre(7, 7));
			System.out.println(plateau.getLettre((positionsLigneDuMot-cpt), positionColonneDuMot));
			while((plateau.getLettre((positionsLigneDuMot-cpt-1), positionColonneDuMot-1)!=null)) {
				System.out.println("positions:"+positionsLigneDuMot+(positionColonneDuMot-cpt+1));
				if (estPasEgal(positionsLigneDuMot-cpt-1,lettreCompleteCompteLigne)){
					point=point+plateau.getLettre(positionsLigneDuMot-cpt-1,positionColonneDuMot-1).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteLigne,positionsLigneDuMot-cpt-1);
					ajoutLigne=true;
				}
				cpt++;
				if (positionsLigneDuMot-cpt<0) {
					break;
				}
			}
		}
		System.out.println("puntos2"+point);
		cpt=1;
		if (positionsLigneDuMot<14) {
			System.out.println("positions1:"+(positionsLigneDuMot+cpt-1)+(positionColonneDuMot-1));
			System.out.println(plateau.getLettre(7, 7));
			System.out.println(plateau.getLettre((positionsLigneDuMot+cpt-1), positionColonneDuMot-1));
			while (plateau.getLettre(positionsLigneDuMot+cpt-1, positionColonneDuMot-1)!=null) {
				System.out.println("positions:"+positionsLigneDuMot+(positionColonneDuMot-cpt+1));
				if (estPasEgal(positionsLigneDuMot+cpt-1,lettreCompleteCompteLigne)){
					point=point+plateau.getLettre(positionsLigneDuMot+cpt-1,positionColonneDuMot-1).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteLigne,positionsLigneDuMot+cpt-1);
					ajoutLigne=true;
				}
				cpt++;
				if (positionsLigneDuMot+cpt>14) {
					break;
				}
			}
		}
		cpt=1;
		System.out.println("puntos3"+point);
		if (positionColonneDuMot<14) {
			System.out.println("positions2:"+(positionsLigneDuMot-1)+(positionColonneDuMot+cpt));
			System.out.println("positions1:"+(positionsLigneDuMot+cpt-1)+(positionColonneDuMot-1));
			System.out.println(plateau.getLettre(7, 7));
			System.out.println(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot+cpt-1));
			while((plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot+cpt-1)!=null)) {
				System.out.println("positionhhhhs:"+(positionsLigneDuMot-1)+(positionColonneDuMot+cpt));
				//System.out.println(estPasEgal(positionColonneDuMot+cpt-1,lettreCompleteCompteColonne));
				//System.out.println(estPasEgal(positionColonneDuMot-1,positionColonneAutreMot));
				System.out.println("^^");
				if (estPasEgal(positionColonneDuMot+1,positionColonneAutreMot)&&(estPasEgal(positionColonneDuMot-1+cpt,lettreCompleteCompteColonne))) {
					System.out.println("test");
					point=point+plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot+cpt-1).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteColonne,positionColonneDuMot+cpt-1);
					ajoutColonne=true;
				}
				cpt++;
				if (positionColonneDuMot+cpt>14) {
					break;
				}
			}
		}
		cpt=2;
		System.out.println("puntos4"+point);
		if (positionColonneDuMot>0) {
			while(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt)!=null) {
				if((estPasEgal(positionColonneDuMot-1,positionColonneAutreMot))&&(estPasEgal(positionColonneDuMot-cpt-1,lettreCompleteCompteColonne))) {
					point=point+plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteColonne,positionColonneDuMot-cpt);
					ajoutColonne=true;
				}
				cpt++;
				if (positionColonneDuMot-cpt<0) {
					break;
				}
			}
		}
		
		/*
		if (positionColonneDuMot>0) {
			while(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt)!=null) {
				System.out.println("ici"+(positionsLigneDuMot-1)+"+"+(positionColonneDuMot-cpt));
				if((estPasEgal(positionColonneDuMot-cpt,lettreCompleteCompteColonne))) {
					System.out.println(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt)+" "+positionsLigneDuMot+ " "+( positionColonneDuMot-cpt));
					point=point+plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteColonne,positionColonneDuMot-cpt);
					ajoutColonne=true;
				}
				cpt++;
				if (positionColonneDuMot-cpt<0) {
					break;
				}
			}
		}*/
		System.out.println("puntos5"+point);
		if(ajoutColonne && ajoutLigne) {
			System.out.println((positionsLigneDuMot-1)+ " "+ (positionColonneDuMot-1));
			point=point+(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-1).getPoint()*plateau.typeCasePosition(positionsLigneDuMot,positionColonneDuMot).multiplicateurCaseLettre());
		}
		System.out.println("puntos"+point);
		return point;
	}
	
	public static int ajoutMotCompleteColonne(Joueur j,Jeu plateau,int positionsLigneDuMot,int positionColonneDuMot,int[]positionColonneAutreMot,int[]lettreCompleteCompteLigne,int[]lettreCompleteCompteColonne) {
		int cpt=1;
		int point=0;
		int[] positionsLettreDuMotComplete;
		boolean estPasEgal=true;
		boolean ajoutLigne=false;
		boolean ajoutColonne=false;
		if (positionsLigneDuMot>0) {
			while((plateau.getLettre((positionsLigneDuMot-cpt-1), positionColonneDuMot-1)!=null)) {	
				if (estPasEgal(positionsLigneDuMot-cpt,positionColonneAutreMot)&&(estPasEgal(positionsLigneDuMot-cpt-1,lettreCompleteCompteLigne))){
					point=point+plateau.getLettre(positionsLigneDuMot-cpt-1,positionColonneDuMot-1).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteLigne,positionsLigneDuMot-cpt-1);
					ajoutLigne=true;
				}
				cpt++;
				if (positionsLigneDuMot-cpt<0) {
					break;
				}
			}
		}

		cpt=1;
		if (positionsLigneDuMot<14) {
			while (plateau.getLettre(positionsLigneDuMot+cpt-1, positionColonneDuMot-1)!=null) {


				/*(estPasEgal(positionsLigneDuMot+cpt,positionColonneAutreMot)&&*/
				if (estPasEgal(positionsLigneDuMot+cpt,positionColonneAutreMot)&&(estPasEgal(positionsLigneDuMot+cpt,lettreCompleteCompteLigne))){
					point=point+plateau.getLettre(positionsLigneDuMot+cpt-1,positionColonneDuMot-1).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteLigne,positionsLigneDuMot+cpt);
					ajoutLigne=true;
				}
				cpt++;
				if (positionsLigneDuMot+cpt>14) {
					break;
				}
			}
		}

		cpt=2;
		if (positionColonneDuMot<14) {
			
			while((plateau.getLettre(positionsLigneDuMot, positionColonneDuMot+cpt-2)!=null)) {
				if ((estPasEgal(positionColonneDuMot+cpt-2,lettreCompleteCompteColonne))) {
					point=point+plateau.getLettre(positionsLigneDuMot, positionColonneDuMot+cpt-2).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteColonne,positionColonneDuMot+cpt-2);
					ajoutColonne=true;
				}
				cpt++;
				if (positionColonneDuMot+cpt>14) {
					break;
				}
			}
		}
		cpt=2;
		if (positionColonneDuMot>0) {
			while(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt)!=null) {

				if((estPasEgal(positionColonneDuMot-cpt,lettreCompleteCompteColonne))) {
					point=point+plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-cpt).getPoint();
					ajouterFinTableauEntier(lettreCompleteCompteColonne,positionColonneDuMot-cpt);
					ajoutColonne=true;
				}
				cpt++;
				if (positionColonneDuMot-cpt<0) {
					break;
				}
			}
		}
		if(ajoutColonne && ajoutLigne) {
			point=point+(plateau.getLettre(positionsLigneDuMot-1, positionColonneDuMot-1).getPoint()*plateau.typeCasePosition(positionsLigneDuMot,positionColonneDuMot).multiplicateurCaseLettre());
		}
		return point;
	}
	 
	public static boolean estPasEgal(int nombre,int[] listeDeNombre) {
		
		boolean estPasEgal=true;
		for (int cpt=0;cpt<listeDeNombre.length;cpt++) {
			System.out.println("nb"+nombre+" lnb"+listeDeNombre[cpt]);
			if (nombre==listeDeNombre[cpt]){estPasEgal=false;}
		}
		return estPasEgal;
	}
	
	public static void compterLesPointsColonne(Joueur j,Jeu plateau,int nombreLettreAPlacer,int positionsLettreChevalet[],int[] positionsLigne,int positionsColonne) {
		//WIP
		int multiplicateurMot=1;
		int scoreMot=0;
		int[] lettreCompleteCompteColonne= new int[15];
		int[] lettreCompleteCompteLigne= new int[15];
		
		for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
			int multiplicateurLettre=1;
			scoreMot=scoreMot+ajoutMotCompleteColonne(j,plateau,positionsLigne[cpt],positionsColonne,positionsLigne,lettreCompleteCompteLigne,lettreCompleteCompteColonne);
			System.out.println("calcul multi:"+positionsLigne[cpt]+" "+positionsColonne);
			System.out.println("case:"+plateau.getLettre(positionsLigne[cpt], positionsColonne));
			System.out.println(plateau.typeCasePosition(positionsLigne[cpt],positionsColonne).multiplicateurCaseMot());
			System.out.println(plateau.typeCasePosition(1,1).multiplicateurCaseMot());

			multiplicateurMot=multiplicateurMot*plateau.typeCasePosition(positionsLigne[cpt]+1,positionsColonne+1).multiplicateurCaseMot();
			System.out.println(plateau.typeCasePosition(positionsLigne[cpt], positionsColonne).multiplicateurCaseLettre());
			multiplicateurLettre=multiplicateurLettre*plateau.typeCasePosition(positionsLigne[cpt]+1, positionsColonne+1).multiplicateurCaseLettre();
			j.afficherChevalet();
			System.out.println("Erreur??"+scoreMot+" multi"+multiplicateurLettre+"lettre:"+j.donnerLettre(positionsLettreChevalet[cpt]-1));
			scoreMot=scoreMot+j.donnerLettre(positionsLettreChevalet[cpt]-1).getPoint()*multiplicateurLettre;
			System.out.println("Erreur??"+scoreMot);

			j.supprimerLettre(cpt);
		}
		if (nombreLettreAPlacer==7){
			scoreMot=scoreMot+50;
		}
		j.setScore(j.getScore()+(scoreMot*multiplicateurMot));
	}
	
	
	
	public static void ajouterFinTableauEntier(int[] tableau,int element) {
		//AjoutElementAutreQue0
		for (int cpt=0;cpt<tableau.length;cpt++) {
			if (tableau[cpt]==0) {
				tableau[cpt]=element;
				break;
			}
		}
	}
}
