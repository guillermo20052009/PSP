package Actividad3_7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;  // Puerto en el que el servidor escucha

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Aceptar el cliente
            try (Socket socket = serverSocket.accept();
                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

                System.out.println("Cliente conectado.");

                // Leer el objeto enviado por el cliente
                Numero numero = (Numero) objectInputStream.readObject();

                // Calcular cuadrado y cubo
                numero.setCuadrado(numero.getNumero() * numero.getNumero());
                numero.setCubo(numero.getNumero() * numero.getNumero() * numero.getNumero());

                // Enviar el objeto de vuelta al cliente
                objectOutputStream.writeObject(numero);

            } catch (ClassNotFoundException e) {
                System.err.println("Error al leer el objeto: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}