package Controller;

import static application.Main.CerrarVentanaAnterior;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.CodeSmell;
import application.CodeSmells;
import application.Proyecto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CodeSmellsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CodeSmell> tablaCodesSmells;

    @FXML
    private TableColumn<CodeSmell, Integer> id;

    @FXML
    private TableColumn<CodeSmell, String> resultado;
    
    private ObservableList<CodeSmell> listaCodeSmells = FXCollections.observableArrayList();

 // Event Listener on Button.onAction
 	@FXML
 	public void atrasOnClick(ActionEvent event) {
 		CerrarVentanaAnterior(event); 
 	}
    
    public void contruyeObservableList(CodeSmells codesmells){
    
    	int id=0;
    	for(String dato:codesmells.getListaDatos()) {
    		this.listaCodeSmells.add(new CodeSmell(id,dato));
    		++id;
    	}
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb,CodeSmells codesmells) {
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	resultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));
    	contruyeObservableList(codesmells);
    	this.tablaCodesSmells.setItems(this.listaCodeSmells);
         }
}

