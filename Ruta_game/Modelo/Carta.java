package Modelo;

import java.io.Serializable;

/**
/Se genera la clase Carta con los atributos:
 *	 nombre - Identificador de la carta con el nombre
 *	 tipo - De que tipo sera la carta (Problema, seguridad, solucion, distancia)
*/

public class Carta implements Interface_Cartas , Serializable {
    private String nombre;
    private String tipo;

    public Carta(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    public void mostrarCartas() {
        System.out.println(" Tu carta es: " + nombre + " de " + tipo);
    }
}
