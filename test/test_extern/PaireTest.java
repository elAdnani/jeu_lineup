package test_extern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import outils.Paire;

public class PaireTest {
	Paire paireNulle;
	Paire paire1;
	Paire paireComme1;
	Paire paire2;
	Paire paire42;
	
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Début des tests pour la classe Paire\n");
	}
	
	
	
	@AfterAll
	public static void afterAllTests() {
		System.out.println("Fin des tests de GrapheType");
	}
	
	
	
	@BeforeEach
	public void beforeATest() {
		 paireNulle = new Paire(0, 0);
		     paire1 = new Paire(1, 1);
		paireComme1 = new Paire(1, 1);
		     paire2 = new Paire(2, 2);
		    paire42 = new Paire(4, 2);
	}
	
	
	
	@AfterEach
	public void afterATest() {
		System.out.println("___ end ___\n");
	}
	
	
	
	@Test
	public void testEquals() {
		System.out.println("testEquals");

		assertEquals(paireNulle, paireNulle);
		assertEquals(paire1, paire1);
		assertEquals(paire1, paireComme1);
		
		assertFalse( paireNulle.equals(null));
		assertFalse( paire1.equals(paire2));
	}
	
	
	
	@Test
	public void testToString() {
		System.out.println("testToString");
		
		assertEquals(paire1.toString(), paireComme1.toString());
		assertEquals(paireNulle.toString(), "(0; 0)");
	}
	
	
	
	@Test
	public void testInverser() {
		System.out.println("testInverser");
		
		assertEquals(paire42.inverser(), new Paire(2, 4));
		paire42.inverser();
		assertEquals(paire42, new Paire(4, 2));
	}
	
	
	
	@Test
	public void testSommeAttributs() {
		System.out.println("testSommeAttributs");
		
		assertEquals(paireNulle.somme(), 0 + 0);
		assertEquals(paire1.somme(),     1 + 1);
		assertEquals(paire2.somme(),     2 + 2);
		assertEquals(paire42.somme(),    4 + 2);
	}
	
	
	
	@Test
	public void testMoyenneAttributs() {
		System.out.println("testMoyenneAttributs");

		assertEquals(paireNulle.moyenne(), (0 + 0) / 2);
		assertEquals(paire1.moyenne(),     (1 + 1) / 2);
		assertEquals(paire2.moyenne(),     (2 + 2) / 2);
		assertEquals(paire42.moyenne(),    (4 + 2) / 2);
	}
	
	
	
	@Test
	public void testSommePaire() {
		System.out.println("testSommePaire");

		assertEquals(paireNulle.sommeAvec(paireNulle) , paireNulle);
		assertEquals(paire42   .sommeAvec(paireNulle) , paire42);
		assertEquals(paireNulle.sommeAvec(paire1)     , paire1);
		assertEquals(paireNulle                       , paire1);
		assertEquals(paire1    .sommeAvec(paireComme1), paire2);
		assertEquals(paire1                           , paire2);
	}
	
	
	
	@Test
	public void testSoustractionPaire() {
		System.out.println("testSoustractionPaire");

		assertEquals(paireNulle.soustractionAvec(paireNulle), paireNulle);
		assertEquals(paire42   .soustractionAvec(paireNulle), paire42);
		assertEquals(paireNulle.soustractionAvec(paire1)    , new Paire(-1, -1));
		assertEquals(paireNulle                             , new Paire(-1, -1));
		assertEquals(paire2    .soustractionAvec(paire1)    , paireComme1);
		assertEquals(paire2                                 , paireComme1);
	}
	
	
	
	@Test
	public void testModificationX() {
		System.out.println("testModificationX");
		
		// Ajouter
		assertEquals(paireNulle.ajouterX(0), paireNulle);
		assertEquals(paire1    .ajouterX(1), new Paire(2, 1));
		assertEquals(paire1                , new Paire(2, 1));
		assertEquals(paire42   .ajouterX(4), new Paire(8, 2));
		
		// Enlever
		assertEquals(paireNulle.enleverX(0), paireNulle);
		assertEquals(paire1    .enleverX(1), new Paire(1, 1));
		assertEquals(paire1                , new Paire(1, 1));
		assertEquals(paire42   .enleverX(4), new Paire(4, 2));
	}
	
	
	
	@Test
	public void testModificationY() {
		System.out.println("testModificationY");
		
		// Ajouter
		assertEquals(paireNulle.ajouterY(0), paireNulle);
		assertEquals(paire1    .ajouterY(1), new Paire(1, 2));
		assertEquals(paire1                , new Paire(1, 2));
		assertEquals(paire42   .ajouterY(4), new Paire(4, 6));
		
		// Enlever
		assertEquals(paireNulle.enleverY(0), paireNulle);
		assertEquals(paire1    .enleverY(1), new Paire(1, 1));
		assertEquals(paire1                , new Paire(1, 1));
		assertEquals(paire42   .enleverY(4), new Paire(4, 2));
	}
	
	
	
	@Test
	public void testModificationPaire() {
		System.out.println("testModificationPaire");
		
		// Ajouter
		assertEquals(paireNulle.ajouter(0), paireNulle);
		assertEquals(paire1    .ajouter(1), new Paire(2, 2));
		assertEquals(paire1               , new Paire(2, 2));
		assertEquals(paire42   .ajouter(4), new Paire(8, 6));
		
		// Enlever
		assertEquals(paireNulle.enlever(0), paireNulle);
		assertEquals(paire1    .enlever(1), new Paire(1, 1));
		assertEquals(paire1               , new Paire(1, 1));
		assertEquals(paire42   .enlever(4), new Paire(4, 2));		
	}
}
