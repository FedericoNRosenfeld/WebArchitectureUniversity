package serviciosRest;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import servicios.EMF;

/**
 * Application Lifecycle Listener implementation class DBConf
 *
 */
@WebListener
public class DBConf implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBConf() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(sce.getServletContext().getContextPath());
		System.out.println(sce.getServletContext().getServletContextName());
		System.out.println(sce.getServletContext().toString());
		EMF.initFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		EMF.closeFactory();
	}
	
	 //// borrado de la BD
	public static void restoreDB() {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		em.createQuery("DELETE FROM ACTIVIDAD").executeUpdate();
		em.createQuery("DELETE FROM CALENDARIO").executeUpdate();
		em.createQuery("DELETE FROM SALA").executeUpdate();
		em.createQuery("DELETE FROM USUARIO").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
}
