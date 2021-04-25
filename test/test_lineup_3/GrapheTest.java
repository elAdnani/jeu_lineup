package test_lineup_3;

import lineup_3.Graphe;
import lineup_3.GrapheType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrapheTest {
	@BeforeAll
	public static void beforeAllTests() {
		System.out.println("Début des tests pour la classe Graphe\n");
		Graphe uG;
		Graphe diG;
		Graphe uwG;
		Graphe diwG;
	}

	@AfterAll
	public static void afterAllTests() {
		System.out.println("Fin des tests de Graphe");
	}

	@BeforeEach
	public void beforeATest() {
		uG   = new Graphe("Undirected Graph"         , GrapheType.UGRAPH);
		diG  = new Graphe("Directed Graph"           , GrapheType.DIGRAPH);
		//uwG  = new Graphe("Undirected Weighted Graph", GrapheType.UWGRAPH);
		//diwG = new Graphe("Directed Weighted Graph"  , GrapheType.DIWGRAPH);
	}

	@AfterEach
	public void afterATest() {
		System.out.println("___ end ___\n");
	}

	@Test
	public void testAddNode() {
		System.out.println("testAddNode");
		
		assertTrue( uG.addNode("A"));
		assertTrue( uG.addNode("B"));
		assertFalse(uG.addNode("A"));
		assertTrue( uG.addNode("C"));
		assertFalse(uG.addNode("B"));
		assertFalse(uG.addNode(null));
		assertFalse(uG.addNode(""));
		assertFalse(uG.addNode(" "));
		assertFalse(uG.addNode("\t"));
	}

	@Test
	public void testRenameNode() {
		System.out.println("testRenameNode");

		uG.addNode("A");
		uG.addNode("B");

		assertTrue( uG.renameNode("A" , "AA"));
		assertFalse(uG.renameNode("A" , "AA"));
		assertTrue( uG.renameNode("AA", "A" ));
		assertFalse(uG.renameNode("A" , "B" ));
		assertFalse(uG.renameNode("A" , "A" ));
		assertFalse(uG.renameNode("A" , null));
		assertFalse(uG.renameNode("A" , ""  ));
		assertFalse(uG.renameNode("A" , "  "));
		assertFalse(uG.renameNode("A" , "\t"));
	}

	@Test
	public void testRemoveNode() {
		System.out.println("testRemoveNode");

		uG.addNode("A");
		uG.addNode("B");
		
		assertTrue( uG.removeNode("A"));
		assertFalse(uG.removeNode("A"));
		assertFalse(uG.removeNode("C"));
		assertTrue( uG.removeNode("B"));
		assertFalse(uG.removeNode(null));
		assertTrue( uG.getNodes().isEmpty() && uG.getEdges().isEmpty());
	}

	@Test
	public void testAddEdge() {
		System.out.println("testAddEdge");
		
		uG.addNode("A");
		uG.addNode("B");
		uG.addNode("C");

		assertTrue( uG.addEdge("A" , "B"));
		assertFalse(uG.addEdge("A" , "B"));
		assertFalse(uG.addEdge("B" , "A"));
		assertFalse(uG.addEdge("A" , "A"));
		assertFalse(uG.addEdge("A" , "D"));
		assertFalse(uG.addEdge("D" , "A"));
		assertFalse(uG.addEdge("D" , "E"));
		assertTrue( uG.addEdge("B" , "C"));
		assertFalse(uG.addEdge(null, "B"));
		assertFalse(uG.addEdge("A" , null));
		assertFalse(uG.addEdge(""  , "B"));
		assertFalse(uG.addEdge("A" , "yftuyin,;l"));

		diG.addNode("A");
		diG.addNode("B");
		diG.addNode("C");
		
		assertTrue( diG.addEdge("A" , "B"));		
		assertFalse(diG.addEdge("A" , "B"));
		assertTrue( diG.addEdge("B" , "A"));
		assertFalse(diG.addEdge("A" , "A"));
		assertFalse(diG.addEdge("A" , "D"));
		assertFalse(diG.addEdge("D" , "A"));
		assertFalse(diG.addEdge("D" , "E"));
		assertTrue( diG.addEdge("B" , "C"));
		assertFalse(diG.addEdge(null, "B"));
		assertFalse(diG.addEdge("A" , null));
		assertFalse(diG.addEdge(" " , "B"));
		assertFalse(diG.addEdge("A" , "\t"));
	}

	@Test
	public void testRemoveEdge() {
		System.out.println("testRemoveEdge");
		
		uG.addNode("A");
		uG.addNode("B");
		uG.addNode("C");

		uG.addEdge("A", "B");
		uG.addEdge("B", "C");

		assertTrue( uG.removeEdge("A" , "B"));
		assertFalse(uG.removeEdge("A" , "B"));
		assertTrue( uG.removeEdge("C" , "B"));
		assertFalse(uG.removeEdge("A" , "D"));
		assertFalse(uG.removeEdge("D" , "B"));
		assertFalse(uG.removeEdge("D" , "E"));
		assertFalse(uG.removeEdge(null, "B"));
		assertFalse(uG.removeEdge("A" , null));
		assertFalse(uG.removeEdge(" " , "B"));
		assertFalse(uG.removeEdge("A" , "\t"));

		diG.addNode("A");
		diG.addNode("B");
		diG.addNode("C");

		diG.addEdge("A", "B");
		diG.addEdge("B", "C");
		diG.addEdge("B", "A");
		
		assertTrue( diG.removeEdge("A" , "B"));
		assertFalse(diG.removeEdge("A" , "B"));
		assertFalse(diG.removeEdge("C" , "B"));
		assertTrue( diG.removeEdge("B" , "C"));
		assertFalse(diG.removeEdge("B" , "D"));
		assertFalse(diG.removeEdge("D" , "A"));
		assertFalse(diG.removeEdge("D" , "E"));
		assertFalse(diG.removeEdge(null, "A"));
		assertFalse(diG.removeEdge("B" , null));
		assertFalse(diG.removeEdge(""  , "B"));
		assertFalse(diG.removeEdge("B" , "\t"));
	}

	@Test
	public void testToString() {
		System.out.println("testToString");
		
		uG.addNode("A");
		uG.addNode("B");
		uG.addNode("C");

		uG.addEdge("A", "B");
		uG.addEdge("B", "C");
		
		String attendu = "# Undirected Graph\n"
				       + "type  : UGRAPH\n"
					   + "nodes : [A, B, C]\n"
					   + "edges : \n"
					   + " - A : [B]\n"
					   + " - B : [A, C]\n"
					   + " - C : [B]";
		
		assertTrue(attendu.equals(uG.toString()));
		
		diG.addNode("A");
		diG.addNode("B");
		diG.addNode("C");

		diG.addEdge("A", "B");
		diG.addEdge("B", "C");
		diG.addEdge("B", "A");
		
		attendu = "# Directed Graph\n"
				+ "type  : DIGRAPH\n"
				+ "nodes : [A, B, C]\n"
				+ "edges : \n"
				+ " - A : [B]\n"
				+ " - B : [C, A]\n"
				+ " - C : []";
		
		assertTrue(attendu.equals(diG.toString()));
	}
}
