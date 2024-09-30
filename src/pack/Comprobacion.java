package pack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Comprobacion {
    public static void main(String[] args) throws IOException {
        // Establecer el directorio de trabajo correcto
        File fichero = new File("/home/usuario/Escritorio/Tema1PSP/out/production/Tema1PSP");

        // Invocar la clase LeerNombre correctamente con el paquete
        ProcessBuilder p = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "pack.LeerNombre", "Javi");

        // Establecer el directorio de trabajo para ProcessBuilder
        p.directory(fichero);

        // Iniciar el proceso
        Process p2 = p.start();

        // En el siguiente bloque vamos a imprimir los argumentos del main del programa al que invocamos
        try {
            InputStream is = p2.getInputStream();
            int c;
            while ((c = is.read()) != -1){
                System.out.print((char)c);
            }
            is.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // En el siguiente bloque imprimiremos el valor de salida del programa al que invocamos
        int salida;
        try {
            salida = p2.waitFor();
            System.out.println("El valor de salida es: " + salida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
