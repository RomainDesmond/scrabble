package scrabble.model;

public enum TypeCase {
	LETTREDOUBLE("LD",2,1),
	LETTRETRIPLE("LT",3,1),
	MOTDOUBLE("MD",1,2),
	MOTTRIPLE("MT",1,3),
	VIDE("",1,1),
	DEPART("*",1,2);
	private String affichageCase;
	private int multiplicateurLettre;
	private int multiplicateurMot;
	private TypeCase(String affichageCase,int multiplicateurLettre,int multiplicateurMot) {
		this.affichageCase=affichageCase;
		this.multiplicateurLettre=multiplicateurLettre;
		this.multiplicateurMot=multiplicateurMot;
	}
	public String AffichageCase() {
		return this.affichageCase;
	}
	public int multiplicateurCaseLettre() {
		return this.multiplicateurLettre;
	}
	public int multiplicateurCaseMot() {
		return this.multiplicateurMot;
	}
}
