package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="ACTIVIDAD")

public class Actividad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1269548906097037547L;
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private String nombre;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Usuario duenio;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private Sala lugar;
	@ManyToMany(cascade=CascadeType.PERSIST)
	private List<Usuario> invitados;
	// invitados pendientes
	@ManyToMany(cascade=CascadeType.PERSIST)
	private List<Usuario> pendientes;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Calendario calendario;
	
	
	public Actividad(String nombre, Usuario duenio, Date fechaInicio, Date fechaFin, Sala lugar, Calendario c) {
		this.nombre = nombre;
		this.duenio = duenio;
		this.calendario = c;
		this.fechaInicio =  fechaInicio;
		this.fechaFin =  fechaFin;
//		this.setLugar(lugar);
		this.setLugar(lugar,fechaInicio,fechaFin);
		this.invitados = new ArrayList<Usuario>();
		this.pendientes = new ArrayList<Usuario>();
	}
	
//	public Actividad(String nombre, Usuario duenio, Date fechaInicio, Date fechaFin, Sala lugar) {
//		this.nombre = nombre;
//		this.duenio = duenio;
//		this.calendario = null;
//		this.fechaInicio = fechaInicio;
//		this.fechaFin = fechaFin;
//		this.setLugar(lugar);
//		this.invitados = new ArrayList<Usuario>();
//		this.pendientes = new ArrayList<Usuario>();
//	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Usuario getDuenio() {
		return duenio;
	}


	public void setDuenio(Usuario duenio) {
		this.duenio = duenio;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Sala getLugar() {
		return lugar;
	}

//	public void setLugar(Sala lugar) {
//		if (lugar.hayLugar(this)) {
//			this.lugar = lugar;
//		}
//		// habria que ver como indicar en caso de no poder ponerlo en esa sala 
//	}
	
	public void setLugar(Sala lugar,Date fechaInicio, Date fechaFin) {
		if (lugar.hayLugar(fechaInicio,fechaFin)) {
			this.lugar = lugar;
			this.lugar.setActividad(this);
		}
		//mmdy: habria que ver como indicar en caso de no poder ponerlo en esa sala 
	}


//     //////Comprueba si dos reuniones se superponen en tiempo y lugar
//	public  boolean compararSuperPosicion(Actividad act2){
//		return verificarSiSuperponen(this,act2); // esto esta asi porque eclipse queria ponerme estaticas las variables( verificar luego )
//	}
//	
//    public static boolean verificarSiSuperponen(Actividad act1, Actividad act2) {
//	    // compara las fechas de inicio y fin de las actividades. 
//    	Date act1i = act1.getFechaInicio();
//    	Date act1f = act1.getFechaFin();
//    	Date act2i = act2.getFechaInicio();
//    	Date act2f = act2.getFechaFin();
//        if ((act1i.compareTo(act2f) > 0 )||(act1f.compareTo(act2i) < 0)) {
//        	return false;
//        }
//        return true;
//    }
	///////////// listado de pendientes hasta que estos acepten
	
	public void setPendientes(List<Usuario> pendientes) {
		for (int i = 0;i< pendientes.size();i++){
			this.setPendiente(pendientes.get(i));
		}
	}
	
	
	/*
	// Para cuando un usuario es  invitado pero aun no aceptado
	*/	
	public void setPendiente(Usuario usuario) {
        if (!usuario.equals(duenio)) {
            pendientes.add(usuario);
            avisarEvento();
        }
    }

	/*
	// Para cuando un usuario rechaze la invitacion
	*/
	public void deletePendiente(Usuario usuario) {
        pendientes.remove(usuario);
        usuario.removerActPendiente(this);
	}
	
	/*
	// Para cuando un usuario acepte la invitacion a la actividad
	*/
	public void setInvitado(Usuario usuario) {
		
		if ( pendientes.remove(usuario)) {
			 invitados.add(usuario);
			 usuario.removerActPendiente(this);
		}

    }
	
	
	////////////////////////////////////////////////
	
	public List<Usuario> getInvitados() {
		return invitados;
	}
	public List<Usuario> getPendientes() {
		return pendientes;
	}
	
	/////////////////////////////// generar aviso
	
	 public void avisarEvento() {
		 for (int i = 0;i< pendientes.size();i++){
			     pendientes.get(i).addNotificacion(this);
	         }
	  }
	 
	/////////// equals
	 public boolean equals(Object obj) {
	        Actividad act = (Actividad) obj;
	        return act.id == id && act.fechaInicio.equals(fechaInicio) && act.fechaFin.equals(fechaFin);
	    }
	 
	 @Override
		public String toString() {
			return "Actividad [id=" + id + ", nombre=" + nombre + ", duenio=" + duenio + ", fechaInicio=" + fechaInicio
					+ ", fechaFin=" + fechaFin + ", lugar=" + lugar + ", invitados=" + invitados + ", pendientes="
					+ pendientes + ", calendario=" + calendario + "]";
		}

	public boolean compararSuperPosicion(Actividad act) {
		// TODO Auto-generated method stub hola hola hola
		return false;
	}
	
}