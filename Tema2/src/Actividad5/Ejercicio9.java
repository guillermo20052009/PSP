package Actividad5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio9 extends Applet implements ActionListener {
    // Clase interna para el hilo del contador
    class HiloContador extends Thread {
        private boolean parar; // Controla si el hilo debe detenerse
        private boolean pausado; // Controla si el hilo está en pausa
        private long contador = 0; // Contador del hilo
        private final String nombre; // Nombre del hilo para identificarlo
        private final int tiempoEspera; // Tiempo de espera entre incrementos

        public HiloContador(String nombre, int tiempoEspera) {
            this.nombre = nombre; // Asigna el nombre del hilo
            this.tiempoEspera = tiempoEspera; // Asigna el tiempo de espera entre incrementos
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
                    Thread.sleep(tiempoEspera); // Pausa el hilo por el tiempo especificado
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

        public String getEstado() {
            return pausado ? "Pausado" : "Ejecutándose"; // Retorna el estado del hilo
        }
    }

    private HiloContador hilo1; // Instancia del primer hilo contador
    private HiloContador hilo2; // Instancia del segundo hilo contador

    private Font fuente; // Fuente de texto para mostrar los contadores
    private Button b1, b2, finalizarAmbosButton; // Botones de control

    @Override
    public void init() {
        setBackground(Color.yellow); // Establece el color de fondo
        setLayout(new FlowLayout()); // Define el layout

        // Inicializar botones
        b1 = new Button("Pausar/Reanudar Hilo 1"); // Botón para pausar/reanudar el hilo 1
        b1.addActionListener(this); // Añade ActionListener al botón
        add(b1); // Añade el botón al applet

        b2 = new Button("Pausar/Reanudar Hilo 2"); // Botón para pausar/reanudar el hilo 2
        b2.addActionListener(this); // Añade ActionListener al botón
        add(b2); // Añade el botón al applet

        finalizarAmbosButton = new Button("Finalizar Ambos Hilos"); // Botón para finalizar ambos hilos
        finalizarAmbosButton.addActionListener(this); // Añade ActionListener al botón
        add(finalizarAmbosButton); // Añade el botón al applet

        fuente = new Font("Serif", Font.BOLD, 26); // Define la fuente para los textos

        // Iniciar los hilos con nombre y tiempo de espera específico
        hilo1 = new HiloContador("Hilo 1", 300); // Crea y asigna el primer hilo
        hilo2 = new HiloContador("Hilo 2", 300); // Crea y asigna el segundo hilo
        hilo1.start(); // Inicia el primer hilo
        hilo2.start(); // Inicia el segundo hilo
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400); // Limpia el área de dibujo
        g.setFont(fuente); // Establece la fuente

        // Muestra el contador y el estado del primer hilo
        g.drawString("Contador Hilo 1: " + hilo1.getContador() + " (" + hilo1.getEstado() + ")", 20, 150);
        // Muestra el contador y el estado del segundo hilo
        g.drawString("Contador Hilo 2: " + hilo2.getContador() + " (" + hilo2.getEstado() + ")", 20, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Si se presiona el botón del hilo 1
            if (!hilo1.isPausado()) {
                hilo1.pausar(); // Pausa el hilo 1
            } else {
                hilo1.reanudar(); // Reanuda el hilo 1
            }
        } else if (e.getSource() == b2) { // Si se presiona el botón del hilo 2
            if (!hilo2.isPausado()) {
                hilo2.pausar(); // Pausa el hilo 2
            } else {
                hilo2.reanudar(); // Reanuda el hilo 2
            }
        } else if (e.getSource() == finalizarAmbosButton) { // Si se presiona el botón para finalizar ambos hilos
            hilo1.detener(); // Detiene el primer hilo
            hilo2.detener(); // Detiene el segundo hilo
            repaint(); // Llama a repaint para actualizar la interfaz
            // Muestra el valor final de los contadores en consola
            System.out.println("Valor final del contador Hilo 1: " + hilo1.getContador());
            System.out.println("Valor final del contador Hilo 2: " + hilo2.getContador());
        }
    }

    @Override
    public void stop() {
        if (hilo1 != null) {
            hilo1.detener(); // Detiene el primer hilo si está activo
        }
        if (hilo2 != null) {
            hilo2.detener(); // Detiene el segundo hilo si está activo
        }
        hilo1 = null; // Libera la referencia del primer hilo
        hilo2 = null; // Libera la referencia del segundo hilo
    }
}
