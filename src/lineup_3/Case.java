package lineup_3;

/**
 * Cette classe représente une Case de plateau puvant stocker différentes infos comme sa disponibilité, si elle possède
 * une caractéristique spéciale(comme un piège) ou autre.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 16 mai 2021
 * @version 16 mai 2021 00:04:58
 */
public class Case {
		// Class Attributes
	
	/**
	 * Correspond aux coordonnées du Pion.
	 */
	private Paire coordonnees;
	
	/**
	 * Représente la disponibilité de la case. Par défaut estLibre est à true.
	 */
	private boolean estLibre = true;
	
	/**
	 * Représente la présence d'un piège. Initialement à faux.
	 */
	private boolean estPiege = false;
	
	/**
	 * Représente le Pion qui occupe la case courante.
	 */
	private Pion pion;
	
			// Getters & Setters
	
	public Paire getCoordonnees() {
		return this.coordonnees;
	}
	
	public boolean EstLibre() {
		return estLibre;
	}



	public boolean EstPiege() {
		return estPiege;
	}

	
			// Constructors

	/**
	 * Ce constructeur instancie une case avec ses coordonnées passées en paramètre.
	 * @param couche représente la coordonnée indiquant la couche sur laquelle se trouve la Case.
	 * @param point représente la coordonnée indiquant le point sur laquelle se trouve la Case, dépendamment de la couche.
	 */
	public Case(int couche, int point) {
		this.coordonnees = new Paire(couche, point);
	}
	
			// Methods
	
	/**
	 * addPion permet de poser un Pion sur la Case courante.
	 * @param p Représente le Pion  que l'on souhaite poser.
	 * @return 	Retourne vrai si le Pion s'est bien poser, faux sinon.
	 */
	public boolean addPion(Pion p) {
		if (this.estLibre) {
			this.pion = p;
			this.estLibre = false;
			return true;
		}
		return false;
	}
	
	/**
	 * removePion permet d'enlever le Pion de la Case courante.
	 * @param p Représente le Pion que l'on souhaite enlever.
	 * @return	Retourne vrai si le Pion s'est bien enlevé, faux sinon.
	 */
	public boolean removePion(Pion p) {
		if (!this.estLibre) {
			this.pion = null;
			this.estLibre = true;
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estLibre ? 1231 : 1237);
		result = prime * result + (estPiege ? 1231 : 1237);
		result = prime * result + ((pion == null) ? 0 : pion.hashCode());
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
		Case other = (Case) obj;
		if (estLibre != other.estLibre)
			return false;
		if (estPiege != other.estPiege)
			return false;
		if (pion == null) {
			if (other.pion != null)
				return false;
		} else if (!pion.equals(other.pion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [coordonnees=");
		builder.append(coordonnees);
		builder.append(", estLibre=");
		builder.append(estLibre);
		builder.append(", estPiege=");
		builder.append(estPiege);
		if (estLibre) {
			builder.append(", pion=");
			builder.append(pion);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
