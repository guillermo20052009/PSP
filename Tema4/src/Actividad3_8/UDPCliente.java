package Actividad3_8;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPCliente {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 9876;
        final int TIMEOUT = 5000;
        boolean stop = false;

        try (DatagramSocket clientSocket = new DatagramSocket();
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            clientSocket.setSoTimeout(TIMEOUT);
            System.out.println("Cliente UDP iniciado. Introduce los datos de una persona para enviarla al servidor.");
            System.out.println("Introduce '*' como nombre para salir.");

            while (!stop) {
                System.out.print("Nombre: ");
                String nombre = userInput.readLine();

                if (nombre.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    stop = true;
                }

                System.out.print("Edad: ");
                int edad = Integer.parseInt(userInput.readLine());

                // Crear objeto Persona
                Persona persona = new Persona(nombre, edad);

                // Serializar el objeto Persona
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                System.out.println("Mensaje enviado: " + persona.toString());
                oos.writeObject(persona);
                byte[] sendData = baos.toByteArray();

                // Enviar el paquete al servidor
                InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                // Recibir la respuesta del servidor
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    clientSocket.receive(receivePacket);

                    // Deserializar el objeto recibido
                    ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Persona receivedPersona = (Persona) ois.readObject();

                    System.out.println("Respuesta del servidor: " + receivedPersona);

                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El paquete se ha perdido.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
