package Modelo;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase contiene métodos relacionados con la lógica del juego y el manejo del mazo de cartas.
 */
public class Logica_Game {

    /**
     * Muestra la cantidad de cada carta en el mazo.
     *
     * @param mazo La lista de cartas que representa el mazo del juego.
     */
    public static void verMazo(List<Carta> mazo) {
        // Mapa para contar la cantidad de cada carta por nombre
        Map<String, Integer> cantidadPorNombre = new HashMap<>();
    
        // Contabiliza la cantidad de cada carta en el mazo
        for (Carta carta : mazo) {
            String nombreCarta = carta.getNombre();
            cantidadPorNombre.put(nombreCarta, cantidadPorNombre.getOrDefault(nombreCarta, 0) + 1);
        }
    
        // Imprime la cantidad de cartas en el mazo y la cantidad por nombre
        System.out.println("Cartas en el mazo: " + mazo.size());
        System.out.println("Cantidad de cartas por nombre:");
    
        for (Map.Entry<String, Integer> entry : cantidadPorNombre.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Agrega un número específico de cartas de un tipo dado al mazo.
     *
     * @param mazo La lista de cartas que representa el mazo del juego.
     * @param fabrica La fábrica de cartas que genera las cartas.
     * @param numeros Un arreglo de números que indica el índice de la carta en el tipo.
     * @param tipo El tipo de cartas que se agregarán al mazo.
     */
    public static void agregarCartasAlMazo(List<Carta> mazo, Cartas_Factory fabrica, Integer[] numeros, String tipo) {
        // Agrega cartas al mazo utilizando los números de índice proporcionados
        for (int i = 0; i < numeros.length; i++) {
            agregarCartasAlMazo(mazo, fabrica, i, numeros[i], tipo);
        }
    }
    
    /**
     * Agrega una cantidad específica de una carta de un tipo dado al mazo.
     *
     * @param mazo La lista de cartas que representa el mazo del juego.
     * @param fabrica La fábrica de cartas que genera las cartas.
     * @param indice El índice de la carta en el tipo.
     * @param cantidad La cantidad de cartas que se agregarán al mazo.
     * @param tipo El tipo de cartas que se agregarán al mazo.
     */
    public static void agregarCartasAlMazo(List<Carta> mazo, Cartas_Factory fabrica, int indice, int cantidad, String tipo) {
        // Agrega la cantidad especificada de cartas al mazo desde la fábrica de cartas
        for (int i = 0; i < cantidad; i++) {
            List<Carta> cartas = fabrica.crearCartas(tipo);
            if (indice < cartas.size()) {
                mazo.add(cartas.get(indice));
            } else {
                System.out.println("No hay suficientes cartas en el tipo '" + tipo + "' para el índice " + indice);
            }
        }
        System.out.println("Se agregaron " + cantidad + " cartas de tipo '" + tipo + "' al mazo.");
    }
}
