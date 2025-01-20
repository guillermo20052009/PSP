package Ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        final int PORT = 9876;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor TCP iniciado en el puerto " + PORT);

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Cliente conectado.");
                String receivedMessage;

                while ((receivedMessage = in.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + receivedMessage);

                    if (receivedMessage.equals("*")) {
                        System.out.println("Servidor finalizado.");
                        break;
                    }

                    int length = receivedMessage.length();
                    out.println("Número de caracteres: " + length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
