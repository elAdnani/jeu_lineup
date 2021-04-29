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
 * Cette classe sert à .........
 *
 * @author 
 * IUT-A Informatique, Universite de Lille.
 * @date 14 avr. 2021
 * @version XXX
 */
public class Resultat {
	
	public static void main(String[] args) {
		int nbPions = 6;
		Joueur j1 = new Joueur("Joueur1", nbPions);
		Joueur j2 = new Joueur("Joueur2", nbPions);
		
		List<String> nodes = new ArrayList<String>();
		nodes.add("a");
		
		Graphe g1 = new Graphe("Grahpe1", GrapheType.UGRAPH);
		
	}
}
