package lineup_3;

import java.util.Arrays;

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
	 * Représente la main du joueur.
	 */
	private Pion[] main;
	
	/**
	 * Indique le prochain pion sur la liste.
	 */
	private int idx;	
	
	/**
	 * Correspond aux paramètres de la partie.
	 */
	private Parametres param;
	
		// Getters
	
	public Pion[] getMain() {
		return main;
	}
	
	public int getIdx() {
		return this.idx+1;
	}
	
	public Pion getPion() {
		return main[idx];
	}
	
		// Constructor
	
	/**
	 * Instancie une main pour un Joueur selon un nombre de Pion donné.
	 * @param nbPions nombre de Pion souhaité pour la main.
	 * @param joueur Joueur à qui la main appartient.
	 */
	public DeckPions(Joueur joueur, Parametres p) {
		this.param = p;
		this.idx = param.getNBPION()-1;
		this.main = new Pion[param.getNBPION()];
		this.initialiserMain(joueur);
	}
	
		// Methods
	
	/**
	 * getPion Permet d'obtenir le prochain Pion de la main en mode de partie Normal.
	 * @return Retourne un Objet de type Pion.
	 */
	public Pion getProchainPion() {
		if (main[idx].getC()!=null) idx--;
		return main[idx];
	}
	
	//TODO écrire la méthode getPion(), pour pourvoir choisir celui qu'on veut en mode Chifumi.
	
	/**
	 * Permet de remplir la main avec des Pions sans position.
	 * @param joueur Correspond au propriétaire de la main en cours d'initialisation.
	 */
	private void initialiserMain(Joueur joueur) {
		for (int i = 0; i < main.length; i++) {
			main[i] = new ChifumiPion(joueur, param);
			main[i].setJoueur(joueur);
		}
	}
	
    public void addPions(Pion pion) {
        if(this.idx<=main.length) {
            this.main[idx]=pion;
        }
        idx= idx +1;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Main :\n");
		builder.append(Arrays.toString(main).replace(",", "\n"));
		builder.append("\nidx : ");
		builder.append(idx);
		return builder.toString();
	}

	
}
