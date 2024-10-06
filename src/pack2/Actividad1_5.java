package pack2;

import java.io.*;

public class Actividad1_5 {
    public static void main(String[] args) throws IOException {
        // Establecer el directorio de trabajo Inventado para comprobar que pasa
        File fichero = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP");

        // Invocar la clase LeerNombre correctamente con el paquete
        ProcessBuilder p = new ProcessBuilder("java", "pack.LeerNombr", "Javi");

        // Establecer el directorio de trabajo para ProcessBuilder
        p.directory(fichero);

        // Iniciar el proceso
        Process p2 = p.start();

        // En el siguiente bloque vamos a imprimir los errores que pueden surgir al invocar el programa como no existe pues nos ales un error de que no existe
        try {
            InputStream er = p2.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = brer.readLine())!=null){
                System.out.println("ERRROR >"+liner);
            }
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

