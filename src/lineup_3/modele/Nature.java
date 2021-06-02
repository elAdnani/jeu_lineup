package lineup_3.modele;

/**
 * Cette classe est une énumération des différentes nature de ChifumiPions possibles :	- Pierre
 * 																						- Papier
 * 																						- Ciseaux
 * 																						- Classique
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 11 mai 2021
 * @version 11 mai 2021 12:27:35
 */
public enum Nature {
	
	/**
	 * Un objet de nature CLASSIQUE ne mange personne et ne se fait manger par personne.
	 */
	CLASSIQUE(0),
	
	/**
	 * Un objet de nature PIERRE peut manger un objet de nature CISEAUX, et se fait manger par un objet de nature PAPIER.
	 */
	PIERRE(0),
	
	/**
	 * Un objet de nature PAPIER peut manger un objet de nature PIERRE, et se fait manger par un objet de nature CISEAUX.
	 */
	PAPIER(1),
	
	/**
	 * Un objet de nature CISEAUX peut manger un objet de nature PAPIER, et se fait manger par un objet de nature PIERRE.
	 */
	CISEAUX(2);
	
		// Class attributes
	
	private int idx;
	
		// Constructor
	
	private Nature(int idx) {
		this.idx = idx;
	}
		// Methods
	
	public int getIdx() {
		return this.idx;
	}
}
