public class Capitan {

    //Declaro los atributos
    private String nombre;
    private String apellido;
    private String matricula;

    //Hago el constructor
    public Capitan(String nombre, String apellido,String matricula){
        this.nombre=nombre;
        this.apellido=apellido;
        this.matricula=matricula;
    }
//getter y setters
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    //Devuelve una representación en forma de cadena del objeto Capitan
    @Override
    public String toString(){
        return nombre+" "+apellido+" "+"(Matricula: "+matricula+")";
    }
}
