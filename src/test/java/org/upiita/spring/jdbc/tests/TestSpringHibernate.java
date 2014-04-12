package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");// Levanta un contexto de spring, configurado con contexto.xml
		UsuarioDAO usuarioDAO = (UsuarioDAO) contexto.getBean("UsuarioDAO");
		
		Usuario usuarioNueavo = new Usuario();
		
		usuarioNueavo.setUsuarioId(3);
		usuarioNueavo.setNombre("paco");
		usuarioNueavo.setPassword("123");
		usuarioNueavo.setEmail("paco@email.com");
		
		usuarioDAO.creaUsuario(usuarioNueavo);
		
		Usuario usuario = usuarioDAO.buscaUsuarioPorId(3);
		System.out.println("USUARIO" + usuario);
		
		Usuario usuarioPorEmail = usuarioDAO.buscaPorEmail("juan@email.com");
		
		System.out.println("USUARIO POR EMAIL: " + usuarioPorEmail);
		
		List<Usuario> usuarios = usuarioDAO.obtenPorNombre("P");
		System.out.println("Usuarios:" + usuarios);
		
		
	}

}
