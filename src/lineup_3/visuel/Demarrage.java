package lineup_3.visuel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lineup_3.modele.JeuTextuel;
import lineup_3.modele.Joueur;
import lineup_3.modele.Mode;
import lineup_3.modele.Partie;
import lineup_3.modele.PlateauPolynomial;
import outils.BinarySerialization;
import outils.LectureEcritureDeFichier;
import outils.jeu.Plateau;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 5 juin 2021
 * @version 5 juin 2021 11:19:58
 */
public class Demarrage {
		// Class Attributes
	protected final static String choixTmp = "tmp"+File.separator+"choixTmp.txt";
	protected Scanner scanner = new Scanner(System.in);
	
		// Methods
	
	private void demarrerMenu() {
		System.out.println("\t\t==== 'Bienvenue dans le menu du Jeu LineUp3' ====\n");
		premierChoix();
	}
	
	private void quitter() {
		System.out.println("Au revoir !");
	}
	
	private void premierChoix() {
		System.out.println("\t\t\t|1. Lire les règles du LineUp3");
		System.out.println("\t\t\t|2. Démarrer une partie");
		System.out.println("\t\t\t|3. Quitter\n");
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				choix = scanner.nextInt();
			}
			switch (choix) {
				case 1 : 
					lireLesRegles();
					break;
				case 2 :
					demarrerUnePartie();
					break;
				case 3 :
					quitter();
					break;
			}
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Vous n'avez pas entrer un nombre");
			premierChoix();
		}
	}
	
	private void lireLesRegles() {
		LectureEcritureDeFichier.bufferedLecture("regles.txt");
		System.out.println("\n\t\t\t|1. Retourner à l'étape précédente");
		System.out.println("\t\t\t|2. Quitter\n");
		
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				if (scanner.hasNextInt()) choix = scanner.nextInt();
			}
			if (choix == 1) {
				premierChoix();
			} else if (choix == 2) {
				quitter();
			}
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Vous n'avez pas entrer un nombre");
			lireLesRegles();
		}
	}
	
	private void demarrerUnePartie() {
		System.out.println("\n\t\t==== 'Parmétrons maintenant une partie à votre goût !' ====");
		System.out.println("\n\t\t\t|1. Jouer en solo");
		System.out.println("\t\t\t|2. Jouer en 1v1");
		System.out.println("\t\t\t|3. Retourner à l'étape précédente");
		System.out.println("\t\t\t|4. Quitter\n");

		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				if (scanner.hasNextInt()) choix = scanner.nextInt();
			}
			switch (choix) {
				case 1: 
					LectureEcritureDeFichier.usingPrintWriter(choixTmp, "\t||solo||");
					ecritureDuPseudo();
					break;
				case 2:
					LectureEcritureDeFichier.usingPrintWriter(choixTmp, "\t|||1v1|||");
					ecritureDesPseudos();
					break;
				case 3:
					premierChoix();
					break;
				case 4:
					quitter();
					break;
			}
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Vous n'avez pas entrer un nombre");
			demarrerUnePartie();
		}
	}
	
	private void ecritureDuPseudo() {
		System.out.print("Entrez votre pseudo : ");
		LectureEcritureDeFichier.usingFileWriter(choixTmp, "pseudo1 : " + scanner.next() + "\n");
		LectureEcritureDeFichier.usingFileWriter(choixTmp, "pseudo2 : IA\n");
		choixDuNomrbreDeCote();
	}
	
	private void ecritureDesPseudos() {
		System.out.print("Entrez votre pseudo : ");
		String pseudo1 = scanner.next();
		LectureEcritureDeFichier.usingFileWriter(choixTmp, "pseudo1 : " + pseudo1 + "\n");
		System.out.print("Entrez celui de votre adversaire : ");
		String pseudo2 = scanner.next();
		while (pseudo2.equals(pseudo1)) {
			System.out.print("Ce pseudo existe déjà, choisissez-en un autre : ");
			pseudo2 = scanner.next();
		}
		LectureEcritureDeFichier.usingFileWriter(choixTmp, "pseudo2 : " + pseudo2 + "\n");
		choixDuNomrbreDeCote();
	}
	
	private void choixDuNomrbreDeCote() {
		System.out.println("\n\t\t\t|1. Jouer sur un plateau triangulaire");
		System.out.println("\t\t\t|2. Jouer sur un plateau carré");
		System.out.println("\t\t\t|3. Retourner à l'étape précédente");
		System.out.println("\t\t\t|4. Quitter\n");
		
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				if (scanner.hasNextInt()) choix = scanner.nextInt();
			}
			switch(choix){
				case 1:
					LectureEcritureDeFichier.usingFileWriter(choixTmp, "nombre de côté : 3\n");
					choixDuMode();
					break;
				case 2:
					LectureEcritureDeFichier.usingFileWriter(choixTmp, "nombre de côté : 4\n");
					choixDuMode();
					break;
				case 3:
					demarrerUnePartie();
					break;
				case 4:
					quitter();
					break;
			}
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Vous n'avez pas entrer un nombre");
			choixDuNomrbreDeCote();
		}
	}
	
	private void choixDuMode() {
		System.out.println("\n\t\t\t|1. Jouer en mode Classique");
		System.out.println("\t\t\t|2. Jouer en mode Chifumi");
		System.out.println("\t\t\t|3. Retourner à l'étape précédente");
		System.out.println("\t\t\t|4. Quitter\n");
		
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				if (scanner.hasNextInt()) choix = scanner.nextInt();
			}
			switch (choix) {
				case 1:
					LectureEcritureDeFichier.usingFileWriter(choixTmp, "mode : CLASSIQUE\n");
					recapitulatif();
					break;
				case 2:
					LectureEcritureDeFichier.usingFileWriter(choixTmp, "mode : CHIFUMI\n");
					recapitulatif();
					break;
				case 3:
					choixDuNomrbreDeCote();
					break;
				case 4:
					quitter();
					break;
			}
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Vous n'avez pas entrer un nombre");
			choixDuMode();
		}
	}
	
	private void recapitulatif() {
		System.out.println("\n\t\t\t==== 'Récapitulatif' ====");
		LectureEcritureDeFichier.bufferedLecture(choixTmp);
		
		System.out.println("\n\t\t\t|1. COMMENCER");
		System.out.println("\t\t\t|2. Enregistrer la configuration");
		System.out.println("\t\t\t|3. Retourner à l'étape précédente");
		System.out.println("\t\t\t|4. Quitter\n");
		
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				if (scanner.hasNextInt()) choix = scanner.nextInt();
			}
			
			switch (choix) {
				case 1:
					commencerJeu();
					break;
				case 2:
					enregistrementDeLaConfig();
					dernierChoix();
					break;
				case 3:
					choixDuNomrbreDeCote();
					break;
				case 4:
				quitter();
			}
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Vous n'avez pas entrer un nombre");
			recapitulatif();
		}
	}
	
	private void dernierChoix() {
		System.out.println("\n\t\t\t==== 'Configuration enregistrée !' ====");
		LectureEcritureDeFichier.bufferedLecture(choixTmp);
		
		System.out.println("\n\t\t\t|1. COMMENCER");
		System.out.println("\t\t\t|2. Quitter\n");
		
		System.out.print("Entrez votre choix : ");
		try {
			int choix = scanner.nextInt();
			while (choix != 1 && choix != 2) {
				System.out.print("Choix inexistant. Veuillez réessayez : ");
				if (scanner.hasNextInt()) choix = scanner.nextInt();
			}
			
			switch (choix) {
				case 1:
					commencerJeu();
					break;
				case 2:
				quitter();
			}
		}
		catch (InputMismatchException e) { 	scanner.next();
		System.out.println("Vous n'avez pas entrer un nombre");
		dernierChoix();}
	}
	
	private void commencerJeu() {
		JeuTextuel.start(creationDeLaPartie());
	}

	private Partie creationDeLaPartie() {
		try (BufferedReader br = new BufferedReader(
				new FileReader(System.getProperty("user.dir")
								+File.separator+"res"
								+File.separator+choixTmp))) {
			
			String pseudo1=null,pseudo2=null,mode = null;
			int nbCote=0, nbPion=0;
			
			String line = "";
			while ((line=br.readLine()) != null) {
				String[] contenu = line.split(" : ");
				String param = contenu[0];
				switch (param) {
				case "pseudo1":
					pseudo1 = contenu[1];
					break;
				case "pseudo2":
					pseudo2 = contenu[1];
					break;
				case "nombre de côté":
					nbCote = Integer.valueOf(contenu[1]);
					break;
				case "mode":
					mode = contenu[1];
					break;
				}
			}

			if (Mode.valueOf(mode) == Mode.CHIFUMI && nbCote == 3) nbPion = 9;
			else if (Mode.valueOf(mode) == Mode.CLASSIQUE && nbCote == 3) nbPion = 6;
			else if (Mode.valueOf(mode) == Mode.CLASSIQUE && nbCote == 4) nbPion = 8;
			else if (Mode.valueOf(mode) == Mode.CHIFUMI && nbCote == 4) nbPion = 12;
			
			List<Joueur> list = new ArrayList<>();
				list.add(new Joueur(pseudo1, nbPion, Mode.valueOf(mode)));
				list.add(new Joueur(pseudo2, nbPion, Mode.valueOf(mode)));
			Partie partie = new Partie(new PlateauPolynomial(nbCote), list, Mode.valueOf(mode), nbPion);
			return partie;
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
		return null;
		
	}

	private void sauvegardeDeLaConfig(Mode m, Plateau p, String nomConfig) {
		BinarySerialization.sauvegardeDuneConfig(m, p, nomConfig);
	}

	private void enregistrementDeLaConfig() {
		
		System.out.print("Entrez un nom de sauvegarde : ");
		String nomConfig = scanner.next();
		
		Partie p =creationDeLaPartie();
		
		sauvegardeDeLaConfig(p.getMode(), p.getPlateau(), nomConfig);
		
	}
	

	public static void main(String[] args) {
		Demarrage demarrage = new Demarrage();
		demarrage.demarrerMenu();
	}
}
