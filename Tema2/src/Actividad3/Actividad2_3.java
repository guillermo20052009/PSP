package Actividad3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_3 extends Applet implements ActionListener {
    class HiloContador extends Thread {
        private boolean parar;
        private boolean pausado;
        long contador=0;

        public HiloContador() {
            parar = false;
            pausado = false;
        }

        @Override
        public void run() {
            while (!parar) {
                synchronized (this) {
                    while (pausado) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
                repaint();
            }
        }

        public synchronized void pausar() {
            pausado = true;
        }

        public synchronized void reanudar() {
            pausado = false;
            notify();
        }

        public void detener() {
            parar = true;
        }

        public long getContador() {
            return contador;
        }

        public boolean isPausado() {
            return pausado;
        }
    }

    private HiloContador hilo1;
    private HiloContador hilo2;

    private Font fuente;
    private Button b1, b2;

    @Override
    public void init() {
        setBackground(Color.yellow);
        setLayout(new FlowLayout());

        // Inicializar botones
        b1 = new Button("Finalizar hilo 1");
        b1.addActionListener(this);
        add(b1);

        b2 = new Button("Finalizar hilo 2");
        b2.addActionListener(this);
        add(b2);

        fuente = new Font("Serif", Font.BOLD, 26);

        // Iniciar los hilos
        hilo1 = new HiloContador();
        hilo2 = new HiloContador();
        hilo1.start();
        hilo2.start();
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString("Contador Hilo 1: " + hilo1.contador, 20, 50);
        g.drawString("Contador Hilo 2: " + hilo2.contador, 20, 80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (!hilo1.isPausado()) {
                hilo1.pausar();
                b1.setLabel("Reanudar hilo 1");
            } else {
                hilo1.reanudar();
                b1.setLabel("Finalizar hilo 1");
            }
        } else if (e.getSource() == b2) {
            if (!hilo2.isPausado()) {
                hilo2.pausar();
                b2.setLabel("Reanudar hilo 2");
            } else {
                hilo2.reanudar();
                b2.setLabel("Finalizar hilo 2");
            }
        }
    }

    @Override
    public void stop() {
        if (hilo1 != null) {
            hilo1.detener();
        }
        if (hilo2 != null) {
            hilo2.detener();
        }
        hilo1 = null;
        hilo2 = null;
    }
}
