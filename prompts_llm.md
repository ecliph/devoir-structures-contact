# Annexes : Documentation des Prompts LLM

**Date :** Avril 2026
**Outils utilisés :** ChatGPT et Gemini/Antigravity

Ce document répertorie les prompts principaux et structurants qui ont permis de guider la conception de ce projet. Certains échanges mineurs de correction ou de débogage ne sont pas reproduits intégralement, mais l'ensemble de la trajectoire du projet est tracé ci-dessous.

### PROMPT 1 — Génération initiale du projet Java
Je dois réaliser un devoir maison en Java pour un cours de Structures de données.
Sujet 9 : RepertoireContacts.
Objectif : comparer expérimentalement `ArrayList<Contact>` et `HashMap<Integer, Contact>`.
Projet Java simple, sans Maven, sans Gradle, sans package, avec fichiers dans `src/`.
Fichiers demandés : Contact.java, RepertoireContacts.java, RepertoireArrayList.java, RepertoireHashMap.java, GenerateurContacts.java, Scenario.java, ResultatBenchmark.java, Benchmark.java, Main.java.
Méthodes demandées : ajouter, supprimer, rechercher, listerTous, compterParCategorie, taille.
Générer des données réalistes.
Mesurer l'ajout, la recherche, la suppression, le listing, le comptage et exécuter des scénarios mixtes.
Compiler avec `javac src/*.java`.
Lancer avec `java -cp src Main`.

### PROMPT 2 — Amélioration benchmark, mémoire, répétitions et CSV
Le projet Java fonctionne comme première version, mais il faut l'améliorer pour mieux respecter le sujet.
Ajouter :
- mesures répétées 5 fois ;
- moyennes ;
- mesure mémoire avec Runtime ;
- scénarios mixtes vraiment mélangés avec `Collections.shuffle` ;
- export dans `resultats.csv` ;
- affichage console plus clair ;
- README ;
- garder un code simple, sans Maven, sans Gradle, sans package.

### PROMPT 3 — Mise à jour GitHub
Après les modifications, mettre à jour le repo GitHub `ecliph/devoir-structures-contact` avec le MCP server GitHub.
Ajouter fichiers modifiés, commit, push.
Ne pas ajouter les fichiers `.class`.
Créer/compléter `.gitignore` si besoin.

### PROMPT 4 — Réduction de la taille du benchmark
Le benchmark est trop long avec 100000 contacts.
Remplacer les tailles par défaut par 1000, 10000, 50000.
Ajouter un commentaire indiquant que 100000 peut être remis si la machine est assez puissante.
Corriger le titre README en Structures de données.
Ajouter une phrase disant d'attendre "COMPARAISON TERMINÉE" avant d'utiliser `resultats.csv`.
Commit et push GitHub.

### PROMPT 5 — Création du rapport
Créer `rapport.md` à partir du code et de `resultats.csv`.
Inclure :
- introduction ;
- présentation du sujet ;
- structures comparées ;
- opérations étudiées ;
- génération des données ;
- protocole expérimental ;
- résultats sous forme de tableaux ;
- analyse ;
- complexités théoriques ;
- limites ;
- conclusion ;
- usage des LLM ;
- empreinte carbone ;
- annexes.
Style étudiant, clair, sobre.

### PROMPT 6 — Correction du style du rapport
Corriger uniquement les formulations trop exagérées ou trop IA.
Ne pas modifier les tableaux ni les résultats.
Rendre le style plus sobre, universitaire et étudiant.
Reformuler la conclusion et l'empreinte carbone.

### PROMPT 7 — Génération du PDF et guide vidéo
Le document final doit être en PDF.
Créer `rapport_RepertoireContacts.pdf` à partir de `rapport.md`.
Créer `video_guide.md` expliquant comment faire la vidéo :
- plan minute par minute ;
- script oral ;
- fichiers à montrer ;
- commandes à taper ;
- conseils d'enregistrement ;
- checklist finale.

### PROMPT 8 — Correction mise en page PDF
Le PDF est généré mais certaines listes Markdown apparaissent mal.
Corriger `rapport.md` en ajoutant les lignes vides nécessaires avant les listes et tableaux.
Régénérer le PDF avec pandoc.
Commit et push.

### PROMPT 9 — Amélioration finale empreinte carbone et prompts
Améliorer fortement la section "Empreinte carbone de l'usage des LLM".
Créer un vrai protocole d'estimation (sans chiffrage exagéré).
Créer un fichier à part pour lister exhaustivement les prompts.
Régénérer le PDF final proprement et commit/push l'ensemble des finitions.