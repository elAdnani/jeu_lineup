package lineup_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Utilisation d'un graphe pouvant être pondéré et/ou dirigé grâce selon une matrice d'adjacence</p>
 * <p>
 * 	Un graphe est un ensemble de sommets.<br>
 * 	Ceux-ci peuvent être connectés entre eux par des arrêtes.<br>
 * 	Ces arrêtes peuvent avoir une direction, pouvant ainsi rendre impossible le passage (en sens inverse) entre deux sommets.<br>
 * 	Ils peuvent également avoir une valeur qui leur est propre que l'on appelle poids.
 * </p>
 * <p>
 * 	Cette classe offre donc la possibilité de créer des graphes, de leur ajouter ou enlever des sommets ou des arrêtes
 * </p>
 * <p>
 * Lors de la création du graphe, les constructeurs offrent la possibilité de :
 * <ul>
 * 	<li>Créer un graphe vide, c'est à dire avec aucun sommet et une matrice vide.</li>
 * 	<li>Créer un graphe avec uniquement des sommets, selon les sommets donnés, une matrice nulle aux dimensions adéquates est créée.</li>
 * 	<li>Créer un graphe complètement spécifié</li>
 * </ul>
 * </p>
 * <p>
 * 	Par défaut, le graphe n'est pas orienté, mais si le premier paramètre précise un type, alors il le respectera.<br>
 * 	Pour le constructeur complètement spécifié, il détectera le type de graphe grâce à la matrice et pourra donc sélectionner seul son type.
 * </p>
 * <p>
 * 	Aucun getter ni setter n'est fourni pour éviter des erreurs.<br> TODO finalement il y a un getter
 * 	Ceci oblige donc à utiliser les méthodes pour paramétrer le graphe.
 * </p>
 * <p>
 * 	Les méthodes fournies permettent d'ajouter ou de supprimer des sommets ou des arrêtes.<br>
 * 	D'autres méthodes servent à vérifier si le graphe est orienté et/ou pondéré.<br>
 * 	Si deux sommets sont reliés entre eux
 * </p>
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 * 
 * @param <T> Un type quelconque à placer aux sommets du graphe.
 */
public class GrapheMatrice<T> extends Graphe<T> {
	/* ATTRIBUTS ______________________________ */
		/**
		 * Regroupe l'ensemble des sommets du graphe dans une pile.
		 * Ceux-ci sont triés par ordre d'ajout afin de correspondre à la matrice d'adjacence.
		 */
		private List<T> ensembleSommets;
		
		/**
		 * Matrice d'adjacence du graphe.
		 * Elle contient les informations essentielles sur le graphe.
		 * Grâce à cette matrice (carrée aux dimensions du nombre de sommets), nous pouvons savoir si le graphe est orienté, pondéré ou pas.
		 * En prenant une case quelconque, afin de l'interpréter, il faut premièrement regarder le sommet correspondant à sa colonne et se dire que celui-ci se dirige vers le sommet correspondant à la ligne.
		 */
		private Matrice matrice;
	
	
	
	
	
	/* CONSTRUCTEURS ______________________________ */
		/**
		 * <p>Constructeur sans paramètres.</p>
		 * <p>Il va créer un graphe nul, sans sommet.</p>
		 * <p>Par défaut, il ne sera ni orienté ni pondéré.</p>
		 */
		public GrapheMatrice() { this(GrapheType.UGRAPH, null); }
		
		/**
		 * <p>Constructeur typé.</p>
		 * <p>Il va créer un graphe nul dont le type sera celui donné en paramètres.</p>
		 * 
		 * @param type Le type de graphe à construire.
		 */
		public GrapheMatrice(GrapheType type) { this(type, null); }
		
		/**
		 * <p>Constructeur avec un ensemble de sommets.</p>
		 * <p>Il créé un graphe ayant pour sommets ceux spécifiés dans le Set donné en paramètre.</p>
		 * 
		 * @param ensembleSommets Contient les sommets à insérer dans le graphe.
		 */
		public GrapheMatrice(Set<T> ensembleSommets) { this(null, ensembleSommets); }
		
		/**
		 * <p>Constructeur typé avec un ensemble de sommets.</p>
		 * <p>Il créé un graphe ayant pour sommets ceux spécifiés en paramètres et ayant pour type, celui donné en premier paramètre.</p>
		 * 
		 * @param type Le type de graphe à construire
		 * @param ensembleSommets Contient les sommets à insérer dans le graphe
		 */
		public GrapheMatrice(GrapheType type, Set<T> ensembleSommets) {
			// Attribut du type
			if (type == null)
				this.type = GrapheType.UGRAPH;
			else
				this.type = type;
			
			// Attribut des sommets et de la matrice
			this.ensembleSommets = new ArrayList<T>();
			if (ensembleSommets != null) {
				for (T t : ensembleSommets)
					this.ensembleSommets.add(t);
				
				matrice = new Matrice(ensembleSommets.size());
			} else
				matrice = new Matrice(0);
		}
		
		/**
		 * <p>Constructeur à partir d'un autre graphe</p>
		 * <p>Ce constructeur créé un nouveau graphe ayant les mêmes caractéristiques que celui passé en paramètres.</p>
		 * 
		 * @param reference Le graphe à copier.
		 */
		public GrapheMatrice(GrapheMatrice<T> reference) {
			type = reference.type;
			matrice = new Matrice(reference.matrice.getMatrice());
			ensembleSommets = new ArrayList<T>();
			
			for (int i = 0; i < reference.ensembleSommets.size(); i++)
				ensembleSommets.add(i, reference.ensembleSommets.get(i));
		}
		
		
		
		
		
	/* GETTER ______________________________ */
		public List<T> getSommets() {
			List<T> copieSommets = new ArrayList<>();
			
			for (T sommet : ensembleSommets)
				copieSommets.add(sommet);
			
			return ensembleSommets;
		}
		
		
		
		
		
	/* TOSTRING ______________________________ */
		/**
		 * <p>Représente sous la forme d'une chaîne de caractères le graphe créé.</p>
		 * <p>De la forme suivante :</p>
		 * <code>
		 * # GrapheMatrice<br>
		 * type  : UGRAPH<br>
		 * nodes : [a, b, c, d]<br>
		 * edges :<br>
		 *  - a : [b, c]<br>
		 *  - b : [a, c, d]<br>
		 *  - c : [a, b]<br>
		 *  - d : [b]<br>
		 *  </code>
		 * 
		 * @return Une chaîne de caractères représentant le graphe.
		 */
		@Override
		public String toString() {
			StringBuilder arretes = new StringBuilder();
			
			if (estPondere())
				for (T s1 : ensembleSommets) {
					arretes.append("\n - " + s1 + " : [");
					for (T s2 : enfantsDe(s1))
						arretes.append(s2 + " ("+ matrice.read(ensembleSommets.indexOf(s1), ensembleSommets.indexOf(s2)) + "), ");
					if (!enfantsDe(s1).isEmpty())
						arretes.delete(arretes.length() - 2, arretes.length());
					arretes.append("]");
				}
			else
				for (T s : ensembleSommets)
					arretes.append("\n - " + s + " : " + enfantsDe(s));
			
			return "# GrapheMatrice\n"
				 + "type  : " + type + "\n"
				 + "nodes : " + ensembleSommets.toString() + "\n"
				 + "edges : " + arretes;
		}
		
		
		
		
		
	/* MÉTHODES ______________________________ */
		public boolean ajouterSommet(T sommet) {
			// si le sommet proposé n'existe pas et qu'il n'est pas nul alors on l'ajoute à la pile de sommets.
			if (sommet == null || existeSommet(sommet))
				return false;
			else {
				ensembleSommets.add(sommet);
				
				if (matrice.getTaille().getX() < ensembleSommets.size()) {
					Matrice oldMatrice = new Matrice(matrice.getMatrice());
					matrice = new Matrice(ensembleSommets.size());
					
					for (int i = 0; i < oldMatrice.getTaille().getX(); i++)
						for (int j = 0; j < oldMatrice.getTaille().getY(); j++)
							matrice.write(i, j, oldMatrice.read(i, j));
				}
				
				return true;
			}
		}
		
		public boolean remplacerSommet(T actuel, T nouveau) {
			if (nouveau != null && existeSommet(actuel) && !existeSommet(nouveau)) {
				ensembleSommets.set(ensembleSommets.indexOf(actuel), nouveau);
				
				return true;
			} else
				return false;
		}

		public boolean enleverSommet(T sommet) {
			if (sommet == null || !existeSommet(sommet))
				return false;
			else {
				int index = ensembleSommets.indexOf(sommet);
				ensembleSommets.remove(sommet);
				
				Matrice oldMatrice = new Matrice(matrice.getMatrice());
				matrice = new Matrice(ensembleSommets.size());
				
				if (ensembleSommets.size() > 0) {
					int ligne = 0;
					
					for (int i = 0; i < oldMatrice.getTaille().getX(); i++) {
						int colonne = 0;
						
						for (int j = 0; j < oldMatrice.getTaille().getY(); j++) {
							matrice.write(ligne, colonne, oldMatrice.read(i, j));
							
							if (j != index)
								colonne++;
						}
						if (i != index)
							ligne++;
					}
				}
				
				return true;
			}
		}

		public boolean existeSommet(T sommet) { return ensembleSommets.contains(sommet); }
		
		public boolean ajouterArrete(T depart, T arrivee) {
			if (depart == null || arrivee == null || !existeSommet(depart) || !existeSommet(arrivee) || existeArrete(depart, arrivee))
				return false;
			else {
				matrice.write(ensembleSommets.indexOf(depart), ensembleSommets.indexOf(arrivee), 1);
				
				if (!type.isDirected())
					matrice.write(ensembleSommets.indexOf(arrivee), ensembleSommets.indexOf(depart), 1);
				
				return true;
			}
		}
		
		/**
		 * <p>Ajoute une arrête avec un poids entre deux sommets.</p>
		 * <p>Pour que cela réussisse, il faut évidemment que les deux sommets existent mais aussi que l'arrête ne soit pas encore créée.</p>
		 * <p>Si le graphe est orienté, l'arrête sera en sens unique et s'il ne l'est pas, l'arrête sera en double sens.</p>
		 * 
		 * @param depart  Un sommet duquel faire partir l'arrête.
		 * @param arrivee Un sommet vers lequel se dirige l'arrête.
		 * @param valeur  La valeur du poids
		 * 
		 * @return - true si l'ajout a pu se faire<br>
		 *         - false si rien n'a été modifié.
		 */
		public boolean ajouterArrete(T depart, T arrivee, int valeur) {
			if (depart == null || arrivee == null || depart.equals(arrivee) || !existeSommet(depart) || !existeSommet(arrivee) || existeArrete(depart, arrivee)) // 
				return false;
			else {
				if (valeur == 0)
					valeur = 1;
				
				if (!type.isWeighted()) {
					if (type.isDirected())
						type = GrapheType.DIWGRAPH;
					else
						type = GrapheType.UWGRAPH;
				}
				
				matrice.write(ensembleSommets.indexOf(depart), ensembleSommets.indexOf(arrivee), valeur);
				
				if (!type.isDirected())
					matrice.write(ensembleSommets.indexOf(arrivee), ensembleSommets.indexOf(depart), valeur);
				
				return true;
			}
		}
		
		public boolean enleverArrete(T depart, T arrivee) {
			if (depart == null || arrivee == null || depart.equals(arrivee) || !existeSommet(depart) || !existeSommet(arrivee) || !existeArrete(depart, arrivee))
				return false;
			else {
				matrice.write(ensembleSommets.indexOf(depart), ensembleSommets.indexOf(arrivee), 0);
				
				if (!type.isDirected())
					matrice.write(ensembleSommets.indexOf(arrivee), ensembleSommets.indexOf(depart), 0);
				
				return true;
			}
		}
		
		public boolean existeArrete(T depart, T arrivee) {
			int indiceDepart  = ensembleSommets.indexOf(depart);
			int indiceArrivee = ensembleSommets.indexOf(arrivee);
			
			return indiceDepart != -1 && indiceArrivee != -1 && matrice.read(indiceDepart, indiceArrivee) != 0;
		}
		
		/**
		 * Donne un set de tous les voisins d'un sommet.
		 * 
		 * @param sommet Le sommet duquel chercher les voisins
		 * 
		 * @return - Un Set des voisins du sommet<br>
		 *         - null en cas de sommet non valide
		 */
		public Set<T> voisinsDe(T sommet) {
			if (sommet == null || !existeSommet(sommet))
				return null;
			
			Set<T> voisins = new HashSet<>();
			int indiceS = ensembleSommets.indexOf(sommet);
			
			for (int i = 0; i < matrice.getTaille().getX(); i++) 
				if (matrice.read(indiceS, i) != 0 && indiceS != i)
					voisins.add(ensembleSommets.get(i));
			
			if (type.isDirected())
				for (int i = 0; i < matrice.getTaille().getX(); i++) 
					if (matrice.read(i, indiceS) != 0 && indiceS != i)
						voisins.add(ensembleSommets.get(i));
			
			return voisins;
		}
		
		/**
		 * <p>Donne un set de tous les parents d'un sommet.</p>
		 * <p>Un parent du sommet Y est un sommet X relié par une arrête partant de X et arrivant à Y<br>(parent) X -> Y (enfant)</p>
		 * 
		 * @param enfant Le sommet enfant
		 * 
		 * @return - Un Set des parents du sommet<br>
		 *         - null en cas d'enfant non valide
		 */
		public Set<T> parentsDe(T enfant) {
			if (enfant == null || !existeSommet(enfant))
				return null;
			
			Set<T> parents = new HashSet<>();
			int indiceS = ensembleSommets.indexOf(enfant);
			
			for (int i = 0; i < matrice.getTaille().getX(); i++)
				if (matrice.read(i, indiceS) != 0 && indiceS != i)
					parents.add(ensembleSommets.get(i));
			
			return parents;
		}
		
		/**
		 * <p>Donne un set de tous les enfants d'un sommet.</p>
		 * <p>Un enfant du sommet Y est un sommet X relié par une arrête partant de Y et arrivant à X<br>(enfant) X <- Y (parent)</p>
		 * 
		 * @param parent Le sommet parent
		 * @return - Un Set des enfants du sommet<br>
		 *         - null en cas de parent non valide
		 */
		public Set<T> enfantsDe(T parent) {
			if (parent == null || !existeSommet(parent))
				return null;
			
			Set<T> enfants = new HashSet<>();
			int indiceS = ensembleSommets.indexOf(parent);
			
			for (int i = 0; i < matrice.getTaille().getX(); i++)
				if (matrice.read(indiceS, i) != 0 && indiceS != i)
					enfants.add(ensembleSommets.get(i));
			
			return enfants;
		}
}
