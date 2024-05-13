package scrabble.model;

public enum ValeurLettre {
	A ("A",9,1),B("B",2,3),C("C",2,3),D("D",3,2),E("E",15,1),F("F",2,4),G("G",2,2),H("H",2,4),I("I",8,1),J("J",1,8),K("K",1,10),L("L",5,1),M("M",3,2),N("N",6,1),O("O",6,1),P("P",2,3),Q("Q",1,8),R("R",6,1),S("S",6,1),T("T",6,1),U("U",6,1),V("V",2,4),W("W",1,10),X("X",1,10),Y("Y",1,10),Z("Z",1,10),JOKER(" ",2,0);
	
	private String affichageLettre;
	private final int recurrence;
	private final int point;
	
	private ValeurLettre(String affichageLettre,int recurrence,int point) {
		this.affichageLettre=affichageLettre;
		this.recurrence=recurrence;
		this.point=point;
	}
	public String AffichageLettre() {
		return this.affichageLettre;
	}
	public int getRecurrence() {
		return recurrence;
	}
	public int getPoint() {
		return point;
	}
}
