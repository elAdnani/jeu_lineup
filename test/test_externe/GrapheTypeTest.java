package test_externe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import outils.GrapheType;

public class GrapheTypeTest {
	GrapheType uType   = GrapheType.UGRAPH;
	GrapheType diType  = GrapheType.DIGRAPH;
	GrapheType uwType  = GrapheType.UWGRAPH;
	GrapheType diwType = GrapheType.DIWGRAPH;
	
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Début des tests pour la classe GrapheType\n");
	}

	@AfterAll
	public static void afterAllTests() {
		System.out.println("Fin des tests de GrapheType");
	}

	@AfterEach
	public void afterATest() {
		System.out.println("___ end ___\n");
	}

	@Test
	public void testIsDirected() {
		System.out.println("testIsDirected");
		
		assertFalse(uType.isDirected());
		assertTrue(diType.isDirected());
		assertFalse(uwType.isDirected());
		assertTrue(diwType.isDirected());
	}

	@Test
	public void testIsWeighted() {
		System.out.println("testIsWeighted");
		
		assertFalse(uType.isWeighted());
		assertFalse(diType.isWeighted());
		assertTrue(uwType.isWeighted());
		assertTrue(diwType.isWeighted());		
	}
}
