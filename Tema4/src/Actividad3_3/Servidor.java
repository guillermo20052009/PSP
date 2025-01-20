package Actividad3_3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;  // Puerto en el que el servidor escucha
        int numSockets = 1; // Número de clientes que aceptará el servidor

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            for (int clienteNumero = 1; clienteNumero <= numSockets; clienteNumero++) {
                // Aceptar una conexión de cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente " + clienteNumero + " conectado.");

                // Enviar mensaje al cliente
                try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
                    String mensaje = "Eres el cliente número " + clienteNumero;
                    dataOutputStream.writeUTF(mensaje);
                    System.out.println("Mensaje enviado al cliente " + clienteNumero + ": " + mensaje);
                }

                // Cerrar el socket del cliente
                socket.close();
            }

            System.out.println("Se alcanzó el límite de " + numSockets + " clientes. Cerrando el servidor.");
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
