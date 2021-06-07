package lineup_3.modele;

import java.io.Serializable;

import outils.Couleur;
import package2.Pion;
import package2.Plateau;

/**
 * Cette classe créer un {@link Joueur} ayant des {@link Pion} et pouvant faire certaines actions sur un {@link Plateau}, notamment poser des {@link Pion}
 * et les déplacer.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 21 avr. 2021
 * @version 21 avr. 2021 19:26:15
 */


public class Joueur implements Serializable{
		// Class Attributes

	private static final long serialVersionUID = -4106475665236130022L;
	
	/**
	 * Stock le nombre de coups joué par le {@link Joueur}.
	 */
	private int nbCoup;
	
	/**
	 * Correspond au nom que le {@link Joueur} choisi.
	 */
	private String pseudo;
	
	/**
	 * Correspond à la couleur que les {@link Pion} du {@link Joueur} auront(notamment en graphique).
	 */
	
	private Couleur couleur;
	
	/**
	 * La main du {@link Joueur} avec laquelle il jouera toute la partie.
	 */
	private DeckPion main;
	
	
		// Getters && Setters
	
	/**
	 * @return Retourne une Chaîne de caractère représentant le {@link Joueur#pseudo} du {@link Joueur}.
	 */
	public String getPseudo() {
		return this.pseudo;
	}
	
	/**
	 * @return Retourne la couleur des {@link Pion} du {@link Joueur}.
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * @return Retourne un objet de type {@link DeckPion} représentant la {@link Joueur#main} du {@link Joueur}.
	 */
	public DeckPion getMain() {
		return main;
	}
	
	/**
	 * @return Retourne un entier représentant le {@link Joueur#nbCoup},le nombre de coup joué par le {@link Joueur}.
	 */
	public int getNbCoup() {
		return nbCoup;
	}

		// Constructor

	/**
	 * Instancie un {@link Joueur} pour la partie en lui affectant un {@link Joueur#pseudo} et une {@link Joueur#main}.
	 * @param p Correspond au {@link Joueur#pseudo} utilisé pour désigner le {@link Joueur}.
	 * @param nbPions Correspond au nombre de pion disponible dans la {@link Joueur#main} en début de partie.
	 * @param mode Correspond au {@link Mode} de Jeu de la partie.
	 */
	public Joueur(String pseudo, int nbPion, Mode mode) {
		this.pseudo = pseudo;
		main = new DeckPion(this, nbPion, mode);
	}
	
		// Methods
	
	/**
	 * ajouterCoup se charge d'implémenter {@link Joueur#nbCoup} lorsque le {@link Joueur} joue.
	 */
	private void ajouterCoup() {
		this.nbCoup++;
	}

	/**
	 * getPion cherche dans la {@link Joueur#main} du {@link Joueur}, un {@link Pion} selon sa {@link Nature}.
	 * @param nature Représente la {@link Nature} du {@link Pion}.
	 * @return Retourne un {@link Pion} si possible, sinon retourne null.
	 */
	public Pion getPion(Nature nature) {
		return this.main.getPion(this, nature);
	}
	
	/**
	 * countPions permet de compter le nombre de {@link Pion} restant dans la {@link Joueur#main} du {@link Joueur}.
	 * @return Retourne un entier représentant une quantité de {@link Pion}.
	 */
	public int compterPions() {
		return main.getMain().size();
	}
	
	/**
	 * poserPion prend le prochain Pion, le retire de la main du {@link Joueur} et
	 * l'ajoute à la {@link Case} souhaitée. Parallèlement, le {@link Pion} prend
	 * connaissance de sa {@link Case}.
	 * @param c Représente la {@link Case} où le {@link Joueur} souhaite poser un {@link Pion}.
	 * @param p Représente le {@link Plateau} sur lequel le {@link Joueur} joue.
	 * @param nbCouche Représente le nombre de couche que possède le {@link Plateau}.
	 * @param nbCote Représente le nombre de côté que possède le {@link Plateau}.
	 * @param nature Représente la {@link Nature} du {@link Pion} que le {@link Joueur} souhaite poser.
	 * @return Retrourne vrai si, suite à la pose du {@link Pion}, un {@link Pion#alignements} est detecté et faux sinon.
	 */
	public boolean poserPion(Case c, PlateauPolynomial p, int  nbCouche, int nbCote, Nature nature) {
		c.ajouterPion(main.getPion(this, nature));
		main.getPion(this, nature).setC(c);
		main.getPion(this, nature).deplacementsPossibles(nbCouche);
		main.getProchainPion(this, nature).setC(c);
		if (c.getPion().alignements(p.getListeCase(), nbCote, nbCouche)){
			//System.out.println(c.getPion().getJoueur().getPseudo() + " a gagné !");
			//TODO faire un blocage à la fin
			return c.getPion().alignements(p.getListeCase(), nbCote, nbCouche);
		}
		return false;
	}
	
	/**
	 * deplacerPion ré-affecte à {@link Pion} une nouvelle {@link Case} en mettant tout à jour.
	 * @param p Représente le pion que le {@link Joueur} souhaite déplacer.
	 * @param direction Représente la direction vers laquelle le {@link Joueur} souhaite aller.
	 * @param p Représente le {@link Plateau} sur lequel le {@link Joueur} joue.
	 * @param nbCouche Représente le nombre de couche que possède le {@link Plateau}.
	 * @param nbCote Représente le nombre de côté que possède le {@link Plateau}.
	 * @return Retrourne vrai si, suite au déplacement d'un {@link Pion}, un {@link Pion#alignements} est detecté et faux sinon.

	 */
	public boolean deplacerPion(Pion p, String direction, PlateauPolynomial pl, int nbCote, int nbCouche) {
		if (p.getJoueur() == this) {
			p.deplacerPion(pl.getListeCase(), direction, nbCote, nbCouche);
			p.getJoueur().ajouterCoup();
			if (p.alignements(pl.getListeCase(), nbCote, nbCouche)) {
				return p.alignements(pl.getListeCase(), nbCote, nbCouche);
				//TODO pareil que le todo précédent.
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		result = prime * result + nbCoup;
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("# ");
		builder.append(pseudo);
		builder.append("\nPions restant : ");
		builder.append(this.compterPions());
		builder.append("\nNombre de Coups : ");
		builder.append(nbCoup);
		builder.append("\n");
		return builder.toString();
	}
	
}
