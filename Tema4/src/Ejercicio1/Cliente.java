package Ejercicio1;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 9876;

        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Cliente TCP iniciado. Escribe mensajes para enviar al servidor. Usa '*' para salir.");

            String message;
            while (true) {
                System.out.print("Escribe un mensaje: ");
                message = userInput.readLine();
                out.println(message);

                if (message.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                String response = in.readLine();
                System.out.println("Respuesta del servidor: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
