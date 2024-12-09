package Actividad3_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPCliente {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 9876;
        final int TIMEOUT = 5000;

        try (DatagramSocket clientSocket = new DatagramSocket();
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            clientSocket.setSoTimeout(TIMEOUT);
            System.out.println("Cliente UDP iniciado. Escribe mensajes para enviar al servidor. Usa '*' para salir.");

            while (true) {
                System.out.print("Escribe un mensaje: ");
                String message = userInput.readLine();

                byte[] sendData = message.getBytes();
                InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                if (message.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    clientSocket.receive(receivePacket);
                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Respuesta del servidor: " + receivedMessage);
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El paquete se ha perdido.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

