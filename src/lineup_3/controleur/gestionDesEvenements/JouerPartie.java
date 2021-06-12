package lineup_3.controleur.gestionDesEvenements;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Est le menu lancé lorsque les utilisateurs sont en train de jouer.
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 8 juin 2021
 */
public class JouerPartie {

	
	@FXML
	Text titreDeLaPartie;
	
	@FXML
	TextFlow ConfigutationDeLaPartie;
	
	@FXML
	Canvas schemaDeLaPartie;
	
	@FXML
	ListView<String> tchat;
	
	@FXML
	VBox menuMessage;
	
	
	public void initialize() {
		this.menuMessage.setVisible(false);
	}
	public void MessageQuitter() {
		
		this.menuMessage.setVisible(false);
		
	}
	
	public void RajoutDeMessage(ActionEvent event) {
		
		this.menuMessage.setVisible(true);
	}
	
	public void Message1(ActionEvent event) {
		// RajoutDeMessage 'Bon courage !'
		MessageQuitter();
		
	}
	public void Message2(ActionEvent event) {
		// RajoutDeMessage 'Bien joué !'
		MessageQuitter();
	}
	public void Message3(ActionEvent event) {
		// RajoutDeMessage 'Tu peux mieux faire !'
		MessageQuitter();
	}
	
}
