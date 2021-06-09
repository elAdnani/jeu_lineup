package lineup_3.modele;

import java.util.InputMismatchException;
import java.util.Scanner;

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
	
	private void quitter() {
		System.out.println("Au revoir !");
		continuer = false;
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
					return true;
				case 2 :
					return false;
				case 3 :
					quitter();
					break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Vous n'avez pas entrer un nombre");
		}
	}
	
	
	
	public static void start(Partie p) {
		while (continuer) {
			//p.getList().get(tour). TODO
		}
	}
}
