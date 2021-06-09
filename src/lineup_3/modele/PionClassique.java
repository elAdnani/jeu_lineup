package lineup_3.modele;

import outils.jeu.Pion;

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
		super(j, Nature.CLASSIQUE);
	}
	
		// Methods
	
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
