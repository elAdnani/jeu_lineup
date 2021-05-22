package lineup_3;

/**
 * Cette classe regroupe deux entiers x et y sous forme de paire.<br>
 * L'intérêt de faire ceci est de simplifier l'utilisation de coordonnées en deux dimensions, tels que pour l'emplacement d'un point dans un canvas ou ses coordonnées de plateau ou la taille d'une matrice par exemple.<br>
 * De plus, de nombreuses méthodes sont ajoutées et permettent de simplifier certaines tâches.
 * Telles que la somme des attributs, la moyenne de ceux-ci, la possibilité de les inverser, d'ajouter ou enlever une valeur à l'un d'eux. Des opérations entre deux paires sont également réalisables.
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</ a>
 */
public class Paire {
	/* ATTRIBUTS ______________________________ */
		/**
		 * Le premier attribut de la paire.
		 * Peut correspondre aux abscisses dans un repère ou à la couche d'un plateau à laquelle se trouve un pion
		 */
		private int x;
		
		/** 
		 * Le second attribut de la paire.
		 * Peut correspondre aux ordonnées dans un repère ou à l'emplacement sur une couche où le pion se trouve.
		 */
		private int y;
		
		
		
		
		
	/* CONSTRUCTEUR ______________________________ */
		/**
		 * Initialisation d'une paire d'entiers en fonction des entiers passés en paramètre.
		 * 
		 * @param first le premier entier de la paire, soit x.
		 * @param second le second entier de la paire, soit y.
		 */
		public Paire(int first, int second) {
			x = first;
			y = second;
		}
		
		
		
		
		
	/* ACCESSEURS ET MUTATEURS ______________________________ */
		/**
		 * Permet d'obtenir le premier attribut
		 * 
		 * @return la valeur du premier entier
		 */
		public int getX() {
			return x;
		}
	
		/**
		 * Permet de définir la valeur du premier attribut
		 * @param first
		 */
		public void setX(int first) {
			x = first;
		}
	
		/**
		 * Permet d'obtenir le deuxième attribut
		 * @return la valeur du deuxième entier
		 */
		public int getY() {
			return y;
		}
	
		/**
		 * Permet de définir la valeur du deuxième attribut
		 * @param second
		 */
		public void setY(int second) {
			y = second;
		}
		
		
		
		
		
	/* HASHCODE ET EQUALS ______________________________ */
		/**
		 * Renvoie une valeur de hash code pour la paire courante.
		 * 
		 * @return un hash code pour la paire courante.
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
		 * Compare l'objet spécifié en paramètre avec la paire courante.<br>
		 * Renvoie true si un objet est comparé avec lui-même, et si tous leurs attributs correspondent lorsque le paramètre est bien une paire.
		 * 
		 * @param obj - l'objet de référence avec lequel comparer la paire courante.
		 * @return true si la paire est la même que l'objet passé en paramètre.
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
		 * Renvoie une chaîne de caractères contenant la représentation de la paire courante.<br>
		 * Sous la forme suivante : (x; y)
		 * 
		 * @return une représentation sous forme de chaîne de caractères d'une paire.
		 */
		@Override
		public String toString() {
			return "(" + x + "; " + y + ")";
		}
		
		
		
		
		
	/* MÉTHODES ______________________________ */
		/**
		 * Permet d'inverser les deux valeurs de la paire courante.
		 */
		public void inverser() {
			int save = x;
			x = y;
			y = save;
		}
		
		/**
		 * Effectue la somme des attributs de la paire courante et renvoie le résultat 
		 * 
		 * @return la somme des attributs
		 */
		public int somme() {
			return x + y;
		}
	
		/**
		 * Effectue la moyenne des attributs de la paire courante et renvoie le résultat
		 * 
		 * @return la moyenne
		 */
		public double moyenne() {
			return somme() / 2.0;
		}
		
		/**
		 * Effectue la somme de la paire courante avec une paire donnée en paramètre et modifie la paire courante.
		 * 
		 * @param terme2 - paire à ajouter à la paire courante
		 */
		public void sommeAvec(Paire terme2) {
			ajouterX(terme2.x);
			ajouterY(terme2.y);
		}
		
		/**
		 * Effectue la soustraction de la paire courante avec une paire donnée en paramètre et modifie la paire courante.
		 * 
		 * @param terme2 - paire à enlever à la paire courante
		 */
		public void soustractionAvec(Paire terme2) {
			enleverX(terme2.x);
			enleverY(terme2.y);
		}
		
		/**
		 * Permet d'augmenter la valeur du premier argument avec la valeur du paramètre donné.
		 * 
		 * @param i - la valeur à ajouter au premier argument.
		 */
		public void ajouterX(int i) {
			x += i;
		}
		
		/**
		 * Permet de réduire la valeur du premier argument avec la valeur du paramètre donné.
		 * 
		 * @param i - la valeur à enlever au premier argument.
		 */
		public void enleverX(int i) {
			ajouterX(-i);
		}
		
		/**
		 * Permet d'augmenter la valeur du deuxième argument avec la valeur du paramètre donné.
		 * 
		 * @param i - la valeur à ajouter au deuxième argument.
		 */
		public void ajouterY(int i) {
			y += i;
		}
		
		/**
		 * Permet de réduire la valeur du deuxième argument avec la valeur du paramètre donné.
		 * 
		 * @param i - la valeur à enlever au deuxième argument.
		 */
		public void enleverY(int i) {
			ajouterY(-i);
		}
		
		/**
		 * Permet d'augmenter la valeur de chacun des arguments avec la valeur du paramètre donné.
		 * 
		 * @param i - la valeur à ajouter aux arguments.
		 */
		public void ajouter(int i) {
			ajouterX(i);
			ajouterY(i);
		}
		
		/**
		 * Permet de réduire la valeur de chacun des arguments avec la valeur du paramètre donné.
		 * 
		 * @param i - la valeur à enlever aux arguments.
		 */
		public void enlever(int i) {
			ajouter(-i);
		}
}
