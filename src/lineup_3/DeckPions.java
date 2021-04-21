package lineup_3;

/**
 * Cette classe créer une pile de pion et compte le nombre de coups joué par un Joueur.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 21 avr. 2021
 * @version 21 avr. 2021 20:18:31
 */


public class DeckPions {
	// class attributes
	
	/**
	 * stock le nombre de coups joué par le joueur.
	 */
	private int nbCoups;
	
	/**
	 * représente la main du joueur.
	 */
	private Pion[] main;
	
	public int getNbCoups() {
		return nbCoups;
	}

	public Pion[] getMain() {
		return main;
	}
	
	// Constructor
	
	
	public DeckPions(int nbPions) {
		this.main = new Pion[nbPions];
	}
}
