package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

@Component("UsuarioDAO")   //Registra cualquier clase en el contexto de spring  
public class HibernateUsuarioDAO implements UsuarioDAO {
	//Clase de utilería hibernate
	@Autowired   //Busca la clase que implemte SessionFactory e inyecta la instancia
	private SessionFactory sessionFactory;
	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		
		Session session = sessionFactory.openSession();  //Creamos una sesión de hibernate
		session.beginTransaction();   // Una vez que se tiene la sesión , debemos abrir una transacción
		Usuario usuario = (Usuario) session.get(Usuario.class, usuarioId);   //Busca por id la tabla mapeada por la clase Usuario. Si no encuentra nada regresa NULL
		//Control + 1 para mostrar sugerencias
		
		//sessionFactory.getCurrentSession().getTransaction().commit(); es equivalente a la línea de abajo
		//termina la tranasacción
		session.getTransaction().commit();
		
		session.close();
		
		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		
			Session session = sessionFactory.openSession();  //Creamos una sesión de hibernate
			session.beginTransaction();   // Una vez que se tiene la sesión , debemos abrir una transacción
			
			//Crea un nuevo renglón en la tabla
			//cuyas columnas se llenan con las propiedades de
			//la instancia usuario
			session.save(usuario);   //Es equivalente a in insert sql
			
			session.getTransaction().commit();
			
			session.close();
			
		
	}
	
	public Usuario buscaPorEmail(String email){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criterio = session.createCriteria(Usuario.class);
		criterio.add(Restrictions.eq("email", email));
		
		//Si saben que regresará una sola entidad
		Usuario usuario = (Usuario) criterio.uniqueResult();
		
		session.getTransaction().commit();
		
		session.close();
		return usuario;
	}

	public List<Usuario> obtenPorNombre(String nombre){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criterio = session.createCriteria(Usuario.class);
		//criterio.add(Restrictions.like("nombre", "%" + nombre + "%"));
		criterio.add(Restrictions.not(Restrictions.like("nombre", "%" + nombre + "%")));
		
		List<Usuario> usuarios = criterio.list();
		
		session.getTransaction().commit();
		
		session.close();
		return usuarios;
	}
	
}//Transacciones de manera progrmática. Es manual  // Existen las transacciones declarativas. Spring se encarga de su manejo
