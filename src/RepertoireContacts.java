import java.util.List;
import java.util.Map;

// Interface décrivant les opérations d'un répertoire de contacts
public interface RepertoireContacts {
    void ajouter(Contact contact);
    boolean supprimer(int id);
    Contact rechercher(int id);
    List<Contact> listerTous();
    Map<String, Integer> compterParCategorie();
    int taille();
}
