package application;

import Controller.CodeSmellsController;
import Controller.MainController;
import Controller.ProyectosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CodeSmellsWindow extends Application{
private CodeSmells codesmells;
	
	
	public CodeSmellsWindow(CodeSmells code) {
		this.codesmells = code;
		 System.out.println("CodeSmells:"+code.getTotal());
	}
	public void start(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CodeSmells.fxml"));
	        Parent root = loader.load();
	        // Obtener el controlador
	        CodeSmellsController controller = loader.getController();
	        controller.initialize(null, null,this.codesmells);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Menu Principal/CodeSmells");
			primaryStage.setScene(scene);
			Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
