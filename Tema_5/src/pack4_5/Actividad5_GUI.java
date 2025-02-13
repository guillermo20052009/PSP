package pack4_5;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

public class Actividad5_GUI {
    private JFrame frame;
    private JTextField servidorField, puertoField, usuarioField;
    private JPasswordField claveField;
    private JButton conectarButton, recuperarButton, desconectarButton;
    private JList<String> mensajesList;
    private DefaultListModel<String> listModel;
    private JTextArea mensajeArea;
    private POP3Client clientePOP;

    public Actividad5_GUI() {
        frame = new JFrame("Cliente POP3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel panelConexion = new JPanel(new GridLayout(5, 2));
        panelConexion.add(new JLabel("Servidor: "));
        servidorField = new JTextField("localhost");
        panelConexion.add(servidorField);

        panelConexion.add(new JLabel("Puerto: "));
        puertoField = new JTextField("110");
        panelConexion.add(puertoField);

        panelConexion.add(new JLabel("Usuario: "));
        usuarioField = new JTextField();
        panelConexion.add(usuarioField);

        panelConexion.add(new JLabel("Contraseña: "));
        claveField = new JPasswordField();
        panelConexion.add(claveField);

        conectarButton = new JButton("Conectar");
        recuperarButton = new JButton("Recuperar mensajes");
        desconectarButton = new JButton("Desconectar");
        recuperarButton.setEnabled(false);
        desconectarButton.setEnabled(false);

        panelConexion.add(conectarButton);
        panelConexion.add(desconectarButton);

        frame.add(panelConexion, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        mensajesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(mensajesList);
        frame.add(scrollPane, BorderLayout.CENTER);

        mensajeArea = new JTextArea();
        mensajeArea.setEditable(false);
        frame.add(new JScrollPane(mensajeArea), BorderLayout.SOUTH);

        conectarButton.addActionListener(e -> conectar());
        recuperarButton.addActionListener(e -> recuperarMensajes());
        desconectarButton.addActionListener(e -> desconectar());
        mensajesList.addListSelectionListener(e -> mostrarMensaje());

        frame.setVisible(true);
    }

    private void conectar() {
        try {
            clientePOP = new POP3Client();
            clientePOP.connect(servidorField.getText(), Integer.parseInt(puertoField.getText()));
            if (!clientePOP.login(usuarioField.getText(), new String(claveField.getPassword()))) {
                JOptionPane.showMessageDialog(frame, "Error de autenticación", "Error", JOptionPane.ERROR_MESSAGE);
                clientePOP.disconnect();
                return;
            }
            JOptionPane.showMessageDialog(frame, "Conectado exitosamente");
            conectarButton.setEnabled(false);
            recuperarButton.setEnabled(true);
            desconectarButton.setEnabled(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recuperarMensajes() {
        try {
            POP3MessageInfo[] mensajes = clientePOP.listMessages();
            listModel.clear();
            if (mensajes == null || mensajes.length == 0) {
                JOptionPane.showMessageDialog(frame, "No hay correos en la bandeja");
            } else {
                for (POP3MessageInfo mensaje : mensajes) {
                    listModel.addElement("Mensaje " + mensaje.number);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al recuperar mensajes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMensaje() {
        int index = mensajesList.getSelectedIndex();
        if (index == -1) return;
        try {
            Reader reader = clientePOP.retrieveMessage(index + 1);
            if (reader != null) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                mensajeArea.setText(contenido.toString());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al leer mensaje", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desconectar() {
        try {
            clientePOP.logout();
            clientePOP.disconnect();
            conectarButton.setEnabled(true);
            recuperarButton.setEnabled(false);
            desconectarButton.setEnabled(false);
            listModel.clear();
            mensajeArea.setText("");
            JOptionPane.showMessageDialog(frame, "Desconectado exitosamente");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al desconectar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Actividad5_GUI::new);
    }
}
