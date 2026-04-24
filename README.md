# Devoir de Base de Données - Benchmark Répertoire Contacts

Ce projet est la proposition algorithmique au Sujet 9 qui évalue et détermine l'efficacité de deux structures de données : `ArrayList` et `HashMap` pour modéliser analytiquement un répertoire de contacts selon la nature des opérations (lectures, écritures, manipulations mixtes).

## Les Structures de la Compétition
- **RepertoireArrayList :** une structure linéaire respectant `java.util.ArrayList`, idéale visuellement pour conserver un ordre de stockage et d'itération, mais sensible aux suppressions ou tris divers.
- **RepertoireHashMap  :** une structure associative basée sur la carte de hachage `java.util.HashMap` stockée via son horodatage de clé / valeur, optimisée pour un accès très bas niveau O(1) si l'indice est connu.

## Compilation et Lancement

### Pour compiler :
Depuis le dossier racine (contenant `src/`) :
```bash
javac src/*.java
```

### Pour exécuter les benchmarks :
```bash
java -cp src Main
```

## Résultats obtenus

Le programme lancera des tests sur différentes échelles de volumétrie. Une conclusion finale apparaîtra dans la console. Les calculs intègrent 5 répétitions pour moyenner le résultat et éviter les incohérences algorithmiques.

*Vous retrouverez l'ensemble du profil d'exécution dans le fichier the `resultats.csv` exporté dynamiquement à la racine qui peut être incorporé très facilement sur le rapport analytique.*

**Note sur l'empreinte mémoire :** 
L'affichage sur l'empreinte mémoire d'une structure sous JAVA s'effectue sans aucune lib supplémentaire mais elle se base sur une estimation via le `Runtime`. La Machine Virtuelle (la JVM) libère sa mémoire avec un Garbage Collector de manière asynchrone, la conversion reste donc **totalement approximative**.
