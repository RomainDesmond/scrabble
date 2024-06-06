package scrabble.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class ScrabbleTest {
	ValeurLettre[] listeVLettre= new ValeurLettre[7];
	int[]listeDeNbPosChevaletPosChevalet= new int[7];
	int[]listePositionLettreAPlacer=new int[7];
	int[]positionLigneColonneMot=new int[2];
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
    	//plateau.afficherPlateau();
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
		
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);

	}
	@Test
	void assertTrueSiMotEstBienPlaceColonne() {

		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[1]=7;
		plateau.afficherPlateau();
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2);
	}
	@Test
	void assertFalseSiMotMalPlaceLigne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=10;
		listePositionLettreAPlacer[1]=11;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
	}
	
	@Test
	void assertFalseSiMotMalPlaceColonne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=10;
		listePositionLettreAPlacer[1]=11;
		positionLigneColonneMot[1]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2);
	}
	
	@Test
	void testScoreEstBonUnMot() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		plateau.afficherPlateau();
		
		assertEquals(3, jTest.getScore());
		}
	
	@Test
	void testScoreEstBonCompleteUnMotLigne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		positionLigneColonneMot=new int[2];
		listeDeNbPosChevaletPosChevalet[0]=5;
		listePositionLettreAPlacer[0]=9;
		positionLigneColonneMot[0]=7;
		plateau.modificationCasePlacable();

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
		plateau.modificationCasePlacable();
		assertEquals(15, jTest.getScore());
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];

		listeDeNbPosChevaletPosChevalet[0]=3;
		listePositionLettreAPlacer[0]=6;
		jTest.donnerLettre(listeDeNbPosChevaletPosChevalet[0]);
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);

		plateau.modificationCasePlacable();
		assertEquals(28, jTest.getScore());
		
		listeDeNbPosChevaletPosChevalet[0]=4;
		positionLigneColonneMot[0]=6;

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
		plateau.modificationCasePlacable();
		assertEquals(31, jTest.getScore());
		
		listeDeNbPosChevaletPosChevalet[0]=7;
		positionLigneColonneMot[0]=8;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
		plateau.modificationCasePlacable();
		assertEquals(53, jTest.getScore());
		}
	
	@Test
	void testScoreEstBonCompleteUnMotColonne() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[1]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,2);
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		positionLigneColonneMot=new int[2];
		listeDeNbPosChevaletPosChevalet[0]=5;
		listePositionLettreAPlacer[0]=9;
		positionLigneColonneMot[1]=7;
		plateau.modificationCasePlacable();

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2);

		assertEquals(15, jTest.getScore());
		plateau.modificationCasePlacable();
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];

		listeDeNbPosChevaletPosChevalet[0]=3;
		listePositionLettreAPlacer[0]=6;
		jTest.donnerLettre(listeDeNbPosChevaletPosChevalet[0]);
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2);

		plateau.modificationCasePlacable();
		assertEquals(28, jTest.getScore());
		
		plateau.afficherPlateau();
		
		listeDeNbPosChevaletPosChevalet[0]=4;
		positionLigneColonneMot[1]=6;

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2);
		plateau.modificationCasePlacable();
		assertEquals(31, jTest.getScore());
		
		listeDeNbPosChevaletPosChevalet[0]=7;
		positionLigneColonneMot[1]=8;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,2);
		plateau.modificationCasePlacable();

		assertEquals(53, jTest.getScore());
		}
	
	
	@Test
	public void utilisationJoker() {
		listeDeNbPosChevaletPosChevalet[0]=1;
		listeDeNbPosChevaletPosChevalet[1]=2;
		listePositionLettreAPlacer[0]=7;
		listePositionLettreAPlacer[1]=8;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,2,1);
		listePositionLettreAPlacer=new int[7];
		listeDeNbPosChevaletPosChevalet=new int[7];
		positionLigneColonneMot=new int[2];
		listeDeNbPosChevaletPosChevalet[0]=6;
		listePositionLettreAPlacer[0]=9;
		positionLigneColonneMot[0]=7;
		plateau.modificationCasePlacable();
        String simulatedUserInput = "X";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNbPosChevaletPosChevalet,listePositionLettreAPlacer,1,1);
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

}