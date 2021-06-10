package outils.jeu;

import java.util.ArrayList;
import java.util.List;

import lineup_3.modele.Deplacement;
import lineup_3.modele.Joueur;
import lineup_3.modele.Nature;

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
	 * Correspond aux coordonnées du Pion.
	 */
	protected Case emplacement;
	
	/**
	 * Correspond au {@link Joueur} à qui appartient le Pion courant.
	 */
	protected Joueur joueur;
	
	/**
	 * Liste des déplacements possibles pour un Pion donné
	 */
	protected List<Deplacement> possibilites = new ArrayList<>();
	
	/**
	 * Correspond à la {@link Nature} du pion.
	 */
	protected Nature nature;
	
	
		// Getters & Setters

	public Joueur getJoueur() {
		return joueur;
	}
	
	public void setCouleur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public Case getEmplacement() {
		return this.emplacement;
	}
	
	public void setEmplacement(Case c) {
		this.emplacement = c;
	}
	
	public List<Deplacement> getPossibilites() {
		return this.possibilites;
	}
	
	public Nature getNature() {
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
	public Pion(Joueur joueur, Nature nature) {
		this.joueur = joueur;
		this.nature = nature;
	}
	
		// Methods
	
	/**
	* deplacementsPossibles détermine, dans l'absolu, les possibles mouvements du {@link Pion} courant.
	*/
	public void deplacementsPossibles(int nbCouche) {
		if(this.possibilites != null) {
			this.possibilites.clear();
		}

	//Peu importe le point, il y a toujours des points à gauche et à droite.
		this.possibilites.add(Deplacement.GAUCHE);
		this.possibilites.add(Deplacement.DROITE);

	//Lorqu'on n'est pas dans un coin, il faut ajouter soit en haut, soit en bas, soit les deux.
		if (this.emplacement.getCoordonnees().getX() == nbCouche-1) {
			this.possibilites.add(Deplacement.BAS);
		} else if (this.emplacement.getCoordonnees().getX() == 0 && this.emplacement.getCoordonnees().getY()%2 !=0) {
			this.possibilites.add(Deplacement.HAUT);
		} else if (this.emplacement.getCoordonnees().getY()%2 != 0) {
			this.possibilites.add(Deplacement.BAS);
			this.possibilites.add(Deplacement.HAUT);	
		}
	}
	
	/*TODO Vérifier (si on a le temps) les cases sans Pion autour la case courante
	* dans le but de surligner les possibilités en vert sur le plateau.
	* L'idée ici, serait : - soit d'établir une liste de voisinsDe(T sommet) dans GrapheMatrice.
	* 						- soit de vérifier dans toute la liste de Case si une case correspond :
	* 									.Soit à (1,0)
	* 									.Soit à (0,1)
	* 									.Soit à (-1,0)
	* 									.Soit à (0,-1)
	* 						et en faire une liste.
	*/
//	public List<Deplacement> casesLibres(Liste<Case> cases) {
//		deplacementsPossibles();
//	}

	
	/**
	* deplacerPion permet de déplacer le {@link Pion} courant vers la {@link Case} de la direction 
	* passée en paramètre si celle-ci {@link Case#es.
	* @param direction Représente la direction vers laquelle on souhaite déplacer le Pion courant.
	*/
	public void deplacerPion(List<Case> cases, String direction, int nbCote, int nbCouche) {
		this.deplacementsPossibles(nbCouche);
		
		if (this.possibilites.contains(Deplacement.valueOf(direction.toUpperCase()))) {
			for (Case c : cases) {
				if (c.getCoordonnees().getX()
						== this.emplacement.getCoordonnees().getX()
						+Deplacement.valueOf(direction.toUpperCase()).getX()
					&& c.getCoordonnees().getY()
						== (this.emplacement.getCoordonnees().getY()
						+Deplacement.valueOf(direction.toUpperCase()).getY()+2*nbCote)%(2*nbCote)
						&& (c.EstLibre() || this.mange(c.getPion()))) {
					this.echangerPion(c);
					break;
				}
			}
		}
	}
	
	/**
	 * Cette méthode determine si le Pion courant peut manger celui passé en paramètre.
	 * @param other Pion comparé au Pion courant.
	 * @return retourne vrai si le Pion courant peut manger celui en paramètre, faux sinon.
	 */
	public boolean mange(Pion other) {
		
		if ((this.nature == Nature.CISEAUX && other.getNature() == Nature.PAPIER) ||
				(this.nature == Nature.PAPIER && other.getNature() == Nature.PIERRE) ||
				(this.nature == Nature.PIERRE && other.getNature() == Nature.CISEAUX)) {
			return true;
		} else {
			return false;
		}
	}
	
	//TODO javadoc.
	public boolean alignements(List<Case> cases, int nbCote, int nbCouche) {
		this.deplacementsPossibles(nbCouche);
		for (Deplacement deplacement : possibilites) {
			for (Case case1 : cases) {
				if (case1.getCoordonnees().getX()
							== this.emplacement.getCoordonnees().getX()
							+deplacement.getX()
						&& case1.getCoordonnees().getY()
							== (this.emplacement.getCoordonnees().getY()
							+deplacement.getY()+2*nbCote)%(2*nbCote)
						&& !case1.EstLibre()
						&& this.emplacement.getPion().getJoueur() ==  case1.getPion().getJoueur()) {
					for (Deplacement deplacement2 : case1.getPion().getPossibilites()) {
						for (Case case2 : cases) {
							if (case2.getCoordonnees().getX()
										== case1.getCoordonnees().getX()
										+deplacement2.getX()
									&& case2.getCoordonnees().getY()
										== (case1.getCoordonnees().getY()
										+deplacement2.getY()+2*nbCote)%(2*nbCote)
									&& case2 != case1 && case2 != this.emplacement
									&& !case2.EstLibre()
									&& case1.getPion().getJoueur() == case2.getPion().getJoueur()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * echangerPion change les données des deux {@link Case} concernées durant le déplacement. 
	 * Elle met à jour également la {@link Case} du {@link Pion} courant.
	 * @param c Représente la {@link Case} sur laquelle le Pion se déplace.
	 */
	public void echangerPion(Case c) {
		Case tmp = this.emplacement;
		c.ajouterPion(tmp.getPion());
		this.emplacement.retirerPion();
		this.setEmplacement(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emplacement == null) ? 0 : emplacement.hashCode());
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
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
		if (emplacement == null) {
			if (other.emplacement != null)
				return false;
		} else if (!emplacement.equals(other.emplacement))
			return false;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
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
		builder.append(emplacement);
		builder.append(", couleur=");
		builder.append(joueur);
		builder.append(", possibilites=");
		builder.append(possibilites);
		builder.append(", nature=");
		builder.append(nature);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
