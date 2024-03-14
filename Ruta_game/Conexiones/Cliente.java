package Conexiones;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import Modelo.Juego;
import Modelo.Jugador;

public class Cliente {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;

    public Cliente(String servidorIP, int puerto) {
        try {
            socket = new Socket(servidorIP, puerto);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            Thread t = new Thread(this::recibirActualizaciones);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recibirActualizaciones() {
        try {
            while (true) {
                Serializable objetoRecibido = (Serializable) inputStream.readObject();

                // Realizar la lógica correspondiente con el objeto recibido
                if (objetoRecibido instanceof List<?>) {
                    // Actualizar la lista de jugadores en JuegoRuta
                    //Juego.actualizarJugadores((List<Jugador>) objetoRecibido);
                } else if (objetoRecibido instanceof Integer) {
                    // Actualizar el turno actual en JuegoRuta
                    Juego.turnoActual = (Integer) objetoRecibido;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void enviarJugadoresYTurno(List<Jugador> jugadores, int turnoActual) {
        try {
            // Envía la lista de jugadores al servidor
            outputStream.writeObject(jugadores);
            // Envía el turno actual al servidor
            outputStream.writeObject(turnoActual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
        // Realiza acciones de inicio si es necesario
        System.out.println("Cliente iniciado con éxito.");
    
        // Aquí puedes agregar cualquier lógica adicional que necesites al iniciar el cliente
    }
}


