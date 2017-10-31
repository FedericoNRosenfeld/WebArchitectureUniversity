package servicios;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.Actividad;
import entidades.Usuario;
import servicios.EMF;

public class DAOUsuario {
private static DAOUsuario daousuario;
	
	private DAOUsuario(){}

	public static DAOUsuario getInstance() {
		if(daousuario==null)
			daousuario=new DAOUsuario();
		return daousuario;
	}
	
	public  Usuario crearUsuario(String nombre, String apellido,String userName, String password) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		Usuario nu= new Usuario(nombre,apellido,userName,password);
		em.persist(nu);
		em.getTransaction().commit();
		em.close();
		return nu;
	}

	public  List<Usuario> getUsuarios() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT u FROM Usuario u"; 
	    Query query = em.createQuery(jpql); 
	    List<Usuario> resultados = query.getResultList();
	    em.close();
	    return resultados;
	    
	}
	
//   Version con el EntityManager desde fuera 	
	
//	public static Usuario getUsuario(int idUsuario,EntityManager em) {
//		String jpql = "Select u From Usuario u where u.id =?1";
//		Query query = em.createQuery(jpql); 
//		query.setParameter(1, idUsuario);
//		return (Usuario) query.getSingleResult();
//	}
//	
	
	public  Usuario getUsuario(int idUsuario) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "Select u From Usuario u where u.idUsuario =?1";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUsuario);
		em.close();
		return (Usuario) query.getSingleResult();
	}
	

	/// UPDATE AND DELETE 
	public  Usuario updateUsuario(int id,String nombre, String apellido) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();		
		String jpql = "UPDATE Usuario u SET u.nombre=?2, " + "u.apellido=?3 WHERE u.idUsuario = ?1"; 
        Query query = em.createQuery(jpql);
        query.setParameter(1, id);
        query.setParameter(2, nombre);
        query.setParameter(3, apellido);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        Usuario usuario = getUsuario(id);
        return usuario;

	}
	
	public  boolean deleteUsuario(Integer idUsuario) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		String jpql = "DELETE FROM Usuario u WHERE u.idUsuario = ?1"; 
        Query query = em.createQuery(jpql);
        query.setParameter(1, idUsuario);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        Usuario usuario = getUsuario(idUsuario);
		if (usuario == null) {
			return  true;
		}	
		return false;
       
	}
	
/// TIEMPO LIBRE PARA AGREGAR UNA NUEVA ACTIVIDAD
		public  boolean tiempoLibreUsuario(int usuario, Date fechaInicio, Date fechafin) {
			// Optimizar para los usuarios invitados
			EntityManager em=EMF.createEntityManager();	
			String jpql = "SELECT a1 FROM Actividad a1"
					+ "WHERE a.duenio_idUsuario = ?1"
					+ " AND (a1.fechaInicio < ?2" + " AND ?2 < a1.fechaFin"
					+ " OR a1.fechaInicio <= ?3" + " AND ?3 <= a1.fechaInicio)";
			Query query = em.createQuery(jpql); 
			query.setParameter(1, usuario);
			query.setParameter(2, fechaInicio);
			query.setParameter(3, fechafin);
			List<Actividad> resultados = query.getResultList();
			em.close();
			return (resultados == null);
		}

	
	//// LOGIN
	
	public Usuario login(Usuario usuario) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "Select u From Usuario u where u.userName =?1 && u.password =?2 ";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, usuario.getUserName());
		query.setParameter(2, usuario.getPassword());
		em.close();
		return (Usuario) query.getSingleResult();
	}


}






	