package scrabble.model;

public class Lettre {
	private final ValeurLettre valeur;
	private final int point;

	public Lettre(ValeurLettre valeur,int point) {
		this.valeur=valeur;
		this.point=point;
	}
	public String AffichageLettre() {
		return this.valeur.AffichageLettre();
	}
}
