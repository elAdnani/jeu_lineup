package outils;

/**
 * <p>Classe abstraite de graphe.</p>
 * <p>
 * 	Un graphe est un ensemble de sommets.<br>
 * 	Ceux-ci peuvent être connectés entre eux par des arrêtes.<br>
 * 	Ces arrêtes peuvent avoir une direction, pouvant ainsi rendre impossible le passage (en sens inverse) entre deux sommets.<br>
 * 	Elles peuvent également avoir une valeur qui leur est propre que l'on appelle poids.
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
public abstract class Graphe<T> {
	/**
	 * <p>Spécifie le type du graphe.</p>
	 * <p>Influence également l'ajout et la suppression des arrêtes lorsque le graphe est orienté ou non.</p>
	 * <p>Cet attribut est modifié lorsqu'un poids se rajoute sur une arrête afin de spécifier que le graphe est pondéré.</p>
	 */
	protected GrapheType type;
	
	
	
	
	
	// SOMMETS
	/**
	 * <p>Ajoute un sommet au graphe.</p>
	 * <p>Ne fonctionne pas si celui-ci existe déjà.</p>
	 * 
	 * @param sommet Le nouveau sommet
	 * 
	 * @return - true si le sommet a bien été ajouté.<br>
	 *         - false si rien n'a été fait.
	 */	
	public abstract boolean ajouterSommet(T sommet);

	/**
	 * <p>Remplace un sommet par un autre.</p>
	 * <p>Ne modifie pas les arrêtes qui lui sont liées.</p>
	 * 
	 * @param actuel  Le sommet à remplacer.
	 * @param nouveau Le nouveau sommet.
	 * 
	 * @return - true si le sommet a bien été remplacé.<br>
	 *         - false si rien n'a été modifié.
	 */
	public abstract boolean remplacerSommet(T actuel, T nouveau);

	/**
	 * <p>Enlève complètement le sommet donné du graphe.</p>
	 * <p>Il faut que celui-ci existe.</p>
	 * <p>Ceci casse toutes les arrêtes qui lui sont liées.</p>
	 * 
	 * @param sommet Le sommet à enlever.
	 * 
	 * @return - true si le sommet a bien été enlevé<br>
	 *         - false si rien n'a été modifié.
	 */
	public abstract boolean enleverSommet(T sommet);

	/**
	 * Vérifie si un sommet donné existe.
	 * 
	 * @param sommet Le sommet à vérifier.
	 * 
	 * @return - true si le sommet existe<br>
	 *         - false si le sommet n'a pas été trouvé.
	 */
	public abstract boolean existeSommet(T sommet);
	
	
	
	
	
	// ARRÊTES
	/**
	 * <p>Ajoute une arrête entre deux sommets donnés.</p>
	 * <p>Pour que cela réussisse, il faut évidemment que les deux sommets existent mais aussi que l'arrête ne soit pas encore créée.</p>
	 * <p>Si le graphe est orienté, l'arrête sera en sens unique et s'il ne l'est pas, l'arrête sera en double sens.</p>
	 * 
	 * @param depart  Un sommet duquel faire partir l'arrête.
	 * @param arrivee Un sommet vers lequel se dirige l'arrête.
	 * 
	 * @return - true si l'ajout a pu se faire<br>
	 *         - false si rien n'a été modifié.
	 */
	public abstract boolean ajouterArete(T depart, T arrivee);

	/**
	 * Enlève une arrête entre deux sommets donnés.
	 * 
	 * @param depart  Sommet duquel part l'arrête.
	 * @param arrivee Sommet duquel arrive l'arrête.
	 * 
	 * @return - true si la suppression a pu se faire<br>
	 *         - false si rien n'a été modifié.
	 */
	public abstract boolean enleverArete(T depart, T arrivee);

	/**
	 * Vérifie s'il existe une arrête entre deux sommets.
	 * 
	 * @param depart  Sommet théorique de départ.
	 * @param arrivee Sommet théorique d'arrivée.
	 * 
	 * @return - true si l'arrête existe<br>
	 *         - false si elle n'existe pas.
	 */
	public abstract boolean existeArete(T depart, T arrivee);
	
	
	
	
	
	// TYPE
	/**
	 * Récupère le type du graphe
	 * 
	 * @return Le type de graphe
	 */
	public GrapheType getType() {
		return type;
	}
	
	/**
	 * Permet de savoir si le graphe est dirigé ou non.
	 * 
	 * @return - true si le graphe est dirigé<br>
	 *         - false s'il ne l'est pas.
	 */
	public boolean estDirige() {
		return type.isDirected();
	}
	
	/**
	 * Permet de savoir si le graphe est pondéré ou non.
	 * 
	 * @return - true si le graphe est pondéré<br>
	 *         - false s'il ne l'est pas.
	 */
	public boolean estPondere() {
		return type.isWeighted();
	}
}
