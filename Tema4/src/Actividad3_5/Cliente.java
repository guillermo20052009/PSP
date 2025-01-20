package Actividad3_5;

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
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream())) {

            System.out.println("Conectado al servidor en " + host + " en el puerto " + puerto);

            // Enviar un mensaje al servidor
            String mensajeEnviado = dis.readUTF().toLowerCase(Locale.ROOT);
            dos.writeUTF(mensajeEnviado);
            System.out.println("Mensaje enviado: " + mensajeEnviado);


            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
