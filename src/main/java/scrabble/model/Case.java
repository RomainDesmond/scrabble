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

	public String getTypeCase() {
		return typeCase;
	}

	public void setTypeCase(String typeCase) {
		this.typeCase = typeCase;
	}

	public Lettre getContenu() {
		return contenu;
	}

	public void setContenu(Lettre contenu) {
		this.contenu = contenu;
	}

	
}
