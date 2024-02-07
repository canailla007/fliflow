package application;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String nombre, password;
	private boolean esAdmin = false;
	private int id;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nom, String pass, boolean esAdmin) {
		this.id = id;
		this.nombre = nom;
		this.password = pass;
		this.esAdmin = esAdmin;
		
	}
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	
}
