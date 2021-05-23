package lineup_3;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>Utilisation d'un graphe non pondéré avec des listes</p>
 * <p>
 * 	Un graphe est un ensemble de sommets.<br>
 *  Ceux-ci peuvent être connectés entre eux par des arrêtes.<br>
 *  Ces arrêtes peuvent avoir une direction, pouvant ainsi rendre impossible le passage (en sens inverse) entre deux sommets.<br>
 * 	Cette classe offre donc la possibilité de créer des graphes, de leur ajouter ou supprimer des sommets et des arrêtes
 * </p>
 * <p>
 * Lors de la création du graphe, les constructeurs offrent la possibilité de :
 * <ul>
 * 	<li>Créer un graphe vide, c'est à dire avec aucun sommet ni arrête.</li>
 * 	<li>Créer un graphe avec uniquement un nom.</li>
 * 	<li>Créer un graphe avec uniquement un nom et le type du graphe.</li>
 * 	<li>Créer un graphe complètement spécifié</li>
 * </ul>
 * </p>
 * <p>
 * Par défaut le graphe est n'est pas orienté.
 * </p>
 * <p>
 * 	Les getters fournis permettent de récupérer la liste de sommets, les arrêtes et le nom du graphe.<br>
 * 	Le seul setter proposé permet de changer le nom du graphe.<br>
 * 	Les autres sont bloqués pour forcer l'utilisateur à utiliser les méthodes proposées pour ne pas casser le graphe.
 * </p>
 * <p>
 * 	Les méthodes fournies permettent d'ajouter ou de supprimer des sommets ou des arrêtes.<br>
 * 	D'autres méthodes servent à vérifier si le graphe est orienté et/ou pondéré.<br>
 * 	Si deux sommets sont reliés entre eux...
 * </p>
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 * 
 * @param <T> Un type quelconque à placer aux sommets du graphe.
 */
public class GrapheListe<T> extends Graphe<T> {
	/* ATTRIBUTS ______________________________ */
		/** Un compteur permettant de donner un nom unique au graphe créé sans paramètres */
		private static int cpt = 0;
		
		/** Le nom du graphe */
		private String nom;
		
		/** La liste de sommets du graphe. Celle-ci ne peut contenir aucun doublon. */
		private Set<T> ensembleSommets;
		
		/** Cette map regroupe toutes les arrêtes du graphe. Une arrête part de l'indice et se dirige vers chacun des sommets qui lui sont renseignés dans la liste. */
		private Map<T, Set<T>> arretes;
		
		
		
		
		
	/* CONSTRUCTEURS ______________________________ */
		/**
		 * <p>Constructeur complètement spécifié.</p>
		 * <p>Il va créer un graphe grâce aux paramètres qui lui sont donnés et vérifie leur cohérence.</p>
		 * 
		 * @param nom Le nom à donner au graphe.
		 * @param type Le type du graphe.
		 * @param ensembleSommets La liste de sommets du graphe.
		 * @param arretes Les arrêtes reliant les sommets.
		 */
		public GrapheListe(String nom, GrapheType type, Set<T> ensembleSommets, Map<T, Set<T>> arretes) {
			// Nom
			if (nom == null)
				this.nom = "Graphe n°" + ++cpt;
			else
				this.nom = nom;
			
			// Type
			if (type == null)
				this.type = GrapheType.UGRAPH;
			else
				this.type = type;
			
			// Sommets
			if (ensembleSommets == null)
				this.ensembleSommets = new HashSet<>();
			else
				this.ensembleSommets = ensembleSommets;
			
			// Arrêtes
			if (arretes == null)
				this.arretes = new HashMap<T, Set<T>>();
			else
				this.arretes = arretes;
		}
		
		/**
		 * <p>Constructeur avec le nom, le type et des sommets</p>
		 * <p>Il va créer un graphe avec les sommets donnés.</p>
		 * <p>Le type et le nom sont également donnés en paramètres</p>
		 * 
		 * @param nom Le nom à donner au graphe.
		 * @param type Le type du graphe.
		 * @param ensembleSommets La liste de sommets du graphe.
		 */
		public GrapheListe(String nom, GrapheType type, Set<T> ensembleSommets) {
			this(nom, type, ensembleSommets, null);
		}
		
		/**
		 * <p>Constructeur avec le nom et le type du graphe</p>
		 * <p>Il va créer un graphe nul ayant pour nom et pour type, ceux passés en paramètres.</p>
		 * 
		 * @param nom Le nom à donner au graphe.
		 * @param type Le type du graphe.
		 */
		public GrapheListe(String nom, GrapheType type) {
			this(nom, type, null);
		}
		
		/**
		 * <p>Constructeur avec seulement le nom du graphe.</p>
		 * <p>Il va créer un graphe nul non orienté dont le nom est donné en paramètres.</p>
		 * 
		 * @param nom Le nom à donner au graphe.
		 */
		public GrapheListe(String nom) {
			this(nom, null);
		}
		
		/**
		 * <p>Constructeur sans paramètres.</p>
		 * <p>Il va créer un graphe nul, sans sommet.</p>
		 * <p>Son nom sera généré en fonction de la valeur d'un compteur</p>
		 * <p>Par défaut, il ne sera pas orienté</p>
		 */
		public GrapheListe() {
			this(null);
		}
		
		
		
		
		
	/* ACCESSEURS ET MUTATEURS ______________________________ */
		/**
		 * Accesseur, permet d'obtenir le nom du graphe.
		 * 
		 * @return Une chaîne de caractères contenant le nom du graphe.
		 */
		public String getNom() {
			return nom;
		}
		
		/**
		 * Mutateur, permet de modifier le nom du graphe en un nouveau.
		 * 
		 * @param nom Le nouveau nom du graphe.
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		/**
		 * Accesseur, permet d'obtenir une copie de la liste des sommets.
		 * 
		 * @return Un set contenant les sommets.
		 */
		public Set<T> getSommets() {
			Set<T> nouveau = new HashSet<>();
			
			for (T s : ensembleSommets) 
				nouveau.add(s);
			
			return nouveau;
		}
		
		/**
		 * Accesseur, permet d'obtenir une copie des arrêtes entre les sommets.
		 * 
		 * @return Une map regroupant les arrêtes.
		 */
		public Map<T, Set<T>> getArretes() {
			Map<T, Set<T>> nouvelleMap = new HashMap<>();
			
			for (T s : ensembleSommets) {
				Set<T> nouveauSet = new HashSet<>();
				
				for (T t : arretes.get(s)) {
					nouveauSet.add(t);
				}
				
				nouvelleMap.put(s, nouveauSet);
			}
			
			return nouvelleMap;
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
			String affichageArretes = "";
			
			for (T s : arretes.keySet())
				affichageArretes += "\n - " + s + " : " + arretes.get(s).toString();
			
			return "# " + nom + "\n"
				 + "type  : " + type + "\n"
				 + "nodes : " + ensembleSommets.toString() + "\n"
				 + "edges : " + affichageArretes;
		}
		
		
		
		
		
	/* MÉTHODES ______________________________ */
		public boolean ajouterSommet(T sommet) {
			if (sommet == null || existeSommet(sommet)) // si le nom existe déjà ou s'il est vide
				return false;
			else {
				ensembleSommets.add(sommet);
				arretes.put(sommet, new HashSet<T>());
				
				return true;
			}
		}	
		
		public boolean remplacerSommet(T actuel, T nouveau) {
			if (nouveau != null && existeSommet(actuel) && !existeSommet(nouveau)) {
				
				// recrée la liste du sommet
				Set<T> save = arretes.remove(actuel);
				arretes.put(nouveau, save);
				
				// Modifie l'ancien élément dans chacune des listes de sommets
				for (T s : arretes.keySet())
					if (arretes.get(s).remove(actuel))
						arretes.get(s).add(nouveau);
				
				// renomme le sommet dans la liste des sommets
				ensembleSommets.remove(actuel);
				ensembleSommets.add(nouveau);
				
				return true;
			} else
				return false;
		}
		
		public boolean enleverSommet(T sommet) {
			if (existeSommet(sommet)) {
				// on enlève le sommet de la liste des sommets
				ensembleSommets.remove(sommet);
				
				// Pour chaque sommet, on enlève les arrêtes qui se dirigent vers le sommet à supprimer
				for (T sommetCourant : ensembleSommets)
					arretes.get(sommetCourant).remove(sommet);
				
				// on enlève toutes les arrêtes du sommet à supprimer
				arretes.remove(sommet);
				
				return true;
			} else
				return false;
		}
		
		public boolean existeSommet(T sommet) {
			return ensembleSommets.contains(sommet);
		}
		
		public boolean ajouterArrete(T depart, T arrivee) {		
			if (depart == null || arrivee == null || depart.equals(arrivee) || !existeSommet(depart) || !existeSommet(arrivee) || existeArrete(depart, arrivee))
				return false;
			else {
				arretes.get(depart).add(arrivee);
				
				if (!type.isDirected())
					arretes.get(arrivee).add(depart);
				
				return true;
			}	
		}	
		
		public boolean enleverArrete(T depart, T arrivee) {
			if (depart == null || arrivee == null || depart.equals(arrivee) || !existeSommet(depart) || !existeSommet(arrivee) || !existeArrete(depart, arrivee))
				return false;
			else {
				boolean reussite = arretes.get(depart).remove(arrivee);
				
				if (!type.isDirected())
					reussite = arretes.get(arrivee).remove(depart);
				
				return reussite;
			}
		}
		
		public boolean existeArrete(T depart, T arrivee) {
			return arretes.get(depart).contains(arrivee);
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
			
			for (T t : arretes.get(parent)) {
				enfants.add(t);
			}
			
			return enfants;
		}
}