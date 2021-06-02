package lineup_3.modele;

/**
 * Cette classe fait.........
 *
 * @author <a href="mailto:choukhiselmene@gmail.com">Selmene CHOUKHI</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 1 juin 2021
 * @version 1 juin 2021 16:21:47
 */
public enum Mode {
		// Class Enum
	CLASSIQUE("classique"), CHIFUMI("chifumi");
	
		// Class Attributes
	
	private String mode;
	
		// Constructor
	
	private Mode(String mode) {
		this.mode = mode;
	}
	
		// Methods
	
	public String getMode() {
		return this.mode;
	}
}
