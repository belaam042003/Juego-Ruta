package Modelo;

import java.io.Serializable;

/**
 * La clase Carta representa una carta en el juego.
 * Implementa la interfaz Interface_Cartas y Serializable para poder ser serializada.
 */
public class Carta implements Interface_Cartas, Serializable {
    private String nombre; // Identificador de la carta con el nombre
    private String tipo; // Tipo de la carta (Problema, seguridad, solucion, distancia)

    /**
     * Constructor de la clase Carta.
     * @param nombre El nombre de la carta.
     * @param tipo El tipo de la carta.
     */
    public Carta(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    /**
     * Método para obtener el nombre de la carta.
     * @return El nombre de la carta.
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener el tipo de la carta.
     * @return El tipo de la carta.
     */
    @Override
    public String getTipo() {
        return tipo;
    }

    /**
     * Método para mostrar la información de la carta.
     */
    public void mostrarCartas() {
        System.out.println(" Tu carta es: " + nombre + " de " + tipo);
    }
}
