package lineup_3.modele;

import java.io.Serializable;

/**
 * Cette classe détermine le mode du jeu d'une partie. </br>
 * Elle détermine par ailleurs le type d'un pion.</br>
 * Un pion peut être un pion classique {@link PionClassique} </br>
 * Ou alors un pion Chifumi {@link PionChifumi}
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 1 juin 2021
 * @version 1 juin 2021 16:21:47
 */
public enum Mode implements Serializable {

	CLASSIQUE("classique"), CHIFUMI("chifumi");

	private String mode;
	

	
	private Mode(String mode) {
		this.mode = mode;
	}
	
	
	public String getMode() {
		return this.mode;
	}
}
