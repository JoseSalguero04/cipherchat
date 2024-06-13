package pp;

public class Persona {
    private static int nextCodigo = 202300000;
    private int codigo;
    private String nombre;
    private String apellido;
    private String password;
    private char genero;
    private int edad;
    private String telefono;

    public Persona(String nombre, String apellido, String password, char genero, int edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.genero = genero;
        this.edad = edad;
        this.telefono = telefono;
        codigo = nextCodigo++;
    }

    public int getCodigo() {
        return codigo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" +
                " nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", password='" + password + '\'' +
                ", genero=" + genero +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}