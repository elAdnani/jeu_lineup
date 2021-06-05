package outils;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 5 juin 2021
 * @version 5 juin 2021 10:53:53
 */
public enum Couleur {
		// Class Values
	BLANC("blanc", 0),
	NOIR("noir", 1),
	BLEU("bleu", 2),
	ROUGE("rouge", 3),
	ORANGE("orange", 4),
	VERT("vert", 5);
	
		// Class Attributes
	private String couleur;
	private int num;
	
		// Getters && Setters
	public String getCouleur() {
		return couleur;
	}
	
	public int getNum() {
		return num;
	}
	
		// Constructor
	private Couleur(String c, int n) {
		this.couleur = c;
		this.num = n;
	}
}
