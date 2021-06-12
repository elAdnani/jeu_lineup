package outils;

import java.util.Comparator;
/**
 * 
 * Cette classe sert à comparer les {@link Couleur} qui sont données aux différents joueurs. </br>
 * Elle permet d'obtenir un ordre et pouvoir garder une logique de distribution.
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 07 juin 2021
 * 
 */
public class comparatorCouleur implements Comparator<Couleur>{

	@Override
	public int compare(Couleur o1, Couleur o2) {
		
		return o1.name().compareTo(o2.name());
	}

}
