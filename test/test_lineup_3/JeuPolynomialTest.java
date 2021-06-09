 package test_lineup_3;

 import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lineup_3.modele.AffichagePlateau;
import lineup_3.modele.PionChifumi;
import lineup_3.modele.DeckPions;
import lineup_3.modele.Joueur;
import lineup_3.modele.PlateauPolynomial;
import outils.Paire;
import outils.jeu.Case;
import outils.jeu.Pion;
import outils.jeu.Plateau;


/**
 * 
 * Cette classe sert à tester le fonctionnement du Jeu à partir d'un plateau polynomial. </br>
 * C'est à dire : </br> 
 * - Générer les pions du plateau correctement en fonction du nombre de côté. </br>
 * - Vérifier les joueurs et ses attributs </br>
 * - Vérifier les déplacements des pions
 * - Vérifier les différentes conditions de victoire
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 22 mai 2021-31 mai 2021
 * @version v2
 */
public class JeuPolynomialTest {
	
	final int NMBREDECOTE=3;
	Plateau PlateauATroisCotes= new PlateauPolynomial(NMBREDECOTE); // matérialisation 
	AffichagePlateau affichage = new AffichagePlateau(PlateauATroisCotes);
	
	
	
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
		

		/*
		 * Existante des pions. Si notre plateau est de trois côté
		 * Alors nos cases devrait se répartir de la façon suivante :
		 * 1er côté : Par six points -> (0;{0,1,2,3,4,5})
		 * 2ème côté : Par six points -> (1;{0,1,2,3,4,5})
		 * 3ème et dernier côté : Par six points -> (2;{0,1,2,3,4,5})
		 * Ainsi les extrémités sont (0;0) et (2;5).
		 */
		
		Case False=null;
		
		assertTrue(PlateauATroisCotes.trouverCase(new Paire(-1,0))==False);
		assertTrue(PlateauATroisCotes.trouverCase(new Paire(-1,6))==False);
		
		for(int i=0; i<2;i++) { // nombre de colonne
			for(int j=0; j<5;j++) { // nombre de ligne
				assertTrue(PlateauATroisCotes.trouverCase(new Paire(i,j))!=False);
			}
		}
		
		assertTrue(PlateauATroisCotes.trouverCase(new Paire(2,6))==False);
		assertTrue(PlateauATroisCotes.trouverCase(new Paire(3,0))==False);
		
			
	}

	
	
	@Test
	public void affichagePlateau() {
		
		
		System.out.println(PlateauATroisCotes.toString());
		
		int nbPion=1;
		Map<Joueur,Character> assignation = new HashMap<>();
		
		// On donne les joueurs :
		// - on a son pseudo, le nombre de pion égal à un
		// - Sa représentation dans le plateau
		// - Sa main, c'est à dire la liste des pions qu'il possède
		
		Joueur un = new Joueur("Gerard", nbPion);
		assignation.put(un, '#');
	//	DeckPions mainJoueurUn = new DeckPions(nbPion, un);
		
		Joueur deux = new Joueur("Jean", nbPion);
		assignation.put(deux, '@');
	//	DeckPions mainJoueurDeux = new DeckPions(nbPion, deux);
		
		Joueur trois = new Joueur("Rima", nbPion);
		assignation.put(trois, '$');
	//	DeckPions mainJoueurTrois = new DeckPions(nbPion, trois);
		
		// représentation de base du plateau :
		affichage.affichagePlateau(NMBREDECOTE, assignation);

	//	mainJoueurUn.addPions(new ChifumiPion(un, 2, 0));
		//if(PlateauATroisCotes.getListSommet().get(PlateauATroisCotes.retrouverIndiceCase(new Paire(2, 0))).addPion(new ChifumiPion(un, 2, 0))) {
		//	PlateauATroisCotes.affichagePlateau(NMBREDECOTE, assignation);
		//}
		
		
	}


	@Test
	public void testVictoire() {
		
		
		/* TODO séparer par bloc, l'alignement intra-couche et inter-couche
		 * Il faut tester toutes les possibilités
		 * pour q'un joueur réussisse et gagne :
		 * - aligné sur une même couche donc :
		 * qu'il soit aligné sur un même côté ou non
		 * - aligné sur différentes couches. 
		 * C'est à dire quand, elle est le pion du milieu sur chaque côté des polygones.
		 * On utilisera les pions suivants (pour le plateau à trois côtés)
		 * True :
		 * ( 2;5 && 1;5 && 0;5 ) | (1;0 && 1;1 && 1;2) | (2;5 && 2;4 && 2;3)
		 * False
		 * ( 2;4 && 1;4 && 0;4 ) | (2;3 && 1;3 && 1;2) | (2;2 && 2;1 && 1;1)
		 * 
		 */
		
		Joueur joueurGagnant = new Joueur("gagnant", 3);
		
	//	Case sommet1 = new Case(2,2);
	//	sommet1.addPion(joueurGagnant.getPion());
	//	Case sommet2 = new Case(1,2);
	//	sommet2.addPion(joueurGagnant.getPion());
	//	Case sommet3 = new Case(0,2);
	//	sommet3.addPion(joueurGagnant.getPion());
		
			
		//assertEquals(joueurGagnant,PlateauPolynomial.);
		
		
		
	}

	
}
