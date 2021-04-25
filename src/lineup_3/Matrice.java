package lineup_3;

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
	 * Crée une matrice identité en fonction de la taille fournie en paramètre.
	 * 
	 * @param taille - correspond au nombre de ligne et de colonnes de la matrice
	 * @return une matrice identité aux dimensions de la taille
	 */
	public Matrice identite() {
		if (this.estCarre()) {
			this.nulle();
			for (int i = 0; i < this.taille.getX(); i++)
				this.write(i, i, 1);
		
			return this;
		} else 
			return Matrice.nulle(this);
	}

	/**
	 * Crée une matrice identité en fonction de la taille fournie en paramètre.
	 * 
	 * @param taille - correspond au nombre de ligne et de colonnes de la matrice
	 * @return une matrice identité aux dimensions de la taille
	 */
	public static Matrice identite(int taille) {
		// On part d'une matrice nulle
		Matrice resultat = Matrice.nulle(taille, taille);
		
		// Et on lui ajoute un 1 dans la diagonale
		for (int i = 0; i < taille; i++)
			resultat.write(i, i, 1);
		
		return resultat;
	}
	
	/**
	 * Si la matrice donnée est bien carrée, alors on crée une matrice identité aux dimensions de la matrice fournie, sinon on retourne une matrice nulle
	 * 
	 * @param m - la matrice de référence
	 * @return une matrice identité aux dimensions de la matrice de référence
	 */
	public static Matrice identite(Matrice m) {
		if (m.estCarre())
			return identite(m.taille.getX());
		else
			return Matrice.nulle(m);
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
	 * Additionne les deux matrices passées en paramètres et retourne le résultat dans une nouvelle matrice.
	 * 
	 * @param terme1 - première matrice
	 * @param terme2 - seconde matrice
	 * @return une matrice résultant de ma somme des deux termes
	 */
	public static Matrice addition(Matrice terme1, Matrice terme2) {
		Matrice resultat = new Matrice(terme1.matrice);
		
		// si l'addition s'est bien déroulée
		if (resultat.addition(terme2))
			// alors on retourne son résultat
			return resultat;
		else
			// sinon, on retourne une matrice nulle aux dimensions de celle du premier terme
			return Matrice.nulle(terme1); 
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
	 * Soustrait le premier terme donné au second terme et retourne le résultat dans une nouvelle matrice.
	 * 
	 * @param terme1 - matrice à soustraire
	 * @param terme2 - matrice à enlever du premier terme
	 * @return une matrice résultant de la soustraction
	 */
	public static Matrice soustraction(Matrice terme1, Matrice terme2) {
		// additionne l'opposé du deuxième terme au premier terme, ce qui revient à soustraire au premier terme le second.
		return addition(terme1, terme2.multiplication(-1));
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
	 * Multiplie chaque valeur de la matrice donnée par le réel fourni.
	 * 
	 * @param reel - facteur du produit
	 * @param m - matrice à multiplier
	 * @return une matrice résultant du produit de la matrice par l'entier donné.
	 */
	public static Matrice multiplication(double reel, Matrice m) {
		Matrice resultat = new Matrice(m.matrice);
		return resultat.multiplication(reel);
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
	 * Multiplie deux matrices entre elles.
	 * 
	 * @param terme1 - première matrice
	 * @param terme2 - deuxième matrice
	 * @return une matrice résultant de la multiplication des deux matrices données
	 */
	public static Matrice multiplication(Matrice terme1, Matrice terme2) {
		Matrice resultat = new Matrice(terme1.matrice);
		
		// si la multiplication s'est bien déroulée
		if (resultat.multiplication(terme2))
			// alors on retourne son résultat
			return resultat;
		else
			// sinon on retourne une matrice vide aux dimensions minimum d'un résultat de multiplication de deux matrices
			return Matrice.nulle(terme1.taille.getX(), 1);
	}
	
	/**
	 * Effectue la puissance de la matrice courante par l'entier donné
	 * 
	 * @param p - puissance
	 * @return la réussite de l'opération
	 */
	public boolean puissance(int p) {
		Matrice origine = new Matrice(this.matrice);
		boolean valide = p >= -1;
		
		if (valide && p < 0)
			valide = valide && this.inverse();
		int i = 0;
		// Tant que le calcul est possible et que l'on n'a pas atteint la puissance voulue
		while (valide && ++i < p) // on réitère i après l'avoir vérifié
			// 
			valide = valide && this.multiplication(origine);
		
		return valide;
	}
	
	/**
	 * Effectue la puissance de la matrice donnée par l'entier fourni
	 * 
	 * @param m - matrice
	 * @param p - puissance
	 * @return une matrice résultant du calcul de puissance sur la matrice par la puissance.
	 */
	public static Matrice puissance(Matrice m, int p) {
		Matrice resultat = new Matrice(m.matrice);
		
		// si le calcul s'est bien passé
		if (resultat.puissance(p))
			// alors on retourne le résultat
			return resultat;
		else
			// sinon on retourne une matrice vide aux dimensions de la matrice donnée
			return Matrice.nulle(m);
	}
	
	/**
	 * Échange les deux lignes qui lui sont passées en paramètre
	 * 
	 * @param l1 - indice de la première ligne
	 * @param l2 - indice de la seconde ligne
	 */
	public void echangerLignes(int l1, int l2) {
		if (this.canRead(l1, 0) && this.canRead(l2, 0)) {
			double[] save = this.matrice[l1];
			this.matrice[l1] = this.matrice[l2];
			this.matrice[l2] = save;
		}
	}
	
	/**
	 * Crée une nouvelle matrice basée sur celle donnée et échange deux lignes de cette nouvelle matrice qui sont spécifiées dans les paramètres
	 * 
	 * @param m - la matrice sur laquelle se baser
	 * @param l1 - indice de la première ligne
	 * @param l2 - indice de la seconde ligne
	 * @return une matrice avec les deux lignes d'échangées ou la même matrice que l'originale s'il y a une erreur dans les indices donnés
	 */
	public static Matrice echangerLignes(Matrice m, int l1, int l2) {
		Matrice resultat = new Matrice(m.matrice);
		resultat.echangerLignes(l1, l2);
		
		return resultat;
	}
	
	/**
	 * Multiplie l'une des lignes par un réel
	 * 
	 * @param n - le réel
	 * @param l - l'indice de la ligne
	 */
	public void produitLigne(double n, int l) {
		if (this.canRead(l, 0))
			for (int colonne = 0; colonne < this.taille.getY(); colonne++)
				this.write(l, colonne, n * this.read(l, colonne));
	}
	
	/**
	 * Crée une nouvelle matrice à partir de celle donnée et multiplie la ligne précisée par un réel.
	 * 
	 * @param m - la matrice de base
	 * @param n - le réel
	 * @param l - l'indice de la ligne
	 * @return une nouvelle matrice dont la ligne a été multipliée par le réel
	 */
	
	public static Matrice produitLigne(Matrice m, double n, int l) {
		Matrice resultat = new Matrice(m.matrice);
		resultat.produitLigne(n, l);
		
		return resultat;
	}
	
	/**
	 * Modifie la première ligne donnée de la matrice courante en y ajoutant les valeurs de la seconde ligne fournie
	 * 
	 * @param l1 - première ligne
	 * @param l2 - seconde ligne
	 */
	
	public void sommeLignes(int l1, int l2) {
		if (this.canRead(l1, 0) && this.canRead(l2, 0))
			for (int colonne = 0; colonne < this.taille.getY(); colonne++)
				this.write(l1, colonne, this.read(l1, colonne) + this.read(l2, colonne));
	}
	
	/**
	 * Crée une nouvelle matrice dont la première ligne a été modifiée avec les valeurs de la seconde ligne en plus
	 * 
	 * @param m - matrice de base
	 * @param l1 - indice de la première ligne
	 * @param l2 - indice de la seconde ligne
	 * @return une nouvelle matrice
	 */
	
	public static Matrice sommeLigne(Matrice m, int l1, int l2) {
		Matrice resultat = new Matrice(m.matrice);
		resultat.sommeLignes(l1, l2);
		
		return resultat;
	}
	
	
	public boolean inverse() { // TODO écrire la fonction inverse avec le pivot de Gauss
		// si la matrice est carrée
		if (this.estCarre()) {
			// et de taille 2
			if (this.taille.getX() == 2) {
				double n = this.read(0, 0) * this.read(1, 1) - this.read(0, 1) * this.read(1, 0); // a*d - b*c
				Matrice old = new Matrice(this.matrice);
				
				// alors on vérifie qu'elle est inversible
				if (n != 0) {
					// on inverse la matrice
					this.write(0, 0,  n * old.read(1, 1)); // 
					this.write(0, 1, -n * old.read(0, 1)); // | a  b | __\  n * | d -b |
					this.write(1, 0, -n * old.read(1, 0)); // | c  d |   /      |-c  a |
					this.write(1, 1,  n * old.read(0, 0)); //
					
					return true;
				} else
					return false;
			} else {
				Matrice old = new Matrice(this.matrice);
				this.identite();
				
				
			}
		} else
			return false;
	}
	
	public static Matrice inverse(Matrice m) { // TODO écrire la fonction inverse static
		return null;
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
	 * Vérifie si la matrice fournie est nulle ou non
	 * 
	 * @param m - la matrice à vérifier
	 * @return true si cette matrice est nulle, false sinon
	 */
	public static boolean estNulle(Matrice m) {
		return m.estNulle();
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
	 * Vérifie si la matrice fournie est carrée ou pas
	 * 
	 * @param m - la matrice à vérifier
	 * @return true si cette matrice est carrée, false sinon
	 */
	public static boolean estCarre(Matrice m) {
		return m.estCarre();
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
	
	
	/**
	 * Vérifie si la matrice passée en paramètre est bien symétrique
	 * @param m - la matrice à vérifier
	 * @return true si cette matrice est bien symétrique, false sinon
	 */
	public static boolean estSymetrique(Matrice m) {
		return m.estSymetrique();
	}
	
	
	public boolean estTriangulaire() { // TODO écrire estTriangulaire
		return true;
	}
	
	
	public static boolean estTriangulaire(Matrice m) { // TODO écrire la fonction statique de estTriangulaire
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
		Matrice m1 = new Matrice(3);
		m1.write(0, 0, 1);  m1.write(0, 1, 0);  m1.write(0, 2, 4);
		m1.write(1, 0, 2);  m1.write(1, 1, -1); m1.write(1, 2, 5);
		m1.write(2, 0, 30); m1.write(2, 1, 1);  m1.write(2, 2, 6);
		
		Matrice m2 = new Matrice(2);
		m2.write(0, 0, 3);
		m2.write(0, 1, 4);
		m2.write(1, 0, -2);
		m2.write(1, 1, -3);

		System.out.println(m1);
		System.out.println(m2);
	}
}