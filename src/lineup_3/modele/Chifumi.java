package lineup_3.modele;

/**
 * Cette classe est une énumération des différentes nature de ChifumiPions possibles :	- Pierre
 * 																						- Papier
 * 																						- Ciseaux
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 11 mai 2021
 * @version 11 mai 2021 12:27:35
 */
public enum Chifumi {
	
	/**
	 * Un objet de nature PIERRE peut manger un objet de nature CISEAUX, et se fait manger par un objet de nature PAPIER.
	 */
	PIERRE,
	
	/**
	 * Un objet de nature PAPIER peut manger un objet de nature PIERRE, et se fait manger par un objet de nature CISEAUX.
	 */
	PAPIER,
	
	/**
	 * Un objet de nature CISEAUX peut manger un objet de nature PAPIER, et se fait manger par un objet de nature PIERRE.
	 */
	CISEAUX;
	
		// Methods
}
