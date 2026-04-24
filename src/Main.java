public class Main {
    public static void main(String[] args) {
        System.out.println("========== DEVOIR MAISON - REPERTOIRE DE CONTACTS ==========");
        System.out.println("Comparaison ArrayList vs HashMap pour différents volumes");
        
        // Tailles de base définies dans le sujet
        int[] volumesCibles = { 1000, 10000, 50000, 100000 };
        
        for (int taille : volumesCibles) {
            Benchmark.lancer(taille);
        }
    }
}
