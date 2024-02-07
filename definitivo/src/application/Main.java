package application;
	
import static application.Main.ClientesAFichero;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import Controller.MainController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;


public class Main extends Application implements Serializable {
	
	//listado para almacenar los usuarios y poder trabajar con ellos
	public static ArrayList<Usuario> listaUsuarios = new ArrayList();
	//listado para almacenar los proyectos y poder trabajar con ellos
	public static ArrayList<Proyecto> listaProyectos = new ArrayList();
	
	public static Usuario user;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*Usuario us = new Usuario(0,"admin", "admin", true);
			listaUsuarios.add(us);
			ClientesAFichero();*/
			/*Resultados r = new Resultados("1","15.0", "70", new CodeSmells("5", null));
			listaProyectos.add(new Proyecto(0,0,"prueba1", "C:\\Users\\alvar\\git\\ProyectoIngenieria\\ProyectoIngenieria\\src\\PoyectoIngenieria",LocalDate.of(2023, 12, 13),LocalDate.of(2023, 12, 14), r));
			ProyectosAFichero();*/
			LeerProyectosDeFichero();
			LeerUsuariosDeFichero();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Main.fxml"));
	        Parent root = loader.load();
	        // Obtener el controlador
	        MainController controller = loader.getController();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Acceso");
			primaryStage.setScene(scene);
			Image icon = new Image(getClass().getResourceAsStream("logo-transparent.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	//Guardar todos los usuairo en un fichero binario de nombre "usuarios.dat"
    public static void ClientesAFichero(){
        if(listaUsuarios.size()>0){
            try {
                ObjectOutputStream escribeFichero = new ObjectOutputStream(new FileOutputStream("usuarios.dat"));
                escribeFichero.writeObject(listaUsuarios);
                escribeFichero.flush(); 
                escribeFichero.close();

            }catch (IOException e) {
                System.out.println( e.getMessage() );
            }

        }
    }
	 
    //Lee los usuarios desde un fichero binario, "usuarios.dat"
    public static void LeerUsuariosDeFichero(){
        try{
            ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream("usuarios.dat"));
            listaUsuarios = (ArrayList<Usuario>)leyendoFichero.readObject();
            leyendoFichero.close();
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }
    
    public static void LeerProyectosDeFichero(){
        try{
            ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream("proyectos.dat"));
            listaProyectos = (ArrayList<Proyecto>)leyendoFichero.readObject();
            leyendoFichero.close();
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }
    
    public static void ProyectosAFichero(){
        if(listaProyectos.size()>0){
            try {
                ObjectOutputStream escribeFichero = new ObjectOutputStream(new FileOutputStream("proyectos.dat"));
                escribeFichero.writeObject(listaProyectos);
                escribeFichero.flush(); 
                escribeFichero.close();

            }catch (IOException e) {
                System.out.println( e.getMessage() );
            }

        }
    }
    
    public static void EliminarProyecto(Proyecto pr){
    	listaProyectos.remove(pr);
    	ProyectosAFichero();
    }
    
    public static void EliminarUsuario(Usuario user){
    	listaUsuarios.remove(user);
    	ClientesAFichero();
    }
    //Se comprueba si el usuario existe ya dentro de la lista de usuarios
    public static Usuario ComprobarUsuario(String nombreComprobar, String contraseñaComprobar) {
    	
    	for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombre().equals(nombreComprobar) && usuario.getPassword().equals(contraseñaComprobar)) 
            {
                Main.user = usuario;
            	return usuario;
            }
        }
    	return null;
    }
    
    public static boolean ModificarUsuario(int id, String newNombre, String newPassword, boolean newEsAdmin){
    	boolean result = false;
    	Usuario user = buscarUsuarioPorId(id);
    	if(user != null) {
    		result = true;
    		EliminarUsuario(user);
    		Usuario usuarioActualizado = new Usuario(id,newNombre,  newPassword,  newEsAdmin);
    		Main.listaUsuarios.add(usuarioActualizado);
    		Main.user = usuarioActualizado;
    		ClientesAFichero();
    	}
    	
    	return result;
    }
    
    private static Usuario buscarUsuarioPorId(int idBuscado) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == idBuscado) {
                return usuario; // Usuario encontrado
            }
        }
        return null; // Usuario no encontrado
    }
    
    public static void CerrarVentanaAnterior(ActionEvent event) {
    	// Obtener el Stage asociado al evento
        Stage originStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana del menú principal
        originStage.close();
    }
    
    public static void crearAlert(Alert alert,String title, String header, Image icon) {
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	// Establecer una imagen personalizada como icono
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(icon);
    	
    	alert.showAndWait();
    	
    }
    
    
}
