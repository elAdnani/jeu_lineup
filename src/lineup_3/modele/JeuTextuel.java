package lineup_3.modele;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import outils.Paire;
import outils.jeu.Case;
import outils.jeu.Pion;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 7 juin 2021
 * @version 7 juin 2021 20:30:33
 */
public class JeuTextuel {
		// Class Attributes
	private static Scanner scanner = new Scanner(System.in);
	private static boolean continuer = true;
	private static int tour = 0;
	private  static Partie partie = null;
	private static Map<Joueur, Character> skinPion = new HashMap<>();
	private static AffichagePlateau at;
	
	private static Random rand = new Random();
	
	private boolean quitter() {
		System.out.println("Au revoir !");
		return continuer = false;
	}
	
	private void recommencer() {
		System.out.println("On oublie tout..");
		continuer = true;
	}
	
	private boolean quelleAction() {
		System.out.println("\t\t==== 'Que souhaitez-vous faire ?' ====");
		System.out.println("\t\t\t|1. Poser un pion");
		System.out.println("\t\t\t|2. Déplacer un pion");
		System.out.println("\t\t\t|3. Quitter\n");
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				choix = scanner.nextInt();
			}
			switch (choix) {
				case 1 : 
					if (partie.getMode()==Mode.CLASSIQUE && estUneIA()) {
						partie.utilisateur.get(tour).poserPion(lectureCase(), partie.getPlateau(),
								partie.getPlateau().NBCOUCHE, partie.getPlateau().getnbCote());
						at.affichagePlateau(partie.getPlateau().getnbCote(), skinPion);
						System.out.println(partie.getPlateau().getListeCase());
					} else if (partie.getMode()==Mode.CHIFUMI) {
						partie.utilisateur.get(tour).poserPionChifumi(lectureCase(), partie.getPlateau(),
								partie.getPlateau().NBCOUCHE, partie.getPlateau().getnbCote(), lectureNature());
						at.affichagePlateau(partie.getPlateau().getnbCote(), skinPion);
						System.out.println(partie.getPlateau().getListeCase());
					}
					if (partie.getMode()==Mode.CLASSIQUE && !estUneIA()) {
						partie.utilisateur.get(tour).poserPion(caseRandom(), partie.getPlateau(),
								partie.getPlateau().NBCOUCHE, partie.getPlateau().getnbCote());
						at.affichagePlateau(partie.getPlateau().getnbCote(), skinPion);
					} else if (partie.getMode()==Mode.CHIFUMI) {
						partie.utilisateur.get(tour).poserPionChifumi(caseRandom(), partie.getPlateau(),
								partie.getPlateau().NBCOUCHE, partie.getPlateau().getnbCote(), natureRandom());
						at.affichagePlateau(partie.getPlateau().getnbCote(), skinPion);
					}
					return continuer;
				case 2 :
					if (estUneIA()) {
						partie.utilisateur.get(tour).deplacerPion(lecturePion(), lectureDirection(), partie.getPlateau(),
							partie.getPlateau().getnbCote(), partie.getPlateau().NBCOUCHE);
						at.affichagePlateau(partie.getPlateau().getnbCote(), skinPion);
						System.out.println(partie.getPlateau().getListeCase());
					} else {
						partie.utilisateur.get(tour).deplacerPion(pionRandom(), directionRandom(), partie.getPlateau(),
								partie.getPlateau().getnbCote(), partie.getPlateau().NBCOUCHE);
						at.affichagePlateau(partie.getPlateau().getnbCote(), skinPion);
					}
					return continuer;
				case 3 :
					quitter();
					return continuer;
			}
		} catch (InputMismatchException e) {
			System.out.println("Vous n'avez pas entrer un nombre");
		}
		return continuer;
	}
	
	private String lectureDirection() {
		System.out.println("\t\t==== 'Dans quelle direciton souhaitez vous allez ?' ====");
		System.out.println("\t\t\t|1. sens horaire");
		System.out.println("\t\t\t|2. sens anti-horaire");
		System.out.println("\t\t\t|3. couche supérieure");
		System.out.println("\t\t\t|4. couche inférieure");
		System.out.println("\t\t\t|5. Quitter\n");
		System.out.print("Entrez votre choix : ");

		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				choix = scanner.nextInt();
			}
			switch (choix) {
				case 1 : 
					return "droite";
				case 2 :
					return "gauche";
				case 3 :
					return "haut";
				case 4 :
					return "bas";
				case 5 :
					quitter();
			}
		} catch (InputMismatchException e) {
			System.out.println("Vous n'avez pas entrer un nombre");
		}
		return null;
	}

	private Pion lecturePion() {
		return lectureCase().getPion();	
	}

	private Case lectureCase() {
		System.out.print("Entrez des coordonnées comme suit -> X;X : ");
		String[] coordonnees = new String[2];
				coordonnees = scanner.next().split(";");
		return partie.getPlateau().trouverCase(new Paire(Integer.valueOf(coordonnees[0]), Integer.valueOf(coordonnees[1])));
	}
	
	private Nature lectureNature() {
		System.out.print("Choisissez la nature du Pion que vous souhaitez : ");
		return Nature.valueOf(scanner.next());
	}
	
	private boolean estUneIA() {
		return partie.utilisateur.get(1).getPseudo().equals("IA");
	}
	
	private Pion pionRandom() {
		return caseRandom().getPion();	
	}
	
	private Case caseRandom() {
		return partie.getPlateau().trouverCase(new Paire(rand.nextInt(partie.getPlateau().getnbCote()*2), rand.nextInt(partie.getPlateau().NBCOUCHE)));
	}
	
	private Nature natureRandom() {
		return Nature.values()[rand.nextInt(4)+1];
	}
	
	private String directionRandom() {
		try {
			int choix = rand.nextInt(4)+1;
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				choix = scanner.nextInt();
			}
			switch (choix) {
				case 1 : 
					return "droite";
				case 2 :
					return "gauche";
				case 3 :
					return "haut";
				case 4 :
					return "bas";
				case 5 :
					quitter();
			}
		} catch (InputMismatchException e) {
			System.out.println("Vous n'avez pas entrer un nombre");
		}
		return null;
	}

	public static void start(Partie p) {
		at = new AffichagePlateau(p.getPlateau());
		skinPion.put(p.utilisateur.get(0), '#');
		skinPion.put(p.utilisateur.get(1), '$');
		
		while (continuer) {
			JeuTextuel jeu = new JeuTextuel();
			System.out.println(p.getList());
			partie = p;
			jeu.quelleAction();
		}
	}
}
