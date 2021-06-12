package lineup_3.controleur.gestionDesEvenements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lineup_3.modele.ManipulationPartie;

/**
 * 
 * Cette classe est le menu aura dans les parties "local" comme "multijoueur". </br>
 * Il pourra depuis ce menu, manipuler les parties, qui utilisera la classe {@link ManipulationPartie}.</br>
 *
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 5 juin 2021
 */
public class MenuCreationDePartie extends Application{
	
	private List<String> listeTotalDesParties = new ArrayList<String>();
	
	
	@FXML
	private TextField rechercherPartie;
	
	@FXML
	private ListView<String> ListeDesParties; // ici on mettra une liste de Partie
	

	
	public void RechercheEnCours() {
		
	}
	
	public void JouerPartie(ActionEvent event) {
		
	}
	
	public void ModifierPartie(ActionEvent event) {
		
	}
	
	public void SupprimerPartie(ActionEvent event) {
		
	}
	
	public void CreerPartie(ActionEvent event) {
		
	}
	
	public void CopierPartie(ActionEvent event) {
		
	}
	
	public void AnnulerSelection(ActionEvent event) {
		
	}
	

	
	
	/**
	 * Est l'affichage temporaire du fichier fxml.
	 */
	 public void start(Stage stage) throws IOException {
         FXMLLoader loader = new FXMLLoader();
         URL fxmlFileUrl = getClass().getResource("../menuCreationDePartie.fxml");
         if (fxmlFileUrl == null) {
                 System.out.println("Impossible de charger le fichier fxml");
                 System.exit(-1);
         }
         loader.setLocation(fxmlFileUrl);
         Parent root = loader.load();
         
         Scene scene = new Scene(root);
         stage.setMinWidth(600);
         stage.setMinHeight(435);
         stage.setScene(scene);
         
         stage.show();
 }


 public static void main(String[] args) {
	 
         Application.launch(args);
 }
}
