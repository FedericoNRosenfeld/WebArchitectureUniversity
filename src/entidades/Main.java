package entidades;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import servicios.DAOActividad;
import servicios.DAOCalendario;
import servicios.DAOSala;
import servicios.DAOUsuario;

public class Main {

public static void main(String[] args) {
	
	///////////////////////////////////Borra BD///////////////////////////////////////////////
//	manager.getTransaction().begin();
//	manager.createQuery("DELETE FROM Usuario").executeUpdate();
//	manager.createQuery("DELETE FROM Calendario").executeUpdate();
//	manager.createQuery("DELETE FROM Actividad").executeUpdate();
//	manager.createQuery("DELETE FROM Sala").executeUpdate();
//	manager.getTransaction().commit();

	//--
	Date fechaA1 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 25, 9, 22).getTime();
    Date fechaA2 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 25, 12, 25).getTime();
	//--
    Date fechaB1 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 25, 9, 22).getTime();
    Date fechaB2 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 25, 12, 25).getTime();
	//--
	Date fechaC1 = new GregorianCalendar(2017, Calendar.MARCH, 25, 9, 22).getTime();
    Date fechaC2 = new GregorianCalendar(2017, Calendar.MARCH, 25, 12, 25).getTime();
	//--
    Date fechaD1 = new GregorianCalendar(2018, Calendar.MARCH, 24, 9, 22).getTime();
    Date fechaD2 = new GregorianCalendar(2018, Calendar.MARCH, 25, 9, 23).getTime();
    
    DAOUsuario daou = DAOUsuario.getInstance();
    // Creacion de 10 usuarios
    Usuario u1 = daou.crearUsuario("Dardo","Marolio","DardoM","1234");
    Usuario u2 = daou.crearUsuario("Uriel","Rosenfeld","UrielR","1234");
    Usuario u3 = daou.crearUsuario("Daniela","Gonzalez","DanielaG","1234");
    Usuario u4 = daou.crearUsuario("Fran","Marconi","FranM","1234");
    Usuario u5 = daou.crearUsuario("Laura","Perez","LauraP","1234");
    Usuario u6 = daou.crearUsuario("Sofia","Perez","SofiaP","1234");
    Usuario u7 = daou.crearUsuario("Dana","Diaz","DanaD","1234");
    Usuario u8 = daou.crearUsuario("Miguel","Peralta","MiguelP","1234");
	Usuario u9 = daou.crearUsuario("Belen","Babbicola","BelenB","1234");
    Usuario u10 = daou.crearUsuario("Raul","Alonso","RaulA","1234");

    System.out.println(u1);
    
	// Creacion de 5 Salas
	Sala sala1 = DAOSala.crearSala("Sala 1", "Direccion 1,A");
	Sala sala2 = DAOSala.crearSala("Sala 2", "Direccion 1,B");
	Sala sala3 = DAOSala.crearSala("Sala 3", "Direccion 2,A");
	Sala sala4 = DAOSala.crearSala("Sala 4", "Direccion 2,B");
	Sala sala5 = DAOSala.crearSala("Sala 5", "Direccion 3");

    // Creacion de 5 Calendarios

    Calendario calendario1 = DAOCalendario.crearCalendario("calendario 1 del Usuario 1",u1);
    Calendario calendario2 = DAOCalendario.crearCalendario("calendario 2 del Usuario 2",u2);
    Calendario calendario3 = DAOCalendario.crearCalendario("calendario 3 del Usuario 2",u2);
    Calendario calendario4 = DAOCalendario.crearCalendario("calendario 4 del Usuario 3",u3);
    Calendario calendario5 = DAOCalendario.crearCalendario("calendario 5 del Usuario 4",u4);

	// Cracion de 10 Actividades
	
    Actividad actividad1 = DAOActividad.crearActividad("Actividad 1 -c1 -u1",calendario1.getId(),u1.getId(),fechaA1,fechaA2, sala1);
    Actividad actividad2 = DAOActividad.crearActividad("Actividad 2 -c1 -u1",calendario1.getId(),u1.getId(),fechaB1,fechaB2, sala2);
    Actividad actividad3 = DAOActividad.crearActividad("Actividad 3 -c2 -u2",calendario2.getId(),u2.getId(),fechaC1,fechaC2, sala1);
    Actividad actividad4 = DAOActividad.crearActividad("Actividad 4 -c2 -u2",calendario2.getId(),u2.getId(),fechaD1,fechaD2, sala3);
    Actividad actividad5 = DAOActividad.crearActividad("Actividad 5 -c3 -u3",calendario3.getId(),u3.getId(),fechaB1,fechaB2, sala3);
    Actividad actividad6 = DAOActividad.crearActividad("Actividad 6 -c4 -u4",calendario4.getId(),u4.getId(),fechaA1,fechaA2, sala2);
    Actividad actividad7 = DAOActividad.crearActividad("Actividad 7 -c5 -u5",calendario5.getId(),u5.getId(),fechaC1,fechaC2, sala4);
    Actividad actividad8 = DAOActividad.crearActividad("Actividad 8 -c5 -u5",calendario5.getId(),u5.getId(),fechaB1,fechaB2, sala5);
    Actividad actividad9 = DAOActividad.crearActividad("Actividad 9 -c1 -u1",calendario1.getId(),u1.getId(),fechaC1,fechaC2, sala5);
    Actividad actividad10 = DAOActividad.crearActividad("Actividad 10 -c2 -u2",calendario2.getId(),u2.getId(),fechaD1,fechaD2, sala1);
	
	
	/*
	System.out.println("Ejercicio c.i:");
	
	Usuario uInfo= DAOUsuario.getUsuario(u1.getId());
	System.out.println(uInfo.toString());
	
	System.out.println("Ejercicio c.ii:");
	
	List<Actividad> resultados = DAOUsuario.getActividadDeUsuarioxFecha(u1.getId(), fechaA1);
	 System.out.println(resultados);
	 for (Actividad a :resultados) {
		 System.out.println(a.toString());
	 }

	*/
	
}

}
