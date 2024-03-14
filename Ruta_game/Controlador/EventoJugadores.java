package Controlador;

import Modelo.Juego;
import Vista.Bienvenida;
import Modelo.Jugador;
import java.util.*;

/**
/Se genera la clase EventoJugadores con el atributo:
 *	 jugadorsitos - Instancia de la clase de eventos Even_Jugadores y se incializa
 *   con la lista de jugadores del juego
 *   Even_Jugadores - Clase eestatica que representa el evento relacionado con los jugadores del 
 *   juego, la cual contiene una lista de jugadores y metodos para obtener y establecer dicha dista.
*/

public class EventoJugadores {

    Even_Jugadores jugadorsitos = new Even_Jugadores(Juego.jugadores);

    public static class Even_Jugadores {
        private List<Jugador> jugadores;

        public Even_Jugadores(List<Jugador> jugadores) {
            this.jugadores = jugadores;
        }

        public List<Jugador> getJugadores() {
            return jugadores;
        }

        public void setJugadores(List<Jugador> jugadores) {
            this.jugadores = jugadores;
        }
        
    }

    public static int StringNumeroVisual(String mensaje) {
        return Bienvenida.mostrarStringYObtenerNumero(mensaje);
    }


}