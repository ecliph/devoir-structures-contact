import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepertoireHashMap implements RepertoireContacts {
    private HashMap<Integer, Contact> mapContacts;

    public RepertoireHashMap() {
        this.mapContacts = new HashMap<>();
    }

    @Override
    public void ajouter(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Le contact ne peut pas être null.");
        }
        if (mapContacts.containsKey(contact.getId())) {
            throw new IllegalArgumentException("Un contact avec cet ID existe déjà.");
        }
        mapContacts.put(contact.getId(), contact);
    }

    @Override
    public boolean supprimer(int id) {
        // La méthode remove de la Map renvoie la valeur supprimée ou null si elle n'existait pas 
        return mapContacts.remove(id) != null;
    }

    @Override
    public Contact rechercher(int id) {
        // Accès direct avec la clé de hachage O(1)
        return mapContacts.get(id);
    }

    @Override
    public List<Contact> listerTous() {
        return new ArrayList<>(mapContacts.values());
    }

    @Override
    public Map<String, Integer> compterParCategorie() {
        Map<String, Integer> statistiques = new HashMap<>();
        for (Contact c : mapContacts.values()) {
            String cat = c.getCategorie();
            statistiques.put(cat, statistiques.getOrDefault(cat, 0) + 1);
        }
        return statistiques;
    }

    @Override
    public int taille() {
        return mapContacts.size();
    }
}
