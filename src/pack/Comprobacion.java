package pack;

import javax.imageio.IIOException;
import java.io.IOException;

public class Comprobacion {
    public static void main(String[] args) throws IOException {
        Process p = new ProcessBuilder("java", "~/Escritorio/Tema1PSP/src/LeerNombre", "ArgumentoEjemplo").start();

        int salida;
        try {
            salida=p.waitFor();
            System.out.println("El valor de salida es: "+salida);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
