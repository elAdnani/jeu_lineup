package lineup_3;

import java.util.List;
import java.util.Set;

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
	private final Paire COORDONNEES;
	
	/**
	 * Représente la disponibilité de la case. Par défaut estLibre est à true.
	 */
	private boolean estLibre = true;
	
	/**
	 * Représente la présence d'un piège. Initialement à faux.
	 */
	private boolean estPiege = false;
	
	/**
	 * Représente le {@link Pion} qui occupe la case courante.
	 */
	private Pion pion;
	
	/**
	 * Correspond aux paramètres de la partie
	 */
	private Parametres param;
	
			// Getters & Setters
	
	public Paire getCoordonnees() {
		return this.COORDONNEES;
	}
	
	public boolean EstLibre() {
		return estLibre;
	}
	
	public void setEstLibre(boolean libre) {
		this.estLibre = libre;
	}

	public boolean EstPiege() {
		return estPiege;
	}
	
	public Pion getPion() {
		return this.pion;
	}

	
			// Constructors

	/**
	 * Ce constructeur instancie une case avec ses coordonnées passées en paramètre.
	 * @param couche Représente la coordonnée indiquant la couche sur laquelle se trouve la Case.
	 * @param point Représente la coordonnée indiquant le point sur laquelle se trouve la Case, dépendamment de la couche.
	 */
	public Case(int couche, int point) {
		this.COORDONNEES = new Paire(couche, point);
	}
	
	/**
	 * {@link #Case(int, int, Parametres)} instancie une case avec ses coordonnées et des paramètres de partie. Surtout utilisé pour  {@link SimulationPartie}
	 * @param couche Représente la coordonnée indiquant la couche sur laquelle se trouve la Case.
	 * @param point Représente la coordonnée indiquant le point sur laquelle se trouve la Case, dépendamment de la couche.
	 * @param p Représente les {@link Parametres} de la partie lors d'une simulation.
	 */
	public Case(int couche, int point, Parametres p) {
		this.COORDONNEES = new Paire(couche, point);
		this.param = p;
	}
	
	
			// Methods
	
	/**
	 * {@link #ajouterPion(Pion)} permet de poser un Pion sur la Case courante.
	 * @param p Représente le Pion  que l'on souhaite poser.
	 * @return 	Retourne vrai si le Pion s'est bien poser, faux sinon.
	 */
	public boolean ajouterPion(Pion p) {
		if (this.estLibre) {
			this.pion = p;
//			p.setC(this);
			this.estLibre = false;
			return true;
		}
		return false;
	}
	
	/**
	 * {@link #retirerPion(Pion)} permet d'enlever le Pion de la {@link Case} courante.
	 * @return	Retourne vrai si le Pion s'est bien enlevé, faux sinon.
	 */
	public boolean retirerPion() {
		if (!this.estLibre) {
			this.pion = null;
			return this.estLibre = true;
		}
		return estLibre;
	}
	
	//TODO javadoc.
	public Set<Case> getVoisins(PlateauPolynomial p) {
		return p.voisinDe(this);
	}
	/**
	 * caseSuivante cherche la case adjacente dans le sens horaire à celle courante selon ses coordonnées.
	 * TODO mettre à jour la javadoc des param.
	 * @return Retourne la case adjacente dans le sens horaire à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseSuivante(List<Case> cases, int nbCote) {
			for (Case c : cases) {
			if (c.getCoordonnees().getY() == this.getCoordonnees().getY()+1%(2*nbCote)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * casePrecedente cherche la case adjacente dans le sens anti-horaire à celle courante selon ses coordonnées.
	 * @return Retourne la case adjacente dans le sens anti-horaire à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case casePrecedente(List<Case> cases, int nbCote) {
		for (Case c : cases) {
			if (c.getCoordonnees().getY() == this.getCoordonnees().getY()-1%(2*nbCote)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * caseInferieure cherche la case adjacente de la couche en-dessous par rapport à celle
	 * courante selon ses coordonnées.
	 * @return Retourne la case adjacente de la couche en-dessous par rapport à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseInferieure(List<Case> cases) {
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getX()-1) {
				return c;
			}
		}
		return null;
	}
	
	
	/**
	 * caseSuperieure cherche la case adjacente de la couche au-dessus par rapport à celle
	 * courante selon ses coordonnées.
	 * @return Retourne la case adjacente de la couche au-dessus par rapport à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseSuperieure(List<Case> cases) {
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getX()+1) {
				return c;
			}
		}
		return null;
	}
	
		// toString, hashCode && equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((COORDONNEES == null) ? 0 : COORDONNEES.hashCode());
		result = prime * result + (estLibre ? 1231 : 1237);
		result = prime * result + (estPiege ? 1231 : 1237);
		result = prime * result + ((param == null) ? 0 : param.hashCode());
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
		if (COORDONNEES == null) {
			if (other.COORDONNEES != null)
				return false;
		} else if (!COORDONNEES.equals(other.COORDONNEES))
			return false;
		if (estLibre != other.estLibre)
			return false;
		if (estPiege != other.estPiege)
			return false;
		if (param == null) {
			if (other.param != null)
				return false;
		} else if (!param.equals(other.param))
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
		builder.append("Case");
		builder.append(COORDONNEES);
		builder.append(" : estLibre=");
		builder.append(estLibre);
		builder.append(", estPiege=");
		builder.append(estPiege);
		return builder.toString();
	}
	
	
	
}
