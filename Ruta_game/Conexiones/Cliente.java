package Conexiones;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import Modelo.Juego;
import Modelo.Jugador;
import Vista.Tablero;

/**
 * La clase Cliente se encarga de establecer la conexión con el servidor y manejar la comunicación entre el cliente y el servidor.
 */
public class Cliente {
    private ObjectInputStream inputStream; // Flujo de entrada para recibir datos del servidor
    private ObjectOutputStream outputStream; // Flujo de salida para enviar datos al servidor
    private Socket socket; // Socket para la conexión con el servidor
    private Tablero tablero;

    /**
     * Constructor de la clase Cliente.
     * @param servidorIP La dirección IP del servidor.
     * @param puerto El número de puerto del servidor.
     */
    public Cliente(String servidorIP, int puerto, Tablero tab)  {
        this.tablero = tab;
        try {
            // Establecer la conexión con el servidor
            socket = new Socket(servidorIP, puerto);
            // Inicializar los flujos de entrada y salida
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            // Iniciar un hilo para recibir actualizaciones del servidor
            Thread t = new Thread(this::recibirActualizaciones);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método privado que se ejecuta en un hilo separado para recibir actualizaciones del servidor.
     */
    private void recibirActualizaciones() {
        int count = 0; 
        try {
            while (true) {
                // Leer un objeto serializable del flujo de entrada
                Serializable objetoRecibido = (Serializable) inputStream.readObject();

                // Realizar la lógica correspondiente con el objeto recibido
                if (objetoRecibido instanceof List<?>) {
                    // Actualizar la lista de jugadores en Juego
                    List< Jugador> Tam_Datos = (List<Jugador>) objetoRecibido;
                    //Lista para contar datos
                    System.out.println(Tam_Datos.get(0).getZona().size() + " Tamaño de zona recibida ");
                    System.out.println("Datos recibidos" + count++);
                    //Juego.actualizarJugadores((List<Jugador>) objetoRecibido);
                    Juego.actualizarJugadores(Tam_Datos);
                    tablero.jugadores_n.actualizarInterfaz(Tam_Datos);
                    tablero.repaint();
                } else if (objetoRecibido instanceof Integer) {
                    // Actualizar el turno actual en Juego
                    Juego.turnoActual = (Integer) objetoRecibido;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para enviar la lista de jugadores y el turno actual al servidor.
     * @param jugadores La lista de jugadores a enviar.
     * @param turnoActual El turno actual a enviar.
     */
    public void enviarJugadoresYAccion(List<Jugador> jugadores, int action) {
        try {
            System.out.println(jugadores.get(0).getZona().size() + " Antes de enviar en el cliente ");
            // Envía la lista de jugadores al servidor
            outputStream.writeObject(jugadores);

            // Implementar Turnos
            outputStream.flush();

            System.out.println("Action ENVIADA" + action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para realizar acciones de inicio del cliente.
     */
    public void iniciar() {
        // Realiza acciones de inicio si es necesario
        System.out.println("Cliente iniciado con éxito.");
    
    }

    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
}
