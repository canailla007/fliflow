package application;
	
import Controller.MainController;
import Controller.ModificacionUsuarioController;
import Controller.PopUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class PopUpMain extends Application {
	
	private String texto;
	
	public PopUpMain(String texto) {
        this.texto = texto;
    }
	@Override
	public void start(Stage primaryStage) {
		try {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PopUpMain.fxml"));
	        Parent root = loader.load();
	        // Obtener el controlador
	        PopUpController controller = loader.getController();
	        controller.initialize(null, null, this.texto);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
