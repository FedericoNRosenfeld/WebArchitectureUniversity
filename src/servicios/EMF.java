package servicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EMF {
	private static EntityManagerFactory emf;


	public static EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
}