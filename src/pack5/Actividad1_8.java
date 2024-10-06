package pack5;

import java.io.*;

public class Actividad1_8 {
    public static void main(String[] args) throws IOException {
        // Directorio donde está el proyecto
        File fichero = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP");

        // Invocar la clase EscribirCadenas correctamente
        ProcessBuilder p = new ProcessBuilder("/home/guillermo/.jdks/openjdk-23/bin/java", "pack4.EscribirCadenas");
        ProcessBuilder p3 = new ProcessBuilder("/home/guillermo/.jdks/openjdk-23/bin/java", "pack4.EscribirCadenas");
        p.directory(fichero);
        p3.directory(fichero);

        // Fichero para redirigir la salida estándar y errores
        File fout = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP/salida.txt");
        File fErr = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP/error.txt");
        p.redirectOutput(ProcessBuilder.Redirect.to(fout));
        p3.redirectOutput(ProcessBuilder.Redirect.INHERIT);// Redirigir la salida estándar a la consola
        p.redirectError(ProcessBuilder.Redirect.to(fErr));       // Redirigir la salida de errores a un archivo

        // Fichero para redirigir la entrada estándar
        File fin = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP/entrada.txt");
        p.redirectInput(ProcessBuilder.Redirect.from(fin));
        p3.redirectInput(ProcessBuilder.Redirect.from(fin));        // Redirigir la entrada desde un archivo

        // Iniciar el proceso
        Process p2 = p.start();
        Process p4 = p3.start();

        // Ya no necesitamos escribir en el OutputStream, así que podemos eliminar esa parte
    }
}