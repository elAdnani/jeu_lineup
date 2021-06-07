package outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lineup_3.modele.Mode;
import lineup_3.modele.Partie;
import package2.Plateau;

/**
 * Cette classe fait.........
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
	
		// Methods
	private static void sauvegardeDunMode(Mode m, String nomConfig) {
		try (ObjectOutputStream mos = new ObjectOutputStream(
				new FileOutputStream(config+nomConfig+".mode"))) {
			mos.writeObject(m);
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
		
	}

	private static void sauvegardeDunPlateau(Plateau p, String nomConfig) {
		try (ObjectOutputStream ppos = new ObjectOutputStream(
				new FileOutputStream(config+nomConfig+".pla"))) {
			ppos.writeObject(p);
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}

	}
	
	public static void sauvegardeDuneConfig(Mode m, Plateau p, String nomConfig) {
		sauvegardeDunMode(m, nomConfig);
		sauvegardeDunPlateau(p, nomConfig);
	}
	
	public static void sauvegardeDunePartie(Partie p, String nomPartie) {
		try (ObjectOutputStream pos = new ObjectOutputStream(
				new FileOutputStream(sauvegarde+nomPartie+".par"))) {
			pos.writeObject(p);
		}
		catch (FileNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
	}
	
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

	public static Mode chargmentDunMode(String nomConfig) {
		try (ObjectInputStream mis = new ObjectInputStream(
				new FileInputStream(sauvegarde+nomConfig+".mode"))) {
			Mode m = (Mode) mis.readObject();
			return m;
		}
		catch (ClassNotFoundException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace();}
		return null;
	}
	
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
