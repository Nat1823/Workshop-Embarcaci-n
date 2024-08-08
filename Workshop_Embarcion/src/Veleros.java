import java.time.Year;

public class Veleros extends Embarcacion {

    //declaro atributos
    private int cantidadMastiles;

    //Hago constructor
    public Veleros(double precioBaseAlquiler, double recargoAlquiler, Year añoFabricacion, double eslora, int cantidadMastiles) {
        super(precioBaseAlquiler, recargoAlquiler, añoFabricacion, eslora);
        this.cantidadMastiles = cantidadMastiles;
    }

    @Override
    public double calcularAlquiler() {
        double montoAlquiler = calcularMontoAlquiler();
        if (esGrande()) {
            montoAlquiler *= 1.10;
        }
        return montoAlquiler;
    }
    //Es importante poder evaluar en un velero si es grande, los velos grandes son aquellos que
    //superan los 4 mastiles. Los veleros grandes tienen un costo adicional del 10% sobre el
    //precio base.
    public boolean esGrande() {
        return cantidadMastiles > 4;
    }

    public int getCantidadMastiles() {
        return cantidadMastiles;
    }

    public void setCantidadMastiles(int cantidadMastiles) {
        this.cantidadMastiles = cantidadMastiles;
    }

    @Override//Representación en cadena del velero, incluyendo si es grande o no.
    public String toString() {
        return super.toString() +
                "\nTipo: Velero" +
                "\nCantidad de mástiles: " + cantidadMastiles +
                "\nEs grande: " + (esGrande() ? "Sí" : "No");
    }
}
