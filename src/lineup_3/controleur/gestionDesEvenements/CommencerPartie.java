package lineup_3.controleur.gestionDesEvenements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import outils.Couleur;
import outils.comparatorCouleur;
import outils.iterateurBoucle;

/**
 * 
 * Cette classe sert à demander à l'utilisateur la configuration de la partie qu'il souhaite. </br>
 * Elle est située avant la création de la partie, elle demande le nombre de côté, pion et de joueur d'une partie. </br>
 * Au minimum, on peut générer :</br>
 * - 3 côté </br>
 * - 3 pions </br>
 * - 2 joueurs </br>
 * 
 * @author <a href="mailto:adnan.kouakoua@univ-lille1.fr">Adnân KOUAKOUA</a>
 * IUT-A Informatique, Universite de Lille.
 * @date 8 juin 2021
 * 
 */
public class CommencerPartie extends Application{
	//TODO définir le maximum de joueur/pion/côté
	static int cptJoueur= 0;
	
	@FXML 
	AnchorPane pane;
	
	@FXML
	Button commencerPartie;
	
	@FXML 
	Label nmbreCote,nmbrePion,nmbreJoueur;
	
	@FXML
	Button cotePlus,pionPlus,joueurPlus;
	@FXML
	Button coteMoins,pionMoins, joueurMoins;
	
	@FXML
	VBox listeDesJoueurs;
	
	/**
	 * Est la liste de Couleur que l'utilisateur peut choisir, il y a jusqu'à 6 couleurs dans {@link Couleur}
	 */
	List<Couleur> CouleurDisponible = new ArrayList<>();
	/**
	 * Est la liste de Couleur que l'utilisateur a déjà parmi la selection de la classe {@link Couleur}
	 */
	List<Couleur> CouleurIndisponible = new ArrayList<>();	
	
	/**
	 * @param c est une couleur recherché 
	 * @return Couleur dans la liste des couleurs qui correspond à celle en paramètre.
	 */
	private Couleur chercherCouleur(Color c) {

		for(Couleur co : CouleurIndisponible) {
			
			if(co.getCouleur().equals(c)) {

				return co;

			}
		}

		return null;
	}
	
	/**
	 *  On récupère la couleur qui suit celle en paramètre
	 * @param c  Couleur entrant qui était celle de l'utilisateur
	 */
	@SuppressWarnings("unchecked")
	public Couleur ChangerCouleur(Couleur c) {

		mettreDisponible(c); 

		Collections.sort(CouleurDisponible, new comparatorCouleur());
		
		
		@SuppressWarnings("rawtypes")
		Iterator<Couleur> it = new iterateurBoucle(CouleurDisponible);
		
		while( c!=null && it.next()!=c ){
			// on reprend la couleur qui était sélectionnée par l'utilisateur
		}	
		
		Couleur nouvelle = it.next(); // la suivante est la couleur présentée à l'utilisateur
		mettreIndisponible(nouvelle); 
		
		return nouvelle;
		
	}
	
	/**
	 * permet de rendre couleur disponible de sorte qu'elle puisse être choisie par l'utilisateur
	 * @param c couleur voulant être mis dans la liste disponible
	 */
	public void mettreDisponible(Couleur c) {
		if(c!=null) {
			
			this.CouleurIndisponible.remove(c);
			this.CouleurDisponible.add(c);
		}

	}
	/**
	 * permet de rendre couleur indisponible car elle est déjà utilisé par un utilisateur
	 * @param ccouleur voulant être mis dans la liste indisponible
	 */
	public void mettreIndisponible(Couleur c) {
		if(c!=null) {
			
			this.CouleurDisponible.remove(c);
			this.CouleurIndisponible.add(c);
		}
	}
	
	/**
	 * Parcours la liste des couleurs en cours d'utilisation.
	 * @param c couleur recherché dans la liste indisponible (celle qui est utilisée par l'utilisateur)
	 * @return la couleur dans la liste indisponible qui correspond à celle mis en paramètre.
	 */
	public Couleur trouverCouleurIndisponible(Color c) {
		
		for(Couleur co : this.CouleurIndisponible) {
			
			if(co.getCouleur().equals(c)) {
				
				return co;
			}
		}
		return null;
	}

	/**
	 * On a pré-déposé les boutons de sortes qu'ils soit à leur selection minimale.
	 * Ainsi on désactive temporairement les boutons.
	 * Le jeu est composé au minimum de deux joueurs, le joueur principale et un autre joueur (pour obtenir une partie).
	 * Et chacun possède un code Couleur, au lancement on décide de proposer les différentes Couleurs
	 * 	 
	 * */
	public void initialize() {
		this.coteMoins.setDisable(true);
		this.pionMoins.setDisable(true);
		this.joueurMoins.setDisable(true);
	
		this.CouleurDisponible= FXCollections.observableArrayList(Couleur.BLEU,Couleur.BLANC,Couleur.VERT,Couleur.ROUGE,Couleur.ORANGE,Couleur.NOIR);
		
		NouveauJoueur("Rima");
		NouveauJoueur();
		

	}
	
	
	/**
	 * Diminue le nombre de côté du plateau
	 * @param event appuie sur le bouton "-" 
	 */
	public void EnleverCote(ActionEvent event) {

		int nombreDeCote = Integer.valueOf(this.nmbreCote.getText());
		
		this.nmbreCote.setText(nombreDeCote-1+"");
		
		if(nombreDeCote<=4) {
			this.coteMoins.setDisable(true);
		}
		else {
			
			if(coteMoins.isDisable()) {
				this.coteMoins.setDisable(false);
			}
		}
	}
	
	/**
	 * Augmente le nombre de côté du plateau
	 * @param event appuie sur le bouton "+" 
	 */
	public void AjouterCote(ActionEvent event) {
		
		int nombreDeCote= Integer.valueOf(this.nmbreCote.getText());
		
		this.nmbreCote.setText(nombreDeCote+1+"");
		this.coteMoins.setDisable(false);
		 // TODO Faire une schéma du plateau (un aperçu)
		
	}
	
	/**
	 * Diminue le nombre de pion par joueur
	 * @param event appuie sur le bouton "-" 
	 */
	public void EnleverPion(ActionEvent event) {
		int nombreDePion = Integer.valueOf(this.nmbrePion.getText());
		
		this.nmbrePion.setText(nombreDePion-3+"");
		
		if(nombreDePion<=6) {
			this.pionMoins.setDisable(true);
		}
	}
	/**
	 * Augmente le nombre de pion par joueur
	 * @param event appuie sur le bouton "+" 
	 */
	public void AjouterPion(ActionEvent event) {
		int nombreDePion = Integer.valueOf(this.nmbrePion.getText());
		this.pionMoins.setDisable(false);
		this.nmbrePion.setText(nombreDePion+3+"");
		
		
	}
	
	/**
	 * Supprime un joueur dans la configuration de la partie
	 * @param event appuie sur le bouton "-" 
	 */
	public void EnleverJoueur(ActionEvent event) {
		int nombreDeJoueur = Integer.valueOf(this.nmbreJoueur.getText());
		
		if(nombreDeJoueur<=3) { // 
			this.joueurMoins.setDisable(true);
		}
		
		this.listeDesJoueurs.getChildren().remove(listeDesJoueurs.getChildren().size()-1); // on enleve le joueur


		cptJoueur--;
		
		this.nmbreJoueur.setText(nombreDeJoueur-1+"");

	}
	 /**
	  * Ajoute un joueur dans la configuration de la partie
	  * @param pseudo du joueur s'il est déjà pré-défini
	  */
	public void AjouterJoueur(String pseudo) {
		int nombreDeJoueur = Integer.valueOf(this.nmbreJoueur.getText());
		
		this.nmbreJoueur.setText(nombreDeJoueur+1+"");
		if(nombreDeJoueur>Couleur.values().length-2) {
			this.joueurPlus.setDisable(true);
		}
		else{
			this.joueurMoins.setDisable(false);
		}
		if(pseudo.equals("")) {
			NouveauJoueur();
		}
		else {
			NouveauJoueur(pseudo);
		}
			
	}
	
	
	/**
	 * Augmente le nombre de joueur dans la partie
	 * @param event appuie sur le bouton "+" 
	 */
	public void AjouterJoueur(ActionEvent event) {
		AjouterJoueur("");
		
	}
	/**
	 * Permet d'ajouter une configuration joueur avec un pseudo vide
	 */
	public void NouveauJoueur() {
		NouveauJoueur(null);
	}
	
	/**
	 *  Permet d'ajouter une configuration joueur avec un pseudo spécifié
	 * @param pseudo nom du l'utilisateur choisi
	 */
	public void NouveauJoueur(String pseudo) {

		cptJoueur++;
		
		Label text = new Label();
		
		text.setText(cptJoueur+" - "); // indication du numéro du joueur
		text.setStyle("-fx-font-size: 1em;");

		Circle famille = new Circle(5, this.CouleurDisponible.get(0).getCouleur());
		mettreIndisponible( this.CouleurDisponible.get(0));
		famille.setRadius(10);
		 // on vient récupérer la premiere couleur de la liste.
		// elle est indiquée comme "indisponible" pour ne pas être utilisé par deux utilisateurs.
		
		
		
		// voici ce que voit l'utilisateur, la "famille" c'est à dire la team/couleur à laquelle il appartient.
		
		ToggleButton robot = new ToggleButton("ROBOT ?"); // l'utilisateur est-il un robot ?
		robot.setStyle("-fx-font-size: 1em;");
		
		Button CouleurSuiv = new Button(">");
		CouleurSuiv.setStyle("-fx-background-color: transparent;-fx-font-size: 0.5em;");
		// bouton cliquable qui permet de changer de couleur
		

		CouleurSuiv.setOnAction(e->{ // si la flèche est cliqué =>

			Paint Couleur = ChangerCouleur( chercherCouleur( (Color)famille.getFill() ) ).getCouleur();
			
			famille.setFill(Couleur); // on récupérer la couleur suivante

			
		});
		
		TextField pseudonyme; // pseudonyme du joueur à choisir
		
		HBox configurationJoueur;
		pseudonyme= new TextField();
		if(pseudo==null) {

			pseudonyme.setPromptText("pseudo");
			
		} // si le nom n'est pas définie alors l'utilisateur peut le choisir
		else {
			
			pseudonyme.setText(pseudo);
			pseudonyme.setDisable(true);
			// si le pseudo a un déjà un pseudo déjà pré-définie ( ex: multijoueur), alors il n'est pas modifiable
		}
		
		pseudonyme.setStyle("-fx-font-size: 1em;");
		
		
		configurationJoueur = new HBox(10,text,pseudonyme,robot,famille,CouleurSuiv);
	
		Region espace = new Region();
        HBox.setHgrow(espace, Priority.ALWAYS);
        
        
		this.listeDesJoueurs.getChildren().addAll(espace,configurationJoueur);
		this.listeDesJoueurs.setSpacing(10);
	}

	/**
	 * Est l'affichage temporaire du fichier fxml.
	 */
	 public void start(Stage stage) throws IOException {
		 
         FXMLLoader loader = new FXMLLoader();
         URL fxmlFileUrl = getClass().getResource("../CommencerPartie.fxml");
         if (fxmlFileUrl == null) {
                 System.out.println("Impossible de charger le fichier fxml");
                 System.exit(-1);
         }
         loader.setLocation(fxmlFileUrl);
         Parent root = loader.load();
         
         Scene scene = new Scene(root);

         stage.setScene(scene);
         stage.setMinWidth(770);
         
         stage.initStyle(StageStyle.DECORATED);
         
         stage.show();
         
//         stage.widthProperty().addListener((observable, oldValue, newValue) ->{
//
//        	 this.width= (double) newValue;
//
//        	 
//         }
//                 );
         
         
 }
	 
	 public static void main(String[] args) {
         Application.launch(args);
         
 }
	 
}
