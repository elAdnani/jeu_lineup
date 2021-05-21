package lineup_3;

/**
 * Cette classe regroupe deux entiers x et y sous forme de paire.<br>
 * L'intérêt de faire ceci est de simplifier l'utilisation de coordonnées en deux dimensions, tels que pour l'emplacement d'un point ou la taille d'une matrice par exemple.<br>
 * De plus, de nombreuses méthodes sont ajoutées et permettent de simplifier de certaines tâches.
 * Telles que la somme des attributs, la moyenne de ceux-ci, la possibilité de les inverser, d'ajouter ou enlever une valeur à l'un d'eux. Des opérations entre deux paires sont également réalisables.
 * 
 * @author <a href="mailto:alexis.bonal.etu@univ-lille.fr">BONAL Alexis</ a>
 */
public class Paire {
	/**
	 * x correspond à la couche sur laquelle le pion se trouve.
	 */
	private int x;
	
	/**
	 * y correspond au point de la couche x sur lequel le pion se trouve.
	 */
	private int y;	
	
	
	/**
	 * Initialisation d'une paire d'entiers en fonction des entiers passés en paramètre.
	 * @param first
	 * @param second
	 */
	public Paire(int first, int second) {
		this.x = first;
		this.y = second;
	}



	/**
	 * Permet d'obtenir le premier attribut
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
		this.x = first;
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
		this.y = second;
	}


	
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
		return this.x == other.x && this.y == other.y;
	}
	
	/**
	 * Permet d'inverser les deux valeurs de la paire courante.
	 */
	public void inverser() {
		int save = this.x;
		this.x = this.y;
		this.y = save;
	}
	
	/**
	 * Inverse les deux attributs de la paire passée en paramètre dans une nouvelle paire qui sera retournée.
	 * 
	 * @param p - la paire de référence
	 * @return une paire ayant l'inverse des attributs de la paire de référence.
	 */
	public static Paire inverser(Paire p) {
		return new Paire(p.y, p.x);
	}
	
	/**
	 * Effectue la somme des attributs de la paire courante et renvoie le résultat 
	 * 
	 * @return la somme des attributs
	 */
	public int somme() {
		return this.x + this.y;
	}
	
	/**
	 * Effectue la somme des attributs de la paire passée en paramètre et renvoie le résultat
	 * 
	 * @param p - une paire quelconque
	 * @return la somme des attributs de la paire donnée
	 */
	public static int somme(Paire p) {
		return p.x + p.y;
	}

	/**
	 * Effectue la moyenne des attributs de la paire courante et renvoie le résultat
	 * 
	 * @return la moyenne
	 */
	public double moyenne() {
		return (double) this.somme() / 2.0;
	}
	
	/**
	 * Effectue la moyenne des attributs de la paire passée en paramètre et renvoie le résultat.
	 * 
	 * @param p - une paire quelconque
	 * @return la moyenne de la paire
	 */
	public static double moyenne(Paire p) {
		return (double) somme(p) / 2.0;
	}
	
	/**
	 * Effectue la somme de la paire courante avec une paire donnée en paramètre et modifie la paire courante.
	 * 
	 * @param terme2 - paire à ajouter à la paire courante
	 */
	public void sommeAvec(Paire terme2) {
		this.ajouterX(terme2.x);
		this.ajouterY(terme2.y);
	}
	
	/**
	 * Effectue la somme entre les deux paire passées en paramètre sans les modifier et renvoie une nouvelle paire résultant de cette somme.
	 * 
	 * @param terme1 - premier terme de la somme
	 * @param terme2 - deuxième terme de la somme
	 * @return une paire résultant de la somme des deux termes.
	 */
	public static Paire sommeEntre(Paire terme1, Paire terme2) {
		int x = terme1.x + terme2.x; // somme du premier attribut
		int y = terme2.y + terme2.y; // somme du second attribut
		
		return new Paire(x, y);
	}
	
	/**
	 * Effectue la soustraction de la paire courante avec une paire donnée en paramètre et modifie la paire courante.
	 * 
	 * @param terme2 - paire à enlever à la paire courante
	 */
	public void soustractionAvec(Paire terme2) {
		this.enleverX(terme2.x);
		this.enleverY(terme2.y);
	}
	
	/**
	 * Effectue la soustraction entre les deux paire passées en paramètre sans les modifier et renvoie une nouvelle paire résultant de cette somme.
	 * 
	 * @param terme1 - premier terme de la soustraction
	 * @param terme2 - deuxième terme de la soustraction
	 * @return une paire résultant de la soustraction des deux termes.
	 */
	public static Paire soustractionEntre(Paire terme1, Paire terme2) {
		int x = terme1.x - terme2.x; // soustraction du premier attribut
		int y = terme2.y - terme2.y; // soustraction du second attribut
		
		return new Paire(x, y);
	}
	
	/**
	 * Permet d'augmenter la valeur du premier argument d'une unité.
	 */
	public void ajouterX() {
		this.ajouterX(1);
	}
	
	/**
	 * Permet d'augmenter la valeur du premier argument avec la valeur du paramètre donné.
	 * 
	 * @param i - la valeur à ajouter au premier argument.
	 */
	public void ajouterX(int i) {
		this.x += i;
	}
	
	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le premier argument vaut une unité de plus que le premier argument de la paire passée en paramètres.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * 
	 * @return une paire ayant son premier attribut avec une unité de plus que la paire de référence.
	 */
	public static Paire ajouterX(Paire p) {
		return ajouterX(p, 1);
	}
	
	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le premier argument vaut le nombre donné d'unité de plus que le premier argument de la paire passée en paramètres.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * @param i - entier à ajouter au premier attribut de la paire de référence.
	 * 
	 * @return une paire p ayant son premier attribut avec un nombre donné d'unité de plus que la paire de référence.
	 */
	public static Paire ajouterX(Paire p, int i) {
		return new Paire(p.x + i, p.y);
	}

	/**
	 * Permet de réduire la valeur du premier argument d'une unité.
	 */
	public void enleverX() {
		this.enleverX(1);;
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
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le premier argument vaut une unité de moins que le premier argument de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * 
	 * @return une paire ayant son premier attribut avec une unité de moins que la paire de référence.
	 */
	public static Paire enleverX(Paire p) {
		return ajouterX(p, -1);
	}
	
	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le premier argument vaut le nombre donné d'unité de moins que le premier argument de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * @param i - entier à enlever au premier attribut de la paire de référence.
	 * 
	 * @return une paire p ayant son premier attribut avec un nombre donné d'unité de moins que la paire de référence.
	 */
	public static Paire enleverX(Paire p, int i) {
		return ajouterX(p, -i);
	}
	
	/**
	 * Permet d'augmenter la valeur du deuxième argument d'une unité.
	 */
	public void ajouterY() {
		++this.y;
	}
	
	/**
	 * Permet d'augmenter la valeur du deuxième argument avec la valeur du paramètre donné.
	 * 
	 * @param i - la valeur à ajouter au deuxième argument.
	 */
	public void ajouterY(int i) {
		this.y += i;
	}

	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le deuxième argument vaut une unité de plus que le deuxième argument de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * 
	 * @return une paire ayant son deuxième attribut avec une unité de plus que la paire de référence.
	 */
	public static Paire ajouterY(Paire p) {
		return ajouterY(p, 1);
	}
	
	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le deuxième argument vaut le nombre donné d'unité de plus que le premier argument de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * @param i - entier à ajouter au deuxième attribut de la paire de référence.
	 * 
	 * @return une paire p ayant son deuxième attribut avec un nombre donné d'unité de plus que la paire de référence.
	 */	
	public static Paire ajouterY(Paire p, int i) {
		return new Paire(p.x, p.y + i);
	}

	/**
	 * Permet de réduire la valeur du deuxième argument d'une unité.
	 */
	public void enleverY() {
		--this.y;
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
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le deuxième argument vaut une unité de moins que le deuxième argument de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * 
	 * @return une paire ayant son deuxième attribut avec une unité de moins que la paire de référence.
	 */
	public static Paire enleverY(Paire p) {
		return ajouterY(p, -1);
	}
	
	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont le deuxième argument vaut le nombre donné d'unité de moins que le deuxième argument de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * @param i - entier à enlever au deuxième attribut de la paire de référence.
	 * 
	 * @return une paire p ayant son deuxième attribut avec un nombre donné d'unité de moins que la paire de référence.
	 */
	public static Paire enleverY(Paire p, int i) {
		return ajouterY(p, -i);
	}
	
	/**
	 * Permet d'augmenter la valeur de chacun des arguments d'une unité.
	 */
	public void ajouter() {
		this.ajouterX();
		this.ajouterY();
	}
	
	/**
	 * Permet d'augmenter la valeur de chacun des arguments avec la valeur du paramètre donné.
	 * 
	 * @param i - la valeur à ajouter aux arguments.
	 */
	public void ajouter(int i) {
		this.ajouterX(i);
		this.ajouterY(i);
	}

	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont tous ses arguments valent une unité de plus que ceux de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * 
	 * @return une paire ayant ses attributs avec une unité de plus que la paire de référence.
	 */
	public static Paire ajouter(Paire p) {
		return ajouter(p, 1);
	}
	
	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont tous ses arguments valent le nombre donné d'unité de plus que les arguments de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * @param i - entier à ajouter aux deux attributs de la paire de référence.
	 * 
	 * @return une paire p ayant ses attributs avec un nombre donné d'unité de plus que la paire de référence.
	 */	
	public static Paire ajouter(Paire p, int i) {
		return new Paire(p.x + i, p.y + i);
	}

	/**
	 * Permet de réduire la valeur de chacun des arguments d'une unité.
	 */
	public void enlever() {
		this.ajouter(-1);
	}
	
	/**
	 * Permet de réduire la valeur de chacun des arguments avec la valeur du paramètre donné.
	 * 
	 * @param i - la valeur à enlever aux arguments.
	 */
	public void enlever(int i) {
		this.ajouter(-i);
	}

	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont tous ses arguments valent une unité de moins que ceux de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * 
	 * @return une paire ayant ses attributs avec une unité de moins que la paire de référence.
	 */
	public static Paire enlever(Paire p) {
		return ajouter(p, -1);
	}

	/**
	 * Sans modifier la paire passée en paramètres, cette méthode renvoie une nouvelle paire dont tous ses arguments valent le nombre donné d'unité de moins que les arguments de la paire passée en paramètre.
	 * 
	 * @param p - paire de référence de laquelle le résultat se base.
	 * @param i - entier à enlever aux attributs de la paire de référence.
	 * 
	 * @return une paire p ayant ses attributs avec un nombre donné d'unités de moins que la paire de référence.
	 */
	public static Paire enlever(Paire p, int i) {
		return ajouter(p, -i);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paire [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
}
