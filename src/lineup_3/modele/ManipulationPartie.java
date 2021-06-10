package lineup_3.modele;

import java.time.LocalDate;

import lineup_3.controleur.Partie;

/**
 * 
 * Cette classe sert à manipuler les parties. Il y a dans la partie graphique une liste de partie.
 * Dès le début avant de pouvoir créer une partie, il devrait avoir un aperçu de toutes les parties déjà existantes en mode local.</br>
 * Cette classe sert à la manipulation des classes {@link Partie}.</br>
 * L'unique différence entre les parties est la date de création.</br>
 * 		Ainsi c'est à partir de cette information que l'on pourra les différencier et manipuler.
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 31 mai 2021
 *
 */
public class ManipulationPartie {
	


	
	/**
	 * La sauvegarde des parties se fera sous le nom "NomPartie.(LocalDate.now).ModeDeJeu".</br>
	 * Cela permettra à l'utilisateur de voir toutes les parties sans lire tous les fichiers sauvegardés.
	 * @param p est le nom de la partie à sauvegarder
	 */
	void sauvegardePartie(Partie p) { 
		
	}
	
	/**
	 * Parcours du fichier de sauvegarde, à la recherche d'une partie ayant une date de création renseignée.
	 * @param d est la date de création de la partie choisie
	 */
	Partie recuperationPartie(LocalDate d) { 
		return null;
	}
	/**
	 * Parcours du fichier de sauvegarde, à la recherche d'une partie ayant une date de création renseignée.</br>
	 * Cette partie sera supprimée.
	 * @param d est la date de création de la partie choisie
	 * @return est le résultat de l'opération. true, si la partie a bel et bien été supprimée. false si l'opération a échoué.
	 */
	boolean supressionPartie(LocalDate d) { 
		return false;
	}
	
	/**
	 * 
	 * @param d est la date de création de la partie choisie
	 * @return est le résultat de l'opération. true, si la partie a bel et bien été renommée. false si l'opération a échoué.
	 */
	boolean modifierNomDeLaPartie(LocalDate d){ 
		return false;
	}
	
	/**
	 * 
	 * @param d est la date de création de la partie choisie
	 * @param nomPartie
	 * @return est le résultat de l'opération. true, si la partie a bel et bien été copiée. false si l'opération a échoué.
	 */
	boolean copierPartie(LocalDate d, String nomPartie) { 
		return false;
	}

}
