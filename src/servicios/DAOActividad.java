package servicios;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import entidades.Actividad;
import entidades.Calendario;
import entidades.Sala;
import entidades.Usuario;
import servicios.EMF;


public class DAOActividad {
	
	private static DAOActividad daoactividad;

	private DAOActividad(){}

	public static DAOActividad getInstance() {
		if(daoactividad==null)
			daoactividad=new DAOActividad();
		return daoactividad;
	}

	public Actividad crearActividad(String nombre, int usuario ,Date fechaInicio, Date fechafin ,int sala ,int idCalendario) {
		EntityManager em=EMF.createEntityManager();	
		em.getTransaction().begin();
		/// Agregar las consultas necesarias para verificar que no se solapen las cosas
		Usuario u= DAOUsuario.getUsuario(usuario);
		Sala s= DAOSala.getSala(sala);
		Calendario c =DAOCalendario.getCalendario(idCalendario);
		///
		Actividad na = new Actividad(nombre,u,fechaInicio,fechafin,s,c);
		em.persist(na);
		em.getTransaction().commit();
		return na;
	}
		
	public List<Actividad> GetActividades() {
		EntityManager em=EMF.createEntityManager();	

		String jpql = "Select a From Actividad a";
		Query query = em.createQuery(jpql); 
		List<Actividad> resultados = query.getResultList(); 
		return resultados;
	}
	
	public List<Actividad> GetActividadesUsuario(int usuario) {
		EntityManager em=EMF.createEntityManager();	
		String jpql = "SELECT a FROM Actividad a,actividad_usuario au WHERE ((au.actividad_id = a.id)" // 
		+ "AND (au.invitados_idUsuario =1?))"  // sea un invitado
		+ "OR (a.duenio_idUsuario=1?)"; // sea duenio
		Query query = em.createQuery(jpql); 
		query.setParameter(1, usuario);
		List<Actividad> resultados = query.getResultList(); 
		return resultados;
	}

		public List<Actividad> getActividadesSobrepuestas(int usuario, int actividad) {
			EntityManager em=EMF.createEntityManager();	
			// saco lo de las actividades solapadas que estaba en Actividad y lo consulto directamenrte en la bd
			//((act1i.compareTo(act2f) > 0 )||(act1f.compareTo(act2i) < 0))	y lo adapto a la consulta
			String jpql = "SELECT a1 FROM Actividad a1 , Actividad a2 "
					+ "WHERE a.duenio_idUsuario = ?1"
					+ " AND a1.id != ?2" /// Sean distintas actividades
					+ " AND a2.id = ?2" /// la a con a2
					+ " AND (a1.fechaInicio < a2.fechaFin" + " AND a2.fechaInicio < a1.fechaFin"
					+ " OR a1.fechaInicio <= a2.fechaFin" + " AND a2.fechaInicio <= a1.fechaInicio)";
			Query query = em.createQuery(jpql); 
			query.setParameter(1, usuario);
			query.setParameter(2, actividad);
			List<Actividad> resultados = query.getResultList(); 
			return resultados;
			}
		
		
}
