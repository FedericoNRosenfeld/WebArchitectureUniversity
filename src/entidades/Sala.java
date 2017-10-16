package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SALA")
public class Sala implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2864708753027261357L;
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private String nombre;
	private String direccion;

	@OneToMany(mappedBy="lugar")
	private List<Actividad> actividades;
	
	public Sala() {
		
	}
	
	public Sala(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.actividades = new ArrayList<Actividad>();
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// actividades
	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void setActividad(Actividad actividad) {
		this.actividades.add(actividad);
	}
	
	public boolean hayLugar(Date fechaI,Date fechaF) {
		for(int i=0; i<  actividades.size(); i++) {
			if (((actividades.get(i).getFechaInicio().compareTo(fechaI)> 0) && (actividades.get(i).getFechaFin().compareTo(fechaF)< 0))  
			 || (actividades.get(i).getFechaFin().compareTo(fechaI)> 0) && (actividades.get(i).getFechaInicio().compareTo(fechaF)< 0))
					return false;
		}
		 return true;
	}
//	 public boolean hayLugar(Actividad act){
//		 // si hay lugar, lo agrega a la lista de tiempos utilizados
//	        if(!actividades.isEmpty()) { /// si la lista de actividades esta con elementos entra , sino queda el true
//	        	for (int i = 0;i< actividades.size();i++){ /// por cada elemento de la lista de actividades
//	                if(actividades.get(i).compararSuperPosicion(act)) { /// busca alguna superposicion de actividades 
//	                	return  false; /// en caso de encontrarla, queda asignado el false
//	                }
//	            }
//	        }
//	        setActividad(act);
//	        return true; // hay lugar para agregar una actividad a esta sala
//	    }
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actividades == null) ? 0 : actividades.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (actividades == null) {
			if (other.actividades != null)
				return false;
		} else if (!actividades.equals(other.actividades))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", Actividades=" + actividades
				+ "]";
	}
	
	
}
