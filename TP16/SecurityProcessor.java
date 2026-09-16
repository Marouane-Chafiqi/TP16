package TP16;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class SecurityProcessor {

    public static void invoquer(Object cible, String nomMethode, String roleUtilisateur, Object... args)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Class<?>[] typesParametres = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            typesParametres[i] = args[i].getClass();
        }

        Method methode = cible.getClass().getMethod(nomMethode, typesParametres);

        if (methode.isAnnotationPresent(RequiresRole.class)) {
            RequiresRole annotation = methode.getAnnotation(RequiresRole.class);
            List<String> rolesRequis = Arrays.asList(annotation.value());

            if (!rolesRequis.contains(roleUtilisateur)) {
                System.out.println("Accès refusé: le rôle '" + roleUtilisateur
                        + "' n'est pas autorisé à exécuter '" + nomMethode + "'."
                        + " Rôles requis: " + rolesRequis);
                return;
            }
        }

        methode.invoke(cible, args);
    }

    public static void main(String[] args) throws Exception {
        ServiceSecurise service = new ServiceSecurise();

        System.out.println("--- Tentative avec le rôle ADMIN ---");
        invoquer(service, "supprimerUtilisateur", "ADMIN", "alice");

        System.out.println("\n--- Tentative avec le rôle USER (refusée) ---");
        invoquer(service, "supprimerUtilisateur", "USER", "bob");

        System.out.println("\n--- Méthode non protégée ---");
        invoquer(service, "consulterProfil", "USER", "charlie");
    }
}
