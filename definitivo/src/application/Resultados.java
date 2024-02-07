package application;

import java.io.Serializable;
import java.time.LocalDate;

public class Resultados implements Serializable{
	private String bugs, duplicidad, vulnerabilidad;
	private CodeSmells codeSmells;
	
	public Resultados() {
		
	}

	public Resultados(String bugs, String duplicidad, String vulnerabilidad, CodeSmells cs) {
		this.bugs = bugs;
		this.duplicidad = duplicidad;
		this.vulnerabilidad = vulnerabilidad;
		this.codeSmells = cs;
	}

	public String getBugs() {
		return bugs;
	}

	public void setBugs(String bugs) {
		this.bugs = bugs;
	}

	public String getDuplicidad() {
		return duplicidad;
	}

	public void setDuplicidad(String duplicidad) {
		this.duplicidad = duplicidad;
	}

	public String getVulnerabilidad() {
		return vulnerabilidad;
	}

	public void setVulnerabilidad(String vulnerabilidad) {
		this.vulnerabilidad = vulnerabilidad;
	}

	public CodeSmells getCodeSmells() {
		return codeSmells;
	}

	public void setCodeSmells(CodeSmells codeSmells) {
		this.codeSmells = codeSmells;
	}

	
	
	
}
