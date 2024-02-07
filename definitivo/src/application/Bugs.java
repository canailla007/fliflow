package application;



import java.io.Serializable;
import java.util.ArrayList;

public class Bugs implements Serializable{
	private String total;
	private ArrayList<String> listaDatos;
	
	public Bugs(ArrayList<String> datos) {
		this.total = String.valueOf(datos.size());
		this.listaDatos = datos;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<String> getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(ArrayList<String> listaDatos) {
		this.listaDatos = listaDatos;
	}
	
}
