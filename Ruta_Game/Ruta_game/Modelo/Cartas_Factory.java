package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Cartas_Factory implementa la interfaz Interface_Fabricas y se encarga de crear diferentes tipos de cartas.
 * Contiene arreglos de nombres de cartas para cada tipo y un método para crear cartas según el tipo especificado.
 */
public class Cartas_Factory implements Interface_Fabricas {

    // Arreglos de nombres de cartas para cada tipo
    private static final String[] C_Problema = {"Falta de combustible", "Pinchadura", "Choque", "Limite de Vel.", "Stop"};
    private static final String[] C_Solucion = {"Combustible", "Rueda de auxilio", "Reparacion", "Fin de Limite", "Siga"};
    private static final String[] C_Seguridad = {"Combustible extra", "Llanta a prueba de pinchaduras", "As de Volante", "Prioridad de paso"};
    private static final String[] C_Distancia = {"200", "100", "75", "50", "25"};

    /**
     * Método para crear cartas según el tipo especificado.
     * @param tipo El tipo de cartas a crear.
     * @return Una lista de cartas del tipo especificado.
     */
    @Override
    public List<Carta> crearCartas(String tipo) {
        List<Carta> cartas = new ArrayList<>();

        // Según el tipo especificado, se agregan las cartas correspondientes a la lista
        switch (tipo) {
            case "Problema":
                agregarCartas(cartas, C_Problema);
                break;
            case "Solucion":
                agregarCartas(cartas, C_Solucion);
                break;
            case "Seguridad":
                agregarCartas(cartas, C_Seguridad);
                break;
            case "Distancia":
                agregarCartas(cartas, C_Distancia);
                break;
            default:
                System.out.println("Tipo de carta no válido.");
                break;
        }

        return cartas;
    }

    /**
     * Método privado para agregar cartas a la lista.
     * @param cartas La lista de cartas a la que se agregarán las nuevas cartas.
     * @param nombres El arreglo de nombres de las cartas a agregar.
     */
    private void agregarCartas(List<Carta> cartas, String[] nombres) {
        for (String nombre : nombres) {
            // Agregar nuevas instancias de 'Carta' a la lista
            cartas.add(new Carta(nombre, Reglas.obtenerTipoCarta(nombre))); // Se utiliza un método para obtener el tipo de carta
        }
    }
}
