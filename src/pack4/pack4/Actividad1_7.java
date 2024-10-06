package pack4.pack4;

import java.io.File;
import java.io.IOException;

public class Actividad1_7 {
    public static void main(String[] args) throws IOException {
        // Directorio donde está el proyecto
        File fichero = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP");

        // Invocar la clase EscribirCadenas correctamente
        ProcessBuilder p = new ProcessBuilder("/home/guillermo/.jdks/openjdk-23/bin/java", "pack4.EscribirCadenas");
        p.directory(fichero);

        // Fichero para redirigir la salida estándar y errores
        File fout = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP/salida.txt");
        File fErr = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP/error.txt");
        p.redirectOutput(fout);      // Redirigir la salida estándar a un archivo
        p.redirectError(fErr);       // Redirigir la salida de errores a un archivo

        // Fichero para redirigir la entrada estándar
        File fin = new File("/home/guillermo/Escritorio/psp/out/production/Tema1PSP/entrada.txt");
        p.redirectInput(fin);        // Redirigir la entrada desde un archivo

        // Iniciar el proceso
        Process p2 = p.start();

        // Ya no necesitamos escribir en el OutputStream, así que podemos eliminar esa parte
    }
}