package application;

import java.io.Serializable;
import java.time.LocalDate;

public class Proyecto implements Serializable{
	private String nombre, url;
	private int id, idUser;
	private LocalDate fechaCreacion, fechaUltimoUso;
	private Resultados resultados;
	
	public Proyecto() {
		
	}

	public Proyecto(int id, int idUser,String nombre, String url, LocalDate fechaCreacion, LocalDate fechaUltimoUso, Resultados result) {
		this.id = id;
		this.idUser = idUser;
		this.nombre = nombre;
		this.url = url;
		this.fechaCreacion = fechaCreacion;
		this.fechaUltimoUso = fechaUltimoUso;
		this.resultados = result;
	}

	public Resultados getResultados() {
		return resultados;
	}

	public void setResultados(Resultados resultados) {
		this.resultados = resultados;
	}

	public int getIdUser() {
		return idUser;
	}

	public LocalDate getFechaUltimoUso() {
		return fechaUltimoUso;
	}

	public void setFechaUltimoUso(LocalDate fechaUltimoUso) {
		this.fechaUltimoUso = fechaUltimoUso;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public int getId() {
		return id;
	}
	
	
	
	
}
