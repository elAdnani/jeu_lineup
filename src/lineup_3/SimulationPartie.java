package lineup_3;

/**
 * Cette classe simule une partie en effectuant tout les cas spécifiques imaginables :<br>
 *<ul>
 * <li>poser des pions :</li>
 *   <ul>
 *    <li>sur des cases libres</li>
 *    <li>sur des cases occupées</li>
 *   </ul>
 * <li>déplacer des pions :</li>
 *  <ul>
 *   <li>vers des cases libres</li>
 *   <li>vers des cases occupées</li>
 *  </ul>
 * <li>supprimer des cases</li>
 * <li>etc...</li>
 *</ul>
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 25 mai 2021
 * @version 25 mai 2021 22:25:36
 */
public class SimulationPartie {
	public static void main(String[] args) {
		
		Parametres parametrePartie = new Parametres(3, 3, false, false, 6);
		
		PlateauPolynomial plateauPartie = new PlateauPolynomial(parametrePartie.getNBCOTE());
		plateauPartie.generationDuPlateau();
		//System.out.println(plateauPartie);
		
		Joueur joueur1 = new Joueur("Joueur1", parametrePartie);
		Joueur joueur2 = new Joueur("Joueur2", parametrePartie);
		
		System.out.println("\n==== 'Les joueurs recoivent leur main' ====\n");
		System.out.println(joueur1);
		System.out.println(joueur2);
		
		Case case1 = new Case(0,0);// En attendant d'avoir une entrée au clavier.
		Case case2 = new Case(0,1);// En attendant d'avoir une entrée au clavier.
		Case case3 = new Case(1,1);// En attendant d'avoir une entrée au clavier.
		Case case4 = new Case(2,1);// En attendant d'avoir une entrée au clavier.
		
		case1 = plateauPartie.trouverCase(case1.getCoordonnees()); // On cherche la case entrée précédemment(ici case1)dans le plateau.
		case2 = plateauPartie.trouverCase(case2.getCoordonnees()); // On cherche la case entrée précédemment(ici case2)dans le plateau.
		case3 = plateauPartie.trouverCase(case3.getCoordonnees()); // On cherche la case entrée précédemment(ici case3)dans le plateau.
		case4 = plateauPartie.trouverCase(case4.getCoordonnees()); // On cherche la case entrée précédemment(ici case4)dans le plateau.
		
		
		joueur1.poserPion(case1);
		System.out.println("\n==== 'Le joueur1 pose un premier Pion' ====\n");
		System.out.println(joueur1);
		System.out.println(joueur1.getPion());
		
		joueur2.poserPion(case2);
		System.out.println("\n==== 'Le joueur2 pose un premier Pion' ====\n");
		System.out.println(joueur2);
		System.out.println(joueur2.getPion());
		
		joueur1.poserPion(case3);
		System.out.println("\n==== 'Le joueur1 pose un deuxième Pion' ====\n");
		System.out.println(joueur1);
		System.out.println(joueur1.getPion());
		
		joueur2.poserPion(case4);
		System.out.println("\n==== 'Le joueur2 pose un deuxième Pion' ====\n");
		System.out.println(joueur2);
		System.out.println(joueur2.getPion());
		
	}
}
