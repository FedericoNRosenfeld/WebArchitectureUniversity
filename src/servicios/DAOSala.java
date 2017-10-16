package servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Sala;
//import entidades.Usuario;
//import entidades.Actividad;
import servicios.EMF;

public class DAOSala {
	
	private static DAOSala daoSala;
	
	private DAOSala(){
	}
	
	public static DAOSala getInstance() {
		if(daoSala == null)
			daoSala = new DAOSala();
		return daoSala;
	}
	
	public Sala crearSala(String nombre, String direccion) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		Sala salaA = new Sala(nombre,direccion);
		em.persist(salaA);
		em.getTransaction().commit();
		return salaA;
	}
	
	public static Sala getSala(int id) {
		// regresa una sala en base a su ID
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT s FROM Site s WHERE s.id = ?"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, id);
		return (Sala) query.getSingleResult();
		 
	}
	
	public List<Sala> getSalas(){
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT s FROM Sala s"; 
	    Query query = em.createQuery(jpql); 
	    List<Sala> resultados = query.getResultList(); 
	    return resultados;
	    
	}
	
	/// UPDATE AND DELETE
	
	public static void updateSala(int id,String nombre, String direccion) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();		
		String jpql = "UPDATE Sala s SET nombre=?2, "
				+ "direccion=?3 WHERE s.id = ?1"; 
        Query query = em.createQuery(jpql);
        query.setParameter(1, id);
        query.setParameter(2, nombre);
        query.setParameter(3, direccion);
        query.executeUpdate();
        em.getTransaction().commit();
	}
	
	public static void deleteSala(Integer id) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		String jpql = "DELETE FROM Sala s WHERE s.id = ?1"; 
        Query query = em.createQuery(jpql);
        query.setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
 
	}
	
	
}
