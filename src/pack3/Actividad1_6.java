package pack3;

import java.io.*;

public class Actividad1_6 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("/home/usuario/Escritorio/Tema1PSP/out/production/Tema1PSP");

        // Invocar la clase Numero correctamente
        ProcessBuilder p = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "pack3.Numero");
        p.directory(fichero);
        Process p2 = p.start();

        // Enviar datos al proceso
        try (OutputStream o1 = p2.getOutputStream()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Introduce el primer numero:");
                String num1 = br.readLine(); // Leer el primer número del usuario

                System.out.println("Introduce el segundo numero:");
                String num2 = br.readLine(); // Leer el segundo número del usuario

                // Escribir los números al proceso "Numero"
                o1.write((num1 + "\n").getBytes()); // Escribir el primer número
                o1.flush(); // Asegurar que los datos se envíen completamente

                o1.write((num2 + "\n").getBytes()); // Escribir el segundo número
                o1.flush(); // Asegurar que los datos se envíen completamente
            }
        }
        // Con este bloque de codigo extraigo la salida del programa numero
        try {
            InputStream is = p2.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

