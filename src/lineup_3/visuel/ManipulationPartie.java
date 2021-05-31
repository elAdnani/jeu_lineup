package lineup_3.visuel;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * Cette classe sert à manipuler les parties
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 31 mai 2021
 * @version XX
 */
public class ManipulationPartie {
	
	/**
	 * TODO      TODO TODO TODO TODO  TODO			  TODO TODO TODO TODO
	 * TODO TODO TODO TODO TODO		  TODO			  TODO 			TODO
	 * TODO TODO TODO TODO   		  TODO			  TODO TODO TODO TODO
	 * TODO      TODO TODO TODO TODO  TODO TODO TODO  TODO
	 * 
	 * CE QUE TU DOIS FAIRE
	 * Creer des fichiers binaire de serialisation .ser
	 * qui vont être nommée : 
	 * NomPartie.Date.ModeDeJeu.nombreJoueur
	 *  
	 */
	
	
	/**
	 * constructeur vide
	 */
	public ManipulationPartie() {
	}
	
	
	
	void recuperationPartie() { // == deserialisation
		
	}
	
	void sauvegardePartie() { // == serialisation
		
	}
	
	boolean supressionPartie(LocalDate d) { // on supprime le fichier ser
		return false;
	}
	
	boolean modifierNomDeLaPartie(LocalDate d){ // modifie le nom du fichier
		return false;
	}
	
	boolean copierPartie(LocalDate d, String nomPartie) { // on copie un fichier en changeant le nom
		return false;
	}
	boolean copierPartie(LocalDate d) { // on copie la partie BIEN SUR LA DATE DE LA PARTIE NE SERA PAS LA MEME MAIS PLUTOT ON RECUP LA PARTIE A PARTIR DE LA DATE
										// puis on copie ce fichier en changeant la date (en LocalDate.now) 
										// donc on copie NomPartie.Date.ModeDeJeu et on crée NomPartie.(LocalDate.now).ModeDeJeu
		return false;
	}
}
