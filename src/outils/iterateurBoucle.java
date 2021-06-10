package outils;

import java.util.Iterator;
import java.util.List;
/**
 * 
 * Cette classe sert à itérer une liste, de façon à ce qu'il n'y ait pas de fin
 * Elle nous ait utile dans le but d'itérer les joueurs ou bien d'itérer dans une liste de couleur pour l'utilisateur dans la partie graphique. 
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 8 juin 2021
 */
public class iterateurBoucle<T> implements Iterator<T>{
	int idxElement;
	List<T> maList;
	
	public iterateurBoucle(List<T> l) {
		this.maList=l;
		this.idxElement = -1;
		this.findNextElement();
	}


	private void findNextElement() {
		idxElement++;
		if(!hasNext()) { // lorsque nous arrivons à la fin de la liste. La lecture de la liste reprend
			idxElement=0; // depuis le début
		}
		
		
	}
	
	@Override
	public boolean hasNext() {
		return idxElement < maList.size();
	}
	
	@Override
	public T next() {
		T res = maList.get(idxElement);
		this.findNextElement();
		return res;
	}
	

}
