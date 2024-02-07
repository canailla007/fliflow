package Controller;

import static application.Main.CerrarVentanaAnterior;
import static application.Main.ClientesAFichero;
import static application.Main.EliminarUsuario;


import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import application.Main;
import application.ModificacionWindow;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class UsuariosController {
	@FXML 
	private TableView<Usuario> tablaUsuarios;
	@FXML 
	private TableColumn<Proyecto, Integer> colId;
	@FXML 
	private TableColumn<Usuario, String> colUsuario;
	@FXML 
	private TableColumn<Usuario, String> colAdmin;
	@FXML 
	private TableColumn<Usuario, String> colModificar;
	@FXML 
	private TableColumn<Usuario, String> colEliminar;
	
	
	@FXML 
	private TextField fieldUsuario;
	@FXML 
	private TextField fieldContraseña;
	@FXML 
	private CheckBox adminCheck;
	
	
	private int idFuturo = 0;
	
	private ObservableList<Usuario> obs = FXCollections.observableArrayList();
	
	private Button botonEliminar;
	
	private Button botonModificar;
	
	public void initialize(URL url, ResourceBundle rb) {
			this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
			this.colId.setStyle("-fx-alignment: CENTER;");
			this.colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			this.colUsuario.setStyle("-fx-alignment: CENTER;");
			this.colAdmin.setCellValueFactory(new PropertyValueFactory<>("esAdmin"));
			this.colAdmin.setStyle("-fx-alignment: CENTER;");
			this.colModificar.setCellValueFactory(new PropertyValueFactory<>("botonEjecutar"));
			this.colModificar.setStyle("-fx-alignment: CENTER;");
			
			//funcion lambda para crear los botones de ejecutar el protyecto
			Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>> construirColEjecutar = (param) -> {
				final TableCell<Usuario, String> celda = new TableCell<Usuario, String>(){
					@Override
					public void updateItem(String item, boolean empty) {
						if(empty) {
							//si no hay ningun dato que rellenar lo dejamos a null para que no se muestre nada
							setGraphic(null);
							setText(null);
						}else {
							botonModificar = crearBoton("Modificar proyecto", "|>", "-fx-background-color: #FFEE58; -fx-font-weight: bold;");
							botonModificar.setOnAction((event) -> {
								//Aqui va el codigo que va a hacer el boton de ejecur al hacer click
								//crear fucnion en main que haga la logica del ejecutar
								Usuario user = getTableView().getItems().get(getIndex());
								modificarUsuarioOnClick(event, user);
							});
							setGraphic(botonModificar);
							setText(null);
						}
					}
				};
				return celda;
			};
			this.colModificar.setCellFactory(construirColEjecutar);
			
			this.colEliminar.setCellValueFactory(new PropertyValueFactory<>("botonEliminar"));
			this.colEliminar.setStyle("-fx-alignment: CENTER;");
			//funcion lambda para crear los botones de eliminar el protyecto
			Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>> construirColEliminar = (param) -> {
				final TableCell<Usuario, String> celda = new TableCell<Usuario, String>(){
					@Override
					public void updateItem(String item, boolean empty) {
						if(empty) {
							setGraphic(null);
							setText(null);
						}else {
							botonEliminar = crearBoton("Eliminar proyecto", "X", "-fx-background-color: #FF0000; -fx-font-weight: bold;");
							botonEliminar.setOnAction((event) -> {
								//Aqui va el codigo que va a hacer el boton de eliminar al hacer click
								Usuario pr = getTableView().getItems().get(getIndex());
								tablaUsuarios.getItems().remove(pr);
								//funcion para eliminar proyecto de los ficheros
								EliminarUsuario(pr);
							});
							setGraphic(botonEliminar);
							setText(null);
						}
					}
				};
				return celda;
			};
			this.colEliminar.setCellFactory(construirColEliminar);
			
			
			for (Usuario user : Main.listaUsuarios) {
				obs.add(user);
				if(user.getId() > idFuturo)
					idFuturo = user.getId();
			}
			
			
			ObservableList<Usuario> items = obs;
			this.tablaUsuarios.setItems(items);
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void atrasOnClick(ActionEvent event) {
		CerrarVentanaAnterior(event); 
	}
	
	@FXML
	public void crearUsuarioOnClick(ActionEvent event) {
		
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
			this.idFuturo++;
			Usuario usuarioNuevo = new Usuario(this.idFuturo,usuarioText, contraseñaText, esAdmin);
			Main.listaUsuarios.add(usuarioNuevo);
			ClientesAFichero();
			obs.add(usuarioNuevo);
			this.fieldUsuario.setText("");
			this.fieldContraseña.setText("");
			adminCheck.setSelected(false);
			this.tablaUsuarios.refresh();
		}
		
		//refrescar la tabla para que salga el nuevo proyecto
		
	}
	
	private Button crearBoton(String toolTip, String text, String style) {
		Button boton = new Button();
		boton.setTooltip(new Tooltip(toolTip));
		boton.setText(text);
		boton.setStyle(style);
		return boton;
	}
	
	@FXML
	public void modificarUsuarioOnClick(ActionEvent event, Usuario user) {
		CerrarVentanaAnterior(event); 
		Stage ModificacionWindow = new Stage();
		ModificacionWindow mp = new ModificacionWindow(user); 
		mp.start(ModificacionWindow);   
	}

   

    


}
