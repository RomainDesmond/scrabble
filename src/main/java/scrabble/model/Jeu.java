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
	}
	
	public void ajouteTypeCase() {
		for (int cpt1=0;cpt1<15;cpt1++) {
			System.out.println(cpt1);
			for (int cpt2 = 0;cpt2<15;cpt2++) {
					this.plateauDeJeu[cpt1][cpt2]=new Case(cpt1,cpt2,TypeCase.VIDE,null);
			}
		}
		
		
		for (int cpt1=1;cpt1<5;cpt1++) {
			this.plateauDeJeu[cpt1][cpt1]=new Case(cpt1,cpt1,TypeCase.MOTDOUBLE,null);
			this.plateauDeJeu[cpt1][14-cpt1]=new Case(cpt1,cpt1,TypeCase.MOTDOUBLE,null);
		}
		for (int cpt1=13;cpt1>9;cpt1--) {
			this.plateauDeJeu[cpt1][cpt1]=new Case(cpt1,cpt1,TypeCase.MOTDOUBLE,null);
			this.plateauDeJeu[cpt1][14-cpt1]=new Case(cpt1,cpt1,TypeCase.MOTDOUBLE,null);
		}
		for (int cpt1=0;cpt1<15;cpt1=cpt1+14) {
			for (int cpt2=0;cpt2<15;cpt2=cpt2+7) {
				this.plateauDeJeu[cpt1][cpt2]=new Case(cpt1,cpt2,TypeCase.MOTTRIPLE,null);
			}
		}
		for (int cpt1=5;cpt1<10;cpt1=cpt1+4) {
			for (int cpt2=1;cpt2<14;cpt2=cpt2+4) {
				this.plateauDeJeu[cpt1][cpt2]=new Case(cpt1,cpt2,TypeCase.LETTRETRIPLE,null);
				this.plateauDeJeu[cpt2][cpt1]=new Case(cpt1,cpt2,TypeCase.LETTRETRIPLE,null);
			}
		}
		for (int cpt1=0;cpt1<15;cpt1=cpt1+7) {
			for (int cpt2=3;cpt2<12;cpt2=cpt2+8) {
				this.plateauDeJeu[cpt1][cpt2]=new Case(cpt1,cpt2,TypeCase.LETTREDOUBLE,null);
				this.plateauDeJeu[cpt2][cpt1]=new Case(cpt2,cpt1,TypeCase.LETTREDOUBLE,null);
			}
		
			
		}
		for (int cpt1=6;cpt1<9;cpt1=cpt1+2) {
			for (int cpt2=2;cpt2<7;cpt2=cpt2+4) {
				this.plateauDeJeu[cpt1][cpt2]=new Case (cpt1,cpt2,TypeCase.LETTREDOUBLE,null);
				this.plateauDeJeu[cpt1][cpt2+6]=new Case (cpt1+6,cpt2+6,TypeCase.LETTREDOUBLE,null);
				this.plateauDeJeu[cpt2][cpt1]=new Case (cpt2,cpt1,TypeCase.LETTREDOUBLE,null);
				this.plateauDeJeu[cpt2+6][cpt1]=new Case (cpt2+6,cpt1+6,TypeCase.LETTREDOUBLE,null);

			}
		}
		for (int cpt1=0;cpt1<15;cpt1=cpt1+14) {
			this.plateauDeJeu[7][cpt1]=new Case (7,cpt1,TypeCase.MOTTRIPLE,null);
		}
		plateauDeJeu[caseMilieuXY][caseMilieuXY]=new Case(caseMilieuXY,caseMilieuXY,TypeCase.DEPART,null,true);


		
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
					if (plateauDeJeu[compteur][compteur2].getContenu()!=null) {
						System.out.print(plateauDeJeu[compteur][compteur2].AffichageLettre()+" "+ "|"+" ");
					}
					else {
						if (plateauDeJeu[compteur][compteur2].getTypeCase()==TypeCase.DEPART) {
							System.out.print(plateauDeJeu[compteur][compteur2].affichageTypeCase()+" "+ "|"+" ");
						}
						else if(plateauDeJeu[compteur][compteur2].getTypeCase()==TypeCase.VIDE) {
							System.out.print(plateauDeJeu[compteur][compteur2].affichageTypeCase()+"  "+ "|"+" ");
						}
						else {
						System.out.print(plateauDeJeu[compteur][compteur2].affichageTypeCase()+""+ "|"+" ");
						}
						}}

				else {
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
	
	public void placerLettreJoue(int posX,int posY,ValeurLettre lettre) {
		
		plateauDeJeu[posX][posY].setContenu(lettre);
	}
	public String finPartie() {
		return null;
		//TODO
	}
	public Boolean motEstPlacable(int positionLigne,int positionColonne,int nombreLettreAPlacer,int choixLigneColonne) {
		//WIP
		Boolean estPlacable=false;
		if (choixLigneColonne==1) {
			for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				if (this.plateauDeJeu[positionLigne][positionColonne+cpt].estUtilisable()==true) {
					estPlacable=true;
					break;
				}
			}
		}
		else {
			for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				if (this.plateauDeJeu[positionLigne+cpt][positionColonne].estUtilisable()==true) {
					estPlacable=true;
					break;
				}
			}
		}
		return estPlacable;
	}
	
	public Boolean motEstPlacableLigne(int positionLigne,int[] positionColonne,int nombreLettreAPlacer) {
		//WIP
		Boolean estPlacable=false;
			for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				System.out.println();
				if (this.plateauDeJeu[positionLigne][positionColonne[cpt]].estUtilisable()==true) {
					
					estPlacable=true;
					break;
				}
			}

		return estPlacable;
	}
	public Boolean motEstPlacableColonne(int[] positionLigne,int positionColonne,int nombreLettreAPlacer) {
		//WIP
		Boolean estPlacable=false;
			for (int cpt=0;cpt<nombreLettreAPlacer;cpt++) {
				System.out.println();
				if (this.plateauDeJeu[positionLigne[cpt]][positionColonne].estUtilisable()==true) {
					
					estPlacable=true;
					break;
				}
			}

		return estPlacable;
	}
	
	public void modificationCasePlacable() {
		for (int cpt=0;cpt<15;cpt++) {
			for(int cpt1=0;cpt1<15;cpt1++) {
				if(this.plateauDeJeu[cpt][cpt1].getContenu()!=null) {
					plateauDeJeu[cpt+1][cpt1].setCaseUtilisable(true);
					plateauDeJeu[cpt-1][cpt1].setCaseUtilisable(true);
					plateauDeJeu[cpt][cpt1+1].setCaseUtilisable(true);
					plateauDeJeu[cpt][cpt1-1].setCaseUtilisable(true);
				}
			}
		}
		for (int cpt=0;cpt<15;cpt++) {
			for(int cpt1=0;cpt1<15;cpt1++) {
				if(this.plateauDeJeu[cpt][cpt1].getContenu()!=null) {
					plateauDeJeu[cpt][cpt1].setCaseUtilisable(false);
				}
			}
		}
	}
	public TypeCase typeCasePosition(int positionLigne, int positionColonne) {
		return this.plateauDeJeu[positionLigne][positionColonne].getTypeCase();
	}

	
}
