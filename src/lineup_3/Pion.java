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
	 * Correspond aux paramètre de la partie.
	 */
	private Parametres param;
	
	/**
	 * Correspond aux coordonnées du Pion.
	 */
	private Case c;
	
	/**
	 * joueur correspond au Joueur à qui appartient le Pion.
	 */
	private String joueur;
	
	/**
	 * Liste des déplacements possibles pour un Pion donné
	 */
	private List<Deplacement> possibilites;
	
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
	 * @param j représente le pseudo du Joueur à qui appartient ce Pion.
	 * @param nbCote représente le nombre de côté du Plateau.(Utile pour savoir où se déplacer).
	 * @param nbCouche représente le nombre de couche du Plateau.(Utile pour savoir où se déplacer).
	 */
	public Pion(String j, int nbCote, int nbCouche) {
		this.joueur = j;
		this.nbCote = nbCote;
		this.NBCOUCHE = nbCouche;
	}
	/**
	 * Instancie un pion avec ses coordonnées.
	 * @param j Représente le pseudo du Joueur à qui appartient ce Pion.
	 */
	public Pion(String j) {
		this(j, 0, 0);
	}
	
	/**
	 * Instancie un pion sans paramètre particulier.
	 */
	public Pion()  {
		this(null);
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
	 * @param couche l'attribut x de Case prend la valeur couche passée en paramètre.
	 * @param point l'attribut y de Case prend la valeur point passée en paramètre.
	 */
	public void setPosition(int couche, int point) {
		this.c.getCoordonnees().setX(couche);
		this.c.getCoordonnees().setY(point);
	}
	
	/**
	* deplacementsPossibles détermine, dans l'absolu, les possibles mouvements du Pion courant.
	*/

	public void deplacementsPossibles() {
		if(this.possibilites != null) {
			this.possibilites.clear();
		}

	//Peu importe le point, il y a toujours des points à gauche et à droite.
		this.possibilites.add(Deplacement.GAUCHE);
		this.possibilites.add(Deplacement.DROITE);

	/*Lorqu'on n'est pas dans un coin, il faut ajouter soit en haut, soit en bas, soit les deux.*/
		if (this.c.getCoordonnees().getX() == this.param.getNBCOUCHE()) {//TODO ajouter un attribut param là où besoin.
			this.possibilites.add(Deplacement.BAS);
		} else if (this.c.getCoordonnees().getX() == 0) {
			this.possibilites.add(Deplacement.HAUT);
		} else if (this.c.getCoordonnees().getX()%2 != 0) {
			this.possibilites.add(Deplacement.BAS);
			this.possibilites.add(Deplacement.HAUT);	
		}
	}
	
	/* A finir si on a le temps, dans le but de surligner les possibilités en vert sur le plateau.
		/**
		* estLibre Vérifie si les cases autour de celle passée en paramètre sont libre ou non.
		* @param listeC Représente la liste des cases du plateau.
		*/
	/*
		public boolean estLibre(List<Case> listeC) {
			deplacementsPossibles();
			for (Déplacement d : this.possibilites) {
				if (this.c.getCoordonnees().getX()) {
					
				}
			}
		}
	*/

	/**
	* deplacerPion permet de déplacer le Pion courant vers la case de la direction passée en paramètre.
	* @param direction Représente la direction vers laquelle on souhaite déplacer le Pion courant.
	* @return Retourne vrai si le déplacement s'est correctement effectué, faux sinon.
	*/
	public boolean deplacerPion(String direction) {
		this.deplacementsPossibles();

		if (this.possibilites.contains(Deplacement.valueOf(direction.toUpperCase()))) {
			this.setPosition(
				(this.c.getCoordonnees().getX() + Deplacement.valueOf(direction.toUpperCase()).getX()),
				(this.c.getCoordonnees().getX() + Deplacement.valueOf(direction.toUpperCase()).getY())%param.getNBPOINT()
			);
			//TODO update l'attribut Pion de l'ancienne case et celui de la nouvelle.
			return true;
		} else {
			return false;
		}
	}

	/**
	* poserPion permet de mettre un Pion qui était dans la main du Joueur sur le Plateau.
	* @param c Représente la case où l'on souhaite poser le Pion courant.
	* @return Retourne vrai si le Pion se pose correctement, faux s'il était déjà poser.
	*/

	public boolean poserPion(Case c) {
		if (this.c != null) {
			return false;
		} else {
			this.c = c;
			return true;
		}
		//TODO update l'attribut Pion de l'ancienne case et celui de la nouvelle.
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NBCOUCHE;
		result = prime * result + ((c.getCoordonnees() == null) ? 0 : c.getCoordonnees().hashCode());
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + nbCote;
		result = prime * result + ((possibilites == null) ? 0 : possibilites.hashCode());
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
		if (c.getCoordonnees() == null) {
			if (other.c.getCoordonnees() != null)
				return false;
		} else if (!c.getCoordonnees().equals(other.c.getCoordonnees()))
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
		if (possibilites == null) {
			if (other.possibilites != null)
				return false;
		} else if (!possibilites.equals(other.possibilites))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pion(");
		builder.append(c.getCoordonnees().getX());
		builder.append(";");
		builder.append(c.getCoordonnees().getY());
		builder.append(")");
		return builder.toString();
	}
}
