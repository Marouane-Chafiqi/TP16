package TP16;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class ConfigProcessor {

    public static void injecter(Object obj, String cheminFichierProperties) throws IOException, IllegalAccessException {
        Properties proprietes = new Properties();

        try (InputStream input = ConfigProcessor.class.getClassLoader()
                .getResourceAsStream(cheminFichierProperties)) {
            if (input != null) {
                proprietes.load(input);
            } else {
                System.out.println("Attention: fichier de configuration introuvable ('"
                        + cheminFichierProperties + "'), utilisation des valeurs par défaut.");
            }
        }

        Class<?> clazz = obj.getClass();
        for (Field champ : clazz.getDeclaredFields()) {
            if (champ.isAnnotationPresent(ConfigValue.class)) {
                ConfigValue annotation = champ.getAnnotation(ConfigValue.class);
                String valeur = proprietes.getProperty(annotation.key(), annotation.defaultValue());

                champ.setAccessible(true);
                champ.set(obj, valeur);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        AppConfig config = new AppConfig();
        injecter(config, "config.properties");
        System.out.println(config);
    }
}
