package application;

import Controller.DatosUsuarioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class DatosWindow extends Application{
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/datosUsuario.fxml"));
	        Parent root = loader.load();
	        // Obtener el controlador
	        DatosUsuarioController controller = loader.getController();
	        controller.initialize(null, null);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Menu Principal/ Mis Datos");
			primaryStage.setScene(scene);
			Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
