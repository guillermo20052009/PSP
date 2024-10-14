package Actividad3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actividad2_3  extends Applet implements ActionListener {
    class hiloContador extends Thread {
        public hiloContador() {
        }

        @Override
        public void run() {
            parar = false;
            while (!parar) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
                contador++;
            }
        }
    }
    private hiloContador hilo;
    long contador;
    private boolean parar;
    private Font fuente;
    private Button b1,b2;

    @Override
    public void init() {
        setBackground(Color.yellow);
        add(b1=new Button("Iniciar"));
        b1.addActionListener(this);
        add(b2=new Button("Finalizar"));
        b2.addActionListener(this);
        fuente = new Font("Serif", Font.BOLD, 26);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString(Long.toString((long) contador), 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            b1.setLabel("Continuar");
            if (hilo != null && hilo.isAlive()) {
            } else {
                hilo = new hiloContador();
                hilo.start();
            }
        } else if (e.getSource() == b2) {
            parar=true;
        }
    }

    @Override
    public void stop() {
        hilo=null;
    }
}