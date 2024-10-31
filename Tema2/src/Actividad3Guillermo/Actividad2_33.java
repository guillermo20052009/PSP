package Actividad3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_33 extends Applet implements ActionListener {
    // Clase interna que representa un hilo contador
    class HiloContador extends Thread {
        long contador = 0; // Contador interno

        public HiloContador(long contadorInicial) {
            this.contador = contadorInicial; // Inicializa el contador
        }

        @Override
        public void run() {
            try {
                while (true) {
                    contador++; // Incrementa el contador
                    repaint(); // Llama a paint() para actualizar la pantalla
                    Thread.sleep(300); // Duerme el hilo por 300 ms
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }

        public long getContador() {
            return contador; // Retorna el valor del contador
        }
    }

    private HiloContador hilo1; // Hilo contador 1
    private HiloContador hilo2; // Hilo contador 2

    private Font fuente; // Fuente para el texto
    private Button b1, b2; // Botones para finalizar hilos

    @Override
    public void init() {
        setBackground(Color.yellow); // Establece el color de fondo
        setLayout(new FlowLayout()); // Establece el layout

        // Inicializar botones
        b1 = new Button("Finalizar Hilo 1");
        b1.addActionListener(this); // Agrega el listener para el botón
        add(b1); // Añadir botón al applet

        b2 = new Button("Finalizar Hilo 2");
        b2.addActionListener(this);
        add(b2); // Añadir botón al applet

        fuente = new Font("Serif", Font.BOLD, 26); // Define la fuente
    }

    @Override
    public void start() {
        // Iniciar los hilos con valores iniciales
        hilo1 = new HiloContador(0); // Crea el hilo 1
        hilo2 = new HiloContador(0); // Crea el hilo 2
        hilo1.start(); // Inicia el hilo 1
        hilo2.start(); // Inicia el hilo 2
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400); // Limpia el área de dibujo
        g.setFont(fuente); // Establece la fuente para el gráfico
        g.drawString("Hilo1: " + hilo1.getContador(), 20, 50); // Dibuja el contador del hilo 1
        g.drawString("Hilo2: " + hilo2.getContador(), 20, 80); // Dibuja el contador del hilo 2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Manejo de eventos de botones
        if (e.getSource() == b1) {
            if (hilo1 != null && hilo1.isAlive()) {
                hilo1.stop(); // Finaliza el hilo 1
                b1.setLabel("Finalizado Hilo 1"); // Actualiza el texto del botón
            }
        } else if (e.getSource() == b2) {
            if (hilo2 != null && hilo2.isAlive()) {
                hilo2.stop(); // Finaliza el hilo 2
                b2.setLabel("Finalizado Hilo 2"); // Actualiza el texto del botón
            }
        }
    }

    @Override
    public void stop() {
        // Detiene los hilos al parar el applet
        if (hilo1 != null && hilo1.isAlive()) {
            hilo1.stop(); // Finaliza el hilo 1
        }
        if (hilo2 != null && hilo2.isAlive()) {
            hilo2.stop(); // Finaliza el hilo 2
        }
    }
}
