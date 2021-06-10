package lineup_3.modele;

import java.util.List;

import outils.jeu.Case;
import outils.jeu.Pion;

/**
 * Cette classe est un type de pion. Cette classe a la particularité de pouvoir être combattue et combattre d'autres pion de même type.
 * Elle peut être de divers {@link Nature} : </br>
 * Pierre, lors d'un combat elle perd contre la feuille et gagne contre le ciseau. </br>
 * Feuille, lors d'un combat elle perd contre le ciseau et gagne contre la pierre. </br>
 * Ciseau, lors d'un combat il perd contre la pierre et gagne contre la feuille. </br>
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 11 mai 2021
 * @version 11 mai 2021 12:06:35
 */
public class PionChifumi extends Pion{
	
		// Getters && Setters
	
	public Nature getNature() {
		return super.nature;
	}
	
		// Constructor
	/**
	 * @param j Représente le pseudo du Joueur à qui appartient ce Pion.
	 */
	public PionChifumi(Joueur j, Nature nature) {
		super(j,nature);
	}
	
		// Methods
	
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
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("#Pion :\nC'est un pion : ");
		builder.append(super.nature);
		builder.append("\nIl est sur ");
		builder.append(super.c);
		builder.append("\nIl appartient à : ");
		builder.append(super.joueur.getPseudo());
		builder.append("\nDirections possibles : ");
		builder.append(super.possibilites);
		builder.append("\n");
		return builder.toString();
	}

}
