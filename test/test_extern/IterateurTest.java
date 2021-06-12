package test_extern;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lineup_3.modele.Joueur;
import lineup_3.modele.Mode;
import outils.iterateurBoucle;

/**
 * 
 * Cette classe sert à tester la fonction d'itérer de manière Infinie
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 10 juin 2021

 */
public class IterateurTest {
	
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Début des tests pour la classe iterateurBoucle\n");
		
	}
	
	
	
	@AfterAll
	public static void afterAllTests() {
		System.out.println("Fin des tests de GrapheType");
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void boucleInfinie() {
		List<Joueur> listeDesJoueurs = new ArrayList<>();
		Joueur Rima = new Joueur("Rima", 6, Mode.CLASSIQUE);
		Joueur Alexis = new Joueur("Alexis", 6, Mode.CLASSIQUE);
		 listeDesJoueurs.add(Rima);
		 listeDesJoueurs.add(Alexis);
		 Iterator<Joueur> it = new iterateurBoucle(listeDesJoueurs);
		 

		 assertEquals(it.next(),Rima);
		 assertEquals(it.next(),Alexis);
		 assertEquals(it.next(),Rima);
		 assertEquals(it.next(),Alexis);
		 assertFalse(it.next().equals(Alexis));
		 assertTrue(it.next().equals(Alexis));
		 assertFalse(it.next().equals(Alexis));
		 assertFalse(it.next().equals(Rima));
		 
	}
	
	
	@AfterEach
	public void afterATest() {
		System.out.println("___ end ___\n");
	}
	
	

}
