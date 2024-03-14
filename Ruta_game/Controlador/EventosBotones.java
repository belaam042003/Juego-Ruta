package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EventosBotones implements ActionListener {

    private EventoAccion accionListener;

    public EventosBotones(EventoAccion accionListener) {
        this.accionListener = accionListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonPresionado = (JButton) e.getSource();

        if (botonPresionado.getText().equals("Poner Carta")) {
            accionListener.realizarAccionB(1);
        } else if (botonPresionado.getText().equals("Descartar Carta")) {
            accionListener.realizarAccionB(2);
        } else if (botonPresionado.getText().equals("Pasar Turno")) {
            accionListener.realizarAccionB(6);
        }
    }
}