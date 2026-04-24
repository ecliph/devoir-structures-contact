import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepertoireArrayList implements RepertoireContacts {
    private ArrayList<Contact> contacts;

    public RepertoireArrayList() {
        this.contacts = new ArrayList<>();
    }

    @Override
    public void ajouter(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Le contact ne peut pas être null.");
        }
        if (rechercher(contact.getId()) != null) {
            throw new IllegalArgumentException("Un contact avec cet ID existe déjà.");
        }
        contacts.add(contact);
    }

    @Override
    public boolean supprimer(int id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Contact rechercher(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null; // On retourne null si inexistant (demande du sujet)
    }

    @Override
    public List<Contact> listerTous() {
        // Retourne une nouvelle liste pour éviter l'altération accidentelle des données sources
        return new ArrayList<>(contacts);
    }

    @Override
    public Map<String, Integer> compterParCategorie() {
        Map<String, Integer> statistiques = new HashMap<>();
        for (Contact c : contacts) {
            String cat = c.getCategorie();
            statistiques.put(cat, statistiques.getOrDefault(cat, 0) + 1);
        }
        return statistiques;
    }

    @Override
    public int taille() {
        return contacts.size();
    }
}
