package Modelo;

import java.util.List;

/**
 * La interfaz Interface_Fabricas define un contrato para las clases que representan fábricas de cartas en el juego.
 * Contiene un método para crear cartas de un tipo específico.
 */
public interface Interface_Fabricas {
    
    /**
     * Método para crear cartas de un tipo específico.
     * @param tipo El tipo de cartas a crear.
     * @return Una lista de cartas del tipo especificado.
     */
    List<Carta> crearCartas(String tipo);
}
