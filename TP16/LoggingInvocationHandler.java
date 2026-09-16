package TP16;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

public class LoggingInvocationHandler implements InvocationHandler {

    private final Object cible;

    public LoggingInvocationHandler(Object cible) {
        this.cible = cible;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean loggable = method.isAnnotationPresent(Loggable.class);

        if (loggable) {
            System.out.println("[LOG] " + LocalDateTime.now()
                    + " - Appel de " + method.getName()
                    + " avec paramètres " + Arrays.toString(args));
        }

        Object resultat = method.invoke(cible, args);

        if (loggable) {
            System.out.println("[LOG] " + LocalDateTime.now()
                    + " - " + method.getName() + " a retourné " + resultat);
        }

        return resultat;
    }

    public static void main(String[] args) {
        Calculateur calculateur = (Calculateur) java.lang.reflect.Proxy.newProxyInstance(
                Calculateur.class.getClassLoader(),
                new Class<?>[] { Calculateur.class },
                new LoggingInvocationHandler(new CalculateurImpl())
        );

        int somme = calculateur.additionner(5, 3);
        System.out.println("Résultat de l'addition: " + somme);

        int difference = calculateur.soustraire(10, 4);
        System.out.println("Résultat de la soustraction (non journalisée): " + difference);
    }
}
