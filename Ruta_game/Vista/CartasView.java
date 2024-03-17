package Vista;

import javax.swing.*;
import Controlador.*;
import Modelo.Carta;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase CartasView representa la vista de una carta en el juego.
 * Esta clase se utiliza para mostrar una carta en la interfaz gráfica.
 */
public class CartasView extends JPanel {

    private String nombreCarta; // Nombre de la carta
    private EventoCartas cartaListener; // Evento asociado a la carta
    private static final Map<String, String> rutaImagenes = new HashMap<>(); // Mapa de rutas de las imágenes de las cartas

    // Inicialización estática del mapa de rutas de las imágenes de las cartas
    static {
        //Cartas problema
        rutaImagenes.put("Falta de combustible", "Vista/Imagenes/FaltaDeCombustible.png");
        rutaImagenes.put("Pinchadura", "Vista/Imagenes/Pinchadura.png");
        rutaImagenes.put("Choque", "Vista/Imagenes/Choque.png");
        rutaImagenes.put("Limite de Vel.", "Vista/Imagenes/VelMax.png");
        rutaImagenes.put("Stop", "Vista/Imagenes/Stop.png");

        //Cartas soluciones
        rutaImagenes.put("Combustible", "Vista/Imagenes/Combustible.png");
        rutaImagenes.put("Rueda de auxilio", "Vista/Imagenes/RuedaDeAuxilio.png");
        rutaImagenes.put("Reparacion", "Vista/Imagenes/Tallerr.png");
        rutaImagenes.put("Fin de Limite", "Vista/Imagenes/FInVelMax.png");
        rutaImagenes.put("Siga", "Vista/Imagenes/Siga.png");

        //Cartas de seguridad
        rutaImagenes.put("Prioridad de paso", "Vista/Imagenes/PrioridadDePaso.png");
        rutaImagenes.put("Llanta a prueba de pinchaduras", "Vista/Imagenes/Pinchadura.png");
        rutaImagenes.put("As de Volante", "Vista/Imagenes/AsDelVolante.png");
        rutaImagenes.put("Combustible extra", "Vista/Imagenes/CombustibleExtra.png");

        //Cartas de distancia
        rutaImagenes.put("200", "Vista/Imagenes/distance200.png");
        rutaImagenes.put("100", "Vista/Imagenes/distance100.png");
        rutaImagenes.put("75", "Vista/Imagenes/distance75.png");
        rutaImagenes.put("50", "Vista/Imagenes/distance50.png");
        rutaImagenes.put("25", "Vista/Imagenes/distance25.png");
    }

    /**
     * Constructor de la clase CartasView.
     * @param carta La carta que se va a mostrar en la vista.
     */
    public CartasView(Carta carta) {
        this.nombreCarta = carta.getNombre(); // Almacena el nombre de la carta

        setPreferredSize(new Dimension(80, 120)); // Establece el tamaño preferido del panel
        setBackground(Color.BLACK); // Establece el color de fondo del panel

        ImageIcon iconoCarta = obtenerIconoSegunTipo(this.nombreCarta); // Obtiene el icono de la carta según su tipo
        JLabel cartaLabel = new JLabel(iconoCarta); // Crea una etiqueta con el icono de la carta

        cartaListener = new EventoCartas(this.nombreCarta); // Crea un evento asociado a la carta
        cartaLabel.addMouseListener(cartaListener); // Agrega el evento de clic a la etiqueta de la carta

        add(cartaLabel); // Agrega la etiqueta de la carta al panel
    }

    /**
     * Método privado para obtener el icono de la carta según su tipo.
     * @param nombre El nombre de la carta.
     * @return El icono de la carta.
     */
    private ImageIcon obtenerIconoSegunTipo(String nombre) {
        String rutaImagen = rutaImagenes.get(nombre); // Obtiene la ruta de la imagen de la carta
        if (rutaImagen != null) {
            // Carga la imagen desde la ruta especificada
            ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
            // Escala la imagen al tamaño del panel
            Image imagenEscalada = icono.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            // Retorna un nuevo ImageIcon con la imagen escalada
            return new ImageIcon(imagenEscalada);
        } else {
            return null; // Si no se encuentra la imagen, retorna null
        }
    }

    /**
     * Método para obtener el nombre de la carta.
     * @return El nombre de la carta.
     */
    public String getNombreCarta() {
        return nombreCarta;
    }
}
