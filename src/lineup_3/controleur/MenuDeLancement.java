package lineup_3.controleur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class MenuDeLancement extends Application{



		static String MyPath = System.getProperty("user.dir")
				+ File.separator + "src"
				+ File.separator + "partieGraphique"
				+ File.separator + "controlleurs"
				+ File.separator;

		 public void start(Stage stage) throws IOException {
	         FXMLLoader loader = new FXMLLoader();
	         URL fxmlFileUrl = getClass().getResource("./gestionDesEvenements/lancement.fxml");
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
