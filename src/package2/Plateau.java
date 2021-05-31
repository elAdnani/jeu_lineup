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


import java.util.List;
import java.util.Map;

/**
 * Cette classe sert à créer le/s plateau/x nécessaire pour le fonctionnement du
 * jeu. Il est la base du fonctionnement du jeu.  </br>
 * Un Plateau consiste à :  </br> 
 * 	- former le schéma de la partie, en créant les cases qui forment pour la plupart du temps des chemins du plateau </br> 
 *  - donner les outils nécessaires aux déplacement et aux changements du plateau</br>
 *  
 * 
 *	</br> 
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 7 mai. 2021
 * @version v1
 */

public abstract class Plateau {
	
	/**
	 * Est un élément obligatoire du plateau. Le plateau possède son propre graphe.</br>
	 * Ainsi un plateau correspond à un graphe de case. Ses sommets sont ses cases. Et ses arêtes, les chemins permettant de se déplacer. 
	 */
	public final GrapheMatrice<Case> grapheDuPlateau;
	
	
	
	/**
	 * @param grapheDuPlateau est un graphe de case prédéfini
	 */
	public Plateau(GrapheMatrice<Case> grapheDuPlateau) {
		
		this.grapheDuPlateau=grapheDuPlateau;
	}
	
	/**
	 * @param grapheDuPlateau est un graphe de case prédéfini
	 * @param TypeDuGraphe est le type du graphe que le graphe en paramètre doit respecter
	 */
	public Plateau(GrapheMatrice<Case> grapheDuPlateau,  GrapheType TypeDuGraphe) {
		
		if(grapheDuPlateau!=null && grapheDuPlateau.getType()==TypeDuGraphe)
			
			this.grapheDuPlateau=grapheDuPlateau;
		else
			
			this.grapheDuPlateau= new GrapheMatrice<Case>(TypeDuGraphe);
	}
	
	
	
	/**
	 * Affiche le plateau actuel sur le terminal. TODO vraiment utile à mettre en abstract ?
	 * @param niveau 
	 * @param pion
	 */
	public abstract void affichagePlateau(int niveau, Map<Joueur, Character> pion);

	
	/**
	 * Génère la partie, les cases ainsi que les chemins du plateau.
	 */
	public abstract void generationDuPlateau();
	
	
	/**
	 * Retrouve à partir d'une paire de coordonnée la case du plateau qui correspond.
	 * @param coordonnee est une {@link Paire} de coordonnée voulant être retrouvé dans le plateau
	 * @return donne la {@link Case} correspondant aux coordonnées, si elle n'existe pas null est renvoyé
	 */
	public Case trouverCase(Paire coordonnee) {
		
		for (Case sommet : this.grapheDuPlateau.getSommets())
			if (sommet.getCoordonnees().equals(coordonnee)) {
				
				return sommet;
			}
		return null;
	}

	
	
	/**
	 * la liste des cases que le plateau possède.
	 * @return la liste des sommets du graphe de case du plateau
	 */
	public List<Case> getListeCase() {
		
		return this.grapheDuPlateau.getSommets();
	}
	
	
	
	

}

