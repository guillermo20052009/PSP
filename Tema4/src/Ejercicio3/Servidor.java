package Ejercicio3;

import java.net.*;
import java.io.*;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final int TAMANO_BUFFER = 256;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en puerto " + PUERTO);

            byte[] buffer = new byte[TAMANO_BUFFER];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

            // Inicialización de los datos ficticios
            Curso curso1 = new Curso("C1", "Programación");
            Curso curso2 = new Curso("C2", "Matemáticas");
            Curso curso3 = new Curso("C3", "Historia");
            Curso curso4 = new Curso("C4", "Física");
            Curso curso5 = new Curso("C5", "Química");

            Alumno[] alumnos = {
                    new Alumno("A1", "Juan Pérez", curso1, 8),
                    new Alumno("A2", "María Gómez", curso2, 7),
                    new Alumno("A3", "Carlos López", curso3, 9),
                    new Alumno("A4", "Lucía Ruiz", curso4, 6),
                    new Alumno("A5", "Ana Sánchez", curso5, 10)
            };

            while (true) {
                socket.receive(paqueteRecibido);
                String idAlumno = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                Alumno alumnoEncontrado = null;
                for (Alumno alumno : alumnos) {
                    if (alumno.getIdAlumno().equals(idAlumno)) {
                        alumnoEncontrado = alumno;
                        break;
                    }
                }

                byte[] respuesta;
                if (alumnoEncontrado != null) {
                    respuesta = (alumnoEncontrado.toString()).getBytes();
                } else {
                    respuesta = ("Alumno no encontrado").getBytes();
                }

                DatagramPacket paqueteRespuesta = new DatagramPacket(respuesta, respuesta.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                socket.send(paqueteRespuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
