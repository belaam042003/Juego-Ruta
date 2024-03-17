package Controlador;

/**
 * La interfaz EventoAccion define un contrato para los objetos que desean manejar acciones.
 * Las clases que implementan esta interfaz deben proporcionar una implementación para el método realizarAccionB.
 */
public interface EventoAccion {
    
    /**
     * Método que define una acción a realizar.
     * @param accion El identificador de la acción a realizar.
     */
    void realizarAccionB(int accion);
}
