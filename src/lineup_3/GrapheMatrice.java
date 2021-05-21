package lineup_3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Un graphe est un ensemble de sommets. Ceux-ci peuvent être connectés entre eux par des arrêtes. Ces arrêtes peuvent avoir une direction, pouvant ainsi rendre impossible le passage (en sens inverse) entre deux sommets.
 * Cette classe offre donc la possibilité de créer des graphes, de leur ajouter ou supprimer des sommets et des arrêtes
 * <br>
 * Lors de la création du graphe, les constructeurs offrent la possibilité de :
 * <ul>
 * 	<li>Créer un graphe vide, c'est à dire avec aucun sommet et une matrice vide.</li>
 * 	<li>Créer un graphe avec uniquement des sommets, selon les sommets donnés, une matrice nulle aux dimensions adéquats est créée.</li>
 * 	<li>Créer un graphe complètement spécifié</li>
 * </ul>
 * Par défaut le graphe est non orienté, mais si le premier paramètre précise un type, alors il le respectera.
 * Pour le constructeur complètement spécifié, il détectera le type de graphe grâce à la matrice et pourra donc sélectionner seul son type.
 * <br>
 * Les getters fournis permettent de récupérer la liste de sommets et la matrice d'adjacence du graphe, pratique pour les utiliser dans un constructeur complètement spécifié.
 * Les setters eux sont bloqués. C'est pour éviter toute modification non souhaitée, ainsi il faut passer par les méthodes de modification.
 * <br>
 * Les méthodes fournies permettent d'ajouter ou de supprimer des sommets ou des arrêtes.
 * D'autres méthodes servent à vérifier si le graphe est orienté et/ou pondéré.
 * Si deux sommets sont reliés entre eux
 * <br>
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 * @param <T>
 */
public class GrapheMatrice<T> {
	/* ATTRIBUTS ______________________________ */
		/**
		 * Spécifie le type du graphe.
		 * Influence également l'ajout et la suppression des arrêtes lorsque le graphe est orienté ou non.
		 * Cet attribut est modifié lorsqu'un poids se rajoute sur une arrête afin de spécifier que le graphe est pondéré.
		 */
		private GrapheType type;
		
		/**
		 * Regroupe l'ensemble des sommets du graphe dans une pile.
		 * Ceux-ci sont triés par ordre d'ajout afin de correspondre à la matrice d'adjacence.
		 */
		private List<T> sommets;
		
		/**
		 * Matrice d'adjacence du graphe.
		 * Elle contient les informations essentielles sur le graphe.
		 * Grâce à cette matrice (carrée aux dimensions du nombre de sommets), nous pouvons savoir si le graphe est orienté, pondéré ou pas.
		 * En prenant une case quelconque, afin de l'interpréter, il faut premièrement regarder le sommet correspondant à sa colonne et se dire que celui-ci se dirige vers le sommet correspondant à la ligne.
		 */
		private Matrice matrice;
	
	
	
	
	
	/* CONSTRUCTEURS ______________________________ */
		/**
		 * Ce constructeur sans paramètres va créer un graphe nul, sans sommet.
		 * Par défaut, il ne sera ni orienté ni pondéré.
		 */
		public GrapheMatrice() {
			this(GrapheType.UGRAPH);
		}
		
		/**
		 * Ce constructeur avec pour seul paramètre un type de graphe va créer un graphe nul dont le type sera celui donné en paramètres
		 * 
		 * @param type Précision du type de graphe à construire
		 */
		public GrapheMatrice(GrapheType type) {
			this(type, null);
		}
		
		/**
		 * Ce constructeur créé un graphe ayant pour sommets ceux spécifiés en paramètres
		 * 
		 * @param sommets Contient les sommets à insérer dans le graphe
		 */
		public GrapheMatrice(Set<T> sommets) {
			this(GrapheType.UGRAPH, sommets);
		}
		
		/**
		 * Ce constructeur créé un graphe ayant pour sommets ceux spécifiés en paramètres et ayant pour type, celui donné en premier paramètre.
		 * 
		 * @param type Précision du type de graphe à construire
		 * @param sommets Contient les sommets à insérer dans le graphe
		 */
		public GrapheMatrice(GrapheType type, Set<T> sommets) {
			// Attribut du type
			if (type.isDirected())
				this.type = GrapheType.DIGRAPH;
			
			// Attribut des sommets
			this.sommets = new ArrayList<T>();
			for (T t : sommets)
				this.sommets.add(t);
			
			// Attribut de la matrice
			this.matrice = Matrice.nulle(sommets.size(), sommets.size());
		}
		
		/**
		 * Ce constructeur créé un graphe ayant pour sommets ceux spécifiés en paramètres et leurs arrêtes de précisées par la matrice d'adjacence.
		 * Il est conseillé de l'utiliser à partir du contenu d'un autre graphe afin d'éviter de commettre des erreurs.
		 * 
		 * @param sommets Contient les sommets à insérer dans le graphe
		 * @param matrice Contient la matrice d'adjacence
		 */
		public GrapheMatrice(List<T> sommets, Matrice matrice) {
			// dans les getters, une copie est déjà faite, ce ne sont donc pas les mêmes listes et matrices
			this.sommets = sommets;
			this.matrice = matrice;
			
			this.type = GrapheType.UGRAPH;
			
			if (!matrice.estSymetrique())
				this.type = GrapheType.DIGRAPH;
			
			int i = 0;
			
			while (!this.type.isWeighted() && i < matrice.getTaille().getX()) {
				int j = 0;
				
				while (!this.type.isWeighted() && j < matrice.getTaille().getY()) {
					if (matrice.read(i, j) > 1 || matrice.read(i, j) < 0) {
						if (this.type.isDirected())
							this.type = GrapheType.DIWGRAPH;
						else
							this.type = GrapheType.UWGRAPH;
					}
					++j;
				}
				++i;
			}
		}
		
		
		
		
		
	/* ACCESSEURS ______________________________ */
		/**
		 * Récupère le type du graphe
		 * 
		 * @return Le type de graphe
		 */
		public GrapheType getType() {
			return type;
		}
		
		/**
		 * Donne la liste des sommets composant le graphe
		 * 
		 * @return La liste des sommets
		 */
		public List<T> getSommets() {
			List<T> contenu = new ArrayList<T>();
			
			for (int i = 0; i < this.sommets.size(); i++)
				contenu.add(i, this.sommets.get(i));
			
			return contenu;
		}
		
		/**
		 * Donne la matrice d'adjacence du graphe
		 * 
		 * @return La matrice d'adjacence
		 */
		public Matrice getMatrice() {
			return new Matrice(this.matrice.getMatrice());
		}
		
		
		
		
		
	/* toString ______________________________ */
		/**
		 * TODO doc de toString
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
		
		
		
		
		
	/* MÉTHODES ______________________________ */
		/**
		 * Ajoute un sommet au graphe.
		 * Ne fonctionne pas si celui-ci existe déjà.
		 * 
		 * @param s Le nouveau sommet
		 * @return true si l'opération s'est bien passée et false sinon
		 */
		public boolean ajouterSommet(T s) {
			// si le sommet proposé n'existe pas et qu'il n'est pas nul alors on l'ajoute à la pile de sommets.
			if (s == null || this.sommets.contains(s))
				return false;
			else {
				this.sommets.add(s);
				return true;
			}
		}
		
		/**
		 * Enlève un certain sommet au graphe.
		 * Il faut que celui-ci existe.
		 * 
		 * @param s Le sommet à enlever
		 * @return true si le sommet a bien été enlevé et false sinon
		 */
		public boolean enleverSommet(T s) {
			if (s == null || !this.sommets.contains(s))
				return false;
			else {
				this.sommets.remove(s);
				
				return true;
			}
		}
		
		/**
		 * Ajoute une arrête entre deux sommets donnés.
		 * Pour que cela réussisse, il faut évidemment que les deux sommets existent mais aussi que l'arrête ne soit pas encore créée.
		 * 
		 * @param s1 Un sommet duquel faire partir l'arrête.
		 * @param s2 Un sommet vers lequel se dirige l'arrête.
		 * @return true si l'ajout a pu se faire et false sinon.
		 */
		public boolean ajouterArrete(T s1, T s2) {
			if (s1 == null || s2 == null || !this.sommets.contains(s1) || !this.sommets.contains(s2)) // TODO ajouter une condition s'il existe pas déjà une arrête
				return false;
			else {
				this.matrice.write(this.sommets.indexOf(s1), this.sommets.indexOf(s1), 1);
				
				if (!type.isDirected())
					this.matrice.write(this.sommets.indexOf(s2), this.sommets.indexOf(s1), 1);
				
				return true;
			}
		}
		
		/**
		 * Ajoute une arrête avec un certain poids entre deux sommets donnés.
		 * Pour que cela réussisse, il faut évidemment que les deux sommets existent, que le poids soit non nul. Mais aussi que l'arrête ne soit pas encore créée.
		 * 
		 * @param s1 Un sommet duquel faire partir l'arrête.
		 * @param s2 Un sommet vers lequel se dirige l'arrête.
		 * @param valeur Le poids à donner à l'arrête.
		 * @return true si l'ajout a pu se faire et false sinon.
		 */
		public boolean ajouterArrete(T s1, T s2, int valeur) {
			if (s1 == null || s2 == null || !this.sommets.contains(s1) || !this.sommets.contains(s2)) // TODO ajouter une condition s'il existe pas déjà une arrête
				return false;
			else {
				if (!this.type.isWeighted() || valeur == 0)
					valeur = 1;
				
				this.matrice.write(this.sommets.indexOf(s1), this.sommets.indexOf(s1), valeur);
				
				if (!type.isDirected())
					this.matrice.write(this.sommets.indexOf(s2), this.sommets.indexOf(s1), valeur);
				
				return true;
			}
		}
		
		// TODO enleverArrete
		
		// TODO existeArrete
		
		// TODO voisinsDe
		
		// TODO estDirige
		
		// TODO estPondere
		
	
	
	
	
	
	/* Test d'algorithmes pour les méthodes */
	public static void main(String[] args) {
//		Map<Integer, String> map = new HashMap<Integer, String>();
//
//		map.put(map.size(), "a");
//		map.put(map.size(), "b");
//		map.put(map.size(), "c");
//		System.out.println(map);
//		
//		// vérifier si la valeur existe déjà et si non, l'ajoute
//		if (!map.containsValue("a"))
//			map.put(map.size(), "a");
//		System.out.println(map);
//		
//		// supprime une valeur en décalant les indices
//		int i = 0;
//		while (!map.get(i).equals("b"))
//			++i;
//		map.remove(i);
//		for (int j = i; j < map.size(); j++) {
//			map.put(j, map.get(j + 1));
//			map.remove(j + 1);
//		}
//		map.put(map.size(), "a");
//		map.put(map.size(), "b");
//		map.put(map.size(), "c");
//		
//		
//		
//		Collection<String> col = map.values();
//		System.out.println(col);
//		col.remove("a");
//		System.out.println(col);
//		System.out.println(col.contains("a"));
		
		List<String> maListe = new ArrayList<>();

		maListe.add("b");
		maListe.add("a");
		maListe.add("c");
		maListe.add("d");
		maListe.add("e");
		
		System.out.println(maListe.indexOf("a"));
		System.out.println(maListe.toString());
	}
}
