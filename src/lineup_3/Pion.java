package lineup_3;

import java.util.List;

/**
 * 
 * Cette classe sert à instancier un Pion avec lequel un Joueur pourra jouer.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 19 avr. 2021
 * @version 19 avr. 2021 15:16:48
 */


public class Pion {
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
	
	
		// Getters & Setters

	public String getJoueur() {
		return joueur;
	}
	
	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}

	// Constructor
	public Pion(int couche, int point, String j, int nbCote, int nbCouche) {
		this.coordonnees = new Paire(couche, point);
		this.joueur = j;
		this.nbCote = nbCote;
		this.NBCOUCHE = nbCouche;
	}
	/**
	 * Instancie un pion
	 * @param couche l'attribut x prend la valeur couche passée en paramètre.
	 * @param point l'attribut y prend la valeur point passée en paramètre.
	 */
	public Pion(int couche, int point) {
		this(couche, point, null, 0, 0);
	}
	
	/**
	 * Instancie un pion sans paramètre particulier
	 */
	public Pion()  {
		this(-1,-1);
	}
	
		// Methods
	
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
		if (Y % 2 == 0 && X != Plateau.NBCOUCHE && !presenceCoordonnee(X + 1, Y))

			this.possibilite.add(Deplacement.HAUT);
		if (Y % 2 == 0 && X != COUCHEPRINCIPALE && !presenceCoordonnee(X - 1, Y))

			this.possibilite.add(Deplacement.BAS);
		if (!presenceCoordonnee(X, (Y + 1) % nombrePionDansUneCouche))

			this.possibilite.add(Deplacement.DROITE);
		if (!presenceCoordonnee(X, (Y - 1) % nombrePionDansUneCouche))

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
						
				 /**   @TODO faire un / modulo        */
				return true;
			// on modifie le X en lui attribuant la valeur de lui même
				// plus la direction dans laquelle il veut se diriger
			}
		return false;
	}

	/**
	 * 
	 * @param X coordonnée
	 * @param Y coordonnée
	 * @return s'il existe un pion à l'emplacement (X,Y)
	 */
	
	public boolean presenceCoordonnee(int X, int Y) {
		for (Pion p : Plateau.getListSommet())
			if (p.coordonnees.getX() == X && p.coordonnees.getY() == Y)
				return true;

		return false;
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
