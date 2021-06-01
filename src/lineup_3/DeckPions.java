package lineup_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	private Map<Nature, Integer> main = new HashMap<>();
	
	
		// Getters
	
	public Map<Nature, Integer> getMain() {
		return main;
	}
	
		// Constructor
	
	/**
	 * Instancie une main pour un Joueur selon un nombre de Pion donné.
	 * @param nbPions nombre de Pion souhaité pour la main.
	 * @param joueur Joueur à qui la main appartient.
	 */
	public DeckPions(Joueur joueur, int nbPion, boolean chifumi) {
		this.initialiserMain(joueur, nbPion, chifumi);
	}	
	
		// Methods
	
	/**
	 * Permet de remplir la main avec des {@link Pion} sans position.
	 * @param joueur Correspond au propriétaire de la main en cours d'initialisation.
	 */
	//TODO enum type de jeu inchallah.
	private void initialiserMain(Joueur joueur, int nbPion, boolean Chifumi) {
		if (!Chifumi) {
				this.main.put(Nature.CLASSIQUE, nbPion);
		} else {
				this.main.put(Nature.PIERRE, nbPion/3);
				this.main.put(Nature.PAPIER, nbPion/3);
				this.main.put(Nature.CISEAUX, nbPion/3);
		}
	}

	public Pion getPion(Joueur joueur, Nature nature) {
		return new PionChifumi(joueur, nature);
	}
	
	/**
	 * getPion Permet d'obtenir le prochain Pion de la main en mode de partie Normal.
	 * @return Retourne un Objet de type Pion.
	 */
	public Pion getProchainPion(Joueur joueur, Nature nature) {
		if (this.main.containsKey(nature)
				&& this.main.get(nature) != 0) this.main.put(nature, this.main.get(nature)-1);
		return new PionChifumi(joueur, nature);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Main :\n");
		builder.append(this.main.toString());
		return builder.toString();
	}

	
}
