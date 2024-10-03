package pack4;

import java.io.File;
import java.io.IOException;

public class Actividad1_7 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("/home/usuario/Escritorio/Tema1PSP/out/production/Tema1PSP");

        // Invocar la clase Numero correctamente
        ProcessBuilder p = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java", "pack4.EscribirCadena");
        p.directory(fichero);
        Process p2 = p.start();

    }
}
