package lineup_3;

import java.util.List;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 30 mai 2021
 * @version 30 mai 2021 11:15:17
 */
public class PionClassique extends Pion{

	/**
	 * @param j
	 */
	public PionClassique(Joueur j) {
		super(j);
	}
	
		// Methods
	
	public void deplacerPion(List<Case> cases, String direction, int nbCote, int nbCouche) {
		this.deplacementsPossibles(nbCouche);
		
		if (super.getPossibilites().contains(Deplacement.valueOf(direction.toUpperCase()))) {
			for (Case c : cases) {
				if (c.getCoordonnees().getX()
						== super.c.getCoordonnees().getX()
						+Deplacement.valueOf(direction.toUpperCase()).getX()
					&& c.getCoordonnees().getY()
						== (super.c.getCoordonnees().getY()
						+Deplacement.valueOf(direction.toUpperCase()).getY()+2*nbCote)%(2*nbCote)
					&& c.EstLibre()) {
					this.echangerPion(c);
					break;
				}
			}
		}
	}

	
		// toString, hashCode && equals

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("#Pion :\nIl est sur ");
		builder.append(super.c);
		builder.append("\nIl appartient à : ");
		builder.append(super.joueur.getPseudo());
		builder.append("\nDirections possibles : ");
		builder.append(super.possibilites);
		builder.append("\n");
		return builder.toString();
	}
	
}
