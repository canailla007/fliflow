package Controller;

import static application.Main.CerrarVentanaAnterior;
import static application.Main.ProyectosAFichero;
import static application.Main.EliminarProyecto;


import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import application.CodeSmells;
import application.DatosWindow;
import application.Main;
import application.PopUpMain;
import application.Proyecto;
import application.ProyectoWindow;
import application.Resultados;
import application.ResultadosWindow;
import application.Selenium;
import application.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class ProyectosController {
	@FXML 
	private TableView<Proyecto> tablaProyectos;
	@FXML 
	private TableColumn<Proyecto, Integer> colID;
	@FXML 
	private TableColumn<Proyecto, Integer> colIdUser;
	@FXML 
	private TableColumn<Proyecto, String> colNombre;
	@FXML 
	private TableColumn<Proyecto, String> colURL;
	@FXML 
	private TableColumn<Proyecto, LocalDate> colFechaCreacion;
	@FXML 
	private TableColumn<Proyecto, LocalDate> colFechaModificacion;
	@FXML 
	private TableColumn<Proyecto, String> colEliminar;
	@FXML 
	private TableColumn<Proyecto, String> colEljecutar;
	@FXML 
	private TableColumn<Proyecto, String> colConsultar;
	
	@FXML 
	private TextField fieldNombreProyecto;
	@FXML 
	private TextField fieldRutaProyecto;
	
	private int idFuturo = 0;
	
	private ObservableList<Proyecto> obs = FXCollections.observableArrayList();
	
	private Button botonEliminar;
	
	private Button botonEjecutar;
	
	private Button botonConsultar;
	
	public void initialize(URL url, ResourceBundle rb) {
		
			this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
			this.colID.setStyle("-fx-alignment: CENTER;");
			this.colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
			this.colIdUser.setStyle("-fx-alignment: CENTER;");
			this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			this.colNombre.setStyle("-fx-alignment: CENTER-LEFT;");
			this.colURL.setCellValueFactory(new PropertyValueFactory<>("url"));
			this.colURL.setStyle("-fx-alignment: CENTER-LEFT;");
			this.colFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
			this.colFechaCreacion.setStyle("-fx-alignment: CENTER;");
			this.colFechaModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaUltimoUso"));
			this.colFechaModificacion.setStyle("-fx-alignment: CENTER;");
			this.colEljecutar.setCellValueFactory(new PropertyValueFactory<>("botonEjecutar"));
			this.colEljecutar.setStyle("-fx-alignment: CENTER;");
			
			//funcion lambda para crear los botones de ejecutar el protyecto
			Callback<TableColumn<Proyecto, String>, TableCell<Proyecto, String>> construirColEjecutar = (param) -> {
				final TableCell<Proyecto, String> celda = new TableCell<Proyecto, String>(){
					@Override
					public void updateItem(String item, boolean empty) {
						if(empty) {
							//si no hay ningun dato que rellenar lo dejamos a null para que no se muestre nada
							setGraphic(null);
							setText(null);
						}else {
							botonEjecutar = crearBoton("Ejecutar proyecto", "|>", "-fx-background-color: #31B404; -fx-font-weight: bold;");
							botonEjecutar.setOnAction((event) -> {
								//Aqui va el codigo que va a hacer el boton de ejecur al hacer click
								//crear fucnion en main que haga la logica del ejecutar
								Proyecto pr = getTableView().getItems().get(getIndex());
								try {
									Proyecto resultado=new Selenium().Ejecutar(pr);
									//Añadir para actualizar proyecto
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Stage ResultadosWindow = new Stage();
								ResultadosWindow mp = new ResultadosWindow(pr); 
								mp.start(ResultadosWindow);
							});
							setGraphic(botonEjecutar);
							setText(null);
						}
					}
				};
				return celda;
			};
			this.colEljecutar.setCellFactory(construirColEjecutar);
			
			this.colConsultar.setCellValueFactory(new PropertyValueFactory<>("botonConsultar"));
			this.colConsultar.setStyle("-fx-alignment: CENTER;");
			Callback<TableColumn<Proyecto, String>, TableCell<Proyecto, String>> construirColConsultar = (param) -> {
				final TableCell<Proyecto, String> celda = new TableCell<Proyecto, String>(){
					@Override
					public void updateItem(String item, boolean empty) {
						if(empty) {
							//si no hay ningun dato que rellenar lo dejamos a null para que no se muestre nada
							setGraphic(null);
							setText(null);
						}else {
							botonConsultar = crearBoton("Consultar resultados", "O", "-fx-background-color: #01A9DB; -fx-font-weight: bold;");
							botonConsultar.setOnAction((event) -> {
								//Aqui va el codigo que va a hacer el boton de ejecur al hacer click
								//crear fucnion en main que haga la logica del ejecutar
								Proyecto pr = getTableView().getItems().get(getIndex());
								Stage ResultadosWindow = new Stage();
								ResultadosWindow mp = new ResultadosWindow(pr); 
								mp.start(ResultadosWindow);
							});
							setGraphic(botonConsultar);
							setText(null);
						}
					}
				};
				return celda;
			};
			this.colConsultar.setCellFactory(construirColConsultar);
			
			this.colEliminar.setCellValueFactory(new PropertyValueFactory<>("botonEliminar"));
			this.colEliminar.setStyle("-fx-alignment: CENTER;");
			//funcion lambda para crear los botones de eliminar el protyecto
			Callback<TableColumn<Proyecto, String>, TableCell<Proyecto, String>> construirColEliminar = (param) -> {
				final TableCell<Proyecto, String> celda = new TableCell<Proyecto, String>(){
					@Override
					public void updateItem(String item, boolean empty) {
						if(empty) {
							setGraphic(null);
							setText(null);
						}else {
							botonEliminar = crearBoton("Eliminar proyecto", "X", "-fx-background-color: #FF0000; -fx-font-weight: bold;");
							botonEliminar.setOnAction((event) -> {
								//Aqui va el codigo que va a hacer el boton de eliminar al hacer click
								Proyecto pr = getTableView().getItems().get(getIndex());
							
								//funcion para eliminar proyecto de los ficheros
								
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Cerrar sesión");
						    	alert.setHeaderText("¿Seguro que quiere eliminar este proyecto?");
						    	// Establecer una imagen personalizada como icono
						    	Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
						        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
						        alertStage.getIcons().add(icon);
						    	
								Optional<ButtonType> result = alert.showAndWait();
								if(result.get() == ButtonType.OK) {	 
									EliminarProyecto(pr);
									tablaProyectos.getItems().remove(pr);
								}
							});
							setGraphic(botonEliminar);
							setText(null);
						}
					}
				};
				return celda;
			};
			this.colEliminar.setCellFactory(construirColEliminar);
			
			
			
			
			for (Proyecto proyect : Main.listaProyectos) {
				if(Main.user.getEsAdmin())
					obs.add(proyect);
				else {
					//el usuario no es admin por lo tanto se cogen los proyectos que sean de su id
					if(proyect.getIdUser() == Main.user.getId())
						obs.add(proyect);
				}
				if(proyect.getId() > idFuturo)
					idFuturo = proyect.getId();
			}
			ObservableList<Proyecto> items = obs;
			this.tablaProyectos.setItems(items);
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void atrasOnClick(ActionEvent event) {
		CerrarVentanaAnterior(event); 
	}
	
	@FXML
	public void crearProyectoOnClick(ActionEvent event) {
		
		//almacenamos en el listado en un metodo en el que guarde el dato en el listado y en el archivo para que al salir se guarde
		String nombreProyectoText = fieldNombreProyecto.getText();
		String rutaProyectoText = fieldRutaProyecto.getText();
		if(nombreProyectoText.trim().isEmpty() || rutaProyectoText.trim().isEmpty()) 
		{
    		Main.crearAlert(new Alert(AlertType.ERROR), "Error", "El nombre y la ruta tienen que rellenarse.", new Image(getClass().getResourceAsStream("logo-transparent.png"))); 
		}
		else {
			LocalDate fechaActual = LocalDate.now();
			this.idFuturo++;
			Proyecto proyectoNuevo = new Proyecto(this.idFuturo, Main.user.getId(), nombreProyectoText, rutaProyectoText, fechaActual, fechaActual, new Resultados("0","0.0", "0", new CodeSmells("0", null)));
			Main.listaProyectos.add(proyectoNuevo);
			ProyectosAFichero();
			obs.add(proyectoNuevo);
			this.fieldNombreProyecto.setText("");
			this.fieldRutaProyecto.setText("");
			this.tablaProyectos.refresh();
			Main.crearAlert(new Alert(AlertType.INFORMATION), "Informacion", "El proyecto se ha creado correctamente.", new Image(getClass().getResourceAsStream("logo-transparent.png")));
	    	
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
}
