import java.util.List;

public class Benchmark {
    private static final int NB_RECHERCHES = 1000;
    private static final int NB_SUPPRESSIONS = 1000;
    private static final int OPERATIONS_SCENARIO_MIXTE = 1000;

    public static void lancer(int taille) {
        System.out.println("\n========================================================");
        System.out.println("Début du Benchmark (" + taille + " contacts générés).");
        
        List<Contact> baseDonnees = GenerateurContacts.genererListeContacts(taille);

        // Différents scénarios du sujet
        Scenario rechercheIntensive = new Scenario("Recherche Intensive", 10, 5, 70, 10, 5);
        Scenario listingIntensif = new Scenario("Listing Intensif", 10, 5, 10, 65, 10);
        Scenario equilibre = new Scenario("Équilibré", 25, 20, 35, 10, 10);

        // Evaluation List vs Map
        RepertoireArrayList repertoireListe = new RepertoireArrayList();
        ResultatBenchmark resultatListe = evaluerRepertoire(repertoireListe, baseDonnees, "ArrayList", rechercheIntensive, listingIntensif, equilibre);

        RepertoireHashMap repertoireTableau = new RepertoireHashMap();
        ResultatBenchmark resultatHashMap = evaluerRepertoire(repertoireTableau, baseDonnees, "HashMap", rechercheIntensive, listingIntensif, equilibre);

        // Comparaison console
        resultatListe.afficher();
        resultatHashMap.afficher();
    }

    private static ResultatBenchmark evaluerRepertoire(
            RepertoireContacts repertoire, 
            List<Contact> baseDonnees, 
            String nomStructure,
            Scenario s1, Scenario s2, Scenario s3) 
    {
        ResultatBenchmark resultat = new ResultatBenchmark(nomStructure, baseDonnees.size());
        
        // --- 1. Temps d'ajout complet de la base
        long debut = System.nanoTime();
        for (Contact c : baseDonnees) {
            repertoire.ajouter(c);
        }
        resultat.tempsAjoutInitial = System.nanoTime() - debut;

        // --- 2. Temps de recherche locale aléatoire
        debut = System.nanoTime();
        for (int i = 0; i < NB_RECHERCHES; i++) {
            repertoire.rechercher(GenerateurContacts.getIdAleatoire(baseDonnees.size()));
        }
        resultat.tempsRecherchesId = System.nanoTime() - debut;

        // --- 3. Temps de listing
        debut = System.nanoTime();
        repertoire.listerTous();
        resultat.tempsListing = System.nanoTime() - debut;

        // --- 4. Temps de parcours/comptage
        debut = System.nanoTime();
        repertoire.compterParCategorie();
        resultat.tempsComptage = System.nanoTime() - debut;

        // --- Exécution des scénarios Mixtes ---
        int maxIdActuel = baseDonnees.size();
        
        resultat.tempsScenarioRechercheIntensive = executerScenario(repertoire, s1, OPERATIONS_SCENARIO_MIXTE, maxIdActuel);
        maxIdActuel += (int)(OPERATIONS_SCENARIO_MIXTE * (s1.getPourcentageAjout() / 100.0));
        
        resultat.tempsScenarioListingIntensif = executerScenario(repertoire, s2, OPERATIONS_SCENARIO_MIXTE, maxIdActuel);
        maxIdActuel += (int)(OPERATIONS_SCENARIO_MIXTE * (s2.getPourcentageAjout() / 100.0));
        
        resultat.tempsScenarioEquilibre = executerScenario(repertoire, s3, OPERATIONS_SCENARIO_MIXTE, maxIdActuel);
        maxIdActuel += (int)(OPERATIONS_SCENARIO_MIXTE * (s3.getPourcentageAjout() / 100.0));

        // --- 5. Suppressions en masse aléatoires
        debut = System.nanoTime();
        for (int i = 0; i < NB_SUPPRESSIONS; i++) {
            repertoire.supprimer(GenerateurContacts.getIdAleatoire(maxIdActuel));
        }
        resultat.tempsSuppressionsId = System.nanoTime() - debut;

        return resultat;
    }

    private static long executerScenario(RepertoireContacts repertoire, Scenario scenario, int totalOperations, int baseId) {
        int opsAjout = (int)(totalOperations * (scenario.getPourcentageAjout() / 100.0));
        int opsSuppression = (int)(totalOperations * (scenario.getPourcentageSuppression() / 100.0));
        int opsRecherche = (int)(totalOperations * (scenario.getPourcentageRecherche() / 100.0));
        int opsListing = (int)(totalOperations * (scenario.getPourcentageListing() / 100.0));
        int opsComptage = (int)(totalOperations * (scenario.getPourcentageComptage() / 100.0));

        int incrementIdNouveau = baseId + 1000;

        long chronometreGlobalDemarre = System.nanoTime();
        
        // Succession d'opérations du scénario
        for (int i = 0; i < opsAjout; i++) {
            try {
                repertoire.ajouter(new Contact(incrementIdNouveau++, "Mixte", "Test", "0", "mail@mail", "Autre"));
            } catch (Exception e) {}
        }
        
        for (int i = 0; i < opsSuppression; i++) {
            repertoire.supprimer(GenerateurContacts.getIdAleatoire(baseId));
        }
        
        for (int i = 0; i < opsRecherche; i++) {
            repertoire.rechercher(GenerateurContacts.getIdAleatoire(baseId));
        }

        for (int i = 0; i < opsListing; i++) {
            repertoire.listerTous();
        }

        for (int i = 0; i < opsComptage; i++) {
            repertoire.compterParCategorie();
        }
        
        long chronometreGlobalFini = System.nanoTime();
        
        return chronometreGlobalFini - chronometreGlobalDemarre;
    }
}
