package lineup_3;

/**
 * 
 * Cette classe sert à instancier un Pion avec lequel un Joueur pourra jouer.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 19 avr. 2021
 * @version 19 avr. 2021 15:16:48
 */


public class Pion {
		// Class Attributes
	
	/**
	 * x correspond à la couche sur laquelle le pion se trouve.
	 */
	private int x;
	
	/**
	 * y correspond au point de la couche x sur lequel le pion se trouve.
	 */
	private int y;
	
		// Getters & Setters
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

		// Constructor
	
	/**
	 * Instancie un pion
	 * @param couche l'attribut x prend la valeur couche passée en paramètre.
	 * @param point l'attribut y prend la valeur point passée en paramètre.
	 */
	public Pion(int couche, int point) {
		this.x = couche;
		this.y = point;
	}
	
	/**
	 * Instancie un pion sans paramètre particulier
	 */
	public Pion()  {
		this(0,0);
	}
	
		// Methods
	
	/**
	 * Affecte une position à un Pion.
	 * @param couche l'attribut x prend la valeur couche passée en paramètre.
	 * @param point l'attribut y prend la valeur point passée en paramètre.
	 */
	public void setPosition(int couche, int point) {
		setX(couche);
		setY(point);
	}
}
