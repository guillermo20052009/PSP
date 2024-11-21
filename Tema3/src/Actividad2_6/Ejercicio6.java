package Actividad2_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio6 extends JFrame {

    private JSlider sliderHilo1, sliderHilo2, sliderHilo3;
    private JTextField prioridadHilo1, prioridadHilo2, prioridadHilo3;
    private JButton comenzarButton;
    private int prioridad1 = 5, prioridad2 = 5, prioridad3 = 5;
    private JLabel ganadorLabel;

    // Variables para las posiciones de los hilos
    private int x1 = 0, x2 = 0, x3 = 0;

    public Ejercicio6() {
        // Configuración de la ventana
        setTitle("USANDO PRIORIDADES. CARRERA DE HILOS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Título de la ventana
        JLabel titleLabel = new JLabel("Carrera de Hilos", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel);

        // Panel de Hilo 1
        JPanel panelHilo1 = new JPanel();
        panelHilo1.setLayout(new FlowLayout());
        panelHilo1.add(new JLabel("HILO 1"));
        sliderHilo1 = new JSlider(1, 10, 5);
        sliderHilo1.setMajorTickSpacing(1);
        sliderHilo1.setPaintTicks(true);
        sliderHilo1.setPaintLabels(true);
        panelHilo1.add(sliderHilo1);
        prioridadHilo1 = new JTextField("Prioridad: 5", 10);
        prioridadHilo1.setEditable(false);
        panelHilo1.add(prioridadHilo1);
        add(panelHilo1);

        // Panel de Hilo 2
        JPanel panelHilo2 = new JPanel();
        panelHilo2.setLayout(new FlowLayout());
        panelHilo2.add(new JLabel("HILO 2"));
        sliderHilo2 = new JSlider(1, 10, 5);
        sliderHilo2.setMajorTickSpacing(1);
        sliderHilo2.setPaintTicks(true);
        sliderHilo2.setPaintLabels(true);
        panelHilo2.add(sliderHilo2);
        prioridadHilo2 = new JTextField("Prioridad: 5", 10);
        prioridadHilo2.setEditable(false);
        panelHilo2.add(prioridadHilo2);
        add(panelHilo2);

        // Panel de Hilo 3
        JPanel panelHilo3 = new JPanel();
        panelHilo3.setLayout(new FlowLayout());
        panelHilo3.add(new JLabel("HILO 3"));
        sliderHilo3 = new JSlider(1, 10, 5);
        sliderHilo3.setMajorTickSpacing(1);
        sliderHilo3.setPaintTicks(true);
        sliderHilo3.setPaintLabels(true);
        panelHilo3.add(sliderHilo3);
        prioridadHilo3 = new JTextField("Prioridad: 5", 10);
        prioridadHilo3.setEditable(false);
        panelHilo3.add(prioridadHilo3);
        add(panelHilo3);

        // Panel de Ganador
        ganadorLabel = new JLabel("Ganador: Ninguno", JLabel.CENTER);
        ganadorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(ganadorLabel);

        // Botón para comenzar la carrera
        comenzarButton = new JButton("Comenzar Carrera");
        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prioridad1 = sliderHilo1.getValue();
                prioridad2 = sliderHilo2.getValue();
                prioridad3 = sliderHilo3.getValue();

                // Actualizar la prioridad en los campos de texto
                prioridadHilo1.setText("Prioridad: " + prioridad1);
                prioridadHilo2.setText("Prioridad: " + prioridad2);
                prioridadHilo3.setText("Prioridad: " + prioridad3);

                // Iniciar la carrera
                comenzarCarrera();
            }
        });
        add(comenzarButton);

        setVisible(true);
    }

    private void comenzarCarrera() {
        // Iniciar los hilos con las prioridades seleccionadas
        Thread hilo1 = new Thread(new Corredor(1, prioridad1));
        Thread hilo2 = new Thread(new Corredor(2, prioridad2));
        Thread hilo3 = new Thread(new Corredor(3, prioridad3));

        hilo1.setPriority(prioridad1);
        hilo2.setPriority(prioridad2);
        hilo3.setPriority(prioridad3);

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }

    // Clase que representa un corredor (hilo) en la carrera
    private class Corredor implements Runnable {
        private int id;
        private int prioridad;

        public Corredor(int id, int prioridad) {
            this.id = id;
            this.prioridad = prioridad;
        }

        @Override
        public void run() {
            while (x1 < getWidth() - 50 && x2 < getWidth() - 50 && x3 < getWidth() - 50) {
                try {
                    if (id == 1) {
                        x1 += 1 + prioridad; // Aumenta la velocidad según la prioridad
                    } else if (id == 2) {
                        x2 += 1 + prioridad;
                    } else if (id == 3) {
                        x3 += 1 + prioridad;
                    }
                    repaint(); // Actualiza la ventana

                    Thread.sleep(50); // Simula el paso del tiempo para la carrera

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Cuando un hilo llega al final, mostrar quién ha ganado
            if (x1 >= getWidth() - 50 && ganadorLabel.getText().equals("Ganador: Ninguno")) {
                ganadorLabel.setText("Ganador: Hilo 1");
            } else if (x2 >= getWidth() - 50 && ganadorLabel.getText().equals("Ganador: Ninguno")) {
                ganadorLabel.setText("Ganador: Hilo 2");
            } else if (x3 >= getWidth() - 50 && ganadorLabel.getText().equals("Ganador: Ninguno")) {
                ganadorLabel.setText("Ganador: Hilo 3");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ejercicio6();
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        // Dibuja los números de los hilos en sus respectivas posiciones
        g.drawString("Hilo 1", x1, 50);
        g.drawString("Hilo 2", x2, 100);
        g.drawString("Hilo 3", x3, 150);
    }
}
