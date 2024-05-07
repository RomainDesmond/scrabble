package scrabble.model;

public class Case {
	private int posX;
	private int posY;
	private TypeCase typeCase;
	private Lettre contenu;
	
	public Case(int posX, int posY, TypeCase typeCase, Lettre contenu) {
		this.posX=posX;
		this.posY=posY;
		this.typeCase=typeCase;
		this.contenu=contenu;		
	}
	public String affichageTypeCase() {
		return this.typeCase.AffichageCase();
	}
	
	public Boolean estUtilise() {
		return null;
		//TODO
	}
	
	public int compterPoint() {
		return posX;
		//TODO
	}
	
	public Boolean lettrePeutEtrePlace() {
		return null;
		//TODO
	}

	public TypeCase getTypeCase() {
		return this.typeCase;
	}

	public void setTypeCase(TypeCase typeCase) {
		this.typeCase = typeCase;
	}

	public Lettre getContenu() {
		return contenu;
	}


	public void setContenu(Lettre contenu) {
		this.contenu = contenu;
	}

	
}
