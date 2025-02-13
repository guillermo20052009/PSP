package pack4_2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Clase LocalFTP que permite seleccionar un archivo local y subirlo a un servidor FTP remoto,
 * mostrando mensajes al usuario mediante cuadros de diálogo.
 */
public class LocalFTP {

    public static void main(String[] args) {
        // Configuración del servidor FTP
        String server = "localhost"; // Dirección del servidor FTP
        int port = 21; // Puerto por defecto para FTP
        String user = "uno"; // Usuario configurado en el servidor FTP
        String password = "uno"; // Contraseña para el usuario
        String remoteDirectory = "/home/usuario"; // Directorio remoto donde se subirá el archivo

        // Inicializar JFileChooser para que el usuario seleccione un archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un fichero para subir al servidor FTP");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); // Archivo seleccionado por el usuario

            // Crear una instancia de FTPClient para gestionar la conexión
            FTPClient ftpClient = new FTPClient();
            try {
                // Conexión al servidor FTP
                ftpClient.connect(server, port);
                boolean login = ftpClient.login(user, password);

                if (login) {
                    ftpClient.enterLocalPassiveMode(); // Activar modo pasivo para evitar problemas de firewall
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // Configurar el modo binario para archivos

                    // Cambiar al directorio remoto, creando uno si no existe
                    if (!ftpClient.changeWorkingDirectory(remoteDirectory)) {
                        if (ftpClient.makeDirectory(remoteDirectory)) { // Crear el directorio si no existe
                            ftpClient.changeWorkingDirectory(remoteDirectory);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al acceder o crear el directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Finalizar si no se puede acceder o crear el directorio
                        }
                    }

                    // Subir el archivo seleccionado al servidor
                    String remoteFile = selectedFile.getName(); // Nombre del archivo remoto
                    try (FileInputStream inputStream = new FileInputStream(selectedFile)) {
                        boolean done = ftpClient.storeFile(remoteFile, inputStream); // Intentar subir el archivo
                        if (done) {
                            JOptionPane.showMessageDialog(null, remoteFile + " se subió correctamente.");

                            // Mostrar el contenido del directorio remoto después de la subida
                            System.out.println("Contenido del directorio actual en el servidor FTP:");
                            for (String file : ftpClient.listNames()) {
                                System.out.println(file); // Imprimir cada archivo/directorio en la consola
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al subir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión en el servidor FTP.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                ftpClient.logout(); // Cerrar sesión en el servidor FTP
            } catch (IOException ex) {
                // Manejo de errores de conexión o de entrada/salida
                JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Imprimir detalles del error en la consola
            } finally {
                try {
                    // Desconectar del servidor si la conexión sigue activa
                    if (ftpClient.isConnected()) {
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(); // Manejo de errores al intentar desconectar
                }
            }
        } else {
            // Mensaje si el usuario no seleccionó ningún archivo
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
    }
}
