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
import java.util.List;
import java.util.Set;


/**
 * Cette classe sert à lancer le jeu.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 21 avr. 2021
 * @version 21 avr. 2021 19:26:15
 */
public class Partie {
	
	public static void main(String[] args) {
		Parametres parametrePartie = new Parametres(3, 3, false, false, 6);
		
		//TODO Adnan : doit écrire une méthode pour initialiser une liste de Case.
		
		//TODO Adnan : instancie un plateau.
		Plateau plateauPartie = new PlateauPolynomial(parametrePartie.getNBCOTE());
		//TODO Adnan : Rajoute les initialisations directement dans les constructeurs.
		plateauPartie.generationDuPlateau();//TODO changer le list en set ou l'inverse : voir l'erreur à l'éxécution.
		
		Joueur joueur1 = new Joueur("Joueur1", parametrePartie);
		Joueur joueur2 = new Joueur("Joueur2", parametrePartie);
		
		System.out.println(plateauPartie);
		System.out.println();
		System.out.println(joueur1);
		
	}
}
