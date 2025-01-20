package Actividad3_7;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 8080;
        boolean salir=false;// Puerto del servidor

        try (Socket socket = new Socket(host, puerto);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            Numero numero;

            do {
                numero = new Numero();
                System.out.print("Introduce un número (0 para salir): ");
                numero.setNumero(sc.nextInt());

                // Enviar número al servidor
                objectOutputStream.writeObject(numero);

                // Si el número es 0, finalizar el bucle
                if (numero.getNumero() == 0) {
                    System.out.println("Finalizando cliente...");
                    salir=true;
                }

                // Leer respuesta del servidor
                numero = (Numero) objectInputStream.readObject();
                System.out.println("Respuesta del servidor: " + numero);

            } while (!salir);

        } catch (UnknownHostException e) {
            System.err.println("Error: Servidor no encontrado.");
        }  catch (ClassNotFoundException e) {
            System.err.println("Error al leer el objeto recibido: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
