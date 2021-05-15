package lineup_3;

/**
 * Cette classe représente une Case de plateau puvant stocker différentes infos comme sa disponibilité, si elle possède
 * une caractéristique spéciale(comme un piège) ou autre.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 16 mai 2021
 * @version 16 mai 2021 00:04:58
 */
public class Case {
		// Class Attributes
	/**
	 * Représente la disponibilité de la case. Par défaut estLibre est à true.
	 */
	private boolean estLibre = true;
	
	/**
	 * Représente la présence d'un piège. Initialement à faux.
	 */
	private boolean estPiege = false;
	
	/**
	 * Représente le Pion qui occupe la case courante.
	 */
	private Pion pion;
	
	public boolean isEstLibre() {
		return estLibre;
	}



	public boolean isEstPiege() {
		return estPiege;
	}

	
			// Constructors

	/**
	 * Ceci est un constructeur vide, car une Case ne contient que des paramètres prédéfinis.
	 */
	public Case() {}
	
			// Methods
	
	/**
	 * addPion permet de poser un Pion sur la Case courante.
	 * @param p Représente le Pion  que l'on souhaite poser.
	 * @return 	Retourne vrai si le Pion s'est bien poser, faux sinon.
	 */
	public boolean addPion(Pion p) {
		this.pion = p;
		
		if (this.pion == p) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * removePion permet d'enlever le Pion de la Case courante.
	 * @param p Représente le Pion que l'on souhaite enlever.
	 * @return	Retourne vrai si le Pion s'est bien enlevé, faux sinon.
	 */
	public boolean removePion(Pion p) {
		this.pion = null;
		
		if (this.pion == null) {
			return true;
		} else {
			return false;
		}
	}
}
