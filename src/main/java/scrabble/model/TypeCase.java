package scrabble.model;

public enum TypeCase {
	LETTREDOUBLE("LD"),
	LETTRETRIPLE("LT"),
	MOTDOUBLE("MD"),
	MOTTRIPLE("MT"),
	VIDE(""),
	DEPART("*");
	private String affichageCase;
	
	private TypeCase(String affichageCase) {
		this.affichageCase=affichageCase;
	}
	public String AffichageCase() {
		return this.affichageCase;
	}
}
