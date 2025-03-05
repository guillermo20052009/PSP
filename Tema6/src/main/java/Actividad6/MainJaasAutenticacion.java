package Actividad6;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

public class MainJaasAutenticacion {
    public static void main(String[] args) {
        CallbackHandler handler = new MyCallbackHandler("pedro", "abcd");
        LoginContext loginContext;

        try {
            loginContext = new LoginContext("EjemploLogin", handler);
            loginContext.login();
            System.out.println("Usuario autenticado.....");

            // Obtener el Subject del usuario autenticado
            Subject subject = loginContext.getSubject();

            // Ejecutar acción privilegiada
            PrivilegedAction action = new EjemploAccion();
            Subject.doAsPrivileged(subject, action, null);

            // Logout del usuario autenticado
            loginContext.logout();

        } catch (LoginException e) {
            System.err.println("ERROR-> No se puede autenticar el usuario.");
            System.out.println(e.getMessage());
        }
    }
}
