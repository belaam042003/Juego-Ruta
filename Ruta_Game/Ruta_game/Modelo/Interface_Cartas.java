package Modelo;

/**
 * La interfaz Interface_Cartas define el contrato para las clases que representan cartas en el juego.
 * Contiene métodos para obtener el nombre y el tipo de la carta.
 */
public interface Interface_Cartas {
    
    /**
     * Método para obtener el nombre de la carta.
     * @return El nombre de la carta.
     */
    String getNombre();
    
    /**
     * Método para obtener el tipo de la carta.
     * @return El tipo de la carta.
     */
    String getTipo();
}
