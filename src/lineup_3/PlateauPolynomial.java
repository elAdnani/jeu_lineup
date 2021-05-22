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
	
	private  int        nbcote  ;
	public  static final int   NBCOUCHE = 3;
	
	public final GrapheListe<Case> grph;
	
	
	/**
	 * Construction d'un Plateau de nombre de côté défini </br>
	 * On le définit en tant que graphe non orienté.
	 * @param nombreCote
	 */
	
	public PlateauPolynomial(int nombreCote) {
		
		this( nombreCote , new GrapheListe<Case> ("Plateau", GrapheType.UGRAPH) );
	
	}

	/**
	 * Construction d'un Plateau de nombre de côté défini et à partir d'un graphe déjà construit. </br>
	 * Il sera toujours possible de construire des sommets par la suite. Mais des sommets peuvent déjà être présent.
	 * @param nombreCote
	 * @param grph
	 * @param joueursDuJeu
	 */
	
	public PlateauPolynomial(int nombreCote, GrapheListe<Case> grph) {
		if(grph.getType()==GrapheType.UGRAPH) {
			this.grph = grph;
		}
		else {
			this.grph= new GrapheListe<Case> ("Plateau", GrapheType.UGRAPH);
		}
		this.nbcote = nombreCote;
	}
	
	
	/**
	 * Génère le nombre de pions qu'il existe dans un polynôme. </br>
	 *
	 * @return nombre de pion qu'il est possible d'avoir dans une couche
	 */
	public int getNbPionMax() {
		return 2* nbcote;
	}
	

	
	
	/**
	 * Est destiné a être l'affichage du plateau permettant de jouer
	 * @return un plateau en  composé de ses pions
	 */
	public String affichagePlateau() {
		
		String plateauEnString="";
		return plateauEnString;
	}
	

	/**
	 * Génère tous les sommets du plateau, à partir du nombre de pion maximum qu'il peut y avoir. </br>
	 * Il commence par générer les sommets puis génère les arrêts grâce aux coordonnée des sommets.
	 * 
	 */

	public void generationDuPlateau() {
		// génération des sommets
		
		for (int i = 0; i < NBCOUCHE; i++)

			for (int j = 0; j < this.getNbPionMax(); j++)

				this.grph.addNode(new Case(i,j) );
		
		// génération des arêtes
		generationListArret(this.grph.getEdges());
	}
	
	/**
	 * Génère les arêtes à partir des sommets. </br>
	 * Regarde pour chaque sommet, la proximité avec d'autres sommets. </br>
	 * Si elle remarque qu'un sommet est voisin d'un autre, alors son arêtes est créées.
	 * @param listeDesSommets
	 */
	private void generationListArret(Map<Case, List<Case>> listeDesSommets) {
		
		for ( Case sommet : grph.getNodes() ) {

			for ( Case voisin : grph.getNodes() ) {

				if(estVoisin(sommet, voisin)) 
			
					grph.addEdge(sommet, voisin);
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
		
		return estVoisin(coordonnee_un.coordonnee, coordonnee_deux.coordonnee);
	}
	
	/**
	 * Vérifie la proximité des deux paires de coordonnée. </br>
	 * Et dit selon les consignes du plateau, si elles sont voisines. </br>
	 * C'est à dire : </br>
	 *  - Si elles sont l'un à côté de l'autre dans  un même polygone </br>
	 *  - Si elles sont au milieu du plateau à des couches voisine
	 * @param point_un
	 * @param point_deux
	 * @return
	 */
	
	public boolean estVoisin (Paire point_un, Paire point_deux) {

		return estVoisin(point_un.getX(),point_un.getY(),     point_deux.getX(), point_deux.getY());
	}
	

	/**
	 * Vérifie la proximité des deux points à partir de deux types de coordonnées mathématiques
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
	
	public int retrouverSommet(Paire coordonnee) {
		for( Case sommet : this.getListSommet()) {
			
			if(sommet.coordonnee.equals(coordonnee))
				
				return this.getListSommet().indexOf(sommet);
		}
		return -1;
	}
	/**
	 * permet à partir de coordonnée de retrouver un sommet. </br>
	 * On cherche à partir de la liste des sommets, les coordonnées correspondant à celui indiqué en paramètre.
	 * @param x
	 * @param y
	 * @return
	 */
	
	protected int retrouverSommet (int x, int y) {
		
		return retrouverSommet( new Paire(x, y) );
	}
	

	// --------------------------------- GETTER ET SETTERS // ---------------------------------\\
	
	/**
	 * Return le nombre de côté que ce plateau possède
	 * @return le NBCOTE
	 */
	
	public int getNBCOTE() {
		
		return this.nbcote;
	}
	
	/**
	 * Modifie, le nombre de côté d'un plateau, si le plateau est déjà généré son nombre de côté ne pourra être changé.
	 * @param nb
	 * @return
	 */
	public boolean setNBCOTE(int nb) {
		
		if(this.grph.getNodes()==null) {
			
			this.nbcote=nb;
			return true;
		}
		return false;
	}
	
	
	/**
	 * la liste de sommet que ce plateau possède
	 * @return 
	 */
	public List<Case> getListSommet() {
		
		return this.grph.getNodes();
	}
	
	/**
	 * la liste des arêtes que ce plateau possède
	 * @return
	 */
	protected Map< Case, List<Case> > getListArret() {
		
		return this.grph.getEdges();
	}

	
	
}
