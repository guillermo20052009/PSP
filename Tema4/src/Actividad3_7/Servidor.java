package Actividad3_7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;
        boolean salir=false;// Puerto en el que el servidor escucha

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);
            try (Socket socket = serverSocket.accept();
                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {


                Numero numero;
                do {
                    System.out.println("Cliente conectado.");

                    // Leer el objeto enviado por el cliente
                    numero = (Numero) objectInputStream.readObject();
                    System.out.println("Número recibido: " + numero.getNumero());

                    // Si el número es 0, terminar el bucle
                    if (numero.getNumero() == 0) {
                        System.out.println("Número recibido es 0. Cerrando servidor.");
                        salir=true;
                    }

                    // Calcular cuadrado y cubo
                    numero.setCuadrado(numero.getNumero() * numero.getNumero());
                    numero.setCubo(numero.getNumero() * numero.getNumero() * numero.getNumero());

                    // Enviar el objeto de vuelta al cliente
                    objectOutputStream.writeObject(numero);
                    System.out.println("Objeto enviado de vuelta al cliente.");

            } while (!salir);
            } catch (ClassNotFoundException e) {
                System.err.println("Error al leer el objeto: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error en la conexión: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
