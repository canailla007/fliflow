package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Main;
import application.MenuPrincipal;
import application.MenuPrincipalAdmin;
import application.PopUpMain;
import application.Usuario;
import javafx.event.ActionEvent;
import static application.Main.ComprobarUsuario;

import java.net.URL;
import java.util.ResourceBundle;

import static application.Main.CerrarVentanaAnterior;

public class MainController{

	@FXML private TextField textUsuario;
	@FXML private PasswordField textContrasena;
	
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void accesoBotonOnClick(ActionEvent event) {
		
		String usuarioText = textUsuario.getText();
		String contrasenaText = textContrasena.getText();
        Usuario user = ComprobarUsuario(usuarioText, contrasenaText);
        
        if(user == null) {
        	//se queda como esta y sacamos popUp informando
    		Main.crearAlert(new Alert(AlertType.ERROR), "Error", "Usuario o contraseña incorrectos. Vuelva a intentarlo.", new Image(getClass().getResourceAsStream("logo-transparent.png")));
        }
        else if(user.getEsAdmin()) {
        	//Usuario admin de la app
        	CerrarVentanaAnterior(event); 
        	Stage MenuPrincipalAdmin = new Stage();
        	MenuPrincipalAdmin mp = new MenuPrincipalAdmin(); 
    		mp.start(MenuPrincipalAdmin);
        }
        else {
        	//Usuario normal
        	CerrarVentanaAnterior(event); 
        	Stage MenuPrincipal = new Stage();
    		MenuPrincipal mp = new MenuPrincipal(); 
    		mp.start(MenuPrincipal);
        } 
        
        	
        
	}

	
	
	
}
