package TP16;

public class ServiceSecurise {

    @RequiresRole({"ADMIN"})
    public void supprimerUtilisateur(String nomUtilisateur) {
        System.out.println("Utilisateur supprimé: " + nomUtilisateur);
    }

    @RequiresRole({"ADMIN", "MODERATEUR"})
    public void bannirUtilisateur(String nomUtilisateur) {
        System.out.println("Utilisateur banni: " + nomUtilisateur);
    }

    public void consulterProfil(String nomUtilisateur) {
        System.out.println("Consultation du profil de: " + nomUtilisateur);
    }
}
