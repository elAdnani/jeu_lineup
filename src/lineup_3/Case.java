package lineup_3;

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
	private Set<Case> cases;
	
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
	
	//TODO javadoc.
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
	
	/**
	 * caseA Cherche une case aux coordonnées passées en paramètres.
	 * @param p Représente une Paire de coordonnées.
	 * @return Retourne la case aux coordonnées voulu et null si elle n'existe pas.
	 */
	public Case caseA(Paire p) {
		for (Case c : cases) {
			if (c.getCoordonnees() == p) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * caseSuivante cherche la case adjacente dans le sens horaire à celle courante selon ses coordonnées.
	 * @return Retourne la case adjacente dans le sens horaire à celle courante.
	 * Si elle n'existe pas, elle retourne une Case null.
	 */
	public Case caseSuivante() {
			for (Case c : cases) {
			if (c.getCoordonnees().getY() == this.getCoordonnees().getY()+1%(2*param.getNBCOTE())) {
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
	public Case casePrecedente() {
		Case tmp = new Case();
		for (Case c : cases) {
			if (c.getCoordonnees().getY() == this.getCoordonnees().getY()-1%(2*param.getNBCOTE())) {
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
	public Case caseSuperieure() {
		for (Case c : cases) {
			if (c.getCoordonnees().getX() == this.getCoordonnees().getX()+1) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * estVoisin regarde si une Case est voisine à celle courante.
	 * @param c Représente la Case potentiellement voisine de la celle courante.
	 * @return Retourne vrai si les deux Case sont voisine, faux sinon.
	 */
	public boolean estVoisin(Case c) {
		
		if (this.coordonnees.getX() == c.getCoordonnees().getX()) {
			if ((this.coordonnees.getY() == Math.abs(c.getCoordonnees().getY()-1%(2*param.getNBCOTE())))
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
	
	/**
	 * alignements regarde si autour du Pion de la Case courante, d'autres Pion appartiennent au même Joueur.
	 * Auquel cas, il y a d'éventuels alignements.
	 * @return Retourne vrai si un alignements de trois Pion est détecté, faux sinon.
	 */
	public boolean alignements() {
		
		// On regarde les éventuels alignements sur la même couche.
			if (this.caseSuivante().getPion().compareTo(this.pion) == 1
					&& this.casePrecedente().getPion().compareTo(this.pion) == 1) {
				return true;
			}
			
			if (this.caseSuivante().getPion().compareTo(this.pion) == 1
					&& this.caseSuivante().caseSuivante().getPion().compareTo(this.pion) == 1) {
				return true;			
			}
			
			if (this.casePrecedente().getPion().compareTo(this.pion) == 1
					&& this.casePrecedente().casePrecedente().getPion().compareTo(this.pion) == 1) {
				return true;
			}
		
		// S'il n'y a pas d'alignements sur la même couche, on regarde les éventuels alignements inter-couche.
			if (this.getCoordonnees().getY()%2 != 0) {
				
				if (this.getCoordonnees().getX() == param.getNBCOUCHE()) {
					if (this.caseInferieure().getPion().compareTo(this.pion) == 1
							&& this.caseInferieure().caseInferieure().getPion().compareTo(this.pion) == 1) {
						return true;
					}
				}
				
				if(this.getCoordonnees().getX() == 0) {
					if(this.caseSuperieure().getPion().compareTo(this.pion)== 1
							&& this.caseSuperieure().caseSuperieure().getPion().compareTo(this.pion) == 1){
						return true;
					}
				}
				
				if(this.caseInferieure().getPion().compareTo(this.pion) == 1
						&& this.caseSuperieure().getPion().compareTo(this.pion) == 1) {
					return true;
				}
			}
			return false;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cases == null) ? 0 : cases.hashCode());
		result = prime * result + ((coordonnees == null) ? 0 : coordonnees.hashCode());
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
		if (cases == null) {
			if (other.cases != null)
				return false;
		} else if (!cases.equals(other.cases))
			return false;
		if (coordonnees == null) {
			if (other.coordonnees != null)
				return false;
		} else if (!coordonnees.equals(other.coordonnees))
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
