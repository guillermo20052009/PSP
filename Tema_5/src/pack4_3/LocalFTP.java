package pack4_3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase LocalFTP que proporciona una interfaz gráfica para conectarse a un servidor FTP,
 * listar los archivos disponibles y descargar un archivo seleccionado.
 */
public class LocalFTP {

    // Cliente FTP para gestionar la conexión y transferencia de archivos
    private static FTPClient ftpClient = new FTPClient();

    public static void main(String[] args) throws IOException {

        // Crear ventana principal de la aplicación
        JFrame frame = new JFrame("DESCARGAR FICHEROS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel superior para ingresar usuario y contraseña
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField("uno"); // Campo para ingresar usuario (predefinido "uno")
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField("uno"); // Campo para ingresar contraseña (predefinida "uno")
        JButton connectButton = new JButton("Conectar");
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(new JLabel()); // Espaciador vacío
        loginPanel.add(connectButton);

        frame.add(loginPanel, BorderLayout.NORTH);

        // Lista para mostrar los archivos del servidor y botones de interacción
        DefaultListModel<String> fileListModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(fileListModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        JButton downloadButton = new JButton("Descargar");
        JButton exitButton = new JButton("Salir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(downloadButton);
        buttonPanel.add(exitButton);

        frame.add(scrollPane, BorderLayout.CENTER); // Lista en el centro
        frame.add(buttonPanel, BorderLayout.SOUTH); // Botones en la parte inferior

        // Acción para conectarse al servidor FTP
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = new String(passField.getPassword());
                try {
                    // Conectar al servidor FTP
                    ftpClient.connect("localhost", 21);
                    boolean login = ftpClient.login(user, password);
                    ftpClient.changeWorkingDirectory("/home/usuario");

                    if (login) {
                        JOptionPane.showMessageDialog(frame, "Conexión exitosa.");
                        ftpClient.enterLocalPassiveMode(); // Configurar modo pasivo

                        // Listar archivos del servidor
                        fileListModel.clear();
                        for (String file : ftpClient.listNames()) {
                            fileListModel.addElement(file);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al conectar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para descargar un archivo seleccionado
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = fileList.getSelectedValue();
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(frame, "Seleccione un archivo para descargar.", "Error", JOptionPane.WARNING_MESSAGE);
                    return; // Terminar si no se selecciona un archivo
                }

                // Permitir al usuario seleccionar el directorio de destino
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File directory = fileChooser.getSelectedFile();
                    File localFile = new File(directory, selectedFile); // Ruta de destino local

                    try (FileOutputStream fos = new FileOutputStream(localFile)) {
                        boolean success = ftpClient.retrieveFile(selectedFile, fos); // Descargar archivo
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Archivo descargado correctamente en: " + localFile.getAbsolutePath());
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error al descargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para salir de la aplicación
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout(); // Cerrar sesión
                        ftpClient.disconnect(); // Desconectar del servidor
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(); // Imprimir detalles de cualquier error
                }
                frame.dispose(); // Cerrar la ventana principal
            }
        });

        frame.setVisible(true); // Mostrar la ventana
    }
}
