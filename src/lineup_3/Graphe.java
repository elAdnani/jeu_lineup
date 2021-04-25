package lineup_3;
// TODO gérer le cas des graphes avec poids
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Permet la lecture et l'utilisation des graphes avec un ordinateur.
 * 
 * @see java.util.List 
 * @see {@link java.util.List}
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 */
public class Graphe {
	private static int cpt = 0;
	private String name;
	private GrapheType type;
	private List<String> nodes; // TODO uitiliser Set<> qui évite les doublons
	private Map<String, List<String>> edges;
	
	
	
	/**
	 * Constructeur complètement spécifié.
	 * @param name
	 * @param type
	 * @param nodes
	 * @param edges
	 */
	public Graphe(String name, GrapheType type, List<String> nodes, Map<String, List<String>> edges) {
		if (name == null)
			this.name = "Graphe";
		else
			this.name = name;
		
		if (type == null)
			this.type = GrapheType.UGRAPH;
		else
			this.type = type;
		
		boolean valide = nodes != null && edges != null && !nodes.isEmpty() && !edges.isEmpty();
		
		if (valide) {
			for (String s : nodes)
				valide = valide && nodes.indexOf(s) == nodes.lastIndexOf(s); // on vérifie que chaque sommet est bien unique
		
			for (String s : edges.keySet()) {
				for (String e : edges.get(s))
					valide = valide && edges.get(s).indexOf(e) == edges.get(s).lastIndexOf(e);
				valide = valide; // TODO vérifier que tous les sommets donnés sont présents et qu'il n'y a qu'eux.
			}
			
			this.nodes = nodes;
			this.edges = edges;
		} else {
			this.nodes = new ArrayList<String>();
			this.edges = new HashMap<String, List<String>>();
		}
	}
	
	/**
	 * Constructeur ne nécessitant que le nom et le type du graphe.
	 * @param name
	 * @param type
	 */
	public Graphe(String name, GrapheType type) {
		this(name, type, null, null);
	}
	
	/**
	 * Contructeur qui crée un graphe non orienté dont le nom est donné en paramètres
	 * @param name
	 */
	public Graphe(String name) {
		this(name, null);
	}
	
	public Graphe() {
		this("Graphe n°" + ++cpt);
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
		if (name == null || name.isBlank() || this.nodes.contains(name)) // si le nom existe déjà ou s'il est vide
			return false;
		else {
			this.nodes.add(name);
			this.edges.put(name, new ArrayList<String>());
			return true;
		}
	}	
	
	/** Permet de renommer un sommet du graphe */
	public boolean renameNode(String before, String after) {
		if (after != null && !after.isBlank() && this.nodes.contains(before) && !this.nodes.contains(after)) {
			
			// recrée la liste du sommet
			List<String> save = this.edges.remove(before);
			this.edges.put(after, save);
			
			// Modifie l'ancien élément dans chacune des listes de sommets
			for (String s : this.edges.keySet())
				if (this.edges.get(s).remove(before))
					this.edges.get(s).add(after);
			
			// renomme le sommet dans la liste des sommets
			this.nodes.remove(before);
			this.nodes.add(after);
			
			return true;
		} else
			return false;
	}
	
	/** Supprime le sommet donné */
	public boolean removeNode(String name) {
		if (this.nodes.contains(name)) {
			for (String s : this.edges.keySet())
				this.edges.get(s).remove(name);
			this.edges.remove(name);
			this.nodes.remove(name);
			
			return true;
		} else
			return false;
	}
	
	
	
	/**
	 * Permet d'ajouter une arrête au graphe.<br>
	 * Dans le cas d'un graphe orienté, l'arrête part du sommet s1 et se dirige vers le sommet s2.
	 */
	public boolean addEdge(String s1, String s2) {		
		if (s1 == null || s2 == null || s1.equals(s2) || !this.nodes.contains(s1) || !this.nodes.contains(s2) || this.edges.get(s1).contains(s2)) // si les sommets donnés sont identiques, que l'un n'existe pas ou que l'arrête existe déjà.
			return false;
		else {
			this.edges.get(s1).add(s2);
			
			if (!this.type.isDirected())
				this.edges.get(s2).add(s1);
			
			return true;
		}	
	}	
	
	/** Supprime une arrête entre deux sommets. */
	public boolean removeEdge(String s1, String s2) {
		if (s1 == null || s2 == null || s1.equals(s2) || !this.nodes.contains(s1) || !this.nodes.contains(s2)) // si les sommets donnés sont identiques ou qu'ils n'existent pas
			return false;
		else {
			boolean reussite = this.edges.get(s1).remove(s2);
			
			if (!this.type.isDirected()) // si le graphe n'est pas dirigé
				reussite = this.edges.get(s2).remove(s1);
			
			return reussite;
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
		G.addEdge("A", "G");
		G.addEdge("A", "D");
		G.addEdge("D", "A");
		G.removeEdge("A", "D");
		G.removeNode("D");
		G.renameNode("B", "BB");
		
		System.out.println(G.toString());

//		Graphe G1 = new Graphe();
//		Graphe Ga = new Graphe("a");
//		Graphe G2 = new Graphe();
//		Graphe G3 = new Graphe();
//
//		System.out.println(G1 + "\n");
//		System.out.println(Ga + "\n");
//		System.out.println(G2 + "\n");
//		System.out.println(G3);
	}
}