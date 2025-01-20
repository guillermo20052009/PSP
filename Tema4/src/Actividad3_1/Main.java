package Actividad3_1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName(): " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // USAMOS MÉTODOS DE LA CLASE
        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }


    public static void main(String[] args) {
        try{
            InetAddress Host=null;
            System.out.println("Introduce el nombre del host: ");
            String host = sc.nextLine();
            Host=InetAddress.getByName(host);
            pruebaMetodos(Host);
        } catch (UnknownHostException e){
            System.out.println("Host no encontrado");
        }

    }
}
