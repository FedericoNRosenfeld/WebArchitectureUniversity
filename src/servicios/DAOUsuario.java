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
	
	public static Usuario crearUsuario(String nombre, String apellido) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		Usuario nu= new Usuario(nombre,apellido);
		em.persist(nu);
		em.getTransaction().commit();
		//DAOCalendario.getInstance().createCalendar("default", newUser);
		return nu;
	}

	public static List<Usuario> getUsuarios() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT u FROM Usuario u"; 
	    Query query = em.createQuery(jpql); 
	    List<Usuario> resultados = query.getResultList(); 
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
	
	public static Usuario getUsuario(int idUsuario) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "Select u From Usuario u where u.id =?1";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUsuario);
		return (Usuario) query.getSingleResult();
	}
	

	public static List<Actividad> getActividadDeUsuarioxFecha(int idUsuario,Date fecha) {
		EntityManager em=EMF.createEntityManager();
		Calendar calendario = Calendar.getInstance(); /// https://stackoverflow.com/questions/2619691/extract-day-from-date
		calendario.setTime(fecha);
		String jpql = "Select a From Actividad a where (a.duenio.id=?1) and ("
			// en base a la fecha de inicio
			+ "(extract(day from a.fechaInicio )= ?2"
			+ " and extract(month from a.fechaInicio) = ?3"
			+ " and extract(year from a.fechaInicio) = ?4) OR "
			// en base a la fecha de fin
			+ "(extract(day from a.fechaFin )= ?2" 
			+ " and extract(month from a.fechaFin) = ?3"
			+ " and extract(year from a.fechaFin) = ?4))"; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, idUsuario);
		query.setParameter(2, calendario.get(Calendar.DAY_OF_MONTH));
		query.setParameter(3, calendario.get(Calendar.MONTH) + 1);
		query.setParameter(4, calendario.get(Calendar.YEAR));
		List<Actividad> resultados = query.getResultList(); 
		return resultados;
	}
	

	public static void getActividadDeUsuarioEntreDias(int idUsuario, Date fecha1, Date fecha2) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT a FROM Actividad a WHERE (a.duenio.id=?1) AND a.fechaInicio >= ?2  AND a.fechaFin <= ?3 "; 
		Query query = em.createQuery(jpql);
		query.setParameter(1, idUsuario);
		query.setParameter(2, fecha1);
		query.setParameter(3, fecha2);
		List<Actividad> resultados = query.getResultList(); 
		for(int i=0; i< resultados.size();i++) { 
			System.out.println( resultados.get(i).toString()); 
		}
		
		
	}

	/// UPDATE AND DELETE 
	public static void updateUsuario(int id,String nombre, String apellido) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();		
		String jpql = "UPDATE Usuario SET nombre=?2, " + "apellido=?3 WHERE User.id = ?1"; 
        Query query = em.createQuery(jpql);
        query.setParameter(1, id);
        query.setParameter(2, nombre);
        query.setParameter(3, apellido);
        query.executeUpdate();
        em.getTransaction().commit();
	}
	
	public static void deleteUsuario(Integer idUsuario) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		em.getTransaction().begin();
		String jpql = "DELETE FROM User u WHERE u.id = ?1"; 
        Query query = em.createQuery(jpql);
        query.setParameter(1, idUsuario);
        query.executeUpdate();
        em.getTransaction().commit();
       
	}

	
}






	