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
	 * Stock le nombre de coups joué par le joueur.
	 */
	private int nbCoups;
	
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
	
	/**
	 * Correspond aux paramètres de la partie.
	 */
	private Parametres param;
	
		// Getters
	
	public String getJoueur() {
		return this.pseudo;
	}

	public LocalTime getTemps() {
		return temps;
	}

	public DeckPions getMain() {
		return main;
	}
	
	public int getNbCoups() {
		return nbCoups;
	}

		// Constructor

	/**
	 * Instancie un Joueur pour la partie en lui affectant un pseudo et une main.
	 * @param p Correspond au pseudo utilisé pour désigner le joueur.
	 * @param nbPions Correspond au nombre de pion disponible dans la main en début de partie.
	 */
	public Joueur(String pseudo, Parametres param) {
		this.pseudo = pseudo;
		this.param = param;
		main = new DeckPions(this, param);
	}
	
		// Methods
	
	public Pion getPion() {
		return this.main.getProchainPion();
	}
	
	/**
	 * countPions permet de compter le nombre de Pion restant dans la main du Joueur.
	 * @return retourne un nombre de Pion.
	 */
	public int countPions() {
		return main.getMain().length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		result = prime * result + nbCoups;
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + ((temps == null) ? 0 : temps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		if (nbCoups != other.nbCoups)
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (temps == null) {
			if (other.temps != null)
				return false;
		} else if (!temps.equals(other.temps))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("# ");
		builder.append(pseudo);
		builder.append("\nPions restant : ");
		builder.append(this.countPions());
		builder.append("\nNombre de Coups : ");
		builder.append(nbCoups);
		builder.append("\nTemps de réflexion : ");
		builder.append(temps);
		builder.append("\n");
		return builder.toString();
	}



	
}
