package pack4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EscribirCadena {
    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        try {
            System.out.println("Introduce una cadena");
            String texto = br.readLine();
            System.out.println("Se ha introducido la cadena: "+texto);
            in.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
