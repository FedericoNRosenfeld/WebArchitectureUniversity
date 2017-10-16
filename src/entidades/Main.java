package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import servicios.DAOCalendario;
import servicios.DAOUsuario;

public class Main {

public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
	EntityManager manager = emf.createEntityManager();

    manager.getTransaction( ).begin( );
    
	Usuario u1 = new Usuario("Nombre1","Apellido1");
	Usuario u2 = new Usuario("Nombre2","Apellido2");
	Usuario u3 = new Usuario("Nombre3","Apellido3");
	Usuario u4 = new Usuario("Nombre4","Apellido4");
	Usuario u5 = new Usuario("Nombre5","Apellido5");
	Usuario u6 = new Usuario("Nombre6","Apellido6");
	Usuario u7 = new Usuario("Nombre7","Apellido7");
	Usuario u8 = new Usuario("Nombre8","Apellido8");
	Usuario u9 = new Usuario("Nombre9","Apellido9");
	Usuario u10 = new Usuario("Nombre10","Apellido10");
	

	Sala sala1 = new Sala("Sala 1", "Direccion 1");
	Sala sala2 = new Sala("Sala 2", "Direccion 2");

	Date fechaA1 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 25, 9, 22).getTime();
    Date fechaA2 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 25, 12, 25).getTime();

	Date fechaB1 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 25, 9, 22).getTime();
    Date fechaB2 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 25, 12, 25).getTime();
    
    List<Usuario> pendientes= new ArrayList<Usuario>();

    pendientes.add(u2);
    pendientes.add(u3);
    pendientes.add(u4);
    pendientes.add(u5);
    
    Calendario c= new Calendario("calendario1",u1);
    Calendario c1= new Calendario("calendario2",u2);
    Calendario c2= new Calendario("calendario3",u3);
    Calendario c3= new Calendario("calendario4",u4);
    Calendario c4= new Calendario("calendario5",u5);
  
    Actividad a1 = new Actividad ("Caminar",u1,fechaA1,fechaA2, sala1,c);
    Actividad a2 = new Actividad ("Correr",u2,fechaB1,fechaB2, sala1,c1);
    a2.setPendiente(u1);
    a1.setPendientes(pendientes);
   
    manager.persist(a1);
    manager.persist(c);
	manager.persist(u1);

	manager.persist(u2);
	manager.persist(u3);
	manager.persist(u4);
	manager.persist(u5);
	manager.persist(u6);
	manager.persist(u7);
	manager.persist(u8);
	manager.persist(u9);
	manager.persist(u10);
	manager.getTransaction().commit( );
	
	
	System.out.println("Ejercicio c.i:");
	
	DAOUsuario.getInfoUsuario(1, manager);
	
	System.out.println("Ejercicio c.ii:");
	
	DAOUsuario.getActividadDeUsuario(1, fechaA1, manager);

//	String jpql = "SELECT u FROM Usuario u"; 
//    Query query = manager.createQuery(jpql); 
//    List<Usuario> resultados = query.getResultList(); 
//    for(Usuario  u : resultados) { 
//        System.out.println(u.getNombre()); 
//    }

	
	manager.close();
	emf.close();

}

}
