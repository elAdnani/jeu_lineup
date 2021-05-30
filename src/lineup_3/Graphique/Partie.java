package lineup_3.Graphique;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class Partie {

	
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
	
	
	
	public void MessageQuitter(ActionEvent event) {
		
		this.menuMessage.setVisible(false);
	}
	
	public void RajoutDeMessage(ActionEvent event) {
		
		this.menuMessage.setVisible(true);
	}
	
	public void Message1(ActionEvent event) {
		// RajoutDeMessage 'Bon courage !'
		
	}
	public void Message2(ActionEvent event) {
		// RajoutDeMessage 'Bien joué !'
	}
	public void Message3(ActionEvent event) {
		// RajoutDeMessage 'Tu peux mieux faire !'
	}
	
}
