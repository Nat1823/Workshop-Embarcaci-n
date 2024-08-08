import java.time.Year;

public class Yates extends Embarcacion {

    private int cantidadCamarotes;
    private int cantidadAlquileres;
    private double precioCompra;

    public Yates(double precioBaseAlquiler, double recargoAlquiler, Year añoFabricacion, double eslora, int cantidadCamarotes, double precioCompra) {
        super(precioBaseAlquiler, recargoAlquiler, añoFabricacion, eslora);
        this.cantidadCamarotes = cantidadCamarotes;
        this.precioCompra = precioCompra;
        this.cantidadAlquileres = 0;
    }

    @Override
    public double calcularAlquiler() {
        return calcularMontoAlquiler() + (cantidadCamarotes * 50);
    }

    @Override
    public double calcularMontoAlquiler() {
        double montoalquiler = super.calcularMontoAlquiler();
        if (añofabricacion.getValue() > 2020) {
            montoalquiler += 20000;
        }
        return montoalquiler;
    }
    //En el caso de los yates debe ser posible comprarlos. El precio de compra tiene también
    //un valor inicial, pero depende de varios factores:
    //o Uso:
    //§ Si el yate ha sido alquilado menos de 20 veces, el precio no se ve afectado.
    //§ Si el yate ha sido alquilado más de 20 veces pero menos de 50, su precio
    //se reduce 10%
    //§ Si el ya te ha sido alquilado más de 50, su precio se reduce 20%.
    //o Lujo:
    //§ Un yate es lujoso si tiene más de 5 camarotes. Para los yates de lujo la
    //reducción del precio de compra por uso solo es del 10%, a partir de los 50
    //alquileres.
    //§ Un yate es muy lujoso si tiene más de 8 camarotes. Para estos la reducción
    //del precio de compra por uso solo es de un 5%, a partir de 80 alquileres.

    // métodos
    public double calcularPrecioCompra() {
        double precioFinal = precioCompra;
        if (cantidadAlquileres > 50) {
            if (esMuyLujoso()) {
                precioFinal *= 0.95;
            } else if (esLujoso()) {
                precioFinal *= 0.90;
            } else {
                precioFinal *= 0.80;
            }
        } else if (cantidadAlquileres > 20) {
            precioFinal *= 0.90;
        }
        return precioFinal;
    }

    public int getCantidadCamarotes() {
        return cantidadCamarotes;
    }

    public void setCantidadCamarotes(int cantidadCamarotes) {
        this.cantidadCamarotes = cantidadCamarotes;
    }

    public void incrementarAlquileres() {
        cantidadAlquileres++;
        disponible = false;
    }

    public int getCantidadAlquileres() {
        return cantidadAlquileres;
    }

    public boolean esLujoso() {
        return cantidadCamarotes > 5;
    }

    public boolean esMuyLujoso() {
        return cantidadCamarotes > 8;
    }

    @Override//Indica que el método sobrescribe una versión en la clase padre.
    public String toString() {
        return super.toString() +
                "\nTipo: Yate" +
                "\nCantidad de camarotes: " + cantidadCamarotes +
                "\nAlquileres: " + cantidadAlquileres +
                "\nPrecio de compra: " + calcularPrecioCompra();
    }
}
