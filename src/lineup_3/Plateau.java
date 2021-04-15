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
/*
 * 
 */
import java.util.List;

/**
 * Cette classe sert à créer les plateaux nécéssaire pour le fonctionnement du jeu
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 15 avr. 2021
 * @version v1-1404
 */

public class Plateau {
	/*
	 * Constructeur;
	 * La couche qui correspond aux différentes épaisseurs du plateau du jeu.
	 * Le nombre de côté correspond aux côté de la bordure.
	 * Le nombre de pion est correspond aux pions qu'un joueur peut posséder
	 * 
	 * listSommet est initialisé par défaut
	 */
	public final int NBCOTE;
	public final int NBCOUCHE;
	public final int NBPIONMAX;
	private static List<Pion> listSommet;
	private static List<Pion> listArret;

	public Plateau(int nombreCote) {
		this.NBCOTE=nombreCote;
		this.NBCOUCHE=3;
		this.NBPIONMAX=NBCOUCHE*NBCOTE;
		Plateau.listSommet=new ArrayList<>();
		Plateau.listArret=new ArrayList<>();
	}
	
	/*
	 * génère un plateau de jeu en utilisant les méthodes 
	 */
	 void generationDuPlateau() {
		
	}

	/**
	 * 
	 * @return la liste des sommets
	 */
	public static List<Pion> getListSommet() {
		return listSommet;
	}

	/**
	 * 
	 * @param listSommet la liste de sommet à définir
	 */
	public static void setListSommet(List<Pion> listSommet) {
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
	 
	 
	 
}
