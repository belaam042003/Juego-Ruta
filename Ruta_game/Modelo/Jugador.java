package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Jugador {
	/**
	 /Se genera la clase Jugador con los atributos:
	  *	 id - Identificador del jugador
	  *	 jug_total - Total de jugadores que participaran
	  *  mazo_jugador - Lista de las cartas que tendra el jugador para poder jugador
	  *  ub_zona - Lista para mirar en que zona estaran las cartas
	  *	 
	 */
    private int id;
    private int jug_total;
    private List<Carta> mazo_jugador;
    private List<Carta> ub_zona;

    public Jugador(int id) {
        this.id = id;
        this.jug_total = 0;
        this.mazo_jugador = new ArrayList<>();
        this.ub_zona = new ArrayList<>();
    }

    // Getters y setters para los atributos

    public int getId() {
        return id;
    }

    public int getTotal() {
        return jug_total;
    }

    public void setTotal(int total) {
        this.jug_total = total;
    }

    public List<Carta> getMano() {
        return mazo_jugador;
    }

    public void setMano(List<Carta> mano) {
        this.mazo_jugador = mano;
    }

    public List<Carta> getZona() {
        return ub_zona;
    }

    public void setZona(List<Carta> zona) {
        this.ub_zona = zona;
    }
}