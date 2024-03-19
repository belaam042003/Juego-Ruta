package Conexiones;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Modelo.Jugador;

/**
 * La clase Game_Servidor representa el servidor del juego.
 * Se encarga de aceptar conexiones de clientes, manejar la comunicación con
 * ellos
 * y redistribuir la información recibida a todos los clientes conectados.
 */
public class Game_Servidor {
    private ServerSocket serverSocket; // Socket del servidor
    private List<ObjectOutputStream> clientesStreams; // Lista de flujos de salida para los clientes

    /**
     * Constructor de la clase Game_Servidor.
     * 
     * @param puerto El puerto en el que escuchará el servidor.
     */

    public Game_Servidor(int puerto) {
        try {
            // Inicializar el servidor en el puerto especificado
            serverSocket = new ServerSocket(puerto);
            // Inicializar la lista de flujos de salida para los clientes
            clientesStreams = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para iniciar el servidor y aceptar conexiones de clientes.
     */
    public void iniciar() {
        try {
            while (true) {
                // Aceptar una nueva conexión de cliente
                Socket socket = serverSocket.accept();
                // Crear un flujo de salida para el cliente y agregarlo a la lista
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                clientesStreams.add(outputStream);

                // Mostrar mensaje cuando se conecta un nuevo cliente
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());

                // Iniciar un hilo para manejar la comunicación con el cliente
                Thread t = new Thread(() -> manejarCliente(socket));
                t.start();
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para manejar la comunicación con un cliente.
     * 
     * @param socket El socket del cliente.
     */
    private void manejarCliente(Socket socket) {
        try {
            // Crear un flujo de entrada para recibir datos del cliente
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                // Leer un objeto del cliente
                Serializable objetoRecibido = (Serializable) inputStream.readObject();
                List< Jugador> Tam_Datos = (List<Jugador>) objetoRecibido;
                System.out.println(Tam_Datos.get(0).getZona().size() + " Tamaño de zona recibida ");
                // Enviar el objeto recibido a todos los clientes conectados
                enviarAClientes(objetoRecibido);
                //inputStream.
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para enviar un objeto a todos los clientes conectados.
     * 
     * @param objeto El objeto a enviar.
     */
    private void enviarAClientes(Serializable objeto) {
        for (ObjectOutputStream clienteStream : clientesStreams) {
            try {
                // Enviar el objeto a través del flujo de salida del cliente
                clienteStream.writeObject(objeto);
               clienteStream.flush();
               clienteStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método principal que crea una instancia del servidor y lo inicia.
     * 
     * @param args Los argumentos de la línea de comandos (no se utilizan en este
     *             caso).
     */
    public static void main(String[] args) {
        // Crear una instancia del servidor en el puerto 12345 y iniciarla
        Game_Servidor servidor = new Game_Servidor(12345);
        servidor.iniciar();
    }
}
