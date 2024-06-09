package scrabble.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import scrabble.model.Jeu;
import scrabble.model.Joueur;
import scrabble.model.ValeurLettre;
import scrabble.application.ScrabbleApplicationConsole;
import scrabble.application.ScrabbleApplicationGraphiqueController;

public class ScrabbleTest {
	ValeurLettre[]VL=new ValeurLettre[7];
	ValeurLettre[] listeVLettre= new ValeurLettre[7];
	int[]listeDeNbPosChevaletPosChevalet= new int[7];
	int[]listePositionLettreAPlacer=new int[7];
	int[]positionLigneColonneMot=new int[2];
	boolean motEstJoue;
	Joueur jTest = new Joueur("test",listeVLettre);
	Jeu plateau = new Jeu();


    @BeforeEach
    public void configuration() {
    	
		listeVLettre[0]=ValeurLettre.A;
		listeVLettre[1]=ValeurLettre.U;
		listeVLettre[2]=ValeurLettre.A;
		listeVLettre[3]=ValeurLettre.U;
		listeVLettre[4]=ValeurLettre.X;
		listeVLettre[5]=ValeurLettre.JOKER;
		listeVLettre[6]=ValeurLettre.Z;
		plateau.ajouteTypeCase();
		jTest = new Joueur("test",listeVLettre);

    }
    @AfterEach
    public void finalisation() {
    	System.out.println("Finalisation");
    	plateau.afficherPlateau();
    }
    
	@Test
	void test() {
		assertTrue(true); //TODO
	}
	
	@Test
	void assertTrueSiMotEstBienPlaceLigne() {

		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[0]=7;
		VL[0]=ValeurLettre.A;
		VL[1]=ValeurLettre.U;
		listePositionLettreAPlacer[0]=8;
		listePositionLettreAPlacer[1]=9;
		positionLigneColonneMot[0]=8;
		
		plateau.afficherPlateau();
		for (int i =0;i<2;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[0]-1));
			plateau.placerLettreJoue(positionLigneColonneMot[0]-1,(listePositionLettreAPlacer[i]-1), VL[i]);
		}
		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,2,1));

		/*motEstJoue=ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		assertTrue(motEstJoue);*/
	}
	
	@Test
	void assertTrueSiMotEstBienPlaceColonne() {
		/*
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[1]=7;*/
		
		
		VL[0]=ValeurLettre.A;
		VL[1]=ValeurLettre.U;
		listePositionLettreAPlacer[0]=8;
		listePositionLettreAPlacer[1]=9;
		positionLigneColonneMot[1]=8;
		
		plateau.afficherPlateau();
		for (int i =0;i<2;i++) {
			plateau.placerLettreJoue((listePositionLettreAPlacer[i]-1),positionLigneColonneMot[1]-1, VL[i]);
		}
		
		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,2,2));

		/*
		motEstJoue=ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2);
		assertTrue(motEstJoue);*/
	}
	
	@Test
	void assertFalseSiMotMalPlaceLigne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=10;
		listePositionLettreAPlacer[1]=11;
		positionLigneColonneMot[0]=7;
		assertFalse(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1));
	}
	
	@Test
	void assertFalseSiMotMalPlaceColonne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=10;
		listePositionLettreAPlacer[1]=11;
		positionLigneColonneMot[1]=7;
		assertFalse(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2));
	}
	
	@Test
	void testScoreEstBonUnMot() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[0]=7;
		VL[0]=ValeurLettre.A;
		VL[1]=ValeurLettre.U;
		listePositionLettreAPlacer[0]=8;
		listePositionLettreAPlacer[1]=9;
		positionLigneColonneMot[0]=8;
		
		plateau.afficherPlateau();
		for (int i =0;i<2;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[0]-1));
			plateau.placerLettreJoue(positionLigneColonneMot[0]-1,(listePositionLettreAPlacer[i]-1), VL[i]);
		}
		plateau.afficherPlateau();
		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,2,1));

		//ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		plateau.afficherPlateau();
		
		assertEquals(4, jTest.getScore());
		}
	/*
	@Test
	void testScoreEstBonCompleteUnMotLigne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7+1;
		listePositionLettreAPlacer[1]=8+1;
		positionLigneColonneMot[0]=7+1;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		positionLigneColonneMot=new int[2];
		listeDeNbPosChevaletPosChevalet[0]=5;
		listePositionLettreAPlacer[0]=9+1;
		positionLigneColonneMot[0]=7+1;
		plateau.modificationCasePlacable();

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
		plateau.modificationCasePlacable();
		assertEquals(15, jTest.getScore());
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		System.out.println("oui?");

		listeDeNbPosChevaletPosChevalet[0]=3;
		listePositionLettreAPlacer[0]=6+1;
		jTest.donnerLettre(listeDeNbPosChevaletPosChevalet[0]);
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);

		plateau.modificationCasePlacable();
		assertEquals(28, jTest.getScore());
		System.out.println("oui2?");

		listeDeNbPosChevaletPosChevalet[0]=4;
		positionLigneColonneMot[0]=6+1;

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
		plateau.modificationCasePlacable();
		assertEquals(31, jTest.getScore());
		System.out.println("oui25?");

		listeDeNbPosChevaletPosChevalet[0]=7;
		positionLigneColonneMot[0]=8+1;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
		plateau.modificationCasePlacable();
		assertEquals(53, jTest.getScore());
		}
	
	@Test
	void testScoreEstBonCompleteUnMotColonne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=8;
		listePositionLettreAPlacer[1]=9;
		positionLigneColonneMot[1]=8;
		
		
		VL[0]=ValeurLettre.A;
		VL[1]=ValeurLettre.U;
		for (int i =0;i<2;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[i]-1));
			plateau.placerLettreJoue(listePositionLettreAPlacer[i]-1, positionLigneColonneMot[1]-1, VL[i]);
		}

		System.out.println("oui");
		plateau.afficherPlateau();

		//assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2));
		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,2,2));
		assertEquals(4, jTest.getScore());
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		positionLigneColonneMot=new int[2];
		VL=new ValeurLettre[7];
		VL[0]=ValeurLettre.X;
		listeDeNbPosChevaletPosChevalet[0]=5;
		listePositionLettreAPlacer[0]=10;
		positionLigneColonneMot[1]=8;

		
		for (int i =0;i<1;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[1]-1));
			plateau.placerLettreJoue(listePositionLettreAPlacer[i]-1, positionLigneColonneMot[1]-1, VL[i]);
		}
		

		
		System.out.println("ici"+plateau.casePosition(9, 7).affichageCase());
		System.out.println("ici"+plateau.casePosition(9, 7).estUtilisable());

		//plateau.typeCasePosition(10, 8).;

		plateau.afficherPlateau();
		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,1,2));

		assertEquals(16, jTest.getScore());
		plateau.modificationCasePlacable();
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];

		listeDeNbPosChevaletPosChevalet[0]=3;
		listePositionLettreAPlacer[0]=6+1;
		VL=new ValeurLettre[7];
		VL[0]=ValeurLettre.A;
		listePositionLettreAPlacer[0]=7;
		positionLigneColonneMot[1]=8;
		
		plateau.afficherPlateau();
		for (int i =0;i<1;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[1]-1));
			plateau.placerLettreJoue(listePositionLettreAPlacer[i]-1, positionLigneColonneMot[1]-1, VL[i]);
		}
		System.out.println();
		plateau.afficherPlateau();

		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,1,2));

		plateau.modificationCasePlacable();
		assertEquals(29, jTest.getScore());
		
		plateau.afficherPlateau();
		
		listeDeNbPosChevaletPosChevalet[0]=4;
		positionLigneColonneMot[1]=6;
		VL[0]=ValeurLettre.U;
		listePositionLettreAPlacer[0]=7;
		positionLigneColonneMot[1]=7;
		
		plateau.afficherPlateau();
		for (int i =0;i<1;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[1]-1));
			plateau.placerLettreJoue(listePositionLettreAPlacer[i]-1, positionLigneColonneMot[1]-1, VL[i]);
		}

		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,1,2));
		plateau.modificationCasePlacable();
		assertEquals(32, jTest.getScore());
		
		listeDeNbPosChevaletPosChevalet[0]=7;
		positionLigneColonneMot[1]=8;
		VL[0]=ValeurLettre.Z;
		listePositionLettreAPlacer[0]=7;
		positionLigneColonneMot[1]=9;
		
		plateau.afficherPlateau();
		for (int i =0;i<1;i++) {
			System.out.print("+"+(listePositionLettreAPlacer[i]-1)+(positionLigneColonneMot[1]-1));
			plateau.placerLettreJoue(listePositionLettreAPlacer[i]-1, positionLigneColonneMot[1]-1, VL[i]);
		}
		assertTrue(ScrabbleApplicationGraphiqueController.jouerMotFX(plateau,jTest,positionLigneColonneMot,VL,listePositionLettreAPlacer,1,2));
		plateau.modificationCasePlacable();

		assertEquals(54, jTest.getScore());
		
		}
	
	
	@Test
	public void utilisationJoker() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7+1;
		listePositionLettreAPlacer[1]=8+1;
		positionLigneColonneMot[0]=7+1;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		positionLigneColonneMot=new int[2];
		listeDeNbPosChevaletPosChevalet[0]=6;
		listePositionLettreAPlacer[0]=9+1;
		positionLigneColonneMot[0]=7+1;
		plateau.modificationCasePlacable();
        String simulatedUserInput = "X";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1));
		assertEquals(5, jTest.getScore());
	}
	@Test
    public void testGetUserInputOther() {
	       	String simulatedUserInput = "3";
	        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
	        System.setIn(testIn);
	    
	        String result = ScrabbleApplicationConsole.demanderLettre("Entrez votre choix");

	        assertEquals(simulatedUserInput, result);
	    }
	@Test
	 public void testSacEstVide() {
		assertFalse(jTest.sacDeLettreEstVide());
		for (int cpt=0;cpt<7;cpt++) {
			listeDeNbPosChevaletPosChevalet[0+cpt]=1+cpt;
			listePositionLettreAPlacer[0+cpt]=7+cpt+1;
		}
		positionLigneColonneMot[0]=7+1;
        String simulatedUserInput = "X";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,7,1));

		jTest.afficherChevalet();
		assertTrue(jTest.sacDeLettreEstVide());
	 }
	@Test
	public void testAjouterLettreAjoutePremierePosition() {
		for (int cpt=0;cpt<7;cpt++) {
			listeDeNbPosChevaletPosChevalet[0+cpt]=1+cpt;
			listePositionLettreAPlacer[0+cpt]=7+cpt+1;
		}
		positionLigneColonneMot[0]=7+1;
        String simulatedUserInput = "X";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,7,1));

		ValeurLettre lettreTemp = ValeurLettre.A;
		jTest.ajouterLettre(lettreTemp);
		assertEquals(lettreTemp,jTest.donnerLettre(0));
	}
	@Test
	public void testCompletentDeuxMot() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[0]=7;
		
        String simulatedUserInput = "X";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);


		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1));
		plateau.modificationCasePlacable();

		positionLigneColonneMot[1]=7;
		listeDeNbPosChevaletPosChevalet[0]=4;
		listeDeNbPosChevaletPosChevalet[1]=3;
		listePositionLettreAPlacer[0]=6;
		listePositionLettreAPlacer[1]=5;


		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2));
		plateau.modificationCasePlacable();

		
		jTest.ajouterLettre(ValeurLettre.A);
		jTest.ajouterLettre(ValeurLettre.A);
		jTest.ajouterLettre(ValeurLettre.A);
		jTest.ajouterLettre(ValeurLettre.A);

		positionLigneColonneMot[0]=5;
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[0]=2;
		listePositionLettreAPlacer[0]=8;
		listePositionLettreAPlacer[1]=9;
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1));
		plateau.modificationCasePlacable();


		int[]listeDeNbPosChevaletPosChevalet= new int[7];
		int[]listePositionLettreAPlacer=new int[7];
		int[]positionLigneColonneMot=new int[2];
		
		listeDeNbPosChevaletPosChevalet[0]=1;
		listePositionLettreAPlacer[0]=6;
		positionLigneColonneMot[1]=9;
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2));
		plateau.modificationCasePlacable();
		
		int scoreTemp=jTest.getScore();
		
		listeDeNbPosChevaletPosChevalet[0]=7;
		listePositionLettreAPlacer[0]=7;

		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2));

		assertEquals(scoreTemp+24,jTest.getScore());
	}
	@Test
	public void testCompletentDeuxMotEnFormantUn() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listeDeNbPosChevaletPosChevalet[2]=7;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		listePositionLettreAPlacer[2]=9;
		positionLigneColonneMot[0]=7;
		
        String simulatedUserInput = "X";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);


		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,3,1));
		plateau.modificationCasePlacable();
		positionLigneColonneMot[1]=7;
		listeDeNbPosChevaletPosChevalet[0]=4;
		listeDeNbPosChevaletPosChevalet[1]=3;
		listePositionLettreAPlacer[0]=6;
		listePositionLettreAPlacer[1]=5;


		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2));
		plateau.modificationCasePlacable();

		
		jTest.ajouterLettre(ValeurLettre.A);
		jTest.ajouterLettre(ValeurLettre.A); 
		jTest.ajouterLettre(ValeurLettre.A);
		jTest.ajouterLettre(ValeurLettre.A);

		positionLigneColonneMot[0]=5;
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[0]=2;
		listePositionLettreAPlacer[0]=8;
		listePositionLettreAPlacer[1]=9;
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1));
		plateau.modificationCasePlacable();


		int[]listeDeNbPosChevaletPosChevalet= new int[7];
		int[]listePositionLettreAPlacer=new int[7];
		int[]positionLigneColonneMot=new int[2];
		
		listeDeNbPosChevaletPosChevalet[0]=1;
		listePositionLettreAPlacer[0]=6;
		positionLigneColonneMot[1]=9;
		int scoreTemp=jTest.getScore();

		
		assertTrue(ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2));
		plateau.modificationCasePlacable();
		
		assertEquals(scoreTemp+12,jTest.getScore());
	}*/

}