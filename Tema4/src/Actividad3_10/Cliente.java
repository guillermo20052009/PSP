package Actividad3_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Cliente {
    private JFrame frame;
    private JTextField idJugadorField, numeroField;
    private JLabel intentosLabel, estadoJuegoLabel;
    private JButton enviarButton, salirButton;

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    private int intentos = 0;

    public Cliente() {
        configurarVentana();
        conectarServidor();
    }

    private void configurarVentana() {
        frame = new JFrame("JUGADOR - ADIVINA UN NÚMERO");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        // Panel para el ID del jugador y el número de intentos
        JPanel idPanel = new JPanel(new FlowLayout());
        idJugadorField = new JTextField(5);
        idJugadorField.setEditable(false); // Campo no editable
        idJugadorField.setText("1"); // ID de jugador por defecto
        intentosLabel = new JLabel("Intentos: 0");
        idPanel.add(new JLabel("ID JUGADOR:"));
        idPanel.add(idJugadorField);
        idPanel.add(intentosLabel);

        // Panel para ingresar el número a adivinar
        JPanel numeroPanel = new JPanel(new FlowLayout());
        numeroField = new JTextField(5);
        numeroPanel.add(new JLabel("NÚMERO A ADIVINAR:"));
        numeroPanel.add(numeroField);

        // Panel para el botón Enviar
        JPanel enviarPanel = new JPanel();
        enviarButton = new JButton("Enviar");
        enviarPanel.add(enviarButton);

        // Panel para el estado del juego
        JPanel estadoPanel = new JPanel();
        estadoJuegoLabel = new JLabel("Adivina un NÚMERO ENTRE 1 Y 25");
        estadoPanel.add(estadoJuegoLabel);

        // Panel para el botón Salir
        JPanel salirPanel = new JPanel();
        salirButton = new JButton("Salir");
        salirPanel.add(salirButton);

        // Agregar los paneles al frame
        frame.add(idPanel);
        frame.add(numeroPanel);
        frame.add(enviarPanel);
        frame.add(estadoPanel);
        frame.add(salirPanel);

        // Eventos de los botones
        enviarButton.addActionListener(e -> enviarNumero());
        salirButton.addActionListener(e -> salir());

        frame.setVisible(true);
    }

    private void conectarServidor() {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 44444;

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            estadoJuegoLabel.setText("Conectado al servidor. ¡Adivina el número!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error al conectar con el servidor", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void enviarNumero() {
        String numeroTexto = numeroField.getText();

        if (numeroTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingresa un número válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Incrementar intentos y actualizar la etiqueta
            intentos++;
            intentosLabel.setText("Intentos: " + intentos);

            // Enviar el número al servidor
            salida.println(numeroTexto);

            // Recibir la respuesta del servidor
            String respuesta = entrada.readLine();

            // Mostrar la respuesta en la interfaz
            estadoJuegoLabel.setText(respuesta);

            // Si el juego termina, deshabilitar el botón Enviar
            if (respuesta.contains("¡Correcto!")) {
                enviarButton.setEnabled(false);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al comunicarse con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salir() {
        try {
            salida.println("*"); // Enviar mensaje de cierre
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cliente::new);
    }
}
