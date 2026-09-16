package TP16;

public class AppConfig {

    @ConfigValue(key = "app.name", defaultValue = "MonApplication")
    private String nomApplication;

    @ConfigValue(key = "app.port", defaultValue = "8080")
    private String port;

    @ConfigValue(key = "app.env")
    private String environnement;

    @Override
    public String toString() {
        return "AppConfig{" +
                "nomApplication='" + nomApplication + '\'' +
                ", port='" + port + '\'' +
                ", environnement='" + environnement + '\'' +
                '}';
    }
}
