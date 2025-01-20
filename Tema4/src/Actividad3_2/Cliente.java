package Actividad3_2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";  // Dirección del servidor (localhost en este caso)
        int puerto = 8080;  // Puerto en el que el servidor escucha

        try {
            // Crear el socket y conectar al servidor
            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor en " + host + " en el puerto " + puerto);

            // Obtener y mostrar información sobre la conexión
            InetAddress address = socket.getInetAddress();
            System.out.println("Información del cliente:");
            System.out.println("Puerto local: " + socket.getLocalPort());
            System.out.println("Puerto remoto: " + socket.getPort());
            System.out.println("Dirección IP: " + socket.getInetAddress());
            System.out.println("Nombre de host: " + address.getHostName());
            System.out.println("Dirección IP del cliente: " + address.getHostAddress());

            // Cerrar la conexión
            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
