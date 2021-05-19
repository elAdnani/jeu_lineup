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
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * Cette classe sert à créer le/s plateau/x nécessaire pour le fonctionnement du
 * jeu. Il est la base du fonctionnement du jeu.  </br>
 * Un Plateau consiste à :  </br> 
 * 	- former le schéma de la partie, en donnant les sommets qui en sommant une partie, forment pour la plupart du temps des arêtes  </br> 
 *  - former les cases permettant les déplacements et le fonctionnement logique du jeu </br> 
 * 
 *	</br> 
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 7 mai. 2021
 * @version v1
 */

public abstract class Plateau {
	
	
	public abstract String affichagePlateau();

	public abstract void generationDuPlateau();

	public abstract int retrouverSommet(Paire paire);
	
	public abstract List<Case> getListSommet();
	
	

}
