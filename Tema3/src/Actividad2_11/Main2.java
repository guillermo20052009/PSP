package Actividad2_11;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Main2 extends JFrame {

    private CarreraCaballosAmpliacion[] caballos;
    private JProgressBar[] barras;
    private JLabel[] etiquetas;
    private Thread[] hilos;

    public Main2() {
        // Configuración de la ventana
        setTitle("Carrera de Caballos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Inicializamos los caballos y sus barras de progreso
        caballos = new CarreraCaballosAmpliacion[4];
        barras = new JProgressBar[4];
        etiquetas = new JLabel[4];
        hilos = new Thread[4];

        for (int i = 0; i < 4; i++) {
            caballos[i] = new CarreraCaballosAmpliacion("Caballo " + (i + 1));
            barras[i] = new JProgressBar(0, 100);
            barras[i].setBounds(100, 50 + i * 50, 400, 30);
            etiquetas[i] = new JLabel("Caballo " + (i + 1));
            etiquetas[i].setBounds(50, 50 + i * 50, 100, 30);
            add(barras[i]);
            add(etiquetas[i]);

            int finalI = i;
            caballos[i].addObserver((observable, arg) -> {
                // Cada vez que un caballo avanza, actualizamos su barra de progreso
                int porcentaje = (int) arg;
                barras[finalI].setValue(porcentaje);
            });

            hilos[i] = new Thread(caballos[i]);
            hilos[i].start();
        }

        // Etiqueta para mostrar el ganador

        JLabel lblGanador = new JLabel("");
        lblGanador.setBounds(350, 300, 100, 30);
        add(lblGanador);

        // Añadimos el KeyListener para controlar la carrera
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_A) {
                    caballos[0].avanzar(); // Aumenta 10% al caballo 1
                } else if (keyCode == KeyEvent.VK_B) {
                    caballos[1].avanzar(); // Aumenta 10% al caballo 2
                } else if (keyCode == KeyEvent.VK_C) {
                    caballos[2].avanzar(); // Aumenta 10% al caballo 3
                } else if (keyCode == KeyEvent.VK_D) {
                    caballos[3].avanzar(); // Aumenta 10% al caballo 4
                }
                // Verificar si algún caballo llegó al 100% y si aún no hay ganador
                for (int i = 0; i < 4; i++) {
                    if (caballos[i].getPorcentaje() >= 100 && lblGanador.getText().equals("")) {
                        lblGanador.setText("¡Caballo " + (i + 1) + " ha ganado!");
                        break; // Detener la verificación si ya hay un ganador
                    }
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {}
        });

        setFocusable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new InterfazMulti();
    }
}


