package outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Cette classe utilise FileReader pour lire le contenu d'un fichier.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 13 avr. 2021
 * @version XX
 */
public class LectureEcritureDeFichier {
		// Class Attributes
	private static String myPath = System.getProperty("user.dir")
							+File.separator+"res"
							+File.separator;
	
	private static String fileName;
	
		// Methods
	
	/**
	 * bufferedLecture lis un le contenu d'un fichier à l'aide d'un buffer.
	 * @param f Représente le fichier que l'on souhaite lire.
	 */
	public static void bufferedLecture(String f) {
		LectureEcritureDeFichier.fileName = f;
		try {
			BufferedReader fichier = new BufferedReader(new FileReader(LectureEcritureDeFichier.myPath + LectureEcritureDeFichier.fileName));
			String line;
			while((line = fichier.readLine()) != null) {
				System.out.println("\t\t\t" + line);
			}
			fichier.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * usingFileWriter utilise la classe fileWriter pour écrire dans un fichier.
	 * @param f Représente le fichier où l'on souhaite écrire.
	 */
	public static void usingFileWriter(String f, String ecritAajouter) {
		LectureEcritureDeFichier.fileName = f;
		try {
			FileWriter stylo = new FileWriter(LectureEcritureDeFichier.myPath + LectureEcritureDeFichier.fileName, true);
			stylo.write(ecritAajouter);
			stylo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * usingPrintWriter écrit plus efficacement du texte que usingFileWriter dans un fichier donné.
	 * @param f Représente le fichier où l'on souhaite écrire.
	 */
	public static void usingPrintWriter(String f, String ecritAajouter) {
		LectureEcritureDeFichier.fileName = f;
		try {
			PrintWriter styloDeluxe = new PrintWriter(LectureEcritureDeFichier.myPath+LectureEcritureDeFichier.fileName);
			styloDeluxe.println(ecritAajouter);
			styloDeluxe.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//TODO convertir le main en en test
		// Main
	public static void main(String[] args) {
		//fileLecture("DicoJava.txt");
		//bufferedLecture("DicoJava.txt");
		//usingFileWriter("TabDouble.txt");
		//usingPrintWriter("TabDouble.txt");
		
	}
}
