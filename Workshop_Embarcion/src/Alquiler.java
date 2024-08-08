public interface Alquiler {

    //La interfaz actúa como un contrato que relaciona las clases a traves de implementar los siguientes metodos en comun

    double calcularAlquiler();
    //calcular el costo de alquilar una embarcación

    double calcularMontoAlquiler();
    //calcula un monto relacionado con el alquiler de la embarcación.

    boolean isDisponible();
    //Verifica si la embarcación está disponible para ser alquilada.

    void finalizarAlquiler();
    //finaliza el alquiler
}