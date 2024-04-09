package scrabble.model;

public class Case {
	private int posX;
	private int posY;
	private String typeCase;
	private Lettre contenu;
	
	public Case(int posX, int posY, String typeCase, Lettre contenu) {
		this.posX=posX;
		this.posY=posY;
		this.typeCase=typeCase;
		this.contenu=contenu;		
	}
	
	public Boolean estUtilise() {
		//a compléter
	}
	
	public int compterPoint() {
		//a compléter
	}
	
	public Boolean lettrePeutEtrePlace() {
		//a compléter
	}
	
}
