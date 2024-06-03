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
	int[]listeDeNb= new int[7];
	int[]listePosition=new int[7];
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
		System.out.println("Nouveau test");

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



		listeDeNb[0]=1;
		listeDeNb[1]=2;
		listePosition[0]=7;
		listePosition[1]=8;
		positionLigneColonneMot[0]=7;
		
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,2,1);

	}
	@Test
	void assertTrueSiMotEstBienPlaceColonne() {

		listeDeNb[0]=1;
		listeDeNb[1]=2;
		listePosition[0]=7;
		listePosition[1]=8;
		positionLigneColonneMot[1]=7;
		plateau.afficherPlateau();
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,2,2);
	}
	@Test
	void assertFalseSiMotMalPlaceLigne() {
		listeDeNb[0]=1;
		listeDeNb[1]=2;
		listePosition[0]=10;
		listePosition[1]=11;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,2,1);
	}
	
	@Test
	void assertFalseSiMotMalPlaceColonne() {
		listeDeNb[0]=1;
		listeDeNb[1]=2;
		listePosition[0]=10;
		listePosition[1]=11;
		positionLigneColonneMot[1]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,2,2);
	}
	
	@Test
	void testScoreEstBonUnMot() {
		listeDeNb[0]=1;
		listeDeNb[1]=2;
		listePosition[0]=7;
		listePosition[1]=8;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,2,1);
		plateau.afficherPlateau();
		
		assertEquals(3, jTest.getScore());
		}
	
	@Test
	void testScoreEstBonCompleteUnMot() {
		listeDeNb[0]=1;
		listeDeNb[1]=2;
		listePosition[0]=7;
		listePosition[1]=8;
		positionLigneColonneMot[0]=7;
		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,2,1);
		listePosition=new int[7];
		listeDeNb=new int[7];
		positionLigneColonneMot=new int[2];
		listeDeNb[0]=5;
		listePosition[0]=9;
		positionLigneColonneMot[0]=7;
		plateau.modificationCasePlacable();

		ScrabbleApplicationConsole.jouerMot(plateau,jTest,positionLigneColonneMot,listeDeNb,listePosition,1,1);


		assertEquals(15, jTest.getScore());
		}
	
	@Test
	public void utilisationJoker() {
		
	}
    @Test
    public void testGetUserInputOther() {
        // Données d'entrée simulées
        String simulatedUserInput = "3";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);

        // Initialisation de la classe à tester
        //InputHandler inputHandler = new InputHandler();

        String result = ScrabbleApplicationConsole.demanderLettre("Entrez votre choix");

        // Vérification du résultat
        assertEquals(simulatedUserInput, result);
    }
}