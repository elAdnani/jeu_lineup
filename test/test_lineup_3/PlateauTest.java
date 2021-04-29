 package test_lineup_3;

 import lineup_3.Joueur;
 import lineup_3.Pion;
 import lineup_3.Plateau;
 import lineup_3.Deplacement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class PlateauTest {
	

	Joueur joueur1 = new Joueur("adnan", 10);
	Joueur joueur2= new Joueur("adnanPuissanceDeux", 10);
	

	Plateau tousLesJoueurs= new Plateau(3); // matérialisation 

	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Début des tests pour la classe Graphe\n");
		
		
	}

	@AfterAll
	public static void afterAllTests() {
		System.out.println("Fin des tests de Graphe");
	}

	@BeforeEach
	public void beforeATest() {
		System.out.println("___ start ___\n");
	}

	@AfterEach
	public void afterATest() {
		System.out.println("___ end ___\n");
	}

	@Test
	public void testGenerationDuPlateau() {
		System.out.println("--testGenerationDuPlateau--");
		tousLesJoueurs.generationDuPlateau();
		System.out.println(tousLesJoueurs);	
		List<Pion> listePion =new ArrayList<>();

		listePion.add(new Pion(1,1));
		listePion.add(new Pion(1,2)); listePion.add(new Pion(1,3));
		listePion.add(new Pion(2,1));
		listePion.add(new Pion(2,2)); listePion.add(new Pion(2,3));
		listePion.add(new Pion(3,1));
		listePion.add(new Pion(3,2));
		listePion.add(new Pion(3,3));

			
		assertFalse(listePion.equals(Plateau.getListSommet()));

		
		System.out.println(tousLesJoueurs);		
	}

	@Test
	public void testDeplacementPion() {
		
		System.out.println("testDeplacementPion");
		
		int X=2,Y=1;
		Pion pionQuiSeDeplace = new Pion(X,Y);

		this.tousLesJoueurs.addPionSommet(pionQuiSeDeplace); /** @TODO mettre le addPion en static **/
		
		this.tousLesJoueurs.deplacerPion(pionQuiSeDeplace, "drOite"); // j'arrive pas le mettre en assertTrue

		assertEquals(pionQuiSeDeplace.getX(), X + Deplacement.DROITE.getX());
		assertEquals(pionQuiSeDeplace.getY(), Y + Deplacement.DROITE.getY());

		X=pionQuiSeDeplace.getX();
		Y=pionQuiSeDeplace.getY();


		this.tousLesJoueurs.deplacerPion(pionQuiSeDeplace, "HauT");
		assertEquals(pionQuiSeDeplace.getX(), X + Deplacement.HAUT.getX());
		assertEquals(pionQuiSeDeplace.getY(), Y + Deplacement.HAUT.getY());
		
		X=pionQuiSeDeplace.getX();
		Y=pionQuiSeDeplace.getY();
		
		
		this.tousLesJoueurs.deplacerPion(pionQuiSeDeplace, "BaS");

		assertEquals(pionQuiSeDeplace.getX(), X + Deplacement.BAS.getX());
		assertEquals(pionQuiSeDeplace.getY(), Y + Deplacement.BAS.getY());
		
		X=pionQuiSeDeplace.getX();
		Y=pionQuiSeDeplace.getY();
		
		this.tousLesJoueurs.deplacerPion(pionQuiSeDeplace, "Gauche");
		assertEquals(pionQuiSeDeplace.getX(), X + Deplacement.GAUCHE.getX());
		assertEquals(pionQuiSeDeplace.getY(), Y + Deplacement.GAUCHE.getY());
		
		
	}


	@Test
	public void testVictoire1() {
		// alignement intérieur
		Pion aligner1 = new Pion(1,1);
		Pion aligner2 = new Pion(1,2);
		Pion aligner3 = new Pion(1,3);
		
		Joueur joueurGagnant = new Joueur("gagnant", 3);

		joueurGagnant.getMain().addPions(aligner1);
		joueurGagnant.getMain().addPions(aligner2);
		joueurGagnant.getMain().addPions(aligner3);
		
		this.tousLesJoueurs.addJoueur(joueurGagnant);
		
		assertEquals(joueurGagnant,Plateau.partieGagnee());
		
		
		
	}
	
	public void testVictoire2() {
		// alignement extérieur
		Pion aligner1 = new Pion(1,2);
		Pion aligner2 = new Pion(2,2);
		Pion aligner3 = new Pion(3,2);
		
		Joueur joueurGagnant = new Joueur("gagnant", 3);

		joueurGagnant.getMain().addPions(aligner1);
		joueurGagnant.getMain().addPions(aligner2);
		joueurGagnant.getMain().addPions(aligner3);
		
		this.tousLesJoueurs.addJoueur(joueurGagnant);
		
		assertEquals(joueurGagnant,Plateau.partieGagnee());
		
		
		
	}
}
