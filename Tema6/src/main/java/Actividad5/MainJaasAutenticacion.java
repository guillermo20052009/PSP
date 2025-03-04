package Actividad5;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.*;

public class MainJaasAutenticacion {
    public static void main(String[] args) {
        // Se obtiene el usuario y la clave desde la línea de comandos
        String user = System.getProperty("usuario");
        String pass = System.getProperty("clave");

        // Se pasa al CallbackHandler el nombre de usuario y la clave
        CallbackHandler handler = new MyCallbackHandler(user, pass);

        try {
            LoginContext loginContext = new LoginContext("EjemploLogin", handler);
            loginContext.login(); // Realiza la autenticación

            System.out.println("Usuario autenticado.....");
        } catch (LoginException e) {
            System.err.println("ERROR-> No se puede autenticar el usuario.");
        }
    }
}
