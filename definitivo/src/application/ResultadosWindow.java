package application;

import Controller.ResultadosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ResultadosWindow extends Application{
	
	private Proyecto proyecto;
	
	
	public ResultadosWindow(Proyecto pro) {
		this.proyecto = pro;
	}
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Resultados.fxml"));
	        Parent root = loader.load();
	        // Obtener el controlador
	        ResultadosController controller = loader.getController();
	        controller.initialize(null, null, this.proyecto);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Menu Principal/ Proyectos / Resultados");
			primaryStage.setScene(scene);
			Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
