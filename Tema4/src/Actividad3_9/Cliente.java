package Actividad3_9;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Cliente {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton sendButton, clearButton, exitButton;

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public Cliente() {
        configurarVentana();
        conectarServidor();
    }

    private void configurarVentana() {
        frame = new JFrame("Cliente TCP");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Enviar");
        inputPanel.add(new JLabel("Escribe un mensaje: "), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        clearButton = new JButton("Limpiar");
        exitButton = new JButton("Salir");
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        // Agregar componentes al frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Eventos de los botones
        sendButton.addActionListener(e -> enviarMensaje());
        clearButton.addActionListener(e -> limpiarCampos());
        exitButton.addActionListener(e -> salir());

        frame.setVisible(true);
    }

    private void conectarServidor() {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 44444;
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputArea.append("Conectado al servidor...\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error al conectar con el servidor", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void enviarMensaje() {
        String mensaje = inputField.getText();
        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El campo de mensaje está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            salida.println(mensaje); // Enviar mensaje al servidor
            if (mensaje.equals("*")) {
                outputArea.append("Desconectado del servidor.\n");
                salida.close();
                entrada.close();
                socket.close();
                System.exit(0);
            } else {
                String respuesta = entrada.readLine(); // Leer respuesta del servidor
                outputArea.append("Respuesta: " + respuesta + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        inputField.setText("");
        outputArea.setText("");
    }

    private void salir() {
        inputField.setText("*");
        enviarMensaje(); // Envía "*" para cerrar la conexión
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cliente::new);
    }
}
