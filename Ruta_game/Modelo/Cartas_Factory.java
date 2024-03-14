package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
/Se genera la clase Cartas_Factory con los atributos:
 *	 C_Problema - Con cartas diferentes segun cada arreglo de cadenas
 *	 C_Solucion 
 *   C_Seguridad
 *   C_Distancia 
*/

public class Cartas_Factory implements Interface_Fabricas {
    private static final String[] C_Problema = {"Sin gasolina", "Pinchazo", "Accidente", "Limite de Vel.", "Pare"};
    private static final String[] C_Solucion = {"Gasolina", "Llanta de repuesto", "Reparacion", "Fin de Limite", "Siga"};
    private static final String[] C_Seguridad = {"Cisterna", "Llanta irrompible", "As de Volante", "Via Libre"};
    private static final String[] C_Distancia = {"200", "100", "75", "50", "25"};

    //Segun el tipo se agrega cierto tipo de cartas 
    @Override
public List<Carta> crearCartas(String tipo) {
    List<Carta> cartas = new ArrayList<>();

    switch (tipo) {
        case "Problema":
            agregarCartas(cartas, C_Problema);
            break;
        case "Solucion":
            agregarCartas(cartas, C_Solucion);
            break;
        case "Seguridad":
            agregarCartas(cartas, C_Seguridad);
            break;
        case "Distancia":
            agregarCartas(cartas, C_Distancia);
            break;
        default:
            System.out.println("Tipo de carta no válido.");
            break;
    }

    return cartas;
}

//Recibe una lista de cartas y el arreglo de cartas y agrega las nuevas instancias de 'Carta'
private void agregarCartas(List<Carta> cartas, String[] nombres) {
    for (String nombre : nombres) {
        //ObtenerTipoCarta
        cartas.add(new Carta(nombre, Reglas.obtenerTipoCarta(nombre)));
    }
}
 
}