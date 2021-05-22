package lineup_3;

import java.util.List;

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

	/**
	 * Correspond à la liste de Case du Plateau.
	 */
	private List<Case> cases;
	
	/**
	 * Correspond aux paramètres de la partie
	 */
	private Parametres param;
	
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
	
	public Pion getPion() {
		return this.pion;
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
	
	public Case(int couche, int point, Parametres p) {
		this.coordonnees = new Paire(couche, point);
		this.param = p;
	}
	
	public Case() {}
	
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
	
	/**
	 * caseSuivante cherche la case adjacente dans le sens horaire à celle courante selon ses coordonnées.
	 * @return Retourne la case adjacente dans le sens horaire à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseSuivante() {
		Case tmp = new Case();
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getY()+1%(2*param.getNBCOTE())) {
				tmp = c;
			}
		}
		return tmp;
	}
	
	/**
	 * casePrecedente cherche la case adjacente dans le sens anti-horaire à celle courante selon ses coordonnées.
	 * @return Retourne la case adjacente dans le sens anti-horaire à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case casePrecedente() {
		Case tmp = new Case();
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getY()-1%(2*param.getNBCOTE())) {
				tmp = c;
			}
		}
		return tmp;
	}
	
	/**
	 * caseInferieure cherche la case adjacente de la couche en-dessous par rapport à celle
	 * courante selon ses coordonnées.
	 * @return Retourne la case adjacente de la couche en-dessous par rapport à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseInferieure() {
		Case tmp = new Case();
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getX()-1) {
				tmp = c;
				if (tmp.getCoordonnees().getX()<0) {
					tmp = null;
				}
			}
		}
		return tmp;
	}
	
	/**
	 * caseSuperieure cherche la case adjacente de la couche au-dessus par rapport à celle
	 * courante selon ses coordonnées.
	 * @return Retourne la case adjacente de la couche au-dessus par rapport à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseSuperieure() {
		Case tmp = new Case();
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getX()+1) {
				tmp = c;
				if (tmp.getCoordonnees().getX()>param.getNBCOUCHE()) {
					tmp = null;
				}
			}
		}
		return tmp;
	}
	
	/**
	 * estVoisin regarde si une Case est voisine à celle courante.
	 * @param c Représente la Case potentiellement voisine de la celle courante.
	 * @return Retourne vrai si les deux Case sont voisine, faux sinon.
	 */
	public boolean estVoisin(Case c) {
		
		if (this.coordonnees.getX() == c.getCoordonnees().getX()) {
			if ((this.coordonnees.getY() == c.getCoordonnees().getY()-1%(2*param.getNBCOTE()))
					|| (this.coordonnees.getY() == c.getCoordonnees().getY()+1%(2*param.getNBCOTE()))) {
				return true;
			}
		} else if (this.coordonnees.getY() == c.getCoordonnees().getY()) {
			if ((this.coordonnees.getX() == c.getCoordonnees().getX()-1)
					|| (this.coordonnees.getX() == c.getCoordonnees().getX()+1)) {
				return true;
			}
		}
		return false;
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
