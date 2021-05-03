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
import java.util.List;


/**
 * Cette classe sert à lancer le jeu.
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * IUT-A Informatique, Université de Lille.
 * @date 21 avr. 2021
 * @version 21 avr. 2021 19:26:15
 */
public class Resultat {
	
	public static void main(String[] args) {
		
		int nbPions = 6;
		List<String> nodes = new ArrayList<String>();
		nodes.add("a");
		Graphe g1 = new Graphe("Grahpe1", GrapheType.UGRAPH);
		
		Joueur j1 = new Joueur("Joueur1", nbPions);
		Joueur j2 = new Joueur("Joueur2", nbPions);
		
		System.out.println(j1);
		System.out.println(j1.getMain().toString());
		//System.out.println(j2);
		//System.out.println(g1);
	}
}
