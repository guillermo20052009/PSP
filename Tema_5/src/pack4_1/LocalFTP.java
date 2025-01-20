package pack4_1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.io.File;

public class LocalFTP {
    public static void main(String[] args) {
        String server = "localhost"; // Servidor FTP
        int port = 21; // Puerto FTP por defecto
        String user = "uno"; // Usuario configurado
        String pass = "uno"; // Contraseña configurada

        FTPClient ftpClient = new FTPClient();

        try {
            // Conexión al servidor FTP
            ftpClient.connect(server, port);
            if (!ftpClient.login(user, pass)) {
                System.out.println("Login fallido.");
                return;
            }

            System.out.println("Conectado al servidor.");

            // Listar los archivos y directorios en el directorio raíz
            System.out.println("Listando archivos:");
            var files = ftpClient.listFiles("/home/usuario");
            for (var file : files) {
                System.out.println(file.getName());
            }

            // Cerrar sesión
            ftpClient.logout();
            System.out.println("Sesión cerrada correctamente.");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                    System.out.println("Conexión cerrada.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
