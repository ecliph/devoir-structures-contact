import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Benchmark {
    private static final int NB_REPETITIONS = 5;
    private static final int NB_RECHERCHES = 1000;
    private static final int NB_SUPPRESSIONS = 1000;
    private static final int OPERATIONS_SCENARIO_MIXTE = 1000;

    enum TypeOperation { AJOUT, SUPPRESSION, RECHERCHE, LISTING, COMPTAGE }

    public static void lancer(int taille, FileWriter csvWriter) throws IOException {
        System.out.println("\n========================================================");
        System.out.println("Début du Benchmark (" + taille + " contacts générés).");
        
        List<Contact> baseDonnees = GenerateurContacts.genererListeContacts(taille);

        // Types de scénarios
        Scenario rechercheIntensive = new Scenario("Recherche Intensive", 10, 5, 70, 10, 5);
        Scenario listingIntensif = new Scenario("Listing Intensif", 10, 5, 10, 65, 10);
        Scenario equilibre = new Scenario("Équilibré", 25, 20, 35, 10, 10);

        // Usage de Supplier pour instancier un objet vierge à chaque répétition
        ResultatBenchmark resultatListe = evaluerMoyenne("ArrayList", () -> new RepertoireArrayList(), baseDonnees, rechercheIntensive, listingIntensif, equilibre);
        ResultatBenchmark resultatHashMap = evaluerMoyenne("HashMap", () -> new RepertoireHashMap(), baseDonnees, rechercheIntensive, listingIntensif, equilibre);

        // Affichage des deux tableaux
        resultatListe.afficher();
        resultatHashMap.afficher();
        
        // Mini conclusion automatique
        System.out.println("====== Mini Conclusion Automatique pour " + taille + " ======");
        if (resultatHashMap.tempsRecherchesId < resultatListe.tempsRecherchesId) {
            System.out.println("- HashMap est nettement plus rapide pour la recherche par identifiant.");
        } else {
            System.out.println("- ArrayList a été plus rapide en recherche (comportement très inhabituel, vérifier la charge JVM).");
        }
        
        if (resultatListe.tempsListing < resultatHashMap.tempsListing) {
            System.out.println("- ArrayList reste souvent plus compétitive pour le listing complet.");
        } else {
            System.out.println("- HashMap a été plus rapide (ou du moins très proche) au listing complet.");
        }
        System.out.println("==============================================================\n");

        // Écriture du CSV
        csvWriter.write(resultatListe.toCsvRow() + "\n");
        csvWriter.write(resultatHashMap.toCsvRow() + "\n");
    }

    private static ResultatBenchmark evaluerMoyenne(String nomStructure, Supplier<RepertoireContacts> constructeurStructure, List<Contact> baseDonnees, Scenario s1, Scenario s2, Scenario s3) {
        ResultatBenchmark cumuls = new ResultatBenchmark(nomStructure, baseDonnees.size());
        
        for (int r = 0; r < NB_REPETITIONS; r++) {
            RepertoireContacts repertoire = constructeurStructure.get();
            
            // ----- MESURE MEMOIRE : Remplissage -----
            // Attention : La mesure mémoire en Java via Runtime est une approximation.
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            try { Thread.sleep(50); } catch(Exception e) {}
            long memoireAvant = runtime.totalMemory() - runtime.freeMemory();
            
            long debutTimer = System.nanoTime();
            for (Contact c : baseDonnees) {
                repertoire.ajouter(c);
            }
            cumuls.tempsAjoutInitial += (System.nanoTime() - debutTimer);
            
            runtime.gc();
            try { Thread.sleep(50); } catch(Exception e) {}
            long memoireApres = runtime.totalMemory() - runtime.freeMemory();
            cumuls.memoireUtiliseeOctets += (memoireApres - memoireAvant);

            // --- 1. Recherches globales aléatoires ---
            debutTimer = System.nanoTime();
            for (int i = 0; i < NB_RECHERCHES; i++) {
                repertoire.rechercher(GenerateurContacts.getIdAleatoire(baseDonnees.size()));
            }
            cumuls.tempsRecherchesId += (System.nanoTime() - debutTimer);

            // --- 2. Listing complet ---
            debutTimer = System.nanoTime();
            repertoire.listerTous();
            cumuls.tempsListing += (System.nanoTime() - debutTimer);

            // --- 3. Comptage complet ---
            debutTimer = System.nanoTime();
            repertoire.compterParCategorie();
            cumuls.tempsComptage += (System.nanoTime() - debutTimer);

            // --- 4. Scénarios Mixtes VRAIMENT mélangés ---
            int maxIdActuel = baseDonnees.size();
            cumuls.tempsScenarioRechercheIntensive += executerScenarioMelange(repertoire, s1, OPERATIONS_SCENARIO_MIXTE, maxIdActuel);
            maxIdActuel += (int)(OPERATIONS_SCENARIO_MIXTE * (s1.getPourcentageAjout() / 100.0));
            
            cumuls.tempsScenarioListingIntensif += executerScenarioMelange(repertoire, s2, OPERATIONS_SCENARIO_MIXTE, maxIdActuel);
            maxIdActuel += (int)(OPERATIONS_SCENARIO_MIXTE * (s2.getPourcentageAjout() / 100.0));
            
            cumuls.tempsScenarioEquilibre += executerScenarioMelange(repertoire, s3, OPERATIONS_SCENARIO_MIXTE, maxIdActuel);
            maxIdActuel += (int)(OPERATIONS_SCENARIO_MIXTE * (s3.getPourcentageAjout() / 100.0));

            // --- 5. Suppressions en bloc aléatoires ---
            debutTimer = System.nanoTime();
            for (int i = 0; i < NB_SUPPRESSIONS; i++) {
                repertoire.supprimer(GenerateurContacts.getIdAleatoire(maxIdActuel));
            }
            cumuls.tempsSuppressionsId += (System.nanoTime() - debutTimer);
        }

        // Faire les moyennes finales en divisant par les répétitions
        cumuls.tempsAjoutInitial /= NB_REPETITIONS;
        cumuls.tempsRecherchesId /= NB_REPETITIONS;
        cumuls.tempsListing /= NB_REPETITIONS;
        cumuls.tempsComptage /= NB_REPETITIONS;
        cumuls.tempsScenarioRechercheIntensive /= NB_REPETITIONS;
        cumuls.tempsScenarioListingIntensif /= NB_REPETITIONS;
        cumuls.tempsScenarioEquilibre /= NB_REPETITIONS;
        cumuls.tempsSuppressionsId /= NB_REPETITIONS;
        cumuls.memoireUtiliseeOctets /= NB_REPETITIONS;
        
        // Sécurité si exceptionnellement la JVM gc fait un effet négatif brutal :
        if (cumuls.memoireUtiliseeOctets < 0) cumuls.memoireUtiliseeOctets = 0;

        return cumuls;
    }

    // Un scénario avec de véritables opérations mélangées et non séquencées
    private static long executerScenarioMelange(RepertoireContacts repertoire, Scenario scenario, int totalOperations, int baseId) {
        int opsAjout = (int)(totalOperations * (scenario.getPourcentageAjout() / 100.0));
        int opsSuppression = (int)(totalOperations * (scenario.getPourcentageSuppression() / 100.0));
        int opsRecherche = (int)(totalOperations * (scenario.getPourcentageRecherche() / 100.0));
        int opsListing = (int)(totalOperations * (scenario.getPourcentageListing() / 100.0));
        int opsComptage = (int)(totalOperations * (scenario.getPourcentageComptage() / 100.0));

        // Remplir une liste représentant l'action pure
        List<TypeOperation> listeOperationsMxt = new ArrayList<>();
        for (int i = 0; i < opsAjout; i++) listeOperationsMxt.add(TypeOperation.AJOUT);
        for (int i = 0; i < opsSuppression; i++) listeOperationsMxt.add(TypeOperation.SUPPRESSION);
        for (int i = 0; i < opsRecherche; i++) listeOperationsMxt.add(TypeOperation.RECHERCHE);
        for (int i = 0; i < opsListing; i++) listeOperationsMxt.add(TypeOperation.LISTING);
        for (int i = 0; i < opsComptage; i++) listeOperationsMxt.add(TypeOperation.COMPTAGE);

        // Mélanger aléatoirement toutes les actions pour un scénario hyper-réaliste
        Collections.shuffle(listeOperationsMxt);
        
        int ajoutCompteurId = baseId + 1000;
        
        long t0 = System.nanoTime();
        
        for (TypeOperation op : listeOperationsMxt) {
            switch(op) {
                case AJOUT:
                    try {
                        repertoire.ajouter(new Contact(ajoutCompteurId++, "UserMix", "M", "06..", "mixte@rm.com", "Universite"));
                    } catch (Exception e) {}
                    break;
                case SUPPRESSION:
                    repertoire.supprimer(GenerateurContacts.getIdAleatoire(baseId));
                    break;
                case RECHERCHE:
                    repertoire.rechercher(GenerateurContacts.getIdAleatoire(baseId));
                    break;
                case LISTING:
                    repertoire.listerTous();
                    break;
                case COMPTAGE:
                    repertoire.compterParCategorie();
                    break;
            }
        }
        
        long t1 = System.nanoTime();
        
        return t1 - t0;
    }
}
