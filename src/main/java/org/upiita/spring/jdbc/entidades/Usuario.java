package org.upiita.spring.jdbc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//MAPEA la clase Usuario con la tabla usuario 
@Entity(name="usuario")
//@Table
public class Usuario {
	
	@Id
	@Column(name="usuario_id")
	private Integer usuarioId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="nombre")
	private String nombre;
	
	public String toString(){
		
		String representacion = "nombre: " + nombre + ","
								+ "usuarioId: " + usuarioId  + ","
								+ "email: " + email  + ","
								+ "password: " + password;
		return representacion;
		
	}
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	//@Column(name="nombre")  no habr�a diferncia entre poner la anotaci�n aqu�  � en la propiedad
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
//source ----> Generate getters and setters
//classpath:   ---->es la ubicaci�n de las librer�as ----> .jar -----> src/main/resources est� en le classpath, tambi�n las dependencias. El editor hace el linkeo