import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Classe utilitaire pour générer des données de test réalistes
public class GenerateurContacts {
    private static final String[] CATEGORIES = {"Famille", "Amis", "Travail", "Universite", "Autre"};
    private static final String[] NOMS = {"Dupont", "Martin", "Dubois", "Lefebvre", "Moreau", "Bernard", "Thomas", "Petit", "Robert", "Richard"};
    private static final String[] PRENOMS = {"Alice", "Jean", "Paul", "Marie", "Sophie", "Luc", "Hugo", "Camille", "Nicolas", "Julie"};
    
    // Génère une liste de la taille spécifiée
    public static List<Contact> genererListeContacts(int nombre) {
        List<Contact> liste = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 1; i <= nombre; i++) {
            String nom = NOMS[rand.nextInt(NOMS.length)];
            String prenom = PRENOMS[rand.nextInt(PRENOMS.length)];
            String telephone = "06" + String.format("%08d", rand.nextInt(100000000));
            String email = prenom.toLowerCase() + "." + nom.toLowerCase() + i + "@email.com";
            String categorie = CATEGORIES[rand.nextInt(CATEGORIES.length)];
            
            // On attribue un ID unique de 1 jusqu'à la taille demandée
            liste.add(new Contact(i, nom, prenom, telephone, email, categorie));
        }
        return liste;
    }
    
    // Fournit un ID aléatoire dans l'intervalle [1, maxId] pour tester rechercher() et supprimer()
    public static int getIdAleatoire(int maxId) {
        Random rand = new Random();
        return rand.nextInt(maxId) + 1;
    }
}
