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

import java.util.HashSet;

/*
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * Cette classe représente un plateau respectant les consignes
 *
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 15 avr. 2021
 * @version v1-2
 */

public class PlateauDeBase extends Plateau{

	/**
	 * Attributs composé du nombre de côté (forme du plateau)
	 * d'un nombre de couche pré-déterminé pour ce type de Plateau
	 * Liste des pions des joueurs et un graphe qui correspond au squelette du plateau
	 */
	
	private  int        nbcote  ;
	public  static final int   NBCOUCHE = 3;
	private  Set<Pion>  listPion;
	
	public final Graphe grph;
	
	
	/**
	 * Construction d'un Plateau de nombre de côté défini
	 * @param nombreCote
	 */
	
	public PlateauDeBase(int nombreCote) {
		
		this(nombreCote, new Graphe("Plateau", GrapheType.UGRAPH), new HashSet<>());
	}
	
	/**
	 * Construction d'un Plateau de nombre de côté défini et à partir d'un graph définie
	 * @param nombreCote
	 * @param grph
	 */
	
	public PlateauDeBase(int nombreCote , Graphe grph) {
		
		this(nombreCote, grph, new HashSet<>());
	}
	
	/**
	 * Construction d'un Plateau de nombre de côté défini et à partir d'un graph définie et d'une liste de joueur
	 * @param nombreCote
	 * @param grph
	 * @param joueursDuJeu
	 */
	
	public PlateauDeBase(int nombreCote, Graphe grph, Set<Joueur> joueursDuJeu) {
		
		super(joueursDuJeu);  
		this.listPion = new HashSet<>();
		this.grph = grph;
		this.nbcote = nombreCote;
	}
	
	/**
	 * 
	 * @return nombre de pion qu'il est possible d'avoir dans une couche
	 */
	public int getNbPionMax() {
		return 6;
	}
	

	
	/**
	 * Permet à partir de coordonnée, de retrouver le pion appartenant au plateau
	 * @param x
	 * @param y
	 * @return le pion qui correspond un pion ou null, en fonction de la correspondance des coordonnées dans la liste des sommets
	 */
	public Pion retrouverPion (int x, int y) {

		for( Pion p :listPion)
			
			if( p.getCoordonnees().getX()==x && p.getCoordonnees().getY()==y)
				
				return p;
		
		return null;
	}
	
	
	
	/**
	 * L'ensemble des composantes du plateau
	 * 
	 * @return donne la présentation des joueurs ainsi que leurs pions.
	 */
	
	@Override
	public String toString() {
		
		String result="";
			for(Joueur j : listJoueur)
				
				result= result + "["+ j.toString()+ "]\n";

			
		return result ;
	}
	
	/**
	 * Est destiné a être l'affichage du plateau permettant de jouer
	 * @return un plateau composé de ses pions
	 */
	public String affichagePlateau() {
		
		String plateauEnString="";
		for(int nombreDeCote=1; nombreDeCote<=this.nbcote; nombreDeCote++) {
			
			plateauEnString= plateauEnString + "  ";
			for(int colonne=0; colonne<10; colonne++) {
				
				
			}
			
			
			
			
			
		}
		return plateauEnString;
	}


	/**
	 * génère un plateau de jeu en utilisant les méthodes du graphes Donnes tous les
	 * sommets d'un plateau (toutes les coordonnée existantes)
	 * 
	 */

	public void generationDuPlateau() {
		// génération des sommets
		
		for (int i = 0; i < NBCOUCHE; i++)

			for (int j = 0; j < this.getNbPionMax(); j++)

				this.grph.addNode(String.format("%02d", i) + String.format("%02d", j) );
				
		
		// génération des arêtes
		generationListArret(this.grph.getEdges());
	}
	
	/**
	 * génère les arêtes à partir des sommets
	 * @param listSommet
	 */
	private void generationListArret(Map<String, List<String>> listeDesSommets) {

		for ( String sommet : grph.getNodes() ) {
			
			for ( String voisin : grph.getNodes() ) 
				
				if(estVoisin(sommet, voisin)) 
					
					grph.addEdge(sommet, voisin);

		}
				
			
	}
	
	/**
	 * Appelle la méthode estVoisin qui return et vérifie la proximité des deux sommets
	 * @param coordonnee_un
	 * @param coordonnee_deux
	 * @return
	 */
	
	private boolean estVoisin(String coordonnee_un, String coordonnee_deux) {
		
		Paire coor1= new Paire(Integer.parseInt( coordonnee_un.substring(0,2) ),   Integer.parseInt(  coordonnee_un.substring( 2,coordonnee_un.length() )  )   );
		Paire coor2= new Paire(Integer.parseInt( coordonnee_deux.substring(0,2) ),   Integer.parseInt(  coordonnee_deux.substring( 2,coordonnee_deux.length() )  )   );
		
		return estVoisin(coor1, coor2);
	}
	
	/**
	 * Appelle la méthode estVoisin qui return et vérifie la proximité des deux sommets
	 * @param point_un
	 * @param point_deux
	 * @return
	 */
	
	public boolean estVoisin (Paire point_un, Paire point_deux) {
		
		return estVoisin(point_un.getX(),point_un.getY(),     point_deux.getX(), point_deux.getY());
	}
	

	/**
	 * Vérifie la proximité des deux points de part les coordonnée
	 * @param x1 premier sommet
	 * @param y1 premier sommet
	 * @param x2 deuxième sommet
	 * @param y2 deuxième sommet
	 * @return true s'il est possible de passer d'un sommet à un autre directement, false ou sinon
	 */
	private boolean estVoisin(int x1, int y1, int x2, int y2) {
		
		if(x1==x2 && ( (y1==(y2-1 + this.getNbPionMax())%this.getNbPionMax() ) || (y1==(y2+1)%this.getNbPionMax() ))){
			// si elles se retrouvent sur la même couche et qu'elles ont une différence de  coordonnée +1 ou -1 en y
			return true;
		}
		else if(y1==y2 && y1%2!=0 && ( (x1==(x2-1 + this.getNbPionMax())%this.getNbPionMax() ) || (x1==(x2+1)%this.getNbPionMax() ))) {
			// si elles sont sur des couches différentes et qu'elles ont une différence de  coordonnée +1 ou -1 en x (ce cas est envisageable si y est impair)
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * permet à partir de coordonnée de retrouver un sommet (va dans la liste des sommets et donne l'indice du sommet qui correspond au coordonnee)
	 * @param x
	 * @param y
	 * @return
	 */
	public int retrouverSommet (int x, int y) {

		return this.getListSommet().indexOf( String.format("%02d", x) + String.format("%02d", y) );	
	}

	
	
	
	/**
	 * Elle permet de voir si la partie est finie
	 * @return s'il existe au moins un alignement de trois pions alors la partie est gagnée
	 */
	
	public Joueur partieGagnee() {
		
		if( lierInterieur()==null) { 
			
			return lierExterieur();
		}
		
		return lierInterieur();
		
	}

	/**
	 * Regarde pour chaque pion, ayant la même coordonnée X, si celui qui est juste
	 * avant et juste après existe
	 * 
	 * @return joueur si l'élèment juste avant et après existe dans la liste des
	 *         sommets
	 * @return joueur si la boucle for each a parcouru toute la liste des sommets
	 *         sans trouver d'alignement.
	 *        @TODO changer modulo en un autre nom, j'ai la flemme de le faire ;(	
	 */
	
	private Joueur lierInterieur() {
		
		int modulo = ( this.getNBCOTE() - 1) * NBCOUCHE;
		for(Joueur j : super.listJoueur)
			
				for (int i = 0; i < NBCOUCHE; i++){
					
					for (Pion p1 : j.getMain().getMain())
						
						for (Pion p2 : j.getMain().getMain())
							
							for (Pion p3 : j.getMain().getMain())
								
								if (p1.getX() == i)
									
									if (p1.getX() == ((p2.getX() - 1) % modulo) && p1.getX() == (p3.getX() - 2) % modulo)
										
										return j;
		
				}
		return null;
	}

	/**
	 * Regarde pour chaque pion, ayant la même coordonnée Y, si celui qui est juste
	 * avant et juste après existe
	 * 
	 * @return true si l'élèment juste avant et après existe dans la liste des
	 *         sommets
	 * @return false si la boucle for each a parcouru toute la liste des sommets
	 *         sans trouver d'alignement.
	 */
	
	private Joueur lierExterieur() {
		
		for (int i = 0; i < NBCOUCHE; i++){
			
			for(Joueur j : super.listJoueur)
				for (Pion p1 : j.getMain().getMain())
				
					for (Pion p2 : j.getMain().getMain())
					
						for (Pion p3 : j.getMain().getMain())
						
							if (p1.getY() == i)
							
								if (p1.getY() == ((p2.getY() - 1) % NBCOUCHE) && p1.getY() == (p3.getY() - 2) % NBCOUCHE)
								
									return j;
									
			
		}
		
		return null;
	}


	/**
	 * Permet d'ajouter un élément pion à notre liste de sommet
	 * 
	 * @param pion
	 */
	
	public void addPion(Pion pion) {
		
		listPion.add(pion);
		
		
	}

	/**
	 * Permet d'enlever un élément pion à notre liste de sommet
	 * 
	 * @param pion
	 */
	
	public void removePion(Pion pion) {
		
		listPion.remove(pion);
	}

	/**
	 * Ajouter un joueur dans la liste de joueur des plateaux, puis on rajoute leurs pions dans la liste des pions
	 * @param utilisateur
	 */
	public void addJoueur(Joueur utilisateur) {
		
		listJoueur.add(utilisateur);
		if(utilisateur.getMain()!=null)
			for(Pion nouveauPionDuJoueur : utilisateur.getMain().getMain()) 
				this.listPion.add(nouveauPionDuJoueur);
			
	}
	
	/**
	 * Ajouter un joueur dans la liste de joueur des plateaux, puis on rajoute leurs pions dans la liste des pions
	 * @param utilisateur
	 */
	public void removeJoueur(Joueur utilisateur) {
		
		listJoueur.remove(utilisateur);
		if(utilisateur.getMain()!=null)
			for(Pion pionDuJoueur : utilisateur.getMain().getMain()) 
				this.listPion.remove(pionDuJoueur);
			
	}
	
	
	

	// --------------------------------- GETTER ET SETTERS // ---------------------------------\\
	
	/**
	 * 
	 * @return la liste des sommets
	 */
	
	public Set<Pion> getListPion() {
		
		return  this.listPion;
	}

	/**
	 * 
	 * @param listSommet la liste de sommet à définir
	 */
	
	public  void setListSommet(Set <Pion> listPion) {
		
		this.listPion = listPion;
	}



	/**
	 * 
	 * @return le NBCOTE
	 */
	
	public int getNBCOTE() {
		
		return this.nbcote;
	}

	
	public List<String> getListSommet() {
		
		return this.grph.getNodes();
	}
	
	public Map<String, List<String>> getListArret() {
		
		return this.grph.getEdges();
	}
	
	
	
}
