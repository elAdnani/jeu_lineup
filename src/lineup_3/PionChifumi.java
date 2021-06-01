package lineup_3;

import java.util.List;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 11 mai 2021
 * @version 11 mai 2021 12:06:35
 */
public class PionChifumi extends Pion{
	
		// Class Attributes
	/**
	 * Correspond à la nature du pion
	 */
	private Nature nature;
	
		// Getters && Setters
	
	public Nature getNature() {
		return this.nature;
	}
	
		// Constructor
	/**
	 * Ce constructeur instancie un Pion avec un maximum d'information.
	 * @param j Représente le pseudo du Joueur à qui appartient ce Pion.
	 */
	public PionChifumi(Joueur j, Nature nature) {
		super(j);
		
	}
	
		// Methods
	
	/**
	 * Cette méthode determine si le Pion courant peut manger celui passé en paramètre.
	 * @param other Pion comparé au Pion courant.
	 * @return retourne vrai si le Pion courant peut manger celui en paramètre, faux sinon.
	 */
	public boolean mange(Pion other) {
		
		if ((this.nature == Nature.CISEAUX && other.getNature() == Nature.PAPIER) ||
				(this.nature == Nature.PAPIER && other.getNature() == Nature.PIERRE) ||
				(this.nature == Nature.PIERRE && other.getNature() == Nature.CISEAUX)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deplacerPion(List<Case> cases, String direction, int nbCote, int nbCouche) {
		this.deplacementsPossibles(nbCouche);
		
		if (super.getPossibilites().contains(Deplacement.valueOf(direction.toUpperCase()))) {
			for (Case c : cases) {
				if (c.getCoordonnees().getX()
						== super.getC().getCoordonnees().getX()
						+Deplacement.valueOf(direction.toUpperCase()).getX()
					&& c.getCoordonnees().getY()
						== (super.getC().getCoordonnees().getY()
						+Deplacement.valueOf(direction.toUpperCase()).getY()+2*nbCote)%(2*nbCote)
					&& (c.EstLibre() || this.mange(c.getPion()))) {
					this.echangerPion(c);
					break;
				}
			}
		}
	}

	
		// toString, hashCode && equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
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
		PionChifumi other = (PionChifumi) obj;
		if (nature != other.nature)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nc'est un : ");
		builder.append(nature);
		return builder.toString();
	}

}
