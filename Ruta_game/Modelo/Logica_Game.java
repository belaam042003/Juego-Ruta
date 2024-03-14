package Modelo;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Logica_Game {

    public static void verMazo(List<Carta> mazo) {
        Map<String, Integer> cantidadPorNombre = new HashMap<>();
    
        for (Carta carta : mazo) {
            String nombreCarta = carta.getNombre();
            cantidadPorNombre.put(nombreCarta, cantidadPorNombre.getOrDefault(nombreCarta, 0) + 1);
        }
    
        System.out.println("Cartas en el mazo: " + mazo.size());
        System.out.println("Cantidad de cartas por nombre:");
    
        for (Map.Entry<String, Integer> entry : cantidadPorNombre.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void agregarCartasAlMazo(List<Carta> mazo, Cartas_Factory fabrica, Integer[] numeros, String tipo) {
        for (int i = 0; i < numeros.length; i++) {
            agregarCartasAlMazo(mazo, fabrica, i, numeros[i], tipo);
        }
    }
    
    public static void agregarCartasAlMazo(List<Carta> mazo, Cartas_Factory fabrica, int indice, int cantidad, String tipo) {
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
