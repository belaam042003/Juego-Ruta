package Conexiones;

import java.io.Serializable;
import java.util.List;
import Modelo.Jugador;

public class Cliente_jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Jugador> jugadores;

    public Cliente_jugador(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
