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
	
	/**
	 * 
	 * joueur correspond au Joueur à qui appartient le Pion.
	 */
	private String joueur;
	
		// Getters & Setters
	
	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}
	
	public String getJoueur() {
		return joueur;
	}
	
	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}

	// Constructor
	public Pion(int couche, int point, String j) {
		this.x = couche;
		this.y = point;
		this.joueur = j;
	}
	/**
	 * Instancie un pion
	 * @param couche l'attribut x prend la valeur couche passée en paramètre.
	 * @param point l'attribut y prend la valeur point passée en paramètre.
	 */
	public Pion(int couche, int point) {
		this(couche, point, null);
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pion(");
		builder.append(x);
		builder.append(", ");
		builder.append(y);
		builder.append(") : ");
		builder.append(joueur);
		return builder.toString();
	}
}
