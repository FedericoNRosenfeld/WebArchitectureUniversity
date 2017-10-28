package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario /*implements Serializable*/ {
	/**
	 * 
	 */
	//private static final long serialVersionUID = -4686073705675341538L;
	@Id 
	@GeneratedValue
	private int idUsuario;
	@Column(nullable=false)
	private String nombre;
	private String apellido;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Calendario> calendarios;
	@ManyToMany(mappedBy ="pendientes", cascade=CascadeType.PERSIST)
	//(cascade=CascadeType.PERSIST)
	private List<Actividad> actividadesporaceptar;
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", calendarios="
				+ calendarios + ", actividadesporaceptar=" + actividadesporaceptar + "]";
	}

	public Usuario() {
	}
	
	//usuario sin calendarios
	public Usuario(String nombre, String apellido) {
//		this.idUsuario = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calendarios =  new ArrayList<Calendario>();
		this.actividadesporaceptar =  new ArrayList<Actividad>();

	}

	//usuario con calendarios

	public Usuario(String nombre, String apellido, List<Calendario> calendarios) {
		super();
//		this.idUsuario = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calendarios = calendarios;
		this.actividadesporaceptar =  new ArrayList<Actividad>();

	}

	public int getId() {
		return idUsuario;
	}

	public void setId(int id) {
		this.idUsuario = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	
	////////////////////////////////// Calendarios
	public List<Calendario> getCalendarios() {
		return calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}
	
	public void setCalendario(Calendario calendario) {
		this.calendarios.add(calendario);
	}

//	 public void setActividadCalendario(Actividad actividad, Calendario calendario) {
//	        if (!horarioUsado(actividad)) {
//	            if (calendario.getDuenio().equals(this)) {
//	            	calendario.setActividad(actividad);
//	            }
//	        }
//	    }
//	
//	  public boolean horarioUsado(Actividad actividad) { 
//			for (int i = 0;i< calendarios.size();i++){
//				List<Actividad> acti = calendarios.get(i).getActividades();
//				for (int j = 0;j< acti.size();j++){
//	                if (acti.get(j).compararSuperPosicion(actividad))
//	                	return true;
//	            }
//	        }
//	        return false;
//	    }

	 /// compartir calendarios y aceptar calendarios de otros
	  
	  public void agregarCalendario(Calendario calendario) {
	        this.calendarios.add(calendario);
	    }
	  
	  public void compartirCalendario(Calendario calendario, Usuario usuario) {
		  usuario.agregarCalendario(calendario);
	    }

	  
	  
	  
	  
	/*
	//////////////////////////// Todo lo relacionado con notificaciones de eventos pendientes
	*/
	public void addNotificacion(Actividad act){
		actividadesporaceptar.add(act);
	}
	
	public List<Actividad> getActividadesPendientes(){
		return actividadesporaceptar;
			
	}
	
	// contador con la cantidad de actividades pendientes, para luego cada vez que loguea vea el contador
	public int getCantAP(){
		return actividadesporaceptar.size();
			
	}
	
	
	/*
	// Para aceptar una actividad en pendientes, esta debe tener disponibilidad 
	// horaria entre los calendarios del usuario
	*/
	
//	public boolean aceptarActividadPendiente(Actividad act) {
//		System.out.println(this.calendarios.size());
//		for (int i = 0;i< this.calendarios.size();i++){
//			if (!this.calendarios.get(i).disponibilidad(act)) {
//				return false;
//			}
//		}
//		act.setInvitado(this);
//		return actividadesporaceptar.remove(act); // tira true si lo remueve
//	}
	
	
	/*
	// Para remover una actividad de su lista de pendientes  
	*/
	public void removerActPendiente(Actividad actividad) {
		actividadesporaceptar.remove(actividad);	
	}
//	
//	public void acptarActividad(Actividad a) {
//		a.setInvitado(this);
//		
//	}
	
	////////// equals

	public boolean equals(Object obj) {
        Usuario u = (Usuario) obj;
        return u.idUsuario == idUsuario && u.nombre.equals(nombre)&& u.apellido.equals(apellido);
    }

	public void enviarInvitacion(Invitacion invitacion) {
		// TODO Auto-generated method stub
		
	}

	public void recordarInvitacion(Actividad actividad) {
		// TODO Auto-generated method stub
		
	}
	
	}