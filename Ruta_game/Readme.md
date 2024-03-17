Componenetes - Comunicación cliente - Conexiones

- Cliente_jugador (Cliente):

Esta clase implementa la interfaz Serializable y se utiliza para enviar información sobre los jugadores desde el cliente al servidor o viceversa.
Tiene un campo que almacena una lista de objetos Jugador.
Proporciona un constructor para inicializar la lista de jugadores y un método para obtener la lista.

- Cliente:

Se encarga de establecer la conexión con el servidor y manejar la comunicación entre el cliente y el servidor.
Inicializa flujos de entrada y salida para enviar y recibir datos del servidor.
Utiliza un hilo separado para recibir actualizaciones continuas del servidor.
Proporciona un método para enviar la lista de jugadores y el turno actual al servidor.
Implementa un método de inicio que se ejecuta al iniciar el cliente.

- Game_Servidor (Servidor):

Representa el servidor del juego y acepta conexiones de clientes.
Inicializa un ServerSocket en un puerto especificado para escuchar conexiones de clientes.
Maneja la comunicación con cada cliente en un hilo separado.
Proporciona métodos para manejar la comunicación con un cliente y enviar objetos a todos los clientes conectados.
Tiene un método principal para crear una instancia del servidor y comenzar a escuchar conexiones.

Controlador - Comunicación entre la vista y el modelo

- Evento Accion:

Es una interfaz la cual define la accion de realizarAccionB -> Basicamente es la que logra que con cada boton de la vista se ejecute una acción al presionarlo.

- Evento Cartas:

Esta clase maneja los eventos del clic del mouse ccon la interfaz de MouseListener que se usa parta programar los eventos y darles ciertas acciones.

- Eventos Jugadores:

Gestiona los eventos relacionados con 