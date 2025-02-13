package pack4_1;

import org.apache.commons.net.ftp.*;
import java.io.IOException;

/**
 * Clase RemotoFTP que permite conectarse a un servidor FTP remoto, listar archivos
 * y directorios, y finalizar la conexión.
 */
public class RemotoFTP {

    public static void main(String[] args) {
        String server = "ftp.rediris.es"; // Dirección del servidor FTP
        int port = 21; // Puerto por defecto del protocolo FTP
        String user = "anonymous"; // Usuario para conexión anónima
        String pass = ""; // Contraseña vacía para usuarios anónimos

        FTPClient ftpClient = new FTPClient(); // Cliente FTP para gestionar la conexión

        try {
            // Establecer conexión al servidor FTP
            ftpClient.connect(server, port);

            // Verificar el código de respuesta del servidor tras la conexión
            int replyCode = ftpClient.getReplyCode();
            if (!ftpClient.login(user, pass)) {
                System.out.println("Login fallido."); // Mensaje de error en caso de fallo de autenticación
                return; // Finalizar ejecución si no se inicia sesión
            }

            System.out.println("Conectado al servidor FTP."); // Confirmación de conexión exitosa

            // Configurar el cliente FTP en modo pasivo y tipo de archivo binario
            ftpClient.enterLocalPassiveMode(); // Modo pasivo para evitar problemas con firewalls/NAT
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // Tipo binario para manejar cualquier archivo

            // Listar los archivos y directorios del directorio raíz del servidor
            System.out.println("Listando directorios y archivos:");
            FTPFile[] files = ftpClient.listFiles("/"); // Obtener lista de archivos/directorios en la raíz
            if (files != null && files.length > 0) {
                for (FTPFile file : files) {
                    System.out.println(file.getName()); // Imprimir el nombre de cada archivo/directorio
                }
            } else {
                System.out.println("No se encontraron archivos o directorios."); // Mensaje si la lista está vacía
            }

            // Cerrar sesión del servidor FTP
            ftpClient.logout();
            // Desconectar del servidor
            ftpClient.disconnect();
            System.out.println("Desconectado del servidor FTP."); // Confirmación de desconexión exitosa
        } catch (IOException ex) {
            // Manejo de excepciones de entrada/salida
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace(); // Mostrar detalles del error en la consola
        }
    }
}
