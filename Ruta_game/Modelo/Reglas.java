package Modelo;


import java.util.List;
import Controlador.*;

public class Reglas {

 
    public static boolean VerificarRegla(Carta c, List<Jugador> jugadores, Jugador jugadorSeleccionado) {

        String nombre = c.getNombre();
        System.out.println(c.getNombre());
        String tipo = obtenerTipoCarta(nombre);
        boolean verificacion = true;
        switch (tipo) {
            case "Problema":
                System.out.println("Carta Problema Encontrada para Regla");
                verificacion = ReglaProblema(c, jugadores, jugadorSeleccionado);

                break;
            case "Solucion":
                System.out.println("Carta de Solucion Encontrada para Regla");
                verificacion = ReglaSolucion(c, jugadorSeleccionado);


                break;
            case "Seguridad":
                System.out.println("Carta Seguridad Encontrada para Regla");
                verificacion = ReglaSeguridad(c, jugadores, jugadorSeleccionado);

                break;
            case "Distancia":
                System.out.println("Carta Distancia Encontrada para Regla");
                List<Carta> zonaJugador = jugadorSeleccionado.getZona();
                verificacion = ReglaDistancia(c, zonaJugador);

                break;
            default:
                System.out.println("Tipo de carta no válido.");
                verificacion = false;
                break;
        }
        return verificacion;
    }

    public static boolean ReglaSeguridad(Carta cartaSeguridad, List<Jugador> jugadores, Jugador jugadorActual) {
        int limiteSeguridad = 2;  // Límite de cartas seguridad en el mazo
    
        // Contar las cartas seguridad en el mazo
        long seguridadEnMazo = jugadores.stream()
                .flatMap(jugador -> jugador.getZona().stream())
                .filter(carta -> obtenerTipoCarta(carta.getNombre()).equals("Seguridad"))
                .count();
    
        // Verificar si se puede poner la carta seguridad en el mazo
        if (seguridadEnMazo < limiteSeguridad) {
            // Poner la carta seguridad en el mazo
            System.out.println("Carta seguridad '" + cartaSeguridad.getNombre() + "' puesta en el mazo exitosamente.");
            return true;
        } else {
            System.out.println("No se puede poner la carta seguridad '" + cartaSeguridad.getNombre() + "' en el mazo porque ya hay dos de seguridad en el mazo.");
            return false;
        }
    }

    public static boolean ReglaSolucion(Carta cartaSolucion,Jugador Jugador) {

        
        boolean hayProblemaEnMazo = Jugador.getZona().stream()
                .anyMatch(carta -> obtenerTipoCarta(carta.getNombre()).equals("Problema"));
        if(hayProblemaEnMazo){
            System.out.println("Hay problema en el mazo :(");
            return false;
        }

        boolean haySigaEnMazo = tieneCartaSiga(Jugador);
        if (!haySigaEnMazo){
            System.out.println("No contiene carta 'Siga' en el mazo");
            return false;
        }
        
        String nombreCartaSolucion = cartaSolucion.getNombre();
        boolean hayCartaSolucionEnZona = Jugador.getZona().removeIf(carta -> obtenerTipoCarta(carta.getNombre()).equals("Solucion"));

        if (hayCartaSolucionEnZona) {
            System.out.println("Ya hay una carta solucion , se sustituye por la nueva "  );
        }

    try {
            // Convertir el nombre de la carta solucion a un entero
            int valorCarta = Integer.parseInt(nombreCartaSolucion);

            // Sumar el valor de la carta al atributo "total" del jugador
            Jugador.setTotal(Jugador.getTotal() + valorCarta);

            System.out.println("Se sumó el valor de la carta solucion '" + nombreCartaSolucion + "' al total del jugador " + Jugador.getId() + ".");
            return true;
        } catch (NumberFormatException e) {
            // Manejar la excepción en caso de que el nombre de la carta no sea un número válido
            System.out.println("Error al convertir el nombre de la carta solucion a un entero.");
            return false;
        }

    }

    public static boolean ReglaProblema(Carta cartaProblema, List<Jugador> jugadores, Jugador jugadorActual) {
        String nombreCartaProblema = cartaProblema.getNombre();

        Jugador jugadorDestino = seleccionarOtroJugador(jugadores, jugadorActual);

        if (jugadorDestino != null) {
            
            if (tieneCartaProblema(jugadorDestino)) {
                System.out.println("El jugador " + jugadorDestino.getId() + " ya tiene una carta problema, no se coloca otra.");
                return false;
            }
            // Verificar si el otro jugador tiene una carta seguridad similar
            String nombreSeguridad = buscarCartasSeguridad(nombreCartaProblema);

            if (tieneCartaSeguridadSimilar(jugadorDestino, nombreSeguridad)) {
                System.out.println("El jugador " + jugadorDestino.getId() + " ya tiene la carta "+nombreSeguridad);
                        return false;
            } 
            
            jugadorDestino.getZona().add(cartaProblema);
            System.out.println("Carta problema '" + nombreCartaProblema + "' puesta en la zona del jugador " + jugadorDestino.getId() + ".");
            
            if (jugadorActual.getMano().remove(cartaProblema)) {
                System.out.println("Carta problema '" + nombreCartaProblema + "' eliminada de la mano del jugador " + jugadorActual.getId() + ".");
                Logica_Game_P.sacarCarta(Juego.mazo, jugadores, jugadorActual.getId());
            } else {
                System.out.println("Error al intentar eliminar la carta problema '" + nombreCartaProblema + "' de la mano del jugador " + jugadorActual.getId() + ".");
            }
            return false;
        } else {
            System.out.println(
                    "No hay otros jugadores disponibles para colocar la carta problema '" + nombreCartaProblema + "'.");
            // Aquí puedes decidir qué hacer en caso de que no haya otros jugadores
            // disponibles.
            return false;
        }
    }

    public static boolean ReglaDistancia(Carta cartaDistancia, List<Carta> zonaJugador) {
        String nombreCartaDistancia = cartaDistancia.getNombre();
        String cartaContraria = contradiccion(nombreCartaDistancia);

        // Verificar si la carta distancia es del tipo "Siga"

        boolean esSiga = nombreCartaDistancia.equals("Siga");

        // Verificar si hay una carta hayCartaDistanciaEnMazo en el mazo
        boolean hayCartaDistanciaEnMazo = zonaJugador.stream()
                .anyMatch(carta -> obtenerTipoCarta(carta.getNombre()).equals("Distancia"));

        boolean contrariaEnMazo = zonaJugador.removeIf(carta -> carta.getNombre().equals(cartaContraria));
        if (esSiga) {
            if (contrariaEnMazo) {
                System.out.println("Quitado Pare en la Zona.");
                return true;
            }
            if (hayCartaDistanciaEnMazo) {
                System.out.println(
                        "No se puede poner la carta distancia 'Siga' en el mazo porque hay una carta problema en el mazo.");
                return false;
            }

            return true;
        }

        if (contrariaEnMazo) {
            // Poner la carta distancias en el mazo
            System.out.println("Carta distancia '" + nombreCartaDistancia + "' puesta en el mazo exitosamente.");
            return true;
        } else {
            System.out.println("No se puede poner la carta distancia '" + nombreCartaDistancia + "' en el mazo.");
            return false;
        }

    }

    private static boolean tieneCartaSeguridadSimilar(Jugador jugador, String nombreSeguridad) {
        // Verificar si el jugador ya tiene una carta seguridad similar en su zona
        return jugador.getZona().stream()
                .anyMatch(carta -> carta.getTipo().equals("Seguridad") && carta.getNombre().equals(nombreSeguridad));
    }

    private static boolean tieneCartaProblema(Jugador jugador) {
        return jugador.getZona().stream().anyMatch(carta -> Reglas.obtenerTipoCarta(carta.getNombre()).equals("Problema"));
    }

    private static boolean tieneCartaSiga(Jugador jugador) {
        // Verificar si el jugador ya tiene una carta seguridad similar en su zona
        return jugador.getZona().stream()
                .anyMatch(carta -> carta.getNombre().equals("Siga"));
    }

    public static Jugador seleccionarOtroJugador(List<Jugador> jugadores, Jugador jugadorActual) {
        
        // Mostrar la lista de jugadores disponibles
        System.out.println("Lista de jugadores disponibles:");

        for (Jugador jugador : jugadores) {
            if (jugador.getId() != jugadorActual.getId()) {
                System.out.println(jugador.getId() + ". Jugador " + jugador.getId());
            }
        }

        // Pedir al usuario que elija un jugador por su ID
        System.out.println("Ingresa el ID del jugador al que quieres poner la carta problema:");
        int idJugadorSeleccionado = EventoJugadores.StringNumeroVisual("Ingresa el ID del jugador al que quieres poner la carta problema:");
        

        // Buscar al jugador por su ID
        Jugador jugadorSeleccionado = jugadores.stream()
                .filter(jugador -> jugador.getId() == idJugadorSeleccionado)
                .findFirst()
                .orElse(null);

        if (jugadorSeleccionado == null || jugadorSeleccionado.getId() == jugadorActual.getId()) {
            System.out.println("ID de jugador no válido. Selecciona a otro jugador.");
            return null;
        }

        return jugadorSeleccionado;
    }

    public static String obtenerTipoCarta(String nombre) {
        switch (nombre) {
            case "Sin gasolina":
            case "Pinchazo":
            case "Accidente":
            case "Limite de Vel.":
            case "Pare":
                return "Problema";
            case "Gasolina":
            case "Llanta de repuesto":
            case "Reparacion":
            case "Fin de Limite":
            case "Siga":
                return "Distancia";
            case "Cisterna":
            case "Llanta irrompible":
            case "As de Volante":
            case "Via Libre":
                return "Seguridad";
            case "200":
            case "100":
            case "75":
            case "50":
            case "25":
                return "Solucion";
            default:
                return "ERROR";
        }
    }

    public static String contradiccion(String nombre) {
        switch (nombre) {

            case "Gasolina":
                return "Sin gasolina";
            case "Sin gasolina":
                return "Gasolina";

            case "Llanta de repuesto":
                return "Pinchazo";
            case "Pinchazo":
                return "Llanta de repuesto";

            case "Reparacion":
                return "Accidente";
            case "Accidente":
                return "Reparacion";

            case "Fin de Limite":
                return "Limite de Vel.";
            case "Limite de Vel.":
                return "Fin de Limite";

            case "Siga":
                return "Pare";
            case "Pare":
                return "Siga";

            default:
                return "ERROR";
        }
    }

    public static String buscarCartasSeguridad(String nombre) {
        switch (nombre) {
            case "Sin gasolina":
                return "Cisterna";

            case "Pinchazo":
                return "Llanta irrompible";

            case "Accidente":
                return "As de Volante";

            case "Limite de Vel.":
                return "Via Libre";

            default:
                return "ERROR";
        }
    }

}

// R seguridad
// Siempre se puede poner max 2

// R Solucion
// Sumar si se puede al jugador
// Si hay carta siga se puede poner si no NO
// Restricciones de Problema, como vel max solo se pone de 25 y 50
// Si hay una otro Problema no se puede poner

// R Problema
// Tirar a otro jugador
// Si tiene una seguridad del mismo tipo no se puede poner

// R distancias
// si es siga y no hay Problema se puede poner
// Si es del mismo nombre que Problema se puede poner para solucionarla
