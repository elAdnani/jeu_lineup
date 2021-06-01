package lineup_3.modele;

import java.time.LocalTime;
import java.util.Map;

import package2.Pion;

/**
 * Cette classe créer un Joueur ayant des Pions et pouvant faire certaines actions sur un Plateau.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 21 avr. 2021
 * @version 21 avr. 2021 19:26:15
 */


public class Joueur {
		// Class Attributes
	
	/**
	 * Stock le nombre de coups joué par le joueur.
	 */
	private int nbCoup;
	
	/**
	 * le nom du que le joueur choisi.
	 */
	private String pseudo;
	
	/**
	 * Le temps total de réflexion qu'a pris le joueur lors d'une partie.
	 */
	private LocalTime temps;
	
	/**
	 * La main du joueur avec laquelle il jouera toute la partie.
	 */
	private DeckPions main;
	
	
		// Getters
	
	public String getPseudo() {
		return this.pseudo;
	}

	public LocalTime getTemps() {
		return temps;
	}

	public DeckPions getMain() {
		return main;
	}
	
	public int getNbCoup() {
		return nbCoup;
	}
	
	public void ajouterCoup() {
		this.nbCoup++;
	}

		// Constructor

	/**
	 * Instancie un Joueur pour la partie en lui affectant un pseudo et une main.
	 * @param p Correspond au pseudo utilisé pour désigner le joueur.
	 * @param nbPions Correspond au nombre de pion disponible dans la main en début de partie.
	 */
	public Joueur(String pseudo, int nbPion) {
		this.pseudo = pseudo;
		main = new DeckPions(this, nbPion);
	}
	
		// Methods
	
	public Pion getPion() {
		return this.main.getPion();
	}
	
	/**
	 * countPions permet de compter le nombre de Pion restant dans la main du Joueur.
	 * @return retourne un nombre de Pion.
	 */
	public int countPions() {
		return this.main.getIdx();
	}
	
	/**
	 * poserPion prend le prochain Pion, le retire de la main du {@link Joueur} et
	 * l'ajoute à la {@link Case} souhaitée. Parallèlement, le {@link Pion} prend
	 * connaissance de sa {@link Case}.
	 * @param c Représente la {@link Case} où le {@link Joueur} souhaite poser le {@link Pion}.
	 */
	public boolean poserPion(Case c, PlateauPolynomial p, Map<Joueur, Character> skinPion, int nbCote, int nbCouche) {
		c.ajouterPion(main.getPion());
		main.getPion().setC(c);
		main.getProchainPion().setC(c);
		main.getPion().deplacementsPossibles(nbCouche);
//		System.out.println(main.getPion().getPossibilites());

		if (c.getPion().alignements(p.getListeCase(), nbCote, nbCouche)){
			System.out.println(c.getPion().getJoueur().getPseudo() + " a gagné !");
			//TODO faire un blocage à la fin
			return c.getPion().alignements(p.getListeCase(), nbCote, nbCouche);
		}
		return false;
	}
	
	/**
	 * deplacerPion ré-affecte à {@link Pion} une nouvelle {@link Case} en mettant tout à jour.
	 * @param p Représente le pion que le {@link Joueur} souhaite déplacer.
	 * @param direction Représente la direction vers laquelle le {@link Joueur} souhaite aller.
	 */
	public void deplacerPion(Pion p, String direction, PlateauPolynomial pl, Map<Joueur, Character> skinPion, int nbCote, int nbCouche) {
		if (p.getJoueur() == this) {
			p.deplacerPion(pl.getListeCase(), direction, nbCote, nbCouche);
			
			p.getJoueur().ajouterCoup();
			if (p.alignements(pl.getListeCase(), nbCote, nbCouche)) {
				System.out.println(p.getC().getPion().getJoueur().getPseudo() + " a gagné !");
				/*for (Case cas : pl.getListeCase()) {
					cas.setEstLibre(false);
				}*/
				//TODO pareil que le todo précédent.
			}
		}
	}
	
	//TODO écrire poserChifumiPion.

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		result = prime * result + nbCoup;
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + ((temps == null) ? 0 : temps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		if (nbCoup != other.nbCoup)
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (temps == null) {
			if (other.temps != null)
				return false;
		} else if (!temps.equals(other.temps))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("# ");
		builder.append(pseudo);
		builder.append("\nPions restant : ");
		builder.append(this.countPions());
		builder.append("\nNombre de Coups : ");
		builder.append(nbCoup);
		builder.append("\nTemps de réflexion : ");
		builder.append(temps);
		builder.append("\n");
		return builder.toString();
	}



	
}
