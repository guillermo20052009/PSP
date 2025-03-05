package Actividad6;

import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.Map;

public class EjemploLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean autenticado;
    private EjemploPrincipal usuarioPrincipal;

    public boolean commit() throws LoginException {
        if (autenticado) {
            usuarioPrincipal = new EjemploPrincipal("pedro");
            subject.getPrincipals().add(usuarioPrincipal);
        }
        return autenticado;
    }

    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(usuarioPrincipal);
        usuarioPrincipal = null;
        return true;
    }

    public boolean abort() throws LoginException {
        return false;
    }

    public void initialize(Subject subject, CallbackHandler handler, Map<String, ?> state, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    public boolean login() throws LoginException {
        autenticado = false;

        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            callbackHandler.handle(callbacks);
            String usuario = ((NameCallback) callbacks[0]).getName();
            char[] passw = ((PasswordCallback) callbacks[1]).getPassword();
            String clave = new String(passw);

            autenticado = "pedro".equalsIgnoreCase(usuario) && "abcd".equals(clave);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autenticado;
    }
}
