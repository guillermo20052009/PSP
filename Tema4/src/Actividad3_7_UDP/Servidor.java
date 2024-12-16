package Actividad3_7_UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080; // Puerto en el que el servidor escucha
        boolean salir = false;

        try (DatagramSocket serverSocket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP escuchando en el puerto " + puerto);

            while (!salir) {
                // Buffer para recibir datos
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket); // Recibir paquete

                // Deserializar el objeto recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numero numero = (Numero) ois.readObject();

                System.out.println("Número recibido: " + numero.getNumero());

                // Si el número es 0, terminar el servidor
                if (numero.getNumero() == 0) {
                    System.out.println("Número recibido es 0. Cerrando servidor.");
                    salir = true;
                } else {
                    // Calcular cuadrado y cubo
                    numero.setCuadrado(numero.getNumero() * numero.getNumero());
                    numero.setCubo(numero.getNumero() * numero.getNumero() * numero.getNumero());
                }

                // Serializar el objeto para enviarlo de vuelta
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numero);
                byte[] sendBuffer = baos.toByteArray();

                // Enviar paquete de vuelta al cliente
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length,
                        receivePacket.getAddress(), receivePacket.getPort()
                );
                serverSocket.send(sendPacket);
                System.out.println("Objeto enviado de vuelta al cliente.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
