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
	private Joueur joueur;
	
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
	
	public Joueur getJoueur() {
		return this.joueur;
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
	public Joueur(Joueur j, Parametres p) {
		this.joueur = j;
		this.param = p;
		main = new DeckPions(joueur, param);
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
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
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
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
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
		builder.append(joueur);
		builder.append("\nPions restant :");
		builder.append(this.countPions());
		builder.append("\nNombre de Coups :");
		builder.append(nbCoups);
		builder.append("\nTemps de réflexion :");
		builder.append(temps);
		builder.append("\n");
		return builder.toString();
	}



	
}
