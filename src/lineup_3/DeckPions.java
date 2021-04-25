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
		// Class Attributes
	
	/**
	 * Stock le nombre de coups joué par le joueur.
	 */
	private int nbCoups;
	
	/**
	 * Représente la main du joueur.
	 */
	private Pion[] main;
	
	/**
	 * Indique le prochain pion sur la liste.
	 */
	private int idx;	
	
		// Getters
	
	public int getNbCoups() {
		return nbCoups;
	}

	public Pion[] getMain() {
		return main;
	}
	
		// Constructor
	
	public DeckPions(int nbPions) {
		this.idx = nbPions;
		this.main = new Pion[nbPions];
		this.initialiserMain();
	}
	
		// Methods
	
	/**
	 * Permet d'obtenir le prochain Pion de la main.
	 * @return Retourne un Objet de type Pion.
	 */
	public Pion getPion() {
		return main[idx];
	}
	
	/**
	 * Permet de remplir la main avec des Pions sans position.
	 */
	private void initialiserMain() {
		for (int i = 0; i < main.length; i++) {
			main[i] = new Pion();
		}
	}
	
    public void addPions(Pion pion) {
        if(this.idx<=main.length) {
            this.main[idx]=pion;
        }
        idx= idx +1;
    }
}
