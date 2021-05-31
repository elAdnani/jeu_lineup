package outils;

import java.util.Arrays;

/**
 * <p>Gestion d'une matrice contenant des nombres.</p>
 * <p>Cette classe permet de créer et utiliser une matrice comme le permettent les mathématiques.<br>
 * <p>Les opérations basiques telles que l'addition, la soustraction et la multiplication sont possibles.</p>
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</a>
 * @version 31 mai 2021 20:00:00
 */
public class Matrice {
	/* ATTRIBUTS ______________________________ */
		/** 
		 * <p>Dimensions de la matrice</p>
		 * <p>Le nombre de lignes est stocké dans le premier attribut alors que le nombre de colonnes se trouve dans le second attribut.</p>
		 */
		private Paire taille;
		
		/**	
		 * <p>La matrice.</p>
		 * <p>Elle est représenté sous forme de tableau à deux entrées</p>
		 * <p>La première entrée est considérée comme contenant les lignes alors que la seconde contient les colonnes</p>
		 */
		private double[][] matrice;
		
		
		
		
		
	/* CONSTRUCTEURS ______________________________ */
		/**	
		 * Création d'une matrice à partir du tableau de double qui lui est passé en paramètre.
		 * 
		 * @param matrice Un tableau de nombres contenant les valeurs de la future matrice.
		 */
		public Matrice(double[][] matrice) {
			// Instantiation de la nouvelle matrice
			this(matrice.length, matrice[0].length);
			
			// ajout de la matrice à l'objet créé
			for (int ligne = 0; ligne < this.getNbLignes(); ligne++)
					this.matrice[ligne] = Arrays.copyOf(matrice[ligne], this.getNbColonnes());
		}
		
		/**
		 * Création d'une matrice nulle dont le nombre de lignes et le nombre de colonnes est passé en paramètres.
		 * 
		 * @param nbLignes   Le nombre de lignes de la future matrice
		 * @param nbColonnes Le nombre de colonnes de la future matrice
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
		 * Création d'une matrice nulle carrée de taille n supérieur à 0.
		 * 
		 * @param n Le nombre de lignes et de colonnes de la future matrice.
		 */
		public Matrice(int n) {
			this(n, n);
		}
		
		/**
		 * Création d'une matrice nulle carrée de taille n supérieur à 0.
		 * 
		 * @param m Le nombre de lignes et de colonnes de la future matrice.
		 */
		public Matrice(Matrice m) {
			this(m.matrice);
		}
		
		
		
		
		
	/* GETTER ______________________________ */
		/** 
		 * Récupère le tableau contenant les valeurs de la matrice.
		 * 
		 * @return Un tableau à deux entrées contenant les valeurs de la matrice
		 */
		public double[][] getMatrice() {
			double[][] matrice = new double[this.getNbLignes()][this.getNbColonnes()];
			
			for (int ligne = 0; ligne < this.getNbLignes(); ligne++)
				matrice[ligne] = Arrays.copyOf(this.matrice[ligne], this.getNbColonnes());
			
			return matrice;
		}
		
		/** 
		 * Nombre de lignes.
		 * 
		 * @return Un entier contenant le nombre de lignes
		 */
		public int getNbLignes() {
			return this.taille.getX();
		}
		
		/** 
		 * Nombre de colonnes.
		 * 
		 * @return Un entier contenant le nombre de colonnes
		 */
		public int getNbColonnes() {
			return this.taille.getY();
		}
		
		
		
		
		
	/* TOSTRING ______________________________ */
		/**
		 * Affichage du contenu de la matrice.
		 * 
		 * @return une chaîne de caractères représentant la matrice souhaitée.
		 */
		@Override
		public String toString() {
			// Initialisation
			String resultat = ""; // variable à laquelle ajouter l'affichage
			int[] dimensionColonnes = new int[this.getNbColonnes()]; // dimensions nécessaires pour chaque colonne
			
			// pour paramétrer chaque dimension,
			for (int colonne = 0; colonne < dimensionColonnes.length; colonne++)
				// on explore chaque ligne
				for (int ligne = 0; ligne < this.getNbLignes(); ligne++) {
					// on regarde la taille d'affichage du contenu de la cellule
					int tailleContenu = String.valueOf(this.read(ligne, colonne)).length();
					
					// si c'est la première cellule ou la plus grande,
					if (tailleContenu > dimensionColonnes[colonne])
						// alors on la conserve pour la suite
						dimensionColonnes[colonne] = tailleContenu;
				}
			
			// pour chaque ligne et chaque colonne,
			for (int ligne = 0; ligne < this.getNbLignes(); ligne++) {
				for (int colonne = 0; colonne < this.getNbColonnes(); colonne++) {
					// on place devant chaque affichage le nombre d'espaces nécessaires pour aligner verticalement sur la droite.
					int tailleContenu = String.valueOf(this.read(ligne, colonne)).length();
					
					for (int nbEspace = 0; nbEspace < dimensionColonnes[colonne] - tailleContenu; nbEspace++)
						resultat += " ";
					
					resultat += this.read(ligne, colonne) + "  ";
				}
				resultat += "\n\n";
			}
			
			// on retourne le résultat attendu
			return resultat;
		}
		
		
		
		
		
	/* MÉTHODES ______________________________ */
		/**
		 * Vérifie si la case aux coordonnées données existe
		 * 
		 * @param ligne La ligne visée
		 * @param colonne La colonne visée
		 * 
		 * @return - true si la case appartient au tableau<br>
		 *         - false sinon
		 */
		public boolean canRead(int ligne, int colonne) {
			return ligne   >= 0 || ligne   < this.getNbLignes() 
				|| colonne >= 0 || colonne < this.getNbColonnes();
		}
		
		/**
		 * Lis le contenu d'une case aux coordonnées données
		 * 
		 * @param ligne Ligne de la case à lire
		 * @param colonne Colonne de la case à lire
		 * 
		 * @return Ce que contient la case
		 */
		public double read(int ligne, int colonne) {
			return this.matrice[ligne][colonne];
		}
		
		/**
		 * Écris une valeur aux coordonnées précisées.
		 * 
		 * @param ligne La ligne de la case
		 * @param colonne La colonne de la case
		 * @param valeur La valeur à attribuer
		 * 
		 * @return - true si la valeur a été modifiée<br>
		 *         - false s'il y a une erreur dans les coordonnées
		 */
		public boolean write(int ligne, int colonne, double valeur) {
			if (this.canRead(ligne, colonne)) {
				this.matrice[ligne][colonne] = valeur;
				return true;
			} else 
				return false;
		}
		
		/**
		 * Addition de deux matrices.
		 * 
		 * @param plus La matrice à ajouter.
		 * 
		 * @return Le résultat de l'addition.
		 */
		public Matrice addition(Matrice plus) {
			Matrice resultat = new Matrice(this);
			
			// si la matrice passée en paramètre a les mêmes dimensions que notre matrice
			if (this.getNbLignes() == plus.getNbColonnes() && this.getNbLignes() == plus.getNbColonnes())
				
				// alors, pour chaque case, ajouter à notre matrice la valeur de la deuxième.
				for (int ligne = 0; ligne < this.getNbLignes(); ligne++)
					for (int colonne = 0; colonne < this.getNbColonnes(); colonne++)
						resultat.write(ligne, colonne, this.read(ligne, colonne) + plus.read(ligne, colonne));
			
			return resultat;
		}
		
		/**
		 * Soustraction de deux matrices.
		 * 
		 * @param moins La matrice à enlever.
		 * 
		 * @return Le résultat de la soustraction.
		 */
		public Matrice soustraction(Matrice moins) {
			// additionne l'opposé du deuxième terme au premier terme, ce qui revient à soustraire au premier terme le second.
			return this.addition(moins.multiplication(-1));
		}
	
		/**
		 * Multiplication d'une matrice par un réel.
		 * 
		 * @param facteur Le facteur du produit
		 * 
		 * @return Le résultat de la multiplication
		 */
		public Matrice multiplication(double facteur) { 
			Matrice resultat = new Matrice(this);		
			// on multiplie chaque case par l'entier passé en paramètre
			for (int ligne = 0; ligne < this.getNbLignes(); ligne++)
				for (int colonne = 0; colonne < this.getNbColonnes(); colonne++)
					resultat.write(ligne, colonne, this.read(ligne, colonne) * facteur);
			
			return resultat;
		}
		
		/**
		 * Multiplication de deux matrices.
		 * 
		 * @param facteur La matrice servant à la multiplication.
		 * 
		 * @return Le résultat de la multiplication.
		 */
		public Matrice multiplication(Matrice facteur) {
			Matrice resultat = new Matrice(this);
			
			// si la matrice passée en paramètres peut être multipliée avec notre matrice
			if (this.getNbColonnes() == facteur.getNbLignes()) 
				resultat = new Matrice(this.getNbLignes(), facteur.getNbColonnes());
				
				// pour chaque case de la matrice résultat
				for (int ligneResultat = 0; ligneResultat < resultat.getNbLignes(); ligneResultat++)
					for (int colonneResultat = 0; colonneResultat < resultat.getNbColonnes(); colonneResultat++)
						
						// on effectue la somme des multiplications de chaque termes "liés" que l'on stock dans le résultat
						for (int n = 0; n < this.getNbColonnes(); n++)
							resultat.write(ligneResultat, colonneResultat, resultat.read(ligneResultat, colonneResultat) + this.read(ligneResultat, n) * facteur.read(n, colonneResultat));
			
				return resultat;
		}
		
		/**
		 * Vérifie si la matrice est nulle.
		 * 
		 * @return - true si la matrice donnée est nulle<br>
		 *         - false si elle ne l'est pas
		 */
		public boolean estNulle() {
			boolean verifie = true;
			
			int ligne   = 0;
			while (verifie && ++ligne < this.getNbLignes()) {
				int colonne = 0;
				while (verifie && ++colonne < this.getNbColonnes())
					verifie = verifie && this.read(ligne, colonne) == 0;
			}
			
			return verifie;
		}
		
		/**
		 * Vérifie si la matrice est carrée.
		 * 
		 * @return - true si la matrice est carrée<br>
		 *         - false elle ne l'est pas
		 */
		public boolean estCarre() {
			return this.getNbLignes() == this.getNbColonnes();
		}
		
		/**
		 * Vérifie si la matrice symétrique.
		 * 
		 * @return - true si la matrice est symétrique<br>
		 *         - false elle ne l'est pas
		 */
		public boolean estSymetrique() {
			if (this.estCarre()) {
				boolean verifie = true;
				
				int x = 0;
				while (verifie && ++x < this.getNbLignes() - 1) {
					int y = 1 + x;
					while (verifie && ++y < this.getNbColonnes())
						verifie = this.read(x, y) == this.read(y, x);
				}
					
				return verifie;
			} else
				return false;
		}
}