package pack3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Numero {
    public static void main(String[] args) {
        int num1, num2;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            num1=Integer.valueOf(br.readLine());
            num2=Integer.valueOf(br.readLine());
            System.out.println("La suma es: "+(num1+num2));
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
