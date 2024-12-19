package Actividad3_8;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Cliente UDP que interactúa con un servidor para enviar objetos Persona y recibir respuestas.
 */
public class UDPCliente {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";  // Dirección del servidor
        final int SERVER_PORT = 9876;  // Puerto del servidor
        final int TIMEOUT = 5000;  // Tiempo de espera para recibir respuesta en milisegundos
        boolean stop = false;

        try (DatagramSocket clientSocket = new DatagramSocket();
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            clientSocket.setSoTimeout(TIMEOUT);  // Configurar tiempo de espera para recibir paquetes
            System.out.println("Cliente UDP iniciado. Introduce los datos de una persona para enviarla al servidor.");
            System.out.println("Introduce '*' como nombre para salir.");

            while (!stop) {
                // Ingreso del nombre de la persona
                System.out.print("Nombre: ");
                String nombre = userInput.readLine();

                // Verificar si se quiere salir
                if (nombre.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    stop = true;
                }

                // Ingreso de la edad de la persona
                System.out.print("Edad: ");
                int edad = Integer.parseInt(userInput.readLine());

                // Crear objeto Persona
                Persona persona = new Persona(nombre, edad);

                // Serializar el objeto Persona
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                System.out.println("Mensaje enviado: " + persona.toString());
                oos.writeObject(persona);
                byte[] sendData = baos.toByteArray();  // Convertir objeto en byte array

                // Enviar el paquete al servidor
                InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);  // Enviar paquete al servidor

                // Recibir la respuesta del servidor
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    clientSocket.receive(receivePacket);  // Recibir paquete del servidor

                    // Deserializar el objeto recibido
                    ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Persona receivedPersona = (Persona) ois.readObject();  // Leer objeto deserializado

                    System.out.println("Respuesta del servidor: " + receivedPersona);

                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El paquete se ha perdido.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Muestra el rastreo de la excepción en caso de error
        }
    }
}
