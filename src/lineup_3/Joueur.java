package lineup_3;

import java.time.LocalTime;

/**
 * Cette classe créer un Joueur ayant des Pions et pouvant faire certaines actions sur un Plateau.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 21 avr. 2021
 * @version 21 avr. 2021 19:26:15
 */


public class Joueur {
		// Class Attributes
	/**
	 * le nom du que le joueur choisi.
	 */
	private String pseudo;
	
	/**
	 * Le temps total de réflexion qu'a pris le joueur lors d'une partie.
	 */
	private LocalTime temps;
	
	/**
	 * La main du joueur avec laquelle il jouera toute la partie.
	 */
	private DeckPions main;
	
		// Getters
	
	public String getPseudo() {
		return pseudo;
	}



	public LocalTime getTemps() {
		return temps;
	}



	public DeckPions getMain() {
		return main;
	}

		// Constructor

	/**
	 * Instancie un Joueur pour la partie en lui affectant un pseudo et une main.
	 * @param p Correspond au pseudo utilisé pour désigner le joueur.
	 * @param nbPions Correspond au nombre de pion disponible dans la main en début de partie.
	 */
	public Joueur(String p, int nbPions) {
		this.pseudo = p;
		main = new DeckPions(nbPions);
	}
	
		// Methods
	
	public Pion getPion() {
		return this.main.getPion();
	}
	
	/**
	 * Permet de compter le nombre de Pion restant dans la main du Joueur.
	 * @return retourne un nombre de Pion.
	 */
	public int countPions() {
		return main.getMain().length;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(pseudo);
		builder.append(", temps de réflexion :");
		builder.append(temps);
		builder.append(", Pions restant :");
		builder.append(main);
		builder.append("]");
		return builder.toString();
	}



	
}
