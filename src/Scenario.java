// Modèle pour configurer les pourcentages d'un scénario de benchmarking
public class Scenario {
    private String nom;
    private double pourcentageAjout;
    private double pourcentageSuppression;
    private double pourcentageRecherche;
    private double pourcentageListing;
    private double pourcentageComptage;

    public Scenario(String nom, double pAjout, double pSuppression, double pRecherche, double pListing, double pComptage) {
        this.nom = nom;
        this.pourcentageAjout = pAjout;
        this.pourcentageSuppression = pSuppression;
        this.pourcentageRecherche = pRecherche;
        this.pourcentageListing = pListing;
        this.pourcentageComptage = pComptage;
    }

    public String getNom() { return nom; }
    public double getPourcentageAjout() { return pourcentageAjout; }
    public double getPourcentageSuppression() { return pourcentageSuppression; }
    public double getPourcentageRecherche() { return pourcentageRecherche; }
    public double getPourcentageListing() { return pourcentageListing; }
    public double getPourcentageComptage() { return pourcentageComptage; }
}
