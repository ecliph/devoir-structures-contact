public class ResultatBenchmark {
    public String nomStructure;
    public int tailleInitiale;
    
    // Temps pour les opérations pures
    public long tempsAjoutInitial;
    public long tempsRecherchesId;   // Cumulé sur plusieurs requêtes
    public long tempsSuppressionsId; // Cumulé sur plusieurs requêtes
    public long tempsListing;
    public long tempsComptage;
    
    // Temps pour les scénarios mixtes
    public long tempsScenarioRechercheIntensive;
    public long tempsScenarioListingIntensif;
    public long tempsScenarioEquilibre;

    // Mémoire
    public long memoireUtiliseeOctets;

    public ResultatBenchmark(String nomStructure, int tailleInitiale) {
        this.nomStructure = nomStructure;
        this.tailleInitiale = tailleInitiale;
    }

    public void afficher() {
        System.out.println("----- " + nomStructure + " (" + tailleInitiale + " élém.) -----");
        System.out.printf("Ajout total             : %.3f ms\n", (tempsAjoutInitial / 1_000_000.0));
        System.out.printf("Recherche (1000 items)  : %.3f ms\n", (tempsRecherchesId / 1_000_000.0));
        System.out.printf("Listing total           : %.3f ms\n", (tempsListing / 1_000_000.0));
        System.out.printf("Comptage par catégorie  : %.3f ms\n", (tempsComptage / 1_000_000.0));
        System.out.printf("Suppr. (1000 items)     : %.3f ms\n", (tempsSuppressionsId / 1_000_000.0));
        System.out.println("--- Scénarios Mixtes ---");
        System.out.printf("Recherche Intensive     : %.3f ms\n", (tempsScenarioRechercheIntensive / 1_000_000.0));
        System.out.printf("Listing Intensif        : %.3f ms\n", (tempsScenarioListingIntensif / 1_000_000.0));
        System.out.printf("Scénario Équilibré      : %.3f ms\n", (tempsScenarioEquilibre / 1_000_000.0));
        System.out.println("--- Empreinte Mémoire (Approximative) ---");
        System.out.println("Mémoire occupée         : " + memoireUtiliseeOctets + " Octets (" + (memoireUtiliseeOctets / 1024) + " Ko / " + (memoireUtiliseeOctets / 1024 / 1024) + " Mo)");
        System.out.println();
    }

    // Retourne la ligne au format CSV
    // taille;structure;tempsAjoutMs;tempsRechercheMs;tempsSuppressionMs;tempsListingMs;tempsComptageMs;scenarioRechercheIntensiveMs;scenarioListingIntensifMs;scenarioEquilibreMs;memoireOctets;memoireKo;memoireMo
    public String toCsvRow() {
        return tailleInitiale + ";" +
               nomStructure + ";" +
               (tempsAjoutInitial / 1_000_000.0) + ";" +
               (tempsRecherchesId / 1_000_000.0) + ";" +
               (tempsSuppressionsId / 1_000_000.0) + ";" +
               (tempsListing / 1_000_000.0) + ";" +
               (tempsComptage / 1_000_000.0) + ";" +
               (tempsScenarioRechercheIntensive / 1_000_000.0) + ";" +
               (tempsScenarioListingIntensif / 1_000_000.0) + ";" +
               (tempsScenarioEquilibre / 1_000_000.0) + ";" +
               memoireUtiliseeOctets + ";" +
               (memoireUtiliseeOctets / 1024) + ";" +
               (memoireUtiliseeOctets / 1024 / 1024);
    }
}
