package pack4_1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.io.File;

/**
 * Clase LocalFTP que se conecta a un servidor FTP, lista archivos y cierra la conexión.
 */
public class LocalFTP {
    public static void main(String[] args) {
        String server = "localhost"; // Dirección del servidor FTP
        int port = 21; // Puerto del servidor FTP (21 es el puerto estándar)
        String user = "uno"; // Nombre de usuario para autenticar en el servidor FTP
        String pass = "uno"; // Contraseña asociada al usuario

        FTPClient ftpClient = new FTPClient(); // Cliente FTP para gestionar la conexión

        try {
            // Conexión al servidor FTP con los datos proporcionados
            ftpClient.connect(server, port);

            // Intentar iniciar sesión con las credenciales proporcionadas
            if (!ftpClient.login(user, pass)) {
                System.out.println("Login fallido."); // Mensaje si la autenticación falla
                return; // Termina la ejecución del programa si el inicio de sesión falla
            }

            System.out.println("Conectado al servidor."); // Confirmación de conexión exitosa

            // Listar los archivos en un directorio específico del servidor FTP
            System.out.println("Listando archivos:");
            var files = ftpClient.listFiles("/home/usuario"); // Ruta del directorio en el servidor
            for (var file : files) {
                System.out.println(file.getName()); // Imprime el nombre de cada archivo/directorio encontrado
            }

            // Cerrar sesión en el servidor FTP
            ftpClient.logout();
            System.out.println("Sesión cerrada correctamente."); // Confirmación de cierre de sesión
        } catch (IOException ex) {
            // Captura y manejo de excepciones de E/S
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace(); // Imprime el stack trace para detalles del error
        } finally {
            try {
                // Verifica si el cliente FTP sigue conectado
                if (ftpClient.isConnected()) {
                    // Cierra la conexión al servidor FTP
                    ftpClient.disconnect();
                    System.out.println("Conexión cerrada."); // Mensaje de confirmación
                }
            } catch (IOException ex) {
                ex.printStackTrace(); // Manejo de error al intentar cerrar la conexión
            }
        }
    }
}
