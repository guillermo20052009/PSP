package Actividad4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio8 extends JFrame implements ActionListener {
    private HiloContador hiloPrimero, hiloSegundo;
    private JLabel estadoHiloPrimero, estadoHiloSegundo, contadorHiloPrimero, contadorHiloSegundo;
    private JButton btnIniciarContadores, btnPausarHilo1, btnPausarHilo2, btnDetenerContadores;

    public Ejercicio8() {
        // Configuración de la ventana principal
        setTitle("Gestor de Hilos");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar botones y etiquetas
        inicializarComponentes();

        // Añadir componentes a la ventana
        agregarComponentes();

        // Configurar eventos de botones
        configurarEventos();
    }

    private void inicializarComponentes() {
        // Inicializar botones
        btnIniciarContadores = new JButton("Iniciar Contadores");
        btnPausarHilo1 = new JButton("Pausar Hilo 1");
        btnPausarHilo2 = new JButton("Pausar Hilo 2");
        btnDetenerContadores = new JButton("Detener Contadores");

        // Inicializar etiquetas
        estadoHiloPrimero = new JLabel("Estado: Hilo 1 detenido");
        estadoHiloSegundo = new JLabel("Estado: Hilo 2 detenido");
        contadorHiloPrimero = new JLabel("Contador Hilo 1: 0");
        contadorHiloSegundo = new JLabel("Contador Hilo 2: 0");

        // Inicializar el estado de los botones
        btnPausarHilo1.setEnabled(false);
        btnPausarHilo2.setEnabled(false);
    }

    private void agregarComponentes() {
        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnIniciarContadores);
        panelBotones.add(btnDetenerContadores);
        panelBotones.add(btnPausarHilo1);
        panelBotones.add(btnPausarHilo2);

        // Panel para las etiquetas de estado
        JPanel panelEstado = new JPanel();
        panelEstado.setLayout(new GridLayout(4, 1));
        panelEstado.add(estadoHiloPrimero);
        panelEstado.add(contadorHiloPrimero);
        panelEstado.add(estadoHiloSegundo);
        panelEstado.add(contadorHiloSegundo);

        // Añadir los paneles a la ventana principal
        add(panelBotones, BorderLayout.NORTH);
        add(panelEstado, BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnIniciarContadores.addActionListener(this);
        btnPausarHilo1.addActionListener(this);
        btnPausarHilo2.addActionListener(this);
        btnDetenerContadores.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Controlar eventos de botones
        if (evento.getSource() == btnIniciarContadores) {
            iniciarContadores(); // Inicia los hilos
        } else if (evento.getSource() == btnPausarHilo1) {
            hiloPrimero.pausar(); // Pausa el hilo 1
        } else if (evento.getSource() == btnPausarHilo2) {
            hiloSegundo.pausar(); // Pausa el hilo 2
        } else if (evento.getSource() == btnDetenerContadores) {
            detenerContadores(); // Detiene ambos hilos
        }
    }

    private void iniciarContadores() {
        // Crear e iniciar hilos
        hiloPrimero = new HiloContador("Hilo 1", 1000, contadorHiloPrimero, estadoHiloPrimero);
        hiloSegundo = new HiloContador("Hilo 2", 1500, contadorHiloSegundo, estadoHiloSegundo);

        hiloPrimero.start(); // Iniciar el hilo 1
        hiloSegundo.start(); // Iniciar el hilo 2

        // Desactivar botón de iniciar
        btnIniciarContadores.setEnabled(false);
        btnPausarHilo1.setEnabled(true);
        btnPausarHilo2.setEnabled(true);
    }

    private void detenerContadores() {
        // Detener ambos hilos
        hiloPrimero.detener();
        hiloSegundo.detener();

        // Mostrar los contadores finales
        System.out.println("Contador final Hilo 1: " + hiloPrimero.obtenerValor());
        System.out.println("Contador final Hilo 2: " + hiloSegundo.obtenerValor());

        // Actualizar el estado en la interfaz
        estadoHiloPrimero.setText("Estado: Hilo 1 Finalizado");
        estadoHiloSegundo.setText("Estado: Hilo 2 Finalizado");

        // Desactivar botones de pausa
        btnPausarHilo1.setEnabled(false);
        btnPausarHilo2.setEnabled(false);
    }

    class HiloContador extends Thread {
        private boolean enPausa = false; // Indicador de pausa
        private boolean detenido = false; // Indicador de detención
        private int valorContador = 0; // Valor del contador
        private final int tiempoEspera; // Tiempo de espera para el hilo
        private JLabel etiquetaValor; // Etiqueta para mostrar el contador
        private JLabel estadoHilo; // Etiqueta para mostrar el estado

        public HiloContador(String nombre, int tiempoEspera, JLabel etiquetaValor, JLabel estadoHilo) {
            super(nombre); // Establecer el nombre del hilo
            this.tiempoEspera = tiempoEspera; // Tiempo de espera
            this.etiquetaValor = etiquetaValor; // Etiqueta del contador
            this.estadoHilo = estadoHilo; // Etiqueta del estado
        }

        public synchronized void pausar() {
            enPausa = true; // Cambiar a estado de pausa
            estadoHilo.setText(getName() + " en Pausa"); // Actualizar estado
        }

        public synchronized void reanudar() {
            enPausa = false; // Cambiar a estado de ejecución
            estadoHilo.setText(getName() + " en Ejecución"); // Actualizar estado
            notify(); // Notificar al hilo
        }

        public void detener() {
            detenido = true; // Cambiar a estado de detenido
            interrupt(); // Interrumpir el hilo
        }

        public int obtenerValor() {
            return valorContador; // Retornar el valor actual
        }

        @Override
        public void run() {
            try {
                while (!detenido) { // Mientras no esté detenido
                    synchronized (this) {
                        while (enPausa) { // Esperar si está en pausa
                            wait(); // Esperar reanudación
                        }
                    }
                    valorContador++; // Incrementar el contador
                    etiquetaValor.setText("Contador Hilo: " + valorContador); // Actualizar etiqueta
                    Thread.sleep(tiempoEspera); // Dormir el hilo
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpido."); // Mensaje si el hilo es interrumpido
            }
            estadoHilo.setText(getName() + " Finalizado"); // Actualizar estado al finalizar
        }
    }

    public static void main(String[] args) {
        Ejercicio8 ventana = new Ejercicio8(); // Crear instancia de la ventana
        ventana.setVisible(true); // Hacer visible la ventana
    }
}