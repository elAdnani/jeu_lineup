package lineup_3.modele;


import java.util.HashMap;
import java.util.Map;

import package2.Pion;

/**
 * Cette classe créer une pile de pion et compte le nombre de coups joué par un Joueur.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
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
	
	/**
	 * getPion permet de tirer un {@link Pion} de sa main selon sa nature.
	 * @param joueur Représente le {@link Joueur} à qui appartient le {@link Pion} tiré.
	 * @param nature Représente la {@link Nature} du Pion que le {@link Joueur} souhaite tirer.
	 * @return Retourne un {@link Pion} d'une {@link Nature} donnée. Retourne null si il n'y a plus de {@link Pion} de ce type disponible.
	 */
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
