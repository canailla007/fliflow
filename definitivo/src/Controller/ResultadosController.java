package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static application.Main.CerrarVentanaAnterior;


import java.net.URL;
import java.util.ResourceBundle;

import application.CodeSmellsWindow;
import application.Proyecto;
import application.ProyectoWindow;
import javafx.event.ActionEvent;

public class ResultadosController {
	
	@FXML 
	private Label labeBugs;
	@FXML 
	private Label labelCodeSmells;
	@FXML 
	private Label labelVulnerabilidades;
	@FXML 
	private Label labelDuplicidad;
	
	private Proyecto proyecto;
	
	public void initialize(URL url, ResourceBundle rb, Proyecto proyecto) {
		this.proyecto=proyecto;
		this.labeBugs.setText(proyecto.getResultados().getBugs());
		this.labeBugs.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
		this.labelCodeSmells.setText(proyecto.getResultados().getCodeSmells().getTotal());
		this.labelCodeSmells.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
		this.labelVulnerabilidades.setText(proyecto.getResultados().getVulnerabilidad());
		this.labelVulnerabilidades.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
		this.labelDuplicidad.setText(proyecto.getResultados().getDuplicidad());
		this.labelDuplicidad.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
	}
	// Event Listener on Button.onAction
	@FXML
	public void atrasOnClick(ActionEvent event) {
		CerrarVentanaAnterior(event); 
	}
	@FXML
    void resultadosCodeSmells(ActionEvent event) {
		Stage CodeSmellWindow = new Stage();
		CodeSmellsWindow mp = new CodeSmellsWindow(this.proyecto.getResultados().getCodeSmells()); 
		mp.start(CodeSmellWindow);
    }
}
