/*
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package lineup_3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * Cette classe représente un plateau respectant les consignes : </br>
 * - Trois Sommets par côtés </br>
 * - Trois couches	</br>
 * - Autant de côté possible </br></br>
 *
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 22 mai 2021
 * @version v2
 */

public class PlateauPolynomial extends Plateau{

	/**
	 * Attributs composé : du nombre de côté (forme du plateau)</br>
	 * d'un nombre de couche pré-déterminé pour ce type de Plateau </br>
	 * Liste des pions des joueurs et un graphe qui correspond au squelette du plateau</br>
	 */
	
	private  int        		nbcote;
	public  static final int	NBCOUCHE = 3;
	
	
	
	/**
	 * Construction d'un Plateau de nombre de côté défini </br>
	 * On le définit en tant que graphe non orienté. </br>
	 * Puis de part le nombre de côté on génère le plateau, composé de case et de chemin. </br>
	 * @param nombreCote
	 */
	
	public PlateauPolynomial(int nombreCote) {
		
		super( new GrapheMatrice<Case>(GrapheType.UGRAPH) );
		
		this.nbcote=nombreCote;
		
		this.generationDuPlateau();
	
	}

	/**
	 * Construction d'un Plateau de nombre de côté défini et à partir d'un graphe déjà construit. </br>
	 * Il sera toujours possible de construire des sommets par la suite. Mais des sommets peuvent déjà être présent.
	 * @param nombreCote
	 * @param grapheDuPlateau
	 * @param joueursDuJeu
	 */
	
	public PlateauPolynomial(int nombreCote, GrapheMatrice<Case> grapheDuPlateau) {
		
		super(grapheDuPlateau,GrapheType.UGRAPH);
		
		this.nbcote = nombreCote;
		
	}
	
	
	/**
	 * Génère le nombre de pions qu'il existe dans un polynôme. </br>
	 *
	 * @return nombre de pion qu'il est possible d'avoir dans une couche
	 */
	public int getNbPionMax() {
		
		return 2* this.nbcote;
	}
	
	/**
	 * Récupère les coordonnées d'une chaîne de caractère.
	 * @param est une chaîne de caractère, sous la forme : x1;y1|x2;y2|x3;y3 formant trois coordonnées
	 * @return est une liste de coordonnée, une liste de Paire  {@link Paire }
	 */
	private List<Case> recuperationDesCoordonnees(String ligne){

		List<Case> coordonnees = new ArrayList<>();
		String[] separationDesCoordoonees;
		separationDesCoordoonees = ligne.split(" ");

		String[] paire= new String[2];
		
		for(int i=0; i< separationDesCoordoonees.length ; i++) {
			
			paire = separationDesCoordoonees[i].split(";");
			coordonnees.add( trouverCase(
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
				+ File.separator + "Plateau";
		 
			File FichierDuNiveau = null ;
			try{
				
				FichierDuNiveau = new File(myPath+nombreDeCote+".txt");
				// on récupère un fichier qui possède le plateau correspondant
				String ligne=" ";
				
				RandomAccessFile raf = new RandomAccessFile(FichierDuNiveau, "rw");
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
	

	/**
	 * Génère tous les sommets du plateau, à partir du nombre de pion maximum qu'il peut y avoir. </br>
	 * Il commence par générer les sommets puis génère les arrêts grâce aux coordonnée des sommets.
	 */

	public void generationDuPlateau() {
		
		for (int i = 0; i < NBCOUCHE; i++)

			for (int j = 0; j < this.getNbPionMax(); j++) 
				
				this.grapheDuPlateau.ajouterSommet(new Case(i,j) );
				
		
		generationListeChemin();
	}
	
	/**
	 * Génère les arêtes à partir des sommets. </br>
	 * Regarde pour chaque sommet, la proximité avec d'autres sommets. </br>
	 * Si elle remarque qu'un sommet est voisin d'un autre, alors son arêtes est créées.
	 * @param listeDesSommets
	 */
	
	private void generationListeChemin() {
		
		for ( Case sommet : grapheDuPlateau.getSommets() ) {

			for ( Case voisin : grapheDuPlateau.getSommets() ) {

				if(estVoisin(sommet, voisin)) 
			
					grapheDuPlateau.ajouterArrete(sommet, voisin);
			}
		}
	}
	
	/**
	 * Appelle la méthode estVoisin qui return et vérifie la proximité des deux sommets
	 * @param coordonnee_un
	 * @param coordonnee_deux
	 * @return
	 */
	
	private boolean estVoisin(Case coordonnee_un, Case coordonnee_deux) {
		
		return estVoisin(coordonnee_un.getCoordonnees().getX(),
						coordonnee_un.getCoordonnees().getY(),
						coordonnee_deux.getCoordonnees().getX(),
						coordonnee_deux.getCoordonnees().getY());
	}
	

	/**
	 * Vérifie la proximité des deux paires de coordonnée. </br>
	 * Et dit selon les consignes du plateau, si elles sont voisines. </br>
	 * C'est à dire : </br>
	 *  - Si elles sont l'un à côté de l'autre dans  un même polygone </br>
	 *  - Si elles sont au milieu du plateau à des couches voisine
	 * @param premierX est le x de la première paire coordonnée
	 * @param premierY est le y de la première paire coordonnée
	 * @param secondX est le x de la deuxième paire coordonnée
	 * @param secondY est le y de la deuxième paire coordonnée
	 * @return 
	 */
	private boolean estVoisin(int premierX, int premierY, int secondX, int secondY) {
		
		if(premierX==secondX && ( (premierY==(secondY-1 + this.getNbPionMax())%this.getNbPionMax() ) || (premierY==(secondY+1)%this.getNbPionMax() ))){
			// si elles se retrouvent sur la même couche et qu'elles ont une différence de  coordonnée +1 ou -1 en y
			return true;
		}
		else if(premierY==secondY && premierY%2!=0 && ( (premierX==(secondX-1 + this.getNbPionMax())%this.getNbPionMax() ) || (premierX==(secondX+1)%this.getNbPionMax() ))) {
			// si elles sont sur des couches différentes et qu'elles ont une différence de  coordonnée +1 ou -1 en x (ce cas est envisageable si y est impair)
			return true;
		}
		
		return false;
	}

	
	/**
	 * Récupère la liste des voisins d'un case d'une jeu
	 * @param est une case à qui l'on recherche ses voisins
	 * @return une liste de case voisin d'une case recherchée
	 */
	public Set<Case> voisinDe(Case c) {
		
		return this.grapheDuPlateau.voisinsDe(c);
	}

	
	/**
	 *@return une chaîne de caractère présentant le plateau en présentant :</br>
	 * - son nombre de côté
	 * - les chemins et les cases du plateau
	 * - le nombre de pion maximum 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("#Plateau :\n\tNombre de côtés :  ");
		builder.append(nbcote);
		builder.append("\n\n\tIl est basé sur un :\n");
		builder.append(grapheDuPlateau);
		builder.append("\n\nLes joueurs on chacun : ");
		builder.append(getNbPionMax());
		builder.append(" pions");
		return builder.toString();
	}
	
}
