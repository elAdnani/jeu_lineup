package lineup_3;

import java.util.List;

/**
 * 
 * Cette classe sert à définir ce qu'est un Pion, en règle générale, avec lequel un Joueur pourra jouer, c'est
 * cette classe qui détermine si un mouvement est possible et, si oui, effectue le déplacement.
 * 
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 19 avr. 2021
 * @version 19 avr. 2021 15:16:48
 */


public abstract class Pion {
		// Class Attributes
	/**
	 * Correspond aux coordonnées du Pion.
	 */
	private Paire coordonnees;
	
	/**
	 * joueur correspond au Joueur à qui appartient le Pion.
	 */
	private String joueur;
	
	/**
	 * Liste des déplacements possibles pour un Pion donné
	 */
	private List<Deplacement> possibilite;
	
	/**
	 * Correspond au nombre de côté par couche du Plateau.
	 */
	private int nbCote;
	
	/**
	 * Correspond au nombre de couches du Plateau. 
	 */
	private final int NBCOUCHE;
	
	/**
	 * Correspond à la nature du pion
	 */
	private Chifumi nature;
	
	
		// Getters & Setters

	public String getJoueur() {
		return joueur;
	}
	
	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}
	
	public Chifumi getNature() {
		return this.nature;
	}

		// Constructors
	
	/**
	 * Instancie un Pion avec un maximum d'information.
	 * @param couche représente la coordonnée indiquant la couche sur laquelle se trouve le Pion.
	 * @param point représente la coordonnée indiquant le point sur laquelle se trouve le Pion, dépendamment de la couche.
	 * @param j représente le pseudo du Joueur à qui appartient ce Pion.
	 * @param nbCote représente le nombre de côté du Plateau.(Utile pour savoir où se déplacer).
	 * @param nbCouche représente le nombre de couche du Plateau.(Utile pour savoir où se déplacer).
	 */
	public Pion(int couche, int point, String j, int nbCote, int nbCouche) {
		this.coordonnees = new Paire(couche, point);
		this.joueur = j;
		this.nbCote = nbCote;
		this.NBCOUCHE = nbCouche;
	}
	/**
	 * Instancie un pion avec ses coordonnées.
	 * @param couche Représente l'attribut x.
	 * @param point Représente l'attribut y.
	 */
	public Pion(int couche, int point) {
		this(couche, point, null, 0, 0);
	}
	
	/**
	 * Instancie un pion sans paramètre particulier.
	 */
	public Pion()  {
		this(-1,-1);
	}
	
	public Paire getCoordonnees() {
		return this.coordonnees;
	}
	
		// Methods
	
	/**
	 * Cette méthode determine si le Pion courant peut manger celui passé en paramètre.
	 * @param other Pion comparé au Pion courant.
	 * @return retourne vrai si le Pion courant peut manger celui en paramètre, faux sinon.
	 */
	public boolean mange(Pion other) {
		
		if ((this.nature == Chifumi.CISEAUX && other.nature == Chifumi.PAPIER) ||
				(this.nature == Chifumi.PAPIER && other.nature == Chifumi.PIERRE) ||
				(this.nature == Chifumi.PIERRE && other.nature == Chifumi.CISEAUX)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Affecte une position à un Pion.
	 * @param couche l'attribut x prend la valeur couche passée en paramètre.
	 * @param point l'attribut y prend la valeur point passée en paramètre.
	 */
	public void setPosition(int couche, int point) {
		this.coordonnees.setX(couche);
		this.coordonnees.setY(point);
	}
	
	/**
	 * Considère les déplacements que peut réaliser un pion dans le plateau.
	 * Il rajoute à l'attribut this.possibilite par quel moyen un même pion peut se déplacer.
	 * @param selection correspond au pion que le joueur souhaite déplacer.
	 */
	
	private void deplacementPossible(Pion selection) {
		if(this.possibilite != null) {
			this.possibilite.clear();
		}
		
		final int COUCHEPRINCIPALE = 1;
		int X = selection.coordonnees.getX();
		int Y = selection.coordonnees.getY();
		int nombrePionDansUneCouche = (nbCote - 1) * NBCOUCHE;
		
		// en haut (faire +1 à la couche) => X=paire et Y!=COUCHEMAX
		// en bas (faire -1 à la couche) => X=paire et Y!=1
		// en droite (faire +1 à X) => X!= coucheMAX
		// à gauche (faire -1 à X) =>
		// HAUT :
		if (Y % 2 == 0 && X != PlateauDeBase.NBCOUCHE && !presencePion(X + 1, Y))

			this.possibilite.add(Deplacement.HAUT);
		if (Y % 2 == 0 && X != COUCHEPRINCIPALE && !presencePion(X - 1, Y))

			this.possibilite.add(Deplacement.BAS);
		if (!presencePion(X, (Y + 1) % nombrePionDansUneCouche))

			this.possibilite.add(Deplacement.DROITE);
		if (!presencePion(X, (Y - 1) % nombrePionDansUneCouche))

			this.possibilite.add(Deplacement.GAUCHE);

	}
	
	/**
	 * Déplace un pion, selon sa disponibilité et la direction donnée.
	 * @param selection est le pion décide de déplacer
	 * @param direction est la direction que le joueur a choisie
	 */
	
	public boolean deplacerPion(Pion selection,String direction) {
		deplacementPossible(selection);
		
			if (this.possibilite.contains(Deplacement.valueOf(direction.toUpperCase()))) {
				selection.setPosition(
						selection.coordonnees.getX()+ Deplacement.valueOf(direction.toUpperCase()).getX(),
						selection.coordonnees.getY()+ Deplacement.valueOf(direction.toUpperCase()).getY()
				);
						
				 /**   @TODO faire un / modulo*/
			//return true;
			// on modifie le X en lui attribuant la valeur de lui même
				// plus la direction dans laquelle il veut se diriger
			}
		return false;
	}

	/**
	 * presencePion vérifie la présence d'un Pion
	 * @param coordonnees Représente la {@link Paire} de coordonnée
	 * @return	Retourne vrai si un Pion est présent aux coordonnées passées en paramètre, faux sinon.
	 */
	
	public boolean presencePion(Paire coordonnees) {
		for (Pion p : PlateauDeBase.getListPion())
			if (p.coordonnees.getX() == coordonnees.getX() && p.coordonnees.getY() == coordonnees.getY())
				return true;

		return false;
	}
	
	/**
	 * presencePion vérifie la présence d'un Pion
	 * @param X	Représente la couche à la coordonnée X.
	 * @param Y	Représente la couche à la coordonnée Y.
	 * @return	Retourne vrai si un Pion est présent aux coordonnées passées en paramètre, faux sinon.
	 */
	
	public boolean presencePion(int X, int Y) {
		for (Pion p : PlateauDeBase.getListPion())
			if (p.coordonnees.getX() == X && p.coordonnees.getY() == Y)
				return true;

		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NBCOUCHE;
		result = prime * result + ((coordonnees == null) ? 0 : coordonnees.hashCode());
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + nbCote;
		result = prime * result + ((possibilite == null) ? 0 : possibilite.hashCode());
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
		Pion other = (Pion) obj;
		if (NBCOUCHE != other.NBCOUCHE)
			return false;
		if (coordonnees == null) {
			if (other.coordonnees != null)
				return false;
		} else if (!coordonnees.equals(other.coordonnees))
			return false;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		if (nature != other.nature)
			return false;
		if (nbCote != other.nbCote)
			return false;
		if (possibilite == null) {
			if (other.possibilite != null)
				return false;
		} else if (!possibilite.equals(other.possibilite))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pion(");
		builder.append(coordonnees.getX());
		builder.append(";");
		builder.append(coordonnees.getY());
		builder.append(")");
		return builder.toString();
	}
}
