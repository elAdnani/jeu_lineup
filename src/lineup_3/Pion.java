package lineup_3;

import java.util.List;
import java.util.Set;

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


public abstract class Pion implements Comparable<Pion>{
		// Class Attributes
	
	/**
	 * Correspond aux cases du Plateau.
	 */
	private Set<Case> cases;
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
	private Joueur joueur;
	
	/**
	 * Liste des déplacements possibles pour un Pion donné
	 */
	private List<Deplacement> possibilites;
	
	/**
	 * Correspond à la nature du pion
	 */
	private Chifumi nature;
	
	
		// Getters & Setters

	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public Chifumi getNature() {
		return this.nature;
	}
	
		// CompareTo

	public int compareTo(Pion p) {
		if (this.joueur == p.getJoueur()) {
			return 1;
		}
		return -1;
	}
	

		// Constructors
	
	/**
	 * Instancie un Pion avec un maximum d'information.
	 * @param j représente le pseudo du Joueur à qui appartient ce Pion.
	 * @param p Représente les paramètres de la partie en cours.
	 */
	public Pion(Joueur j, Parametres p) {
		this.joueur = j;
		this.param = p;
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

	//Lorqu'on n'est pas dans un coin, il faut ajouter soit en haut, soit en bas, soit les deux.
		if (this.c.getCoordonnees().getX() == this.param.getNBCOUCHE()) {
			this.possibilites.add(Deplacement.BAS);
		} else if (this.c.getCoordonnees().getX() == 0) {
			this.possibilites.add(Deplacement.HAUT);
		} else if (this.c.getCoordonnees().getY()%2 != 0) {
			this.possibilites.add(Deplacement.BAS);
			this.possibilites.add(Deplacement.HAUT);	
		}
	}
	
	/*TODO Vérifier (si on a le temps) les cases sans Pion autour la case courante
	* dans le but de surligner les possibilités en vert sur le plateau.
	*/

	/**
	* deplacerPion permet de déplacer le Pion courant vers la case de la direction passée en paramètre si celle-ci est libre..
	* @param direction Représente la direction vers laquelle on souhaite déplacer le Pion courant.
	* @return Retourne vrai si le déplacement s'est correctement effectué, faux sinon.
	*/
	public boolean deplacerPion(String direction) {
		this.deplacementsPossibles();

		if (this.possibilites.contains(Deplacement.valueOf(direction.toUpperCase()))) {//TODO Vérifier que la case suivante est bien Libre!
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((cases == null) ? 0 : cases.hashCode());
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + ((param == null) ? 0 : param.hashCode());
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
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (cases == null) {
			if (other.cases != null)
				return false;
		} else if (!cases.equals(other.cases))
			return false;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		if (nature != other.nature)
			return false;
		if (param == null) {
			if (other.param != null)
				return false;
		} else if (!param.equals(other.param))
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
		builder.append("Pion [c=");
		builder.append(c);
		builder.append(", joueur=");
		builder.append(joueur);
		builder.append(", possibilites=");
		builder.append(possibilites);
		if (param.getCHIFUMI()) {
			builder.append(", nature=");
			builder.append(nature);
		}
		builder.append("]");
		return builder.toString();
	}
}
