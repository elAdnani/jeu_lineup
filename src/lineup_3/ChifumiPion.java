package lineup_3;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 11 mai 2021
 * @version 11 mai 2021 12:06:35
 */
public class ChifumiPion extends Pion{
		// Constructor
	/**
	 * Instancie un Pion avec un maximum d'information.
	 * @param couche représente la coordonnée indiquant la couche sur laquelle se trouve le Pion.
	 * @param point représente la coordonnée indiquant le point sur laquelle se trouve le Pion, dépendamment de la couche.
	 * @param j représente le pseudo du Joueur à qui appartient ce Pion.
	 * @param nbCote représente le nombre de côté du Plateau.(Utile pour savoir où se déplacer).
	 * @param nbCouche représente le nombre de couche du Plateau.(Utile pour savoir où se déplacer).
	 */
	public ChifumiPion(int couche, int point, String j, int nbCote, int nbCouche) {
		super(couche, point, j, nbCote, nbCouche);
	}
	
	/**
	 * Instancie un pion
	 * @param couche l'attribut x prend la valeur couche passée en paramètre.
	 * @param point l'attribut y prend la valeur point passée en paramètre.
	 */
	public ChifumiPion(int couche, int point) {
		super(couche, point);
	}
	
	/**
	 * Instancie un pion sans paramètre particulier
	 */
	public ChifumiPion() {
		super();
	}
	
		// Methods
	
}
