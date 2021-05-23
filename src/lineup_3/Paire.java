package lineup_3;

/**
 * <p>Une paire de deux entiers</p>
 * <p>Cette paire regroupe deux entiers x et y.</p>
 * <p>Cela permet de simplifier l'utilisation de coordonnées en deux dimensions, tels que pour l'emplacement d'un point dans un canvas, ses coordonnées de plateau ou la taille d'une matrice par exemple.</p>
 * <p>
 * 	De plus, de nombreuses méthodes sont ajoutées et permettent de simplifier certaines tâches.<br>
 * 	Telles que la somme des attributs, la moyenne de ceux-ci, la possibilité de les inverser, d'ajouter ou enlever une valeur à l'un d'eux.<br>
 * 	Des opérations entre deux paires sont également réalisables.
 * </p>
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</ a>
 */
public class Paire {
	/* ATTRIBUTS ______________________________ */
		/**
		 * <p>Le premier attribut de la paire.</p>
		 * <p>Peut correspondre aux abscisses dans un repère ou à la couche d'un plateau à laquelle se trouve un pion</p>
		 */
		private int x;
		
		/** 
		 * <p>Le second attribut de la paire.</p>
		 * <p>Peut correspondre aux ordonnées dans un repère ou à l'emplacement sur une couche où le pion se trouve.</p>
		 */
		private int y;
		
		
		
		
		
	/* CONSTRUCTEUR ______________________________ */
		/**
		 * Constructeur d'une paire avec deux entiers.
		 * 
		 * @param first  Le premier entier de la paire, soit x.
		 * @param second Le second entier de la paire, soit y.
		 */
		public Paire(int first, int second) {
			x = first;
			y = second;
		}
		
		
		
		
		
	/* ACCESSEURS ET MUTATEURS ______________________________ */
		/**
		 * Récupère le premier attribut.
		 * 
		 * @return La valeur du premier entier
		 */
		public int getX() { return x; }
	
		/**
		 * Modifie le premier attribut.
		 * 
		 * @param first Le premier attribut, soit x.
		 */
		public void setX(int first) { x = first; }
	
		/**
		 * Récupère deuxième attribut.
		 * 
		 * @return La valeur du deuxième entier
		 */
		public int getY() { return y; }
	
		/**
		 * Modifie le deuxième attribut.
		 * 
		 * @param second Le second attribut, soit y.
		 */
		public void setY(int second) { y = second; }
		
		
		
		
		
	/* HASHCODE ET EQUALS ______________________________ */
		/**
		 * Génère un hash de la paire.
		 * 
		 * @return Un hash code.
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			
			result = prime * result + x;
			result = prime * result + y;
			
			return result;
		}
	
	
	
		/**
		 * <p>Compare deux paires.</p>
		 * <p>Renvoie true si un objet est comparé avec lui-même, et si tous leurs attributs correspondent lorsque le paramètre est bien une paire.</p>
		 * 
		 * @param obj L'objet à comparer avec la paire.
		 * 
		 * @return - true si la paire est la même que l'objet passé en paramètre<br>
		 *         - false s'ils sont différents
		 */
		@Override
		public boolean equals(Object obj) {
			// si l'objet passé en paramètre est l'objet courant
			if (this == obj)
				return true;
			
			// si null est passé en paramètre
			if (obj == null)
				return false;
			
			// si les deux objets sont de classe différente
			if (getClass() != obj.getClass())
				return false;
			
			// finalement, on vérifie que tous les éléments correspondent bien entre eux.
			Paire other = (Paire) obj;
			
			return x == other.x && y == other.y;
		}
		
		
		
		
		
	/* TOSTRING ______________________________ */
		/**
		 * <p>Génère une représentation de la paire</p>
		 * <p>Renvoie une chaîne de caractères contenant la représentation de la paire courante.</p>
		 * <p>Sous la forme suivante : (x; y)</p>
		 * 
		 * @return Une chaîne de caractères contenant la représentation d'une paire.
		 */
		@Override
		public String toString() { return "(" + x + "; " + y + ")"; }
		
		
		
		
		
	/* MÉTHODES ______________________________ */
		/**
		 * Inverse les deux valeurs d'une paire.
		 * 
		 * @return Une paire aux attributs échangés.
		 */
		public Paire inverser() {
			int save = x;
			x = y;
			y = save;
			
			return new Paire(x, y);
		}
		
		/**
		 * Somme des attributs
		 * 
		 * @return la somme des attributs
		 */
		public int somme() { return x + y; }
	
		/**
		 * Moyenne des attributs
		 * 
		 * @return la moyenne
		 */
		public double moyenne() { return somme() / 2.0; }
		
		/**
		 * <p>Somme de deux paires</p>
		 * <p>La paire courante est modifiée et le résultat est également renvoyé.</p>
		 * 
		 * @param terme2 La paire à ajouter.
		 * 
		 * @return Une paire résultant de la somme.
		 */
		public Paire sommeAvec(Paire terme2) {
			ajouterX(terme2.x);
			ajouterY(terme2.y);
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Soustraction de deux paires</p>
		 * <p>La paire courante est modifiée et le résultat est également renvoyé.</p>
		 * 
		 * @param terme2 La paire à enlever.
		 * 
		 * @return Une paire résultant de la soustraction.
		 */
		public Paire soustractionAvec(Paire terme2) {
			enleverX(terme2.x);
			enleverY(terme2.y);
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Augmente le premier argument</p>
		 * 
		 * @param i La valeur à ajouter.
		 * 
		 * @return Une paire.
		 */
		public Paire ajouterX(int i) {
			x += i;
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Réduis le premier argument</p>
		 * 
		 * @param i - la valeur à enlever.
		 * 
		 * @return Une paire.
		 */
		public Paire enleverX(int i) {
			ajouterX(-i);
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Augment le second argument</p>
		 * 
		 * @param i La valeur à ajouter.
		 * 
		 * @return Une paire.
		 */
		public Paire ajouterY(int i) {
			y += i;
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Réduis le second argument</p>
		 * 
		 * @param i La valeur à enlever.
		 * 
		 * @return Une paire.
		 */
		public Paire enleverY(int i) {
			ajouterY(-i);
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Augmente les arguments</p>
		 * 
		 * @param i La valeur à ajouter.
		 * 
		 * @return Une paire.
		 */
		public Paire ajouter(int i) {
			ajouterX(i);
			ajouterY(i);
			
			return new Paire(x, y);
		}
		
		/**
		 * <p>Réduis les arguments</p>
		 * 
		 * @param i La valeur à enlever.
		 * 
		 * @return Une paire.
		 */
		public Paire enlever(int i) {
			ajouter(-i);
			
			return new Paire(x, y);
		}
}
