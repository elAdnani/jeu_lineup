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

/**
 * Cette enum permet de définir les mouvements existants dans le jeu
 *
 * @author <a href="mailto:adnan.kouakoua.etu@univ-lille.fr">Adnân KOUAKOUA</a>
 *         IUT-A Informatique, Université de Lille.
 * @date 21 avr. 2021
 * @version v1-1404
 */

public enum Deplacement {

	DROITE("droite",0,1), GAUCHE("gauche",0,-1), BAS("bas",-1,0), HAUT("haut",1,0);

	private String cheminement;
	private int X;
	private int Y;

	Deplacement(String cheminement, int X, int Y) {
		this.cheminement=cheminement;
		this.X=X;
		this.Y=Y;
	}

	public String getCheminement() {
		return this.cheminement;
	}

	public int getX() {
		return this.X;
	}
	public int getY() {
		return this.Y;
	}

	
	/*
	 * On pourrait faire de cette manière :
	 *	 	public String action() {
	 *			switch(this) {
	 *				case SAMEDI : return "cinema";
	 *				case DIMANCHE : return "dormir";
	 *	 			default : return "travailler";
	 *	 		}
 	 *		}
	 * 
	 * 
	 */
	
}
