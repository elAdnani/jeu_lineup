package lineup_3.modele;

import java.util.ArrayList;
import java.util.List;

import outils.jeu.Pion;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 1 juin 2021
 * @version 1 juin 2021 15:50:54
 */
public class DeckPion {
		// Class Attributes
	
	/**
	 * Représente une liste de {@link Pion} servant de main au {@link Joueur}.
	 */
	private List<Pion> main = new ArrayList<>();
	
		// Getters && Setters
	
	public List<Pion> getMain() {
		return this.main;
	}
	
		// Constructor
	
	/**
	 * Instancie une main pour un Joueur selon un nombre de Pion donné.
	 * @param nbPions nombre de Pion souhaité pour la main.
	 * @param joueur Joueur à qui la main appartient.
	 */
	public DeckPion(Joueur joueur, int nbPion, Mode mode) {
		this.initialiserMain(joueur, nbPion, mode);
	}

	/**
	 * initialiserMain permet d'ajouter à {@link DeckPion#main} un certain nombre de {@link Pion}, 
	 * avec plusieurs {@link Nature} le cas échéant.
	 * @param joueur Représente le {@link Joueur} à qui appartient la main.
	 * @param nbPion Représente le nombre de {@link Pion} donné à chaque {@link Joueur}.
	 * @param chifumi Représente le {@link Mode} de Jeu auquel le {@link Joueur} s'apprête à jouer.
	 */
	private void initialiserMain(Joueur joueur, int nbPion, Mode mode) {
		if (mode.equals(Mode.CHIFUMI)) {
			Nature nature;
			
			nature = Nature.PIERRE;
			for (int i = 0; i < nbPion/3; i++) {
				this.main.add(new PionChifumi(joueur, nature));
			}
			
			nature = Nature.PAPIER;
			for (int j = 0; j < nbPion/3; j++) {
				this.main.add(new PionChifumi(joueur, nature));
			}
			
			nature = Nature.CISEAUX;
			for (int k = 0; k < nbPion/3; k++) {
				this.main.add(new PionChifumi(joueur, nature));
			}
		}
		
		if (mode.equals(Mode.CLASSIQUE)) {
			for (int i = 0; i < nbPion; i++) {
				this.main.add(new PionClassique(joueur));
			}
		}
		
	}
	
	/**
	 * getPion permet de tirer un {@link Pion} de sa main selon sa nature.
	 * @param joueur Représente le {@link Joueur} à qui appartient le {@link Pion} tiré.
	 * @param nature Représente la {@link Nature} du Pion que le {@link Joueur} souhaite tirer.
	 * @return Retourne un {@link Pion} d'une {@link Nature} donnée. Retourne null si il n'y a plus de {@link Pion} de ce type disponible.
	 */
	public Pion getPion(Joueur joueur, Nature nature) {
		for (Pion pion : main) {
			if (pion.getNature() == nature) {
				Pion tmp = pion;
				return tmp;
			}
		}
		return null;
	}
	
	/**
	 * getPion permet de tirer un {@link Pion} de sa main selon sa nature.
	 * @param joueur Représente le {@link Joueur} à qui appartient le {@link Pion} tiré.
	 * @param nature Représente la {@link Nature} du Pion que le {@link Joueur} souhaite tirer.
	 * @return Retourne un {@link Pion} d'une {@link Nature} donnée. Retourne null si il n'y a plus de {@link Pion} de ce type disponible.
	 */
	public Pion getProchainPion(Joueur joueur, Nature nature) {
		for (Pion pion : main) {
			if (pion.getNature() == nature) {
				Pion tmp = pion;
				main.remove(pion);
				return tmp;
			}
		}
		return null;
	}
		// toSring
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeckPion [main=");
		builder.append(main);
		builder.append("]");
		return builder.toString();
	}
	
		// Main


	public static void main(String[] args) {
		int nbPion = 9;
		Mode mode = Mode.CHIFUMI;
		Joueur joueur = new Joueur("joueur", nbPion, mode);
		DeckPion deck = new DeckPion(joueur, nbPion, mode);
		
		System.out.println(deck);
	}
}
