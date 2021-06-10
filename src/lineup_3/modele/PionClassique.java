package lineup_3.modele;

import outils.jeu.Pion;

/**
 * Cette classe permet de définir un pion de nature (cf. {@link Nature}) Classique.
 * Elle réalise des simplemes déplacements de part une case à une autre voisine. </br>
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 30 mai 2021
 * @version 30 mai 2021 11:15:17
 */
public class PionClassique extends Pion{

	/**
	 * @param j est un joueur possedant le pion courrant
	 */
	public PionClassique(Joueur j) {
		super(j, Nature.CLASSIQUE);
	}
	
	/**
	 * Retourne le hashCode de {@link Pion}
	 */
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
		builder.append(super.emplacement);
		builder.append("\nIl appartient à : ");
		builder.append(super.joueur.getPseudo());
		builder.append("\nDirections possibles : ");
		builder.append(super.possibilites);
		builder.append("\n");
		return builder.toString();
	}
	
}
