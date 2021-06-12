package outils;

import javafx.scene.paint.Color;

/**
 * Cette classe définit les couleurs existantes dans notre jeu. </br>
 * Elle permettra en mode graphique de définir les couleurs des pions des joueurs qui permettent de les identifier.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 5 juin 2021
 * @version 5 juin 2021 10:53:53
 */
public enum Couleur {

	BLANC(Color.WHITE,"blanc"),
	NOIR(Color.BLACK,"noir"),
	BLEU(Color.BLUE,"bleu"),
	ROUGE(Color.RED,"rouge"),
	ORANGE(Color.ORANGE,"orange"),
	VERT(Color.GREEN,"vert");
	

	private Color couleur;
	private String nom;
	
		/**
		 * @return la couleur attribué à l'enum
		 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * @return le nom attribué à l'enum
	 */
	public String getNom() {
		return nom;
	}
	
		// Constructor
	private Couleur(Color couleur, String nomDeLaCouleur) {
		this.couleur = couleur;
		this.nom = nomDeLaCouleur;
	}
}
