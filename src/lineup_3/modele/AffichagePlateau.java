package lineup_3.modele;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import outils.Paire;
import package2.Plateau;

/**
 * 
 * Cette classe sert à afficher un plateau
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 31 mai 2021
 * @version v1
 */
public class AffichagePlateau {
	
	private Plateau plateau;
	
	public AffichagePlateau(Plateau p) {
		this.plateau=p;
	}

	/**
	 * Modifie en attribut le plateau qui lui, est le centre de la classe
	 * @param plateau est un plateau du Jeu
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}





	/**
	 * Récupère les coordonnées d'une chaîne de caractère.
	 * @param est une chaîne de caractère, sous la forme : x1;y1|x2;y2|x3;y3 formant trois coordonnées
	 * @return est une liste de coordonnée, une liste de Paire  {@link Paire }
	 */
	private List<Case> recuperationDesCoordonnees(String ligne){
		System.out.println(plateau.getClass().getName());
		List<Case> coordonnees = new ArrayList<>();
		String[] separationDesCoordoonees;
		separationDesCoordoonees = ligne.split(" ");

		String[] paire= new String[2];
		
		for(int i=0; i< separationDesCoordoonees.length ; i++) {
			
			paire = separationDesCoordoonees[i].split(";");
			coordonnees.add( plateau.trouverCase(
						new Paire( Integer.valueOf(paire[0]), Integer.valueOf(paire[1]) )
										)  );
		}
		
		return coordonnees;
		
	}
	
	
	
	
	
	/**
	 * Est destiné a être l'affichage du plateau permettant de jouer. </br>
	 * Elle consiste à récupérer les coordonnées de chaque case qui sera lui et du plateau qui sera renvoyé sur le terminal. 
	 * @param est le nombre de côté du plateau qui sera récupérer. 
	 * @param est l'assignation de chaque joueur de son caractère. 
	 * Si un joueur possède un pion dans une case du plateau, il sera représenté par celui-ci.
	 */
	
	public void affichagePlateau(int nombreDeCote, Map<Joueur, Character> pion) {
		
		 String myPath = System.getProperty("user.dir")
				+ File.separator + "res"
				+ File.separator + plateau.getClass().getSimpleName()
		 		+ File.separator + "Plateau";
		 
			File FichierDuNiveau = null ;
			try{
				
				FichierDuNiveau = new File(myPath+nombreDeCote+".txt");
				// on récupère un fichier qui possède le plateau correspondant
				String ligne=" ";

				RandomAccessFile raf = new RandomAccessFile(FichierDuNiveau, "r");
				raf.seek(0);
				// on se replace tout au début du fichier

				ligne = raf.readLine();
				
				// et on récupère la première ligne qui est la liste des cases ordonnées en fonction de sa venue dans la lecture du plateau
				List<Case> coordonneeDesCases = recuperationDesCoordonnees(ligne);

				int compteurDePaire = 0;
				
				while(ligne != null) {
					
					ligne = raf.readLine();
					// on récupère chaque ligne et on envoie sur le terminal chaque caractère
					// si le caractère est un 'O' alors on vérifie si cette case possède un pion
						// si elle possède un pion alors on envoie sur le terminal le caractère que représente ce joueur.
						// ou sinon on envoie tout simplement 'O' pour montrer que la case est vide.
					if(ligne != null) {

						for(int i=0; i< ligne.length() ;i++) {
							
							if(ligne.charAt(i)!='O')
								
								System.out.print(ligne.charAt(i) );
							else {
								
								if( ! coordonneeDesCases.get(compteurDePaire).EstLibre()) 

									System.out.print( pion.get( coordonneeDesCases.get( compteurDePaire ).getPion().getJoueur() ) );
								else 
									
									System.out.print("O");
								compteurDePaire = compteurDePaire + 1;
							}
						}
						System.out.println("");
					}
				}
				
				raf.close();
			}
			catch(IOException o) {
				
				o.printStackTrace();
			}
	}
	
}
