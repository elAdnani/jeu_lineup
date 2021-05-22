package lineup_3;

public abstract class Graphe<T> {
	/**
	 * Spécifie le type du graphe.
	 * Influence également l'ajout et la suppression des arrêtes lorsque le graphe est orienté ou non.
	 * Cet attribut est modifié lorsqu'un poids se rajoute sur une arrête afin de spécifier que le graphe est pondéré.
	 */
	protected GrapheType type;
	
	// Manipulation des sommets
	public abstract boolean ajouterSommet(T s);
	public abstract boolean renommerSommet(T avant, T apres);
	public abstract boolean enleverSommet(T s);
	public abstract boolean existeSommet(T s);
	
	// Manipulation des arrêtes
	public abstract boolean ajouterArrete(T s1, T s2);
	public abstract boolean enleverArrete(T s1, T s2);
	public abstract boolean existeArrete(T s1, T s2);
	
	// Le type du graphe
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
