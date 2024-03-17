package Conexiones;

import java.io.Serializable;
import java.util.List;
import Modelo.Jugador;

/**
 * La clase Cliente_jugador implementa la interfaz Serializable, lo que significa que los objetos de esta clase pueden ser convertidos en una secuencia de bytes.
 * Esta clase se utiliza para enviar información sobre los jugadores desde el cliente al servidor o viceversa.
 */
public class Cliente_jugador implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador único para la serialización

    private List<Jugador> jugadores; // Lista de jugadores

    /**
     * Constructor de la clase Cliente_jugador.
     * @param jugadores La lista de jugadores a enviar.
     */
    public Cliente_jugador(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Método para obtener la lista de jugadores.
     * @return La lista de jugadores.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
