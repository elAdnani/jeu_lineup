package lineup_3;

/**
 * Vise à désigner le type précis d'un graphe.
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 */
public enum GrapheType {
	/**
	 * (Undirected GRAPH)<br>
	 * Signifie que les arrêtes du graphe n'ont pas de sens défini.
	 */
	UGRAPH,
	
	/**
	 * (DIrected GRAPH)<br>
	 * Signifie que le graphe est dirigé, ses arrêtes ont un sens a respecter.
	 */
	DIGRAPH,
	

	/**
	 * (Undirected Weighted GRAPH)<br>
	 * Signifie que les arrêtes ont un poid donné et qu'elles n'ont aucun sens de spécifié.
	 */
	UWGRAPH,
	

	/**
	 * (DIrected Weighted GRAPH)<br>
	 * Signifie que les arrêtes d'un graphe ont une valeur de poid et qu'elles ont un sens à respecter.
	 */
	DIWGRAPH;
	
	
	
	/** Si les arrêtes doivent être dirigées, retourne vrai */
	public boolean isDirected() {
		if (this == GrapheType.DIGRAPH || this == GrapheType.DIWGRAPH)
			return true;
		else
			return false;
	}
	
	/** Si les arrêtes doivent contenir un poid, retourne vrai */
	public boolean isWeighted() {
		if (this == GrapheType.UWGRAPH || this == GrapheType.DIWGRAPH)
			return true;
		else
			return false;
	}
}