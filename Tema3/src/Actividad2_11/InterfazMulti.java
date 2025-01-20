package Actividad2_11;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class InterfazMulti extends JFrame implements Observer {

    private CarreraCaballosAmpliacion[] caballos;
    private Thread[] hilos;
    private JProgressBar[] barrasProgreso;
    private JLabel lblGanador;
    private int cont=0;

    public InterfazMulti() {
        initComponents();
        caballos = new CarreraCaballosAmpliacion[4];
        hilos = new Thread[4];

        // Crear caballos y sus hilos
        for (int i = 0; i < caballos.length; i++) {
            caballos[i] = new CarreraCaballosAmpliacion("Caballo " + (i + 1));
            caballos[i].addObserver(this);
            hilos[i] = new Thread(caballos[i]);
            hilos[i].start();
        }
    }

    private void initComponents() {
        setTitle("Carrera Multijugador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);

        barrasProgreso = new JProgressBar[4];
        lblGanador = new JLabel();
        lblGanador.setBounds(200, 300, 200, 30);
        add(lblGanador);

        // Crear barras de progreso y etiquetas
        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel("Caballo " + (i + 1) + " (Tecla: " + (char) ('A' + i) + ")");
            label.setBounds(20, 50 + i * 50, 150, 30);
            add(label);

            barrasProgreso[i] = new JProgressBar(0, 100);
            barrasProgreso[i].setBounds(150, 50 + i * 50, 400, 30);
            add(barrasProgreso[i]);
        }

        // Añadir el evento de teclado
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                char tecla = e.getKeyChar();
                int index = tecla - 'a'; // Calcula el índice según la tecla presionada

                if (index >= 0 && index < caballos.length) {
                    caballos[index].avanzar(); // Avanzar el caballo correspondiente
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        CarreraCaballosAmpliacion caballo = (CarreraCaballosAmpliacion) o;
        int progreso = (int) arg;

        // Actualizar la barra de progreso correspondiente
        for (int i = 0; i < caballos.length; i++) {
            if (caballo == caballos[i]) {
                barrasProgreso[i].setValue(progreso);
                break;
            }
        }

        // Verificar si hay un ganador
        if (progreso >= 100 && cont==0) {
            lblGanador.setText("Ganador: " + caballo.getNombre());
            terminarCarrera();
            cont++;
        }
    }

    private void terminarCarrera() {
        for (Thread hilo : hilos) {
            hilo.interrupt(); // Detener todos los hilos
        }
    }

    public static void main(String[] args) {
        new CarreraCaballosAmpliacion("Carrera Multijugador");
    }
}

