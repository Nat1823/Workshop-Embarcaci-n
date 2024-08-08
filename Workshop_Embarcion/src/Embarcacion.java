import java.time.Year;

public abstract class Embarcacion implements Alquiler {

    //declaro atributos
    protected double preciobasealquiler;
    protected double recargoalquiler;
    protected Year añofabricacion;
    protected double eslora;
    protected Capitan capitan;
    protected Usuario usuario;
    protected boolean disponible;
//hago constructor
    public Embarcacion(double preciobasealquiler, double recargoalquiler, Year añofabricacion, double eslora) {
        this.preciobasealquiler = preciobasealquiler;
        this.recargoalquiler = recargoalquiler;
        this.añofabricacion = añofabricacion;
        this.eslora = eslora;
        this.disponible = true; // Inicialmente disponible
        this.usuario=null;
    }
    //llamo del padre
    @Override
    public double calcularMontoAlquiler() {
        double montoalquiler = preciobasealquiler + recargoalquiler;
        if (añofabricacion.getValue() > 2020) {
            montoalquiler += 20000;
        }
        return montoalquiler;
    }

    @Override
    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public void finalizarAlquiler() {
        disponible = true; // Marcar como disponible al finalizar el alquiler
    }

    @Override
    public String toString() {
        return "Embarcacion{" +
                "capitan=" + capitan +
                ", precio base alquiler=" + preciobasealquiler +
                ", recargo=" + recargoalquiler +
                ", año de fabricacion=" + añofabricacion +
                ", eslora=" + eslora +
                ", disponible=" + (disponible ? "Sí" : "No") +
                '}';
    }

    // Getters y setters
    public Capitan getCapitan() {
        return capitan;
    }

    public void setCapitan(Capitan capitan) {
        this.capitan = capitan;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
