package lineup_3;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Permet la lecture et l'utilisation des graphes avec un ordinateur.
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 */
public class Graphe {
	private String name;
	private GrapheType type;
	private List<String> nodes;
	private Map<String, List<String>> edges;
	
	
	
	/**
	 * Constructeur complètement spécifié.
	 * @param name
	 * @param type
	 * @param nodes
	 * @param edges
	 */
	public Graphe(String name, GrapheType type, List<String> nodes, Map<String, List<String>> edges) {
		this.name = name;
		this.type = type;
		this.nodes = nodes;
		this.edges = edges;
	}
	
	
	
	/** Permet d'obtenir le nom du graphe */
	public String getName() {
		return this.name;
	}
	
	/** Change le nom du graphe */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Permet d'obtenir le type du graphe */
	public GrapheType getType() {
		return this.type;
	}
	
	/** Permet d'obtenir la liste des sommets */
	public List<String> getNodes() {
		return this.nodes;
	}
	
	/** Permet d'obtenir les arrêtes du graphe */
	public Map<String, List<String>> getEdges() {
		return this.edges;
	}
	
	
	
	/** Donne la possibilité d'ajouter un sommet au graphe. */
	public boolean addNode(String name) {
		if (this.nodes.contains(name)) // si le nom existe déjà
			return false;
		else {
			this.nodes.add(name);
			this.edges.put(name, new ArrayList<String>());
			return true;
		}
	}
	
	
	
	/** Renomme un sommet */
	public void renameNode(String old, String save) {
		
	}
	
	
	
	/**
	 * Permet d'ajouter une arrête au graphe.<br>
	 * Dans le cas d'un graphe orienté, l'arrête part du sommet s1 et se dirige vers le sommet s2.
	 */
	public boolean addEdge(String s1, String s2) {
		if (s1.equals(s2) || !this.nodes.contains(s1) || !this.nodes.contains(s2)) // si les sommets donnés sont identiques ou qu'ils n'existent pas
			return false;
		else {
			if (!this.edges.get(s1).contains(s2)) // si la destination n'existe pas déjà
				this.edges.get(s1).add(s2);
			
			if (!this.type.isDirected() && !this.edges.get(s2).contains(s1)) // si le graphe n'est pas dirigé et que la destination n'existe pas déjà
				this.edges.get(s2).add(s1);
			return true;
		}
			
	}
	
	
	
	/***
	 * Renvoie une chaîne de caractères de la forme suivante :<br>
	 * <br>
	 * # Nom du graphe<br>
	 * type  : UGRAPH<br>
	 * nodes : [a, b, c, d]<br>
	 * edges :<br>
	 *  - a : [b, c]<br>
	 *  - b : [a, c, d]<br>
	 *  - c : [a, b]<br>
	 *  - d : [b]<br>
	 */
	@Override
	public String toString() {
		String arretes = "";
		
		for (String s : this.edges.keySet())
			arretes += "\n - " + s + " : " + this.edges.get(s).toString();
		
		return "# " + this.name + "\n"
			 + "type  : " + this.type + "\n"
			 + "nodes : " + this.nodes.toString() + "\n"
			 + "edges : " + arretes;
	}
	
	
	
	public static void main(String[] args) {
		List<String> sommets = new ArrayList<>();
		sommets.add("A");
		sommets.add("B");
		sommets.add("C");
		sommets.add("D");
		
		Map<String, List<String>> arretes = new HashMap<String, List<String>>();
		for (String s : sommets)
			arretes.put(s, new ArrayList<String>());
		
		Graphe G = new Graphe("Plateau", GrapheType.UGRAPH, sommets, arretes);

		G.addNode("E");
		G.addEdge("A", "B");
		G.addEdge("A", "C");
		G.addEdge("A", "D");
		
		System.out.println(G.toString());
	}
}
