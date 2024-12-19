package Actividad3_10;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 44444;
        Random random = new Random();
        int numeroSecreto = random.nextInt(25) + 1;

        System.out.println("Número secreto: " + numeroSecreto);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Conexión establecida con: " + socket.getInetAddress());

                new Thread(() -> {
                    try (
                            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
                    ) {
                        String mensaje;
                        int intentos = 0;

                        while ((mensaje = entrada.readLine()) != null) {
                            if (mensaje.equals("*")) {
                                System.out.println("Cliente desconectado.");
                                break;
                            }

                            intentos++;
                            int numeroCliente = Integer.parseInt(mensaje);

                            if (numeroCliente == numeroSecreto) {
                                salida.println("¡Correcto! Número adivinado en " + intentos + " intentos.");
                                break;
                            } else if (numeroCliente < numeroSecreto) {
                                salida.println("Número demasiado pequeño.");
                            } else {
                                salida.println("Número demasiado grande.");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
