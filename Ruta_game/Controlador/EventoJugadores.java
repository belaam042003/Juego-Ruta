package Controlador;

import Modelo.Juego;
import Vista.Bienvenida;
import Vista.Instrucciones;
import Modelo.Jugador;
import java.util.*;

/**
 * La clase EventoJugadores se encarga de gestionar los eventos relacionados con los jugadores del juego.
 * Contiene una instancia de la clase interna estática Even_Jugadores, que representa el evento relacionado con los jugadores del juego.
 * Even_Jugadores contiene una lista de jugadores y métodos para obtener y establecer dicha lista.
 */
public class EventoJugadores {

    // Instancia de la clase Even_Jugadores que representa el evento relacionado con los jugadores del juego
    Even_Jugadores jugadorsitos = new Even_Jugadores(Juego.jugadores);

    /**
     * Clase interna estática Even_Jugadores que representa el evento relacionado con los jugadores del juego.
     */
    public static class Even_Jugadores {
        private List<Jugador> jugadores; // Lista de jugadores del juego

        /**
         * Constructor de la clase Even_Jugadores.
         * @param jugadores Lista de jugadores del juego.
         */
        public Even_Jugadores(List<Jugador> jugadores) {
            this.jugadores = jugadores;
        }

        /**
         * Método para obtener la lista de jugadores.
         * @return La lista de jugadores del juego.
         */
        public List<Jugador> getJugadores() {
            return jugadores;
        }

        /**
         * Método para establecer la lista de jugadores.
         * @param jugadores La nueva lista de jugadores del juego.
         */
        public void setJugadores(List<Jugador> jugadores) {
            this.jugadores = jugadores;
        }
        
    }

    /**
     * Método estático que muestra un mensaje en la interfaz de bienvenida y solicita al usuario un número como entrada.
     * @param mensaje El mensaje a mostrar al usuario.
     * @return El número ingresado por el usuario.
     */
    public static int StringNumeroVisual(String mensaje) {
        return Instrucciones.mostrarStringYObtenerNumero(mensaje);
    }
}
