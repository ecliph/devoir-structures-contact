public class Contact {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String categorie;

    public Contact(int id, String nom, String prenom, String telephone, String email, String categorie) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.categorie = categorie;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getCategorie() { return categorie; }
    
    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", nom='" + nom + '\'' + ", categorie='" + categorie + '\'' + '}';
    }
}
