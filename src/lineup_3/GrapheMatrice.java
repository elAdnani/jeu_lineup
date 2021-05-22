package lineup_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * @param <T> Un type quelconque à placer aux sommets du graphe.
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
			this.type = type;
			
			// Attribut des sommets
			this.sommets = new ArrayList<T>();
			if (sommets != null) {
				for (T t : sommets)
					this.sommets.add(t);
			
				// Attribut de la matrice
				matrice = new Matrice(sommets.size());
			} else {
				matrice = new Matrice(0);
			}
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
			
			for (int i = 0; i < sommets.size(); i++)
				contenu.add(i, sommets.get(i));
			
			return contenu;
		}
		
		/**
		 * Donne la matrice d'adjacence du graphe
		 * 
		 * @return La matrice d'adjacence
		 */
		public Matrice getMatrice() {
			return new Matrice(matrice.getMatrice());
		}
		
		
		
		
		
	/* TOSTRING ______________________________ */
		/**
		 * Représente sous la forme d'une chaîne de caractères le graphe créé.
		 * De la forme suivante :
		 * 
		 * # GrapheMatrice<br>
		 * type  : UGRAPH<br>
		 * nodes : [a, b, c, d]<br>
		 * edges :<br>
		 *  - a : [b, c]<br>
		 *  - b : [a, c, d]<br>
		 *  - c : [a, b]<br>
		 *  - d : [b]<br>
		 * 
		 * @return Une chaîne de caractères
		 */
		@Override
		public String toString() {
			String arretes = "";
			
			if (estPondere())
				for (T s1 : sommets) {
					arretes += "\n - " + s1 + " : [";
					for (T s2 : enfantsDe(s1))
						arretes += s2 + " ("+ matrice.read(sommets.indexOf(s1), sommets.indexOf(s2)) + "), ";
					arretes = arretes.substring(0, arretes.length() - 2);
					arretes += "]";
				}
			else
				for (T s : sommets)
					arretes += "\n - " + s + " : " + enfantsDe(s);
			
			return "# GrapheMatrice\n"
				 + "type  : " + type + "\n"
				 + "nodes : " + sommets.toString() + "\n"
				 + "edges : " + arretes;
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
			if (s == null || existeSommet(s))
				return false;
			else {
				sommets.add(s);
				
				if (matrice.getTaille().getX() < sommets.size()) {
					Matrice oldMatrice = new Matrice(matrice.getMatrice());
					matrice = new Matrice(sommets.size());
					
					for (int i = 0; i < oldMatrice.getTaille().getX(); i++)
						for (int j = 0; j < oldMatrice.getTaille().getY(); j++)
							matrice.write(i, j, oldMatrice.read(i, j));
				}
				
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
			if (s == null || !existeSommet(s))
				return false;
			else {
				int index = sommets.indexOf(s);
				sommets.remove(s);
				
				Matrice oldMatrice = new Matrice(matrice.getMatrice());
				matrice = new Matrice(sommets.size());
				
				if (sommets.size() > 0) {
					int ligne = 0;
					for (int i = 0; i < oldMatrice.getTaille().getX(); i++) {

						int colonne = 0;
						for (int j = 0; j < oldMatrice.getTaille().getY(); j++) {
							matrice.write(ligne, colonne, oldMatrice.read(i, j));
							if (j != index) {
								colonne++;
							}
						}
						if (i != index) {
							ligne++;
						}
					}
				}
				
				return true;
			}
		}
		
		/**
		 * Vérifie si un sommet donné existe.
		 * 
		 * @param s Le sommet à vérifier.
		 * @return true si le sommet existe et false sinon.
		 */
		public boolean existeSommet(T s) {
			return sommets.contains(s);
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
			if (s1 == null || s2 == null || !existeSommet(s1) || !existeSommet(s2) || existeArrete(s1, s2))
				return false;
			else {
				matrice.write(sommets.indexOf(s1), sommets.indexOf(s2), 1);
				
				if (!type.isDirected())
					matrice.write(sommets.indexOf(s2), sommets.indexOf(s1), 1);
				
				return true;
			}
		}
		
		/**
		 * Ajoute une arrête avec un certain poids entre deux sommets donnés.
		 * Pour que cela réussisse, il faut évidemment que les deux sommets existent, que le poids soit non nul. Mais aussi que l'arrête ne soit pas encore créée.
		 * Attention ! Cette méthode transforme le graphe en graphe pondéré !
		 * 
		 * @param s1 Un sommet duquel faire partir l'arrête.
		 * @param s2 Un sommet vers lequel se dirige l'arrête.
		 * @param valeur Le poids à donner à l'arrête.
		 * @return true si l'ajout a pu se faire et false sinon.
		 */
		public boolean ajouterArrete(T s1, T s2, int valeur) {
			if (s1 == null || s2 == null || !existeSommet(s1) || !existeSommet(s2) || existeArrete(s1, s2))
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
				
				matrice.write(sommets.indexOf(s1), sommets.indexOf(s2), valeur);
				
				if (!type.isDirected())
					matrice.write(sommets.indexOf(s2), sommets.indexOf(s1), valeur);
				
				return true;
			}
		}
		
		/**
		 * Enlève une arrête entre deux sommets donnés.
		 * 
		 * @param s1 Sommet duquel part l'arrête.
		 * @param s2 Sommet duquel arrive l'arrête.
		 * @return true si la suppression a pu se faire et false sinon.
		 */
		public boolean enleverArrete(T s1, T s2) {
			if (s1 == null || s2 == null || !existeSommet(s1) || !existeSommet(s2) || !existeArrete(s1, s2))
				return false;
			else {
				matrice.write(sommets.indexOf(s1), sommets.indexOf(s2), 0);
				
				if (!type.isDirected())
					matrice.write(sommets.indexOf(s2), sommets.indexOf(s1), 0);
				
				return true;
			}
		}
		
		/**
		 * Vérifie s'il existe une arrête entre deux sommets.
		 * 
		 * @param s1 sommet théorique de départ
		 * @param s2 sommet théorique d'arrivée
		 * @return true si l'arrête existe, false sinon
		 */
		public boolean existeArrete(T s1, T s2) {
			int indiceS1 = sommets.indexOf(s1);
			int indiceS2 = sommets.indexOf(s2);
			
			return indiceS1 != -1 && indiceS2 != -1 && matrice.read(indiceS1, indiceS2) != 0;
		}
		
		/**
		 * Donne un set de tous les voisins d'un sommet.
		 * 
		 * @param s Le sommet duquel chercher les voisins
		 * @return Un set null s'il y a une erreur et contenant les voisins sinon 
		 */
		public Set<T> voisinsDe(T s) {
			if (s == null || !existeSommet(s))
				return null;
			
			Set<T> voisins = new HashSet<>();
			int indiceS = sommets.indexOf(s);
			
			for (int i = 0; i < matrice.getTaille().getX(); i++) {
				if (matrice.read(indiceS, i) != 0 && indiceS != i)
					voisins.add(sommets.get(i));
			}
			
			if (type.isDirected())
				for (int i = 0; i < matrice.getTaille().getX(); i++) {
					if (matrice.read(i, indiceS) != 0 && indiceS != i)
						voisins.add(sommets.get(i));
				}
			
			return voisins;
		}
		
		/**
		 * 
		 * @param s
		 * @return
		 */
		public Set<T> parentsDe(T s) {
			if (s == null || !existeSommet(s))
				return null;
			
			Set<T> parents = new HashSet<>();
			int indiceS = sommets.indexOf(s);
			
			for (int i = 0; i < matrice.getTaille().getX(); i++) {
				if (matrice.read(i, indiceS) != 0 && indiceS != i)
					parents.add(sommets.get(i));
			}
			
			return parents;
		}
		
		/**
		 * 
		 * @param s
		 * @return
		 */
		public Set<T> enfantsDe(T s) {
			if (s == null || !existeSommet(s))
				return null;
			
			Set<T> enfants = new HashSet<>();
			int indiceS = sommets.indexOf(s);
			
			for (int i = 0; i < matrice.getTaille().getX(); i++) {
				if (matrice.read(indiceS, i) != 0 && indiceS != i)
					enfants.add(sommets.get(i));
			}
			
			return enfants;
		}
		
		/**
		 * Permet de savoir si le graphe est dirigé ou non.
		 * 
		 * @return true s'il est dirigé et false sinon.
		 */
		public boolean estDirige() {
			return type.isDirected();
		}
		
		/**
		 * Permet de savoir si le graphe est pondéré ou non.
		 * 
		 * @return true s'il est pondéré et false sinon.
		 */
		public boolean estPondere() {
			return type.isWeighted();
		}
}
