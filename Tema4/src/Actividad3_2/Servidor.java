package Actividad3_2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;  // Puerto en el que el servidor escucha

        try {
            // Crea el ServerSocket y empieza a escuchar el puerto
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Aceptar dos clientes
            Socket socket = serverSocket.accept();  // Acepta la primera conexión de cliente
            System.out.println("Primer cliente conectado.");

            Socket socket1 = serverSocket.accept(); // Acepta la segunda conexión de cliente
            System.out.println("Segundo cliente conectado.");

            serverSocket.close();  // Cerrar el servidor
            System.out.println("Servidor cerrado.");

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

