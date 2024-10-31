package Actividad5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_5 extends Applet implements ActionListener {
    // Clase interna para el hilo del contador
    class HiloContador extends Thread {
        private boolean parar; // Controla si el hilo debe detenerse
        private boolean pausado; // Controla si el hilo está en pausa
        long contador = 0; // Contador del hilo

        public HiloContador() {
            parar = false; // Inicializa el hilo sin detener
            pausado = false; // Inicializa el hilo sin pausa
        }

        @Override
        public void run() {
            // Ejecuta el ciclo mientras no se indique detener el hilo
            while (!parar) {
                synchronized (this) {
                    // Pausa el hilo mientras el estado pausado esté activado
                    while (pausado) {
                        try {
                            wait(); // Espera hasta que se reanude el hilo
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(300); // Pausa el hilo por 300 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++; // Incrementa el contador
                repaint(); // Llama a repaint para actualizar el contador en pantalla
            }
        }

        public synchronized void pausar() {
            pausado = true; // Activa el estado pausado
        }

        public synchronized void reanudar() {
            pausado = false; // Desactiva el estado pausado
            notify(); // Notifica para reanudar el hilo
        }

        public void detener() {
            parar = true; // Indica que el hilo debe detenerse
        }

        public long getContador() {
            return contador; // Retorna el valor actual del contador
        }

        public boolean isPausado() {
            return pausado; // Verifica si el hilo está en pausa
        }
    }

    private HiloContador hilo1; // Instancia del primer hilo contador
    private HiloContador hilo2; // Instancia del segundo hilo contador

    private Font fuente; // Fuente de texto para mostrar los contadores
    private Button b1, b2; // Botones para pausar/reanudar los hilos

    @Override
    public void init() {
        setBackground(Color.yellow); // Establece el color de fondo
        setLayout(new FlowLayout()); // Define el layout

        // Inicializar botones
        b1 = new Button("Finalizar hilo 1"); // Botón para el hilo 1
        b1.addActionListener(this); // Añade ActionListener al botón
        add(b1); // Añade el botón al applet

        b2 = new Button("Finalizar hilo 2"); // Botón para el hilo 2
        b2.addActionListener(this); // Añade ActionListener al botón
        add(b2); // Añade el botón al applet

        fuente = new Font("Serif", Font.BOLD, 26); // Define la fuente para los textos

        // Iniciar los hilos
        hilo1 = new HiloContador(); // Crea y asigna el primer hilo
        hilo2 = new HiloContador(); // Crea y asigna el segundo hilo
        hilo1.start(); // Inicia el primer hilo
        hilo2.start(); // Inicia el segundo hilo
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400); // Limpia el área de dibujo
        g.setFont(fuente); // Establece la fuente
        g.drawString("Contador Hilo 1: " + hilo1.contador, 20, 50); // Muestra el contador del hilo 1
        g.drawString("Contador Hilo 2: " + hilo2.contador, 20, 80); // Muestra el contador del hilo 2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica si el evento proviene del botón del hilo 1
        if (e.getSource() == b1) {
            if (!hilo1.isPausado()) { // Si el hilo no está en pausa
                hilo1.pausar(); // Pone en pausa el hilo
                b1.setLabel("Reanudar hilo 1"); // Cambia el texto del botón
            } else {
                hilo1.reanudar(); // Reanuda el hilo
                b1.setLabel("Finalizar hilo 1"); // Cambia el texto del botón
            }
        } else if (e.getSource() == b2) {
            if (!hilo2.isPausado()) { // Si el hilo no está en pausa
                hilo2.pausar(); // Pone en pausa el hilo
                b2.setLabel("Reanudar hilo 2"); // Cambia el texto del botón
            } else {
                hilo2.reanudar(); // Reanuda el hilo
                b2.setLabel("Finalizar hilo 2"); // Cambia el texto del botón
            }
        }
    }

    @Override
    public void stop() {
        if (hilo1 != null) {
            hilo1.detener(); // Detiene el primer hilo
        }
        if (hilo2 != null) {
            hilo2.detener(); // Detiene el segundo hilo
        }
        hilo1 = null; // Libera la referencia del primer hilo
        hilo2 = null; // Libera la referencia del segundo hilo
    }
}

