package test_lineup_3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import lineup_3.modele.Joueur;
import lineup_3.modele.Mode;
import lineup_3.modele.Nature;
import lineup_3.modele.PionClassique;
import lineup_3.modele.PlateauPolynomial;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 1 juin 2021
 * @version 1 juin 2021 23:10:52
 */
class JoueurTest {
	
	Joueur joueur = new Joueur("PseudoDuJoueur", 9, Mode.CLASSIQUE);
	Joueur joueuse = new Joueur("PseudoDeLaJoueuse", 9, Mode.CHIFUMI);
	PlateauPolynomial plateau = new PlateauPolynomial(3);
	
//	@BeforeAll
//	public static void beforeAllTests() {
//		
//		System.out.println("Début des tests pour la classe Graphe\n");
//	}

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
	public void test() {
		assertTrue(true);
	}
	
	
	
}