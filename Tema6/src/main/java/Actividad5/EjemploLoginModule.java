package Actividad5;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.Map;

public class EjemploLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean autenticado;

    public boolean commit() throws                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    LoginException {
        return true;
    }

    public boolean logout() throws LoginException {
        return true;
    }

    public boolean abort() throws LoginException {
        return true;
    }

    public void initialize(Subject subject, CallbackHandler handler, Map<String, ?> state, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    public boolean login() throws LoginException {
        autenticado = true;

        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        // Se crea el array de Callbacks
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            // Invoca el método handle del CallbackHandler para solicitar usuario y clave
            callbackHandler.handle(callbacks);
            String usuario = ((NameCallback) callbacks[0]).getName();
            char[] passw = ((PasswordCallback) callbacks[1]).getPassword();
            String clave = new String(passw);

            // **Se añade el usuario "pedro" con clave "abcd"**
            autenticado = ("maria".equalsIgnoreCase(usuario) && "1234".equals(clave)) ||
                    ("pedro".equalsIgnoreCase(usuario) && "abcd".equals(clave));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autenticado; // Devuelve `true` si la autenticación es correcta, `false` si falla.
    }
}
