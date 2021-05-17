package lineup_3;

/**
 * Cette classe permet de rassembler tous les paramètres d'une partie pour une meilleure efficacité lors d'initialisations de Plateau,
 * de calculs de déplacement, d'ajouts de piège etc... Les autres objets s'appuient sur les attributs de Parametres pour fonctionner.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 28 avr. 2021
 * @version 28 avr. 2021 13:35:52
 */
public class Parametres {
	// TODO mettre tous les paramètres d'avant partie.

		// Class Attributes
	/**
	 * Correspond au nombre de couche choisi pour la partie.
	 */
	private final int NBCOUCHE;
	
	/**
	 * Correspond au nombre de côté choisi pour la partie.
	 */
	private final int NBCOTE;
	
	/**
	 * Correspond au choix de mettre des pièges ou non.
	 */
	private final boolean PIEGES;
	
		// Constructor
	
	public Parametres(int nbCouche, int nbCote, boolean piege) {
		this.NBCOUCHE = nbCouche;
		this.NBCOTE = nbCote;
		this.PIEGES = piege;
	}

		// Methods
	
	public int getNBCOUCHE() {
		return NBCOUCHE;
	}

	public int getNBCOTE() {
		return NBCOTE;
	}

	public boolean isPIEGES() {
		return PIEGES;
	}
	
	public int getNBPOINT() {
		return (2*this.NBCOTE)*this.NBCOUCHE;
	}
	
}
