package Controller;

import static application.Main.CerrarVentanaAnterior;
import static application.Main.ComprobarUsuario;

import java.util.Optional;

import application.Main;
import application.MenuPrincipal;
import application.MenuPrincipalAdmin;
import application.PopUpMain;
import application.ProyectoWindow;
import application.Usuario;
import application.UsuarioWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuPrincipalAdminController {
	@FXML 
	private ImageView  imagenMenu;
	
	public void initialize() {
		
		Image imagen = new Image(getClass().getResourceAsStream("logo-transparent.png"));
		imagenMenu.setImage(imagen);
	}
	// Event Listener on Button.onAction
	@FXML
	public void proyectoMenuOnClick(ActionEvent event) {
		//CerrarVentanaAnterior(event); 
		Stage ProyectoWindow = new Stage();
		ProyectoWindow mp = new ProyectoWindow(); 
		mp.start(ProyectoWindow);   
	}
	
	public void usuarioMenuOnClick(ActionEvent event) {
		//CerrarVentanaAnterior(event); 
		Stage UsuarioWindow = new Stage();
		UsuarioWindow mp = new UsuarioWindow(); 
		mp.start(UsuarioWindow);   
	}
	
	public void cerrarSesionOnClick(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Cerrar sesión");
    	alert.setHeaderText("¿Seguro que quiere cerrar sesión?");
    	// Establecer una imagen personalizada como icono
    	Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(icon);
    	
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			CerrarVentanaAnterior(event); 
			Stage Main = new Stage();
			Main mp = new Main(); 
			mp.start(Main);  
		}  
	}
	
}
