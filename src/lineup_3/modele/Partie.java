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
package lineup_3.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import outils.jeu.Plateau;

/**
 * Cette classe sert à récupérer.</br>
 * Permet l'enregistrement, 
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmène CHOUKHI</a>
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Université de Lille.
 * @date 21 avr. 2021
 */
public class Partie implements Serializable{
	
	
	private static final long serialVersionUID = 283664428347848905L;

	private Plateau plateauDeLaPartie;
	
	private transient final LocalDate dateDeCreation; // ne sera pas dans la partie mais plutôt dans le nom du fichier
	
	List<Joueur> utilisateur;
	
	private Mode mode;
	
	/**
	 * Constructeur
	 * @param plateauDeLaPartie
	 * @param utilisateur
	 */
	public Partie(Plateau plateauDeLaPartie, List<Joueur> utilisateur, Mode mode) {
		
		this.plateauDeLaPartie=plateauDeLaPartie;
		
		dateDeCreation=LocalDate.now();
		
		this.utilisateur=utilisateur;
		
		this.mode = mode;
	}
	
	public LocalDate getDateDeCreation() {
		
		LocalDate debut= this.dateDeCreation;
		return debut;
	}
	
	
	public int getNombreJoueur() {
		return this.utilisateur.size();
	}
	
	public List<Joueur> getList() {
		return this.utilisateur;
	}
	
	public Plateau getPlateau() {
		return this.plateauDeLaPartie;
	}
	
	public Mode getMode() {
		return this.mode;
	}

	
	/**
	 * Permet de modifier le plateau de la partie.</br>
	 * 
	 * @param plateauDeLaPartie
	 */
	protected void setPlateauDeLaPartie(Plateau plateauDeLaPartie) {
		
		this.plateauDeLaPartie = plateauDeLaPartie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partie [plateauDeLaPartie=");
		builder.append(plateauDeLaPartie);
		return builder.toString();
	}


}