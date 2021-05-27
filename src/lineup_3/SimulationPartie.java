package lineup_3;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 25 mai 2021
 * @version 25 mai 2021 22:25:36
 */
public class SimulationPartie {
	public static void main(String[] args) {
		Parametres parametrePartie = new Parametres(3, 3, false, false, 6);
		
		//TODO Adnan : doit écrire une méthode pour initialiser une liste de Case.
		
		//TODO Adnan : instancie un plateau.
		Plateau plateauPartie = new PlateauPolynomial(parametrePartie.getNBCOTE());
		plateauPartie.generationDuPlateau();
		
		Joueur joueur1 = new Joueur("Joueur1", parametrePartie);
		Joueur joueur2 = new Joueur("Joueur2", parametrePartie);
		
		Case case1 = new Case(); 
		case1 = plateauPartie.trouverCase(new Paire(0,1));
		
		joueur1.getPion().setC(case1);
		
		System.out.println(plateauPartie);
		System.out.println();
		System.out.println(joueur1);
		System.out.println(joueur2);
		System.out.println(joueur1.getPion());
		
	}
}
