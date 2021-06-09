package test_lineup_3;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lineup_3.modele.AffichagePlateau;
import lineup_3.modele.Joueur;
import lineup_3.modele.Mode;
import lineup_3.modele.Nature;
import lineup_3.modele.Parametres;
import lineup_3.modele.PlateauPolynomial;
import outils.jeu.Case;

/**
 * Cette classe teste toutes les méthodes de la classe {@link Joueur}.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 1 juin 2021
 * @version 1 juin 2021 23:10:52
 */
class JoueurTest {
	
	Parametres parametre;
	PlateauPolynomial plateau;

	Joueur joueur1, joueur2;
	Map<Joueur, Character> skinPion;
	AffichagePlateau at;
	
	Case case00, case01, case02, case03, case04, case05, case10, case11, case12, case13, case20, case21;
	

	@BeforeEach
	public void initialization() {
		
		this.parametre = new Parametres(3, false, Mode.CLASSIQUE, 9);
		this.plateau = new PlateauPolynomial(this.parametre.getNBCOTE());
		
		this.joueur1 = new Joueur("joueur1", parametre.getNBPION(), parametre.getMODE());
		this.joueur2 = new Joueur("joueur2", parametre.getNBPION(), parametre.getMODE());		
		this.skinPion = new HashMap<>();
		this.skinPion.put(this.joueur1, '#');
		this.skinPion.put(this.joueur2, '$');
		
		this.at = new AffichagePlateau(this.plateau);
		
		this.case00 = new Case(0,0); 
		this.case01 = new Case(0,1); 
		this.case02 = new Case(0,2); 
		this.case03 = new Case(0,3); 
		this.case04 = new Case(0,4); 
		this.case05 = new Case(0,5); 
		this.case10 = new Case(1,0); 
		this.case11 = new Case(1,1); 
		this.case12 = new Case(1,2); 
		this.case13 = new Case(1,3); 
		this.case20 = new Case(2,0); 
		this.case21 = new Case(2,1);
		
		this.case00 = this.plateau.trouverCase(case00.getCoordonnees());
		this.case01 = this.plateau.trouverCase(case01.getCoordonnees());
		this.case02 = this.plateau.trouverCase(case02.getCoordonnees());
		this.case03 = this.plateau.trouverCase(case03.getCoordonnees());
		this.case04 = this.plateau.trouverCase(case04.getCoordonnees());
		this.case05 = this.plateau.trouverCase(case05.getCoordonnees());
		this.case10 = this.plateau.trouverCase(case10.getCoordonnees());
		this.case11 = this.plateau.trouverCase(case11.getCoordonnees());
		this.case12 = this.plateau.trouverCase(case12.getCoordonnees());
		this.case13 = this.plateau.trouverCase(case13.getCoordonnees());
		this.case20 = this.plateau.trouverCase(case20.getCoordonnees());
		this.case21 = this.plateau.trouverCase(case21.getCoordonnees());
	}

	@AfterEach
	public void afterATest() {
		for (Case cases : plateau.getListeCase()) {
			cases.retirerPion();
		}
		
		joueur1 = new Joueur("joueur1", parametre.getNBPION(), parametre.getMODE());
		joueur2 = new Joueur("joueur2", parametre.getNBPION(), parametre.getMODE());
	}
	
						/*On vérifie que la méthode compterPions de Joueur fonctionne*/
	@Test
	public void compterPionsTest() {
		assertEquals(joueur1.compterPions(), 9);
		joueur1.poserPion(case00, plateau, parametre.getNBCOUCHE(), parametre.getNBCOTE(), Nature.CLASSIQUE);
		assertEquals(joueur1.compterPions(), 8);
		assertNotEquals(joueur1.compterPions(), 1);
	}
	
						/*On vérifie la détection d'un alignement en posant plusieurs Pion et en créant un alignement*/
	@Test
	public void alignementSurPoseIntraCoucheTest() {
		/*Sur une pose de Pion intra-couche*/
		assertFalse(joueur1.poserPion(this.case00, plateau, parametre.getNBCOUCHE(), parametre.getNBCOTE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(this.case01, plateau, parametre.getNBCOUCHE(), parametre.getNBCOTE(), Nature.CLASSIQUE));
		assertTrue(joueur1.poserPion(this.case02, plateau, parametre.getNBCOUCHE(), parametre.getNBCOTE(), Nature.CLASSIQUE));
	}
	
	@Test
	public void alignementSurPoseInterCoucheTest() {
		/*Sur une pose de Pion inter-couche*/
		assertFalse(joueur1.poserPion(this.case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(this.case11, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertTrue(joueur1.poserPion(this.case21, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
	}
	
	@Test
	public void alignementSurDeplacementIntraCoucheTest() {
		/*Sur un déplacement intra-couche*/
		assertFalse(joueur1.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case11, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case13, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertTrue(joueur1.deplacerPion(case13.getPion(), "gauche", plateau, parametre.getNBCOUCHE(), parametre.getNBCOTE()));
	}
	
	@Test
	public void alignementSurDeplacementInterCoucheTest() {
		/*Sur un déplacement inter-couche*/
		assertFalse(joueur1.poserPion(case00, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case01, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertTrue(joueur1.deplacerPion(case10.getPion(), "droite", plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE()));
	}
	
	@Test
	public void nonAlignementSurCaseNonLiees() {
		/*On s'assure que les Case sans arêtes ne forment pas d'alignement*/
		assertFalse(joueur1.poserPion(case20, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case00, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
	}
	
						/*On aligne maintenant trois Pions de différents Joueurs et on vérifie la non-detection de l'alignement*/
	@Test
	public void nonAlignementSurPoseIntraCouche() {
		/*Sur une pose de Pion intra-couche*/
		assertFalse(joueur1.poserPion(case11, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur2.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case12, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
	}
	
	@Test
	public void nonAlignementSurPoseInterCouche() {
		/*Sur une pose de Pion inter-couche*/
		assertFalse(joueur2.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case11, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur2.poserPion(case21, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
	}
	
	@Test
	public void nonAlignementSurDeplacementIntraCouche() {
		/*Sur un déplacement intra-couche*/
		assertFalse(joueur1.poserPion(case11, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur2.poserPion(case13, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur2.deplacerPion(case13.getPion(), "gauche", plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE()));
	}
	
	@Test
	public void nonAlignementSurDeplacementInterCouche () {
		/*Sur un déplacement inter-couche*/
		assertFalse(joueur1.poserPion(case00, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur2.poserPion(case01, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.poserPion(case10, plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE(), Nature.CLASSIQUE));
		assertFalse(joueur1.deplacerPion(case10.getPion(), "droite", plateau, parametre.getNBCOTE(), parametre.getNBCOUCHE()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}