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

import java.util.ArrayList;
import java.util.HashSet;
/*
 * 
 */
import java.util.List;
import java.util.Set;


/**
 * Cette classe sert à créer le/s plateau/x nécessaire pour le fonctionnement du
 * jeu. Il est la base du fonctionnement du jeu.
 *
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 15 avr. 2021
 * @version v1-2.000 
 */

public class Plateau {
	/**
	 * Constructeur; La couche qui correspond aux différentes épaisseurs du plateau
	 * du jeu. Le nombre de côté correspond aux côté de la bordure. Le nombre de
	 * pion est correspond aux pions qu'un joueur peut posséder
	 * 
	 * listSommet est initialisé par défaut
	 * 
	 * @TODO voir la liste d'arret
	 *
	 *
	 */
	private static int        nbcote   = 0;
	public static final int   NBCOUCHE = 3;
	public final int          NBPIONMAX;
	private static Set<Pion>  listSommet;
	private static List<Pion> listArret; // je n'y touche pas pour le moment 
	private static Set<Joueur> listJoueur;
	
	
	List<Deplacement>         possibilite;

	/**
	 * 
	 * @param nombreCote
	 */
	public Plateau(int nombreCote) {
		this(nombreCote, new HashSet<>(), new ArrayList<>(), new HashSet<>());
	}

	/**
	 * 
	 * @param nombreCote
	 * @param sommet
	 * @param arret
	 */
	
	public Plateau(int nombreCote, Set<Pion> sommet, List<Pion> arret, Set<Joueur> joueursDuJeu) {
		Plateau.nbcote = nombreCote;
		this.NBPIONMAX = NBCOUCHE * nbcote;
		Plateau.listSommet = sommet;
		Plateau.listArret = arret;
		Plateau.listJoueur = joueursDuJeu;
		
		this.possibilite = new ArrayList<>();
	}
	@Override
	public String toString() {
		String result="";
			for(Pion p : listSommet )
				result= result + p.toString();
		return result ;
	}
	/**
	 * Considère les déplacements que peut réaliser un pion dans le plateau.
	 * Il rajoute à l'attribue this.possibilite par quel moyen un même pion peut se déplacer.
	 * @param selection correspond au pion que le joueur souhaite déplacer
	 */
	
	private void deplacementPossible(Pion selection) {
		if(this.possibilite != null) {
			
		this.possibilite.clear();
		}
		final int COUCHEPRINCIPALE = 1;

		int X = selection.getX();
		int Y = selection.getY();
		int nombrePionDansUneCouche = (nbcote - 1) * NBCOUCHE;
		// en haut (faire +1 à la couche) => X=paire et Y!=COUCHEMAX
		// en bas (faire -1 à la couche) => X=paire et Y!=1
		// en droite (faire +1 à X) => X!= coucheMAX
		// à gauche (faire -1 à X) =>
		// HAUT :
		if (Y % 2 == 0 && X != Plateau.NBCOUCHE && !presenceCoordonnee(X + 1, Y))

			this.possibilite.add(Deplacement.HAUT);
		if (Y % 2 == 0 && X != COUCHEPRINCIPALE && !presenceCoordonnee(X - 1, Y))

			this.possibilite.add(Deplacement.BAS);
		if (!presenceCoordonnee(X, (Y + 1) % nombrePionDansUneCouche))

			this.possibilite.add(Deplacement.DROITE);
		if (!presenceCoordonnee(X, (Y - 1) % nombrePionDansUneCouche))

			this.possibilite.add(Deplacement.GAUCHE);

	}
	
	/**
	 * Déplace un pion, selon sa disponibilité et la direction donnée.
	 * @param selection est le pion décide de déplacer
	 * @param direction est la direction que le joueur a choisie
	 */
	
	public boolean deplacerPion(Pion selection,String direction) {
		deplacementPossible(selection);
		
			if (this.possibilite.contains(Deplacement.valueOf(direction.toUpperCase()))) {
				selection.setPosition(
						selection.getX()+ Deplacement.valueOf(direction.toUpperCase()).getX(),
						selection.getY()+ Deplacement.valueOf(direction.toUpperCase()).getY()
				);
						
				 /**   @TODO faire un / modulo        */
				return true;
			// on modifie le X en lui attribuant la valeur de lui même
				// plus la direction dans laquelle il veut se diriger
			}
		return false;
	}

	/**
	 * 
	 * @param X coordonnée
	 * @param Y coordonnée
	 * @return s'il existe un pion à l'emplacement (X,Y)
	 */
	
	public boolean presenceCoordonnee(int X, int Y) {
		for (Pion p : Plateau.listSommet)
			if (p.getX() == X && p.getY() == Y)
				return true;

		return false;
	}
	


	/**
	 * génère un plateau de jeu en utilisant les méthodes du graphes Donnes tous les
	 * sommets d'un plateau (toute les coordonnée
	 * 
	 * @TODO revoir; l'enlever
	 */

	public void generationDuPlateau() {
		for (int i = 1; i <= NBCOUCHE; i++)

			for (int j = 1; j <= nbcote; j++)
			
				listSommet.add(new Pion(i, j));	
		
	}
	
	/**
	 * Elle permet de voir si la partie est finie
	 * @return s'il existe au moins un alignement de trois pions alors la partie est gagnée
	 */
	
	public static Joueur partieGagnee() {
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
	
	private static Joueur lierInterieur() {
		int modulo = (nbcote - 1) * NBCOUCHE;
		for(Joueur j : Plateau.listJoueur)
				for (int i = 0; i < NBCOUCHE; i++)
				{
					for (Pion p1 : j.getMain().getMain()) /** j.getMain().getMain() == assez compliqué,  @TODO voir avec selmene */
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
	
	private static Joueur lierExterieur() {
		for (int i = 0; i < NBCOUCHE; i++)
		{
			for(Joueur j : Plateau.listJoueur)
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
	
	public void addPionSommet(Pion pion) {
		
		listSommet.add(pion);
	}

	/**
	 * Permet d'enlever un élément pion à notre liste de sommet
	 * 
	 * @param pion
	 */
	
	public void removePionSommet(Pion pion) {
		
		listSommet.remove(pion);
	}

	/**
	 * Permet d'ajouter un élément pion à notre liste d'arret
	 * 
	 * @param pion
	 */
	
	public void addPionArret(Pion pion) {
		
		listArret.add(pion);
	}

	/**
	 * Permet d'enlever un élément pion à notre liste d'arret
	 * 
	 * @param pion
	 */
	
	public void removePionArret(Pion pion) {
		
		listArret.remove(pion);
	}

	public void addJoueur(Joueur utilisateur) {
		
		listJoueur.add(utilisateur);
		if(utilisateur.getMain()!=null)
			for(Pion nouveauPionDuJoueur : utilisateur.getMain().getMain()) 
				Plateau.listSommet.add(nouveauPionDuJoueur);
			
	}

	public void removeJoueur(Joueur utilisateur) {
		
		listJoueur.remove(utilisateur);
		if(utilisateur.getMain()!=null)
			for(Pion nouveauPionDuJoueur : utilisateur.getMain().getMain()) 
				
				Plateau.listSommet.add(nouveauPionDuJoueur);
			
	}
	

	// --------------------------------- GETTER ET SETTERS // ---------------------------------\\
	
	/**
	 * 
	 * @return la liste des sommets
	 */
	
	public static Set<Pion> getListSommet() {
		
		return  listSommet;
	}

	/**
	 * 
	 * @param listSommet la liste de sommet à définir
	 */
	
	public static void setListSommet(Set <Pion> listSommet) {
		
		Plateau.listSommet = listSommet;
	}

	/**
	 * 
	 * @return la listArret
	 */
	
	public static List<Pion> getListArret() {
		
		return listArret;
	}

	/**
	 * 
	 * @param listArret, la liste arrete à définir
	 */
	
	public static void setListArret(List<Pion> listArret) {
		
		Plateau.listArret = listArret;
	}

	/**
	 * 
	 * @return le NBCOTE
	 */
	
	public static int getNBCOTE() {
		
		return nbcote;
	}

}
