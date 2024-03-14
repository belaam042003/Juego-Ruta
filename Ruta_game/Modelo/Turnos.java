package Modelo;

import java.io.Serializable;
import java.util.List;

public class Turnos implements Serializable {
    private List<Jugador> jugadores;
    private int turnoActual;

    public void EstadoJuego(List<Jugador> jugadores, int turnoActual) {
        this.jugadores = jugadores;
        this.turnoActual = turnoActual;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int getTurnoActual() {
        return turnoActual;
    }
}