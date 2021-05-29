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
		
		System.out.println("\n==== 'Les joueurs recoivent respectivement leurs mains' ====\n");
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
		
			/*On pose des Pion pour voir si toutes les mises à jour se font*/
//		joueur1.poserPion(case1);
//		System.out.println("\n==== 'Le joueur1 pose un premier Pion' ====\n");
//		System.out.println(joueur1);
//		System.out.println(joueur1.getPion());
//		
//		joueur2.poserPion(case2);
//		System.out.println("\n==== 'Le joueur2 pose un premier Pion' ====\n");
//		System.out.println(joueur2);
//		System.out.println(joueur2.getPion());
//		
//		joueur1.poserPion(case3);
//		System.out.println("\n==== 'Le joueur1 pose un deuxième Pion' ====\n");
//		System.out.println(joueur1);
//		System.out.println(joueur1.getPion());
//		
//		joueur2.poserPion(case4);
//		System.out.println("\n==== 'Le joueur2 pose un deuxième Pion' ====\n");
//		System.out.println(joueur2);
//		System.out.println(joueur2.getPion());
		
			/*On sait que les mises à jour sont faites lors de la pose de Pion.
			 * On peut donc tous les poser pour passer à l'étape suivante : Les déplacements*/
//		for (int i = 0; i < joueur1.getMain().getMain().length; i++) {
//			Paire tmp = new Paire(0, i);
//			joueur1.poserPion(plateauPartie.trouverCase(tmp));
//		}
//		System.out.println("\n==== '"+ joueur1.getJoueur() +" pose ses Pions' ====\n");
//		
//		for (int i = 0; i < joueur2.getMain().getMain().length; i++) {
//			Paire tmp = new Paire(1, i);
//			joueur2.poserPion(plateauPartie.trouverCase(tmp));
//		}
//		System.out.println("\n==== '"+ joueur2.getJoueur() +" pose ses Pions' ====\n");
		
		
			/*On pose un Pion et on essaie de le déplacer dans des endroits libre et non-libre et
			 * on observe le comportement des Cases et du Pion*/
		System.out.println("\n==== '"+ joueur1.getJoueur() + " pose un Pion' ====\n");
		joueur1.poserPion(case3);
		System.out.println(case3);
		
		System.out.println("\n==== '" + joueur1.getJoueur() + " déplace le Pion vers une case libre' ====\n");
		joueur1.deplacerPion(case3.getPion(), "haut", plateauPartie.getListeCase());//TODO corriger le pb de case3 et case4 deux case aux mêmes coordonnées
		System.out.println(case3);
		System.out.println(case4);//TODO corriger la classe voisinsDe(T sommet)
		
		System.out.println("\n==== '" + joueur1.getJoueur() + " re-déplace le Pion vers l'ancienne case' ====\n");
		joueur1.deplacerPion(case4.getPion(), "bas", plateauPartie.getListeCase());
		System.out.println(case4);
		System.out.println(case3);
		
		System.out.println("\n==== '"+ joueur2.getJoueur() + " pose un Pion' ====\n");
		joueur2.poserPion(case2);
		System.out.println(case2);
		
		System.out.println("\n==== '" + joueur1.getJoueur() + " tante de déplacer son Pion vers une case occupée' ====\n");
		joueur2.deplacerPion(case3.getPion(), "bas", plateauPartie.getListeCase());
		System.out.println(case3);
		System.out.println(case3.getPion());
		System.out.println(case2);
		System.out.println(case2.getPion());
		
		System.out.println("\n==== '" + joueur2.getJoueur() + " tante de déplacer son Pion d'un tour complet dans le sens anti-horiare' ====\n");
		joueur2.deplacerPion(case2.getPion(), "gauche", plateauPartie.getListeCase());
		joueur2.deplacerPion(case1.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case1);//TODO Problème lors du déplacement de (0,0) à (0,5)

		for (int i = 5; i >= 0; i--) {
			Paire tmp = new Paire(0, i);
			joueur2.deplacerPion(plateauPartie.trouverCase(tmp).getPion(), "gauche", plateauPartie.getListeCase());
		}
		System.out.println(case2);
	}
}
