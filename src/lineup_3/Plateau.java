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


import java.util.Set;

/**
 * Cette classe sert à créer le/s plateau/x nécessaire pour le fonctionnement du
 * jeu. Il est la base du fonctionnement du jeu.
 *
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 7 mai. 2021
 * @version v1
 */

public abstract class Plateau {
	
	protected Set<Joueur> listJoueur;


	public Plateau(Set<Joueur> joueursDuJeu) {
		this.listJoueur = joueursDuJeu;
	}
	
	public abstract Joueur partieGagnee();
	
	public abstract String affichagePlateau();
	
	
	public Set<Joueur> getListJoueur() {
		Set<Joueur> listeJoueur = this.listJoueur;
		return  listeJoueur;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
