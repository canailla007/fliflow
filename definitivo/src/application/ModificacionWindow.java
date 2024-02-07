package application;

import Controller.MainController;
import Controller.ModificacionUsuarioController;
import Controller.ProyectosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ModificacionWindow extends Application{
	private Usuario usuario;
	
	public ModificacionWindow(Usuario usuario) {
        this.usuario = usuario;
    }
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ModificacionUsuario.fxml"));
	        Parent root = loader.load();
	        // Obtener el controlador
	        ModificacionUsuarioController controller = loader.getController();
	        controller.initialize(null, null, usuario);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Menu Principal/ Usuarios / Modificacion Usuario");
			primaryStage.setScene(scene);
			Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
