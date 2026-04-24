import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== DEVOIR MAISON - REPERTOIRE DE CONTACTS ==========");
        System.out.println("Comparaison ArrayList vs HashMap pour différents volumes");
        System.out.println("Note : Chaque mesure est répétée 5 fois pour calculer la moyenne.");

        // Tailles de base définies dans le sujet
        int[] volumesCibles = { 1000, 10000, 50000, 100000 };
        
        try (FileWriter csvWriter = new FileWriter("resultats.csv", false)) {
            // Écriture de l'en-tête (une seule fois)
            csvWriter.write("taille;structure;tempsAjoutMs;tempsRechercheMs;tempsSuppressionMs;tempsListingMs;tempsComptageMs;scenarioRechercheIntensiveMs;scenarioListingIntensifMs;scenarioEquilibreMs;memoireOctets;memoireKo;memoireMo\n");
            
            for (int taille : volumesCibles) {
                // Lancer prend en paramètre le writer pour logger les deux structures
                Benchmark.lancer(taille, csvWriter);
            }
            
            System.out.println("\n============ COMPARAISON TERMINÉE ============");
            System.out.println("Les résultats ont été exportés dans 'resultats.csv'.");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'export CSV : " + e.getMessage());
        }
    }
}
