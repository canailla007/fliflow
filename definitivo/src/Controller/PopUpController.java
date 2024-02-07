package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class PopUpController{

	@FXML private Label labelID;
	
	public void initialize(URL url, ResourceBundle rb, String texto) {
		this.labelID.setText(texto);
	}
	
		

	
	
	
}
