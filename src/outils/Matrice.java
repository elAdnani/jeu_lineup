package outils;

/**
 * La classe matrice permet de créer et utiliser une matrice comme le permettent les mathématiques.<br>
 * Les opérations basiques telles que l'addition, la soustraction et la multiplication sont possibles.
 * La méthode du Pivot de Gauss est utilisée pour trouver l'inverse d'une matrice.
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 */
public class Matrice {
	/** Dimensions de la Matrice */
	private Paire taille;
	/**	Contient la matrice créée. */
	private double[][] matrice;
	
	
	
	/**	
	 * Ce constructeur crée une matrice à partir de celle passée en paramètre.
	 * 
	 * @param matrice - tableau de nombres contenant les valeurs de la future matrice
	 */
	public Matrice(double[][] matrice) {
		// Instantiation de la nouvelle matrice
		this(matrice.length, matrice[0].length);
		
		// ajout de la matrice à l'objet créé
		for (int ligne = 0; ligne < this.taille.getX(); ligne++)
			for (int colonne = 0; colonne < this.taille.getY(); colonne++)
				this.matrice[ligne][colonne] = matrice[ligne][colonne];
	}
	
	/**
	 * Constructeur d'une matrice avec ligne par colonne.
	 * 
	 * @param nbLignes - le nombre de lignes de la future matrice
	 * @param nbColonnes - le nombre de colonnes de la future matrice
	 */
	public Matrice(int nbLignes, int nbColonnes) {
		// On évite les valeurs négatives ou nulles !
		if (nbLignes <= 0)
			nbLignes = 1;
		if (nbColonnes <= 0)
			nbColonnes = 1;
		
		this.taille  = new Paire(nbLignes, nbColonnes);
		this.matrice = new double[nbLignes][nbColonnes];
	}
	
	/**
	 * Constructeur de matrice carrée de taille n.
	 * 
	 * @param n - nombre de lignes et de colonnes de la future matrice
	 */
	public Matrice(int n) {
		this(n, n);
	}
	
	
	
	/** 
	 * Getter, permet de récupérer la matrice créée.
	 * 
	 * @return tableau à deux entrées contenant les valeurs de la matrice
	 */
	public double[][] getMatrice() {
		double[][] matrice = new double[this.taille.getX()][this.taille.getY()];
		
		for (int ligne = 0; ligne < this.taille.getX(); ligne++)
			for (int colonne = 0; colonne < this.taille.getY(); colonne++)
				matrice[ligne][colonne] = read(ligne, colonne);
		
		return matrice;
	}
	
	
	
	/** 
	 * Getter, permet de récupérer la taille de la matrice.
	 * 
	 * @return paire contenant la taille de la matrcice
	 */
	public Paire getTaille() {
		return new Paire(this.taille.getX(), this.taille.getY());
	}
	
	
	
	
	/**
	 * Convertit la matrice courante en une chaîne de caractères.
	 * 
	 * @return une chaîne de caractères représentant la matrice souhaitée
	 */
	@Override
	public String toString() {
		// Initialisation
		String resultat = ""; // variable à laquelle ajouter l'affichage
		int[] dimensionColonnes = new int[this.taille.getY()]; // dimensions nécessaires pour chaque colonne
		
		// pour paramétrer chaque dimension,
		for (int colonne = 0; colonne < dimensionColonnes.length; colonne++)
			// on explore chaque ligne
			for (int ligne = 0; ligne < this.taille.getX(); ligne++) {
				// on regarde la taille d'affichage du contenu de la cellule
				int tailleContenu = String.valueOf(this.read(ligne, colonne)).length();
				
				// si c'est la première cellule ou la plus grande,
				if (tailleContenu > dimensionColonnes[colonne])
					// alors on la conserve pour la suite
					dimensionColonnes[colonne] = tailleContenu;
			}
		
		// pour chaque ligne et chaque colonne,
		for (int ligne = 0; ligne < this.taille.getX(); ligne++) {
			resultat += "\n";
			
			for (int colonne = 0; colonne < this.taille.getY(); colonne++) {
				// on place devant chaque affichage le nombre d'espaces nécessaires pour aligner verticalement sur la droite.
				int tailleContenu = String.valueOf(this.read(ligne, colonne)).length();
				
				for (int nbEspace = 0; nbEspace < dimensionColonnes[colonne] - tailleContenu; nbEspace++)
					resultat += " ";
				
				resultat += this.matrice[ligne][colonne] + "  ";
			}
			resultat += "\n";
		}
		
		// on retourne le résultat attendu
		return resultat;
	}
	
	
	
	/**
	 * Permet de dire si une case appartient ou non au tableau.
	 * 
	 * @param ligne - ligne visée
	 * @param colonne - colonne visée
	 * @return vrai si la case appartient au tableau, faux sinon
	 */
	public boolean canRead(int ligne, int colonne) {
		return ligne   >= 0 || ligne   < this.taille.getX() 
			|| colonne >= 0 || colonne < this.taille.getY();
	}
	
	/**
	 * Permet de lire le contenu d'une case aux coordonnées données
	 * 
	 * @param ligne - de la case à lire
	 * @param colonne - de la case à lire
	 * @return le contenu de la case
	 */
	public double read(int ligne, int colonne) {
		return this.matrice[ligne][colonne];
	}
	
	/**
	 * write est une méthode permettant d'écrire la valeur d'une case aux coordonnées données par une valeur donnée.
	 * 
	 * @param ligne - ligne de la case visée
	 * @param colonne - colonne de la case visée
	 * @param value - valeur à attribuer à la case visée
	 * @return vrai si la valeur a été modifiée, faux s'il y a une erreur dans les coordonnées
	 */
	public boolean write(int ligne, int colonne, double value) {
		if (this.canRead(ligne, colonne)) {
			this.matrice[ligne][colonne] = value;
			return true;
		} else 
			return false;
	}
	
	/**
	 * Remet à zéro toutes les cases de la matrice courante.
	 * 
	 * @return la même matrice nulle que la matrice courante
	 */
	public Matrice nulle() {
		for (int ligne = 0; ligne < this.taille.getX(); ligne++)
			for (int colonne = 0; colonne < this.taille.getY(); colonne++)
				write(ligne, colonne, 0);

		return this;
	}
	
	/**
	 * Matrice avec des zéros partout en fonction du nombre de lignes et de colonnes passés en paramètres.
	 * 
	 * @param nbLignes - le nombre de lignes à créer
	 * @param nbColonnes - le nombre de colonnes à créer
	 * @return une matrice nulle dont sa taille correspond aux paramètres donnés
	 */
	public static Matrice nulle(int nbLignes, int nbColonnes) {
		return new Matrice(nbLignes, nbColonnes);
	}
	
	/**
	 * Matrice nulle calquée d'une matrice existante.
	 * 
	 * @param m - la matrice dont il faut calquer la taille
	 * @return une matrice nulle aux dimensions de la matrice passée en paramètre
	 */
	public static Matrice nulle(Matrice m) {
		return nulle(m.taille.getX(), m.taille.getX());
	}
	
	/**
	 * Permet d'additionner à la première matrice, une autre matrice de même taille.
	 * 
	 * @param terme2 - terme à ajouter à la matrice courante
	 * @return la réussite du calcul
	 */
	public boolean addition(Matrice terme2) {
		
		// si la matrice passée en paramètre a les mêmes dimensions que notre matrice
		if (this.taille.getX() == terme2.taille.getY() && this.taille.getX() == terme2.taille.getY()) {
			
			// alors, pour chaque case, ajouter à notre matrice la valeur de la deuxième.
			for (int ligne = 0; ligne < this.taille.getX(); ligne++)
				for (int colonne = 0; colonne < this.taille.getY(); colonne++)
					this.write(ligne, colonne, this.read(ligne, colonne) + terme2.read(ligne, colonne));

			return true;
		}
		else
			return false;
	}
	
	/**
	 * Soustrait à la matrice courante la matrice passée en paramètre.
	 * 
	 * @param terme2 - matrice à enlever à la matrice courante
	 * @return la réussite de la soustraction
	 */
	public boolean soustraction(Matrice terme2) {
		// additionne l'opposé du deuxième terme au premier terme, ce qui revient à soustraire au premier terme le second.
		return this.addition(terme2.multiplication(-1));
	}

	/**
	 * Multiplie chaque valeur de la matrice courante par le paramètre fourni.
	 * 
	 * @param reel - facteur du produit
	 * @return une matrice résultant du produit de la matrice par l'entier donné en plus d'avoir modifié la matrice courante.
	 */
	public Matrice multiplication(double reel) { // ici nous ne retournons pas un booléen car il semble toujours être possible de multiplier une matrice par un nombre quelconque.
		
		// on multiplie chaque case par l'entier passé en paramètre
		for (int ligne = 0; ligne < this.taille.getX(); ligne++)
			for (int colonne = 0; colonne < this.taille.getY(); colonne++)
				this.write(ligne, colonne, this.read(ligne, colonne) * reel);
		
		return new Matrice(this.matrice);
	}
	
	/**
	 * Multiplie la matrice courante par une matrice donnée.
	 * 
	 * @param terme2 - matrice servant au produit de la matrice courante
	 * @return la réussite de la multiplication
	 */
	public boolean multiplication(Matrice terme2) {
		
		// si la matrice passée en paramètres peut être multipliée avec notre matrice
		if (this.taille.getY() == terme2.taille.getX()) {
			Matrice resultat = new Matrice(this.taille.getX(), terme2.taille.getY());
			
			// pour chaque case de la matrice résultat
			for (int ligneResultat = 0; ligneResultat < resultat.taille.getX(); ligneResultat++)
				for (int colonneResultat = 0; colonneResultat < resultat.taille.getY(); colonneResultat++)
					
					// on effectue la somme des multiplications de chaque termes "liés" que l'on stock dans le résultat
					for (int n = 0; n < this.taille.getY(); n++)
						resultat.write(ligneResultat, colonneResultat, resultat.read(ligneResultat, colonneResultat) + this.read(ligneResultat, n) * terme2.read(n, colonneResultat));

			this.taille  = resultat.taille;
			this.matrice = resultat.matrice;
			
			return true;
		} else
			return false;
	}
	
	/**
	 * Vérifie si la matrice courante est nulle ou non
	 * 
	 * @return true si la matrice donnée est nulle, false sinon
	 */
	public boolean estNulle() {
		boolean verifie = true;
		
		int ligne   = 0;
		while (verifie && ++ligne < this.taille.getX()) {
			int colonne = 0;
			while (verifie && ++colonne < this.taille.getY())
				verifie = verifie && this.read(ligne, colonne) == 0;
		}
		
		return verifie;
	}
	
	/**
	 * Vérifie si la matrice courante est carrée ou pas
	 * 
	 * @return true si elle l'est, false sinon
	 */
	public boolean estCarre() {
		return this.taille.getX() == this.taille.getY();
	}
	
	/**
	 * Vérifie si la matrice courante est bien symétrique
	 * 
	 * @return true si la matrice est symétrique, false sinon
	 */
	public boolean estSymetrique() {
		if (this.estCarre()) {
			boolean verifie = true;
			
			int x = 0;
			while (verifie && ++x < this.taille.getX() - 1) {
				int y = 1 + x;
				while (verifie && ++y < this.taille.getY())
					verifie = this.read(x, y) == this.read(y, x);
			}
				
			return verifie;
		} else
			return false;
	}
}