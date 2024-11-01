package clientes;

public class Usuario {
    // Atributos
	
    private String nombre;
    private String apellido;
    private String identificacion;
    private int Id;


	public int getId() {
		return Id;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}



	public void setId(int id) {
		Id = id;
	}



	// Constructor
    public Usuario(int id,String nombre, String apellido, String identificacion) {
    	this.Id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
    }

    // Métodos getters para obtener los valores
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    // Método para mostrar la información del usuario
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + ", Apellido: " + apellido + ", Identificación: " + identificacion);
    }
}

