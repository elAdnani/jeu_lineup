package lineup_3;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Un graphe est un ensemble de sommets. Ceux-ci peuvent être connectés entre eux par des arrêtes. Ces arrêtes peuvent avoir une direction, pouvant ainsi rendre impossible le passage (en sens inverse) entre deux sommets.
 * Cette classe offre donc la possibilité de créer des graphes, de leur ajouter ou supprimer des sommets et des arrêtes
 * <br>
 * Lors de la création du graphe, les constructeurs offrent la possibilité de :
 * <ul>
 * 	<li>Créer un graphe vide, c'est à dire avec aucun sommet ni arrête.</li>
 * 	<li>Créer un graphe avec uniquement un nom.</li>
 * 	<li>Créer un graphe avec uniquement un nom et le type du graphe.</li>
 * 	<li>Créer un graphe complètement spécifié</li>
 * </ul>
 * Par défaut le graphe est non orienté.
 * <br>
 * Les getters fournis permettent de récupérer la liste de sommets, les arrêtes et le nom du graphe.
 * Le seul setter proposé permet de changer le nom du graphe. Les autres sont bloqués pour forcer l'utilisateur à utiliser les méthodes proposées pour ne pas casser le graphe.
 * <br>
 * Les méthodes fournies permettent d'ajouter ou de supprimer des sommets ou des arrêtes.
 * D'autres méthodes servent à vérifier si le graphe est orienté et/ou pondéré.
 * Si deux sommets sont reliés entre eux...
 * <br>
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 * @param <T> Un type quelconque à placer aux sommets du graphe.
 */
public class GrapheListe<T> extends Graphe<T> {
	/* ATTRIBUTS ______________________________ */
		/** Un compteur permettant de donner un nom unique au graphe créé sans paramètres */
		private static int cpt = 0;
		
		/** Le nom du graphe */
		private String nom;
		
		/** La liste de sommets du graphe. Celle-ci ne peut contenir aucun doublon. */
		private Set<T> sommets;
		
		/** Cette map regroupe toutes les arrêtes du graphe. Une arrête part de l'indice et se dirige vers chacun des sommets qui lui sont renseignés dans la liste. */
		private Map<T, Set<T>> arretes;
		
		
		
		
		
	/* CONSTRUCTEURS ______________________________ */
		/**
		 * Constructeur complètement spécifié.
		 * 
		 * @param nom Le nom à donner au graphe.
		 * @param type Le type du graphe.
		 * @param sommets La liste de sommets du graphe.
		 * @param arretes Les arrêtes reliant les sommets.
		 */
		public GrapheListe(String nom, GrapheType type, Set<T> sommets, Map<T, Set<T>> arretes) {
			if (nom == null)
				this.nom = "Graphe";
			else
				this.nom = nom;
			
			if (type == null)
				this.type = GrapheType.UGRAPH;
			else
				this.type = type;
			
	
			this.sommets = sommets;
			this.arretes = arretes;
		}
		
		/**
		 * Constructeur ne nécessitant que le nom et le type du graphe.
		 * 
		 * @param nom Le nom à donner au graphe.
		 * @param type Le type du graphe.
		 */
		public GrapheListe(String nom, GrapheType type) {
			this(nom, type, null, null);
		}
		
		/**
		 * Constructeur qui crée un graphe non orienté dont le nom est donné en paramètres.
		 * 
		 * @param nom Le nom à donner au graphe.
		 */
		public GrapheListe(String nom) {
			this(nom, null);
		}
		
		/**
		 * Constructeur sans paramètres.
		 */
		public GrapheListe() {
			this("Graphe n°" + ++cpt);
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
			
			for (T s : sommets) 
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
			
			for (T s : sommets) {
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
			String affichageArretes = "";
			
			for (T s : arretes.keySet())
				affichageArretes += "\n - " + s + " : " + arretes.get(s).toString();
			
			return "# " + nom + "\n"
				 + "type  : " + type + "\n"
				 + "nodes : " + sommets.toString() + "\n"
				 + "edges : " + affichageArretes;
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
			if (s == null || existeSommet(s)) // si le nom existe déjà ou s'il est vide
				return false;
			else {
				sommets.add(s);
				arretes.put(s, new HashSet<T>());
				
				return true;
			}
		}	
		
		/**
		 * Remplace un sommet par un autre sans modifier les arrêtes qui lui sont liées.
		 * 
		 * @param avant Le sommet à remplacer.
		 * @param apres Le nouveau sommet.
		 * @return true si l'opération s'est bien déroulée et false sinon.
		 */
		public boolean renommerSommet(T avant, T apres) {
			if (apres != null && existeSommet(avant) && !existeSommet(apres)) {
				
				// recrée la liste du sommet
				Set<T> save = arretes.remove(avant);
				arretes.put(apres, save);
				
				// Modifie l'ancien élément dans chacune des listes de sommets
				for (T s : arretes.keySet())
					if (arretes.get(s).remove(avant))
						arretes.get(s).add(apres);
				
				// renomme le sommet dans la liste des sommets
				sommets.remove(avant);
				sommets.add(apres);
				
				return true;
			} else
				return false;
		}
		
		/**
		 * Enlève un certain sommet au graphe.
		 * Il faut que celui-ci existe.
		 * 
		 * @param s Le sommet à enlever.
		 * @return true si le sommet a bien été enlevé et false sinon.
		 */
		public boolean enleverSommet(T s) {
			if (existeSommet(s)) {
				for (T sommet : arretes.keySet())
					arretes.get(sommet).remove(s);
				
				arretes.remove(s);
				sommets.remove(s);
				
				return true;
			} else
				return false;
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
		 * Permet d'ajouter une arrête au graphe.<br>
		 * Dans le cas d'un graphe orienté, l'arrête part du sommet s1 et se dirige vers le sommet s2.
		 */
		public boolean ajouterArrete(T s1, T s2) {		
			if (s1 == null || s2 == null || s1.equals(s2) || !existeSommet(s1) || !existeSommet(s2) || existeArrete(s1, s2))
				return false;
			else {
				arretes.get(s1).add(s2);
				
				if (!type.isDirected())
					arretes.get(s2).add(s1);
				
				return true;
			}	
		}	
		
		/** Supprime une arrête entre deux sommets. */
		public boolean enleverArrete(T s1, T s2) {
			if (s1 == null || s2 == null || s1.equals(s2) || !existeSommet(s1) || !existeSommet(s2) || !existeArrete(s1, s2))
				return false;
			else {
				boolean reussite = arretes.get(s1).remove(s2);
				
				if (!type.isDirected()) // si le graphe n'est pas dirigé
					reussite = arretes.get(s2).remove(s1);
				
				return reussite;
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
			return arretes.get(s1).contains(s2);
		}
		
		/**
		 * Donne un set de tous les sommets vers lesquels arrivent des arrêtes partant du sommet donné
		 * 
		 * @param s Le sommet parent
		 * @return Un set null s'il y a une erreur et contenant les enfants du sommet sinon.
		 */
		public Set<T> enfantsDe(T s) {
			if (s == null || !existeSommet(s))
				return null;
			
			Set<T> enfants = new HashSet<>();
			
			for (T t : arretes.get(s)) {
				enfants.add(t);
			}
			
			return enfants;
		}
}