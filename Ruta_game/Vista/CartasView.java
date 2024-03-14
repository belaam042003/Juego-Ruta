package Vista;

import javax.swing.*;
import Controlador.*;
import Modelo.Carta;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CartasView extends JPanel {

    private String nombreCarta;
    private EventoCartas cartaListener=new EventoCartas(nombreCarta);
    private static final Map<String, String> rutaImagenes = new HashMap<>();

    static {
        rutaImagenes.put("Sin gasolina", "cartasRoja/faltaCombustible.png");
        rutaImagenes.put("Pinchazo", "cartasRoja/pinchadura.png");
        rutaImagenes.put("Accidente", "cartasRoja/choque.png");
        rutaImagenes.put("Limite de Vel.", "cartasRoja/velocidadMaxima.png");
        rutaImagenes.put("Pare", "cartasRoja/stop.png");
        rutaImagenes.put("Gasolina", "cartaVerde/combustible.png");
        rutaImagenes.put("Llanta de repuesto", "cartaVerde/ruedaAux.png");
        rutaImagenes.put("Via Libre", "cartasAmarilla/pasoPrioritario.png");
        rutaImagenes.put("Reparacion", "cartaVerde/taller.png");
        rutaImagenes.put("Fin de Limite", "cartaVerde/velocidadMaxima.png");
        rutaImagenes.put("Siga", "cartaVerde/siga.png");
        rutaImagenes.put("Cisterna", "cartasAmarilla/cisterna.png");
        rutaImagenes.put("Llanta irrompible", "cartasAmarilla/antiPinchadura.png");
        rutaImagenes.put("As de Volante", "cartasAmarilla/asVolante.png");
        rutaImagenes.put("200", "cartaAzules/200.png");
        rutaImagenes.put("100", "cartaAzules/100.png");
        rutaImagenes.put("75", "cartaAzules/75.png");
        rutaImagenes.put("50", "cartaAzules/50.png");
        rutaImagenes.put("25", "cartaAzules/25.png");
    }

    public CartasView(Carta carta) {
        this.nombreCarta = carta.getNombre();  // Almacena el nombre de la carta

        setPreferredSize(new Dimension(80, 120));
        setBackground(Color.WHITE);

        ImageIcon iconoCarta = obtenerIconoSegunTipo(this.nombreCarta);
        JLabel cartaLabel = new JLabel(iconoCarta);

        cartaListener = new EventoCartas(this.nombreCarta);  // Pasa el nombreCarta al constructor

        cartaLabel.addMouseListener(cartaListener);

        add(cartaLabel);
    }
    private ImageIcon obtenerIconoSegunTipo(String nombre) {
        String rutaImagen = rutaImagenes.get(nombre);
        if (rutaImagen != null) {
            // Carga la imagen desde la ruta especificada
            ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
            // Escala la imagen al tamaño del panel
            Image imagenEscalada = icono.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            // Retorna un nuevo ImageIcon con la imagen escalada
            return new ImageIcon(imagenEscalada);
        } else {
            return null; // Si no se encuentra la imagen, retorna null
        }
    }
    public String getNombreCarta() {
        return nombreCarta;
    }
}
