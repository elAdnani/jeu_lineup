package lineup_3;

import java.util.HashMap;
import java.util.Map;

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
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
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
		
		Case case00 = new Case(0,0);// En attendant d'avoir une entrée au clavier.
		Case case01 = new Case(0,1);// En attendant d'avoir une entrée au clavier.
		Case case02 = new Case(0,2);// En attendant d'avoir une entrée au clavier.
		Case case03 = new Case(0,3);// En attendant d'avoir une entrée au clavier.
		Case case04 = new Case(0,4);// En attendant d'avoir une entrée au clavier.
		Case case05 = new Case(0,5);// En attendant d'avoir une entrée au clavier.
		Case case11 = new Case(1,1);// En attendant d'avoir une entrée au clavier.
		Case case21 = new Case(2,1);// En attendant d'avoir une entrée au clavier.
		
		case00 = plateauPartie.trouverCase(case00.getCoordonnees()); // On cherche la case entrée précédemment(ici case00)dans le plateau.
		case01 = plateauPartie.trouverCase(case01.getCoordonnees()); // On cherche la case entrée précédemment(ici case01)dans le plateau.
		case02 = plateauPartie.trouverCase(case02.getCoordonnees()); // On cherche la case entrée précédemment(ici case02)dans le plateau.
		case03 = plateauPartie.trouverCase(case03.getCoordonnees()); // On cherche la case entrée précédemment(ici case03)dans le plateau.
		case04 = plateauPartie.trouverCase(case04.getCoordonnees()); // On cherche la case entrée précédemment(ici case04)dans le plateau.
		case05 = plateauPartie.trouverCase(case05.getCoordonnees()); // On cherche la case entrée précédemment(ici case05)dans le plateau.
		case11 = plateauPartie.trouverCase(case11.getCoordonnees()); // On cherche la case entrée précédemment(ici case11)dans le plateau.
		case21 = plateauPartie.trouverCase(case21.getCoordonnees()); // On cherche la case entrée précédemment(ici case21)dans le plateau.
		
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
		joueur1.poserPion(case11);
		System.out.println(case11);
		
		System.out.println("\n==== '" + joueur1.getJoueur() + " déplace le Pion vers une case libre' ====\n");
		joueur1.deplacerPion(case11.getPion(), "haut", plateauPartie.getListeCase());
		System.out.println(case11);
		System.out.println(case21);//TODO corriger la classe voisinsDe(T sommet)
		
		System.out.println("\n==== '" + joueur1.getJoueur() + " re-déplace le Pion vers l'ancienne case' ====\n");
		joueur1.deplacerPion(case21.getPion(), "bas", plateauPartie.getListeCase());
		System.out.println(case21);
		System.out.println(case11);
		
		System.out.println("\n==== '"+ joueur2.getJoueur() + " pose un Pion' ====\n");
		joueur2.poserPion(case01);
		System.out.println(case01);
		
		System.out.println("\n==== '" + joueur1.getJoueur() + " tante de déplacer son Pion vers une case occupée' ====\n");
		joueur2.deplacerPion(case11.getPion(), "bas", plateauPartie.getListeCase());
		System.out.println(case11);
		//System.out.println(case3.getPion());
		System.out.println(case01);
		//System.out.println(case2.getPion());
		
		System.out.println("\n==== '" + joueur2.getJoueur() + " tante de déplacer son Pion d'un tour complet dans le sens anti-horiare' ====\n");
		joueur2.deplacerPion(case01.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case01);
		System.out.println(case00+"\n");
		
		joueur2.deplacerPion(case00.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case00);
		System.out.println(case05+"\n");
		
		joueur2.deplacerPion(case05.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case05);
		System.out.println(case04+"\n");

		joueur2.deplacerPion(case04.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case04);
		System.out.println(case03+"\n");

		joueur2.deplacerPion(case03.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case03);
		System.out.println(case02+"\n");

		joueur2.deplacerPion(case02.getPion(), "gauche", plateauPartie.getListeCase());
		System.out.println(case02);
		System.out.println(case01+"\n");
		
		System.out.println("\n==== 'On vérifie que la liste des Cases est bien mise à jour après les déplacements' ====\n");
		System.out.println(plateauPartie);
		
//		System.out.println("\n==== 'On tente d'afficher un Plateau en mode textuel' ====\n");
//		Map<Joueur, Character> skinPion = new HashMap<>();
//		skinPion.put(joueur1, '#');
//		skinPion.put(joueur2, '$');
//		plateauPartie.affichagePlateau(3, skinPion);
		
	}
}
