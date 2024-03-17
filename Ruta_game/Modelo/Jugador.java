package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * La clase Jugador representa a un jugador en el juego.
 * Contiene atributos para el identificador del jugador, el total de jugadores,
 * la lista de cartas que tiene el jugador en su mano, y la lista de cartas en su zona.
 */
public class Jugador implements Serializable {
    /**
     * Identificador del jugador.
     */
    private int id;

    /**
     * Total de puntos de los jugadores que participarán en el juego.
     */
    private int jug_total;

    /**
     * Lista de las cartas que tendrá el jugador para poder jugar.
     */
    private List<Carta> mazo_jugador;

    /**
     * Lista para indicar en qué zona estarán las cartas del jugador.
     */
    private List<Carta> ub_zona;

    /**
     * Constructor de la clase Jugador.
     * @param id Identificador del jugador.
     */
    public Jugador(int id) {
        this.id = id;
        this.jug_total = 0;
        this.mazo_jugador = new ArrayList<>();
        this.ub_zona = new ArrayList<>();
    }

    // Getters y setters para los atributos

    /**
     * Método para obtener el identificador del jugador.
     * @return El identificador del jugador.
     */
    public int getId() {
        return id;
    }

    /**
     * Método para obtener el total de jugadores.
     * @return El total de jugadores.
     */
    public int getTotal() {
        return jug_total;
    }

    /**
     * Método para establecer el total de jugadores.
     * @param total El total de jugadores.
     */
    public void setTotal(int total) {
        this.jug_total = total;
    }

    /**
     * Método para obtener la mano del jugador (lista de cartas que tiene en su mano).
     * @return La lista de cartas en la mano del jugador.
     */
    public List<Carta> getMano() {
        return mazo_jugador;
    }

    /**
     * Método para establecer la mano del jugador.
     * @param mano La lista de cartas que se establecerá como la mano del jugador.
     */
    public void setMano(List<Carta> mano) {
        this.mazo_jugador = mano;
    }

    /**
     * Método para obtener la zona del jugador (lista de cartas en su zona).
     * @return La lista de cartas en la zona del jugador.
     */
    public List<Carta> getZona() {
        return ub_zona;
    }

    /**
     * Método para establecer la zona del jugador.
     * @param zona La lista de cartas que se establecerá como la zona del jugador.
     */
    public void setZona(List<Carta> zona) {
        this.ub_zona = zona;
    }
}
