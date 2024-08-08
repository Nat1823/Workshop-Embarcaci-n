import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Listas para almacenar usuarios, embarcaciones y capitanes
        List<Usuario> usuarios = new ArrayList<>();
        List<Embarcacion> embarcaciones = new ArrayList<>();
        List<Capitan> capitanes = new ArrayList<>();

        // Instanciar los capitanes
        Capitan capitan1 = new Capitan("David", "Morales", "ED8546-9");
        Capitan capitan2 = new Capitan("Santiago", "Ramos", "ED1823-2");
        Capitan capitan3 = new Capitan("Jennifer", "Barragán", "ED1900-1");
        Capitan capitan4 = new Capitan("Sara", "Connor", "ED2303-3");
        Capitan capitan5 = new Capitan("Rahena", "Targaryen", "ED0018-1");

        // Añadir capitanes a la lista
        capitanes.add(capitan1);
        capitanes.add(capitan2);
        capitanes.add(capitan3);
        capitanes.add(capitan4);
        capitanes.add(capitan5);

        // Crear algunos yates y veleros
        Yates yate1 = new Yates(2000, 300, Year.of(2022), 30, 6, 50000);
        yate1.setCapitan(capitan1);
        Yates yate2 = new Yates(2500, 350, Year.of(2019), 25, 8, 60000);
        yate2.setCapitan(capitan2);

        Veleros velero1 = new Veleros(1505, 300, Year.of(2021), 20, 5);
        velero1.setCapitan(capitan1);
        Veleros velero2 = new Veleros(2700, 220, Year.of(2001), 15, 4);
        velero2.setCapitan(capitan2);

        // Añadir las embarcaciones a la lista
        embarcaciones.add(yate1);
        embarcaciones.add(yate2);
        embarcaciones.add(velero1);
        embarcaciones.add(velero2);

        Scanner scanner = new Scanner(System.in);

        // Mostrar las opciones del menú
        while (true) {
            System.out.println("ALQUILER DE EMBARCACIONES MAKAIA <3");
            System.out.println("\nMenú de opciones");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Alquilar embarcación");
            System.out.println("3. Devolver embarcación");
            System.out.println("4. Mostrar embarcaciones alquiladas");
            System.out.println("5. Mostrar lista de capitanes");
            System.out.println("6. Ver disponibilidad de yates");
            System.out.println("7. Vender Yate");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1: // Registrar usuario
                    System.out.println("Ingrese la identificación del usuario:");
                    String identificacion = scanner.nextLine();

                    System.out.println("Ingrese el nombre del usuario:");
                    String nombre = scanner.nextLine();

                    System.out.println("Ingrese la dirección del usuario:");
                    String direccion = scanner.nextLine();

                    System.out.println("Ingrese el teléfono del usuario:");
                    String telefono = scanner.nextLine();

                    Usuario usuario = new Usuario(identificacion, nombre, direccion, telefono);
                    usuarios.add(usuario); // Agregar el usuario a la lista
                    System.out.println("Usuario registrado exitosamente.");
                    break;

                case 2: // Alquilar embarcación
                    //verifico que hayan usuarios registrados, osea, primero tienen que registrar uno
                    if (usuarios.isEmpty()) {
                        System.out.println("Debe registrar al menos un usuario antes de alquilar una embarcación.");
                        break;//Si no hay usuarios registrados,se termina la ejecución
                    }

                    System.out.println("Seleccione la embarcación para alquilar:");
                    for (int i = 0; i < embarcaciones.size(); i++) {//muestra las embarcaciones dispo
                        System.out.println((i + 1) + ". " + embarcaciones.get(i));
                    }
                    int seleccion = scanner.nextInt();
                    scanner.nextLine(); //Lee por consola la opcion del usuario
                    //Miro si esta disponible
                    if (seleccion >= 1 && seleccion <= embarcaciones.size()) {
                        Embarcacion seleccionada = embarcaciones.get(seleccion - 1);
                        if (seleccionada.isDisponible()) {
                            System.out.println("Ingrese el nombre del usuario:");
                            String nombreUsuario = scanner.nextLine();

                            Usuario usuarioSeleccionado = null;
                            for (Usuario u : usuarios) {
                                if (u.getNombre().equals(nombreUsuario)) {//busca que el nombre del usuario sea igual a uno que este registrado
                                    usuarioSeleccionado = u;
                                    break;
                                }
                            }

                            if (usuarioSeleccionado == null) {
                                System.out.println("Usuario no encontrado.");
                                break;//salta aqui si no hay usuario
                            }

                            seleccionada.setUsuario(usuarioSeleccionado);
                            //Asocioa al usuario con la embarcación si esta esta dispo
                            if (seleccionada instanceof Yates) {
                                Yates yate = (Yates) seleccionada;
                                System.out.println("Yate alquilado con éxito.");
                                System.out.println("Monto del alquiler: " + yate.calcularMontoAlquiler());
                            } else if (seleccionada instanceof Veleros) {
                                Veleros velero = (Veleros) seleccionada;
                                System.out.println("Velero alquilado con éxito.");//saca un mensaje diciendo que se pudo alquilar
                                System.out.println("Monto del alquiler: " + velero.calcularMontoAlquiler());
                            }
                        } else {
                            System.out.println("La embarcación ya está alquilada.");//si no esta dispo dice que esta alquilada
                        }
                    } else {
                        System.out.println("Selección inválida.");
                    }
                    break;

                case 3: // Devolver embarcación

                    System.out.println("Seleccione la embarcación que desea devolver:");
                    boolean algunaAlquilada = false;
                    for (int i = 0; i < embarcaciones.size(); i++) {//mira que dentro de la lista de embarcaciones una este asignada al usuario
                        Embarcacion e = embarcaciones.get(i);
                        if (e.getUsuario() != null) {
                            System.out.println((i + 1) + ". " + e);//las muestra en pantalla
                            algunaAlquilada = true;//Marca algunaAlquilada como true si encuentra al menos una embarcación alquilada
                        }
                    }
                    if (!algunaAlquilada) {
                        System.out.println("No hay embarcaciones alquiladas.");//si no
                        break;
                    }

                    int devolucionSeleccion = scanner.nextInt();
                    scanner.nextLine(); // el usuario elije el numero de la embarcacion a devolver

                    if (devolucionSeleccion >= 1 && devolucionSeleccion <= embarcaciones.size()) {//verifica que la eleccion este dentro del rango
                        Embarcacion embarcacionDevolvida = embarcaciones.get(devolucionSeleccion - 1);
                        if (embarcacionDevolvida.getUsuario() != null) {
                            embarcacionDevolvida.finalizarAlquiler(); // Devolver la embarcación
                            embarcacionDevolvida.setUsuario(null); // Limpiar el usuario
                            System.out.println("Embarcación devuelta con éxito.");//mensaje de confirmación
                        } else {
                            System.out.println("La embarcación seleccionada no está alquilada.");//mensaje si escribe un numero que no este alquilado
                        }
                    } else {
                        System.out.println("Selección inválida.");
                    }
                    break;

                case 4: // Mostrar embarcaciones alquiladas
                    boolean hayAlquiladas = false;

                    System.out.println("Embarcaciones alquiladas:");
                    for (Embarcacion e : embarcaciones) {
                        if (e.getUsuario() != null) {
                            System.out.println("Tipo: " + (e instanceof Yates ? "Yate" : "Velero"));
                            System.out.println("Capitán: " + e.getCapitan().getNombre());
                            System.out.println("Usuario: " + e.getUsuario().getNombre());
                            System.out.println("Monto del alquiler: " + e.calcularMontoAlquiler());
                            System.out.println("---------");
                            hayAlquiladas = true;
                        }
                    }
                    if (!hayAlquiladas) {
                        System.out.println("No hay embarcaciones alquiladas.");
                    }
                    break;

                case 5: // Mostrar lista de capitanes
                    System.out.println("Lista de capitanes:");
                    for (Capitan capitan : capitanes) {
                        System.out.println(capitan);
                    }
                    break;

                case 6: // Ver disponibilidad de yates
                    System.out.println("Disponibilidad de yates:");
                    for (Embarcacion e : embarcaciones) {
                        if (e instanceof Yates) {
                            Yates yate = (Yates) e;
                            System.out.println("Yate: " + yate);
                            System.out.println("Disponible: " + (yate.isDisponible() ? "Sí" : "No"));
                            System.out.println("---------");
                        }
                    }
                    break;
                case 7:  // Vender yate
                    System.out.println("Seleccione el yate para vender:");
                    for (int i = 0; i < embarcaciones.size(); i++) {//muestra la lisat de yates disponibles para vender al usuario
                        if (embarcaciones.get(i) instanceof Yates) {
                            System.out.println((i + 1) + ". " + embarcaciones.get(i));
                        }
                    }
                    int seleccionVenta = scanner.nextInt();
                    scanner.nextLine(); // Lee la opcion del usuario
                    //verifica que la embarcacion este dentro de los que se pueden vender
                    if (seleccionVenta >= 1 && seleccionVenta <= embarcaciones.size()) {
                        Embarcacion embarcacionVenta = embarcaciones.get(seleccionVenta - 1);
                        if (embarcacionVenta instanceof Yates) {
                            Yates yate = (Yates) embarcacionVenta;
                            double precioFinal = yate.calcularPrecioCompra();
                            System.out.println("El precio de compra del yate es: " + precioFinal);//muestra el precio a pagar
                            embarcaciones.remove(yate); // Eliminar el yate de la lista//lo remueve de la lista embarcaciones
                            System.out.println("Yate vendido exitosamente.");
                        } else {
                            System.out.println("La embarcación seleccionada no es un yate.");
                        }
                    } else {
                        System.out.println("Selección no válida.");
                    }
                    break;


                case 8:
                    // Salir
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0); // Salir del sistema
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción correcta.");
                    break;
            }
        }
    }
}
