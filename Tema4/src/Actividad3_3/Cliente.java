package Actividad3_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 8080;         // Puerto del servidor

        try (Socket socket = new Socket(host, puerto);
             DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            System.out.println("Conectado al servidor en " + host + " en el puerto " + puerto);
            System.out.println("Mensaje recibido: "+dis.readUTF());;
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
