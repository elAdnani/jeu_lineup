package outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lineup_3.modele.ManipulationPartie;
import lineup_3.modele.Mode;
import lineup_3.modele.Partie;
import outils.jeu.Plateau;

/**
 * Cette classe permet la sauvegarde pour la partie textuelle. </br>
 * En attendant la sérialisation de {@link ManipulationPartie}.</br>
 * Il y aura après cela une fusion de ces deux classes.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 7 juin 2021
 * @version 7 juin 2021 16:44:02
 */
public class BinarySerialization {
		// Class Attributes
	private static final String config = System.getProperty("user.dir")
										+File.separator+"res"
										+File.separator+"configurations"
										+File.separator;
	
	private static final String sauvegarde = System.getProperty("user.dir")
										+File.separator+"res"
										+File.separator+"sauvegardes"
										+File.separator;
	
		/**
		 * Permet une sauvegarde individuelle, celle du mode de jeu.
		 * @param m mode de jeu
		 * @param nomConfig nom du fichier
		 */
	private static void sauvegardeDunMode(Mode m, String nomConfig) {
		try (ObjectOutputStream mos = new ObjectOutputStream(
				new FileOutputStream(config+nomConfig+".mode"))) {
			mos.writeObject(m);
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
		
	}

	/**
	 * Permet une sauvegarde individuelle, celle du plateau
	 * @param p plateau du jeu
	 * @param nomConfig nom du fichier
	 */
	private static void sauvegardeDunPlateau(Plateau p, String nomConfig) {
		try (ObjectOutputStream ppos = new ObjectOutputStream(
				new FileOutputStream(config+nomConfig+".pla"))) {
			ppos.writeObject(p);
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}

	}
	
	/**
	 * Permet la configuration textuelle d'une configuration
	 * @param m mode de jeu
	 * @param p plateau du jeu
	 * @param nomConfig nom du fichier
	 */
	public static void sauvegardeDuneConfig(Mode m, Plateau p, String nomConfig) {
		sauvegardeDunMode(m, nomConfig);
		sauvegardeDunPlateau(p, nomConfig);
	}
	
	/**
	 *  Permet la configuration d'une partie
	 * @param p partie contenant le jeu entier
	 * @param nomPartie nom du fichier
	 */
	public static void sauvegardeDunePartie(Partie p, String nomPartie) {
		try (ObjectOutputStream pos = new ObjectOutputStream(
				new FileOutputStream(sauvegarde+nomPartie+".par"))) {
			pos.writeObject(p);
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
	}
	
	/**
	 * Permet la récupération d'une partie
	 * @param nomPartie nom du fichier
	 * @return retourne la partie recherchée ou bien null s'il n'a pas été trouvé
	 */
	public static Partie chargementDunePartie(String nomPartie) {
		try (ObjectInputStream pis = new ObjectInputStream(
				new FileInputStream(sauvegarde+nomPartie+".par"))) {
			Partie p = (Partie) pis.readObject();
			return p;
		}
		catch (ClassNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
		return null;
	}

	
	/**
	 * Permet la récupération d'une plateau
	 * @param nomPartie nom du fichier
	 * @return retourne le plateau recherché ou bien null s'il n'a pas été trouvé
	 */
	public static Plateau chargementDunPlateauPolynomial(String nomConfig) {
		try (ObjectInputStream pis = new ObjectInputStream(
				new FileInputStream(sauvegarde+nomConfig+".pla"))) {
			Plateau p = (Plateau) pis.readObject();
			return p;
		}
		catch (ClassNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
		return null;
	}
}
