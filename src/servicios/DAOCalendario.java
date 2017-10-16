package servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Calendario;
import entidades.Usuario;
import servicios.EMF;

public class DAOCalendario {

	private static DAOCalendario daocalendario;
	
	private DAOCalendario(){}

	public static DAOCalendario getInstance() {
		if(daocalendario==null)
			daocalendario=new DAOCalendario();
		return daocalendario;
	}

	
	public static Calendario crearCalendario(String nombre, Usuario usuario) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		Calendario nc = new Calendario(nombre, usuario);
		em.persist(nc);
		em.getTransaction().commit();
		return nc;
	}
	
	public static Calendario getCalendario(int idCalendario) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "Select c From Calendario c where c.id =?1";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idCalendario);
		Calendario calen = (Calendario) query.getSingleResult();
		//System.out.println(calen.toString());
		return calen;
		

}

	public static List<Calendario> getCalendariosXusuario(int idUsuario) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "Select c From Calendario c where (c.duenio_idUsuario = ?1) ";
		Query query = em.createQuery(jpql);
		query.setParameter(1, idUsuario);
		List<Calendario> resultados = query.getResultList(); 
	    return resultados;
	}
	
	
	///UPDATE ADN DELETE

		public static void updateCalendario(int idCalendario,String nombre, int duenio) {
			EntityManager em=EMF.createEntityManager();
			em.getTransaction().begin();		
			String jpql = "UPDATE Calendario SET nombre=?2, duenio=?3 WHERE User.id = ?1"; 
	        Query query = em.createQuery(jpql);
	        query.setParameter(1, idCalendario);
	        query.setParameter(2, nombre);
	        query.setParameter(3, duenio);
	        query.executeUpdate();
	        em.getTransaction().commit();
		}
		
		public static void deleteCalendario(Integer idCalendario) {
			EntityManager em=EMF.createEntityManager();
			em.getTransaction().begin();
			em.getTransaction().begin();
			String jpql = "DELETE FROM Calendario c WHERE c.id = ?1"; 
	        Query query = em.createQuery(jpql);
	        query.setParameter(1, idCalendario);
	        query.executeUpdate();
	        em.getTransaction().commit();
	       
		}
	
	
	
	
	
	
}