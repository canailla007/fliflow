package Controller;

import static application.Main.CerrarVentanaAnterior;
import static application.Main.ModificarUsuario;


import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import application.Main;
import application.PopUpMain;
import application.Proyecto;
import application.ProyectoWindow;
import application.Usuario;
import application.UsuarioWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ModificacionUsuarioController {
	
	@FXML 
	private TextField fieldUsuario;
	@FXML 
	private TextField fieldContraseña;
	@FXML
	private CheckBox adminCheck;
	@FXML 
	private ImageView  imagenMenu;
	
	private int id;
	
	
	public void initialize(URL url, ResourceBundle rb, Usuario user) {
		Image imagen = new Image(getClass().getResourceAsStream("logo-transparent.png"));
		imagenMenu.setImage(imagen);
		this.fieldUsuario.setText(user.getNombre());
		this.fieldContraseña.setText(user.getPassword());
		this.adminCheck.setSelected(user.getEsAdmin());
		this.id = user.getId();
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void atrasOnClick(ActionEvent event) {
		CerrarVentanaAnterior(event); 
		Stage UsuarioWindow = new Stage();
		UsuarioWindow mp = new UsuarioWindow(); 
		mp.start(UsuarioWindow);  
	}
	
	@FXML
	public void ModificarUsuarioOnClick(ActionEvent event) {
		
		//almacenamos en el listado en un metodo en el que guarde el dato en el listado y en el archivo para que al salir se guarde
		String usuarioText = fieldUsuario.getText();
		String contraseñaText = fieldContraseña.getText();
		boolean esAdmin = adminCheck.isSelected();
		if(usuarioText.trim().isEmpty() || contraseñaText.trim().isEmpty()) 
		{
    		Main.crearAlert(new Alert(AlertType.ERROR), "Error", "El nombre y la contraseña tienen que rellenarse.", new Image(getClass().getResourceAsStream("logo-transparent.png")));
		}
		else 
		{
			boolean result = ModificarUsuario(id, usuarioText, contraseñaText, esAdmin);
			if(result) {
				CerrarVentanaAnterior(event); 
				Main.crearAlert(new Alert(AlertType.INFORMATION), "Informacion", "El usuario se ha modificado correctamente.", new Image(getClass().getResourceAsStream("logo-transparent.png")));
		    	
				Stage UsuarioWindow = new Stage();
				UsuarioWindow mp = new UsuarioWindow(); 
				mp.start(UsuarioWindow);  
			}
		}
		
		
		
		
	}
	
}
