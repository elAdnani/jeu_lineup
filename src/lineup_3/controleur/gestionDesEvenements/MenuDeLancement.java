package lineup_3.controleur.gestionDesEvenements;


import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * 
 * Cette classe sert à demander à l'utilisateur un pseudonyme lui appartenant. </br>
 * Il est l'utilsateur de l'ordinateur, ce pseudonyme sera utilisé pour les parties locals comme multijoueurs.
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 3 juin 2021
 */
public class MenuDeLancement extends Application {
	@FXML
	AnchorPane pane;
	
	@FXML
	Button quitter;

	@FXML
	Text messageErreur;
	
	
	@FXML
	TextField pseudoDuJoueur;
	// où on entre son pseudo
	
	@FXML
	Button entrerDansLeJeu;
	// bouton passage du menu de départ, au menu central 
	
	
	/**
	 * Sert à récupérer le pseudo de l'utilisateur. Il doit obligatoirement avoir au moins 1 caractère et ne doit pas contenir le caractère '\'
	 */
	public void entrerPseudo() {
		
		if(pseudoDuJoueur.getLength()!=0 &&  !pseudoDuJoueur.getText().contains("\\")) {
			
			entrerDansLeJeu.setDisable(false);
		}
		else {
			
			entrerDansLeJeu.setDisable(true);
		}
	}

	/**
	 * @param event lorsque le joueur clique le bouton quitté, pour sortir de l'application
	 */
	public void Exit(ActionEvent event) {
		
		Stage stage = (Stage) quitter.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Permet un passage au menu principal. Il suffit pour ça d'avoir écrit avant ça un pseudo.
	 * @param event est lors que le joueur clique sur le bouton "jouer"
	 */
	public void entrerMenu(ActionEvent event) {
		if(pseudoDuJoueur.getLength()==0) {
			messageErreur.setStyle("-fx-font-weight: bold");
			
			entrerDansLeJeu.setDisable(true);
			messageErreur.setVisible(true);
			
		}
		else {
			//-----------entrer menu
		}
		
	}
	
	public void initialize() {
		entrerDansLeJeu.setDisable(true);
		messageErreur.setVisible(false);
		
		
	}

	/**
	 * Est l'affichage temporaire du fichier fxml.
	 */
	 public void start(Stage stage) throws IOException {
         FXMLLoader loader = new FXMLLoader();
         URL fxmlFileUrl = getClass().getResource("../lancement.fxml");
         if (fxmlFileUrl == null) {
                 System.out.println("Impossible de charger le fichier fxml");
                 System.exit(-1);
         }
         loader.setLocation(fxmlFileUrl);
         Parent root = loader.load();
         
         Scene scene = new Scene(root);
         
         stage.setScene(scene);
         
         stage.setResizable(false);
         stage.initStyle(StageStyle.TRANSPARENT);
         stage.show();
 }


 public static void main(String[] args) {
         Application.launch(args);
 }
}
