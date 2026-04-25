# Annexes : prompts LLM utilisés

Date : avril 2026

Outils utilisés :
- ChatGPT
- Gemini via Google Antigravity
- MCP GitHub connecté à Antigravity

Les prompts ci-dessous correspondent aux prompts structurants utilisés pendant le projet. Certains échanges mineurs de dépannage ou de clarification ne sont pas reproduits intégralement, mais les prompts principaux ayant guidé la production du code, du benchmark, du rapport, du PDF et de la vidéo sont fournis.

### PROMPT 1 — Génération initiale du projet Java

Je dois réaliser un devoir maison en Java pour un cours de Structures de données.

Sujet 9 : RepertoireContacts.

Objectif du devoir :
Comparer expérimentalement deux structures de données pour gérer un répertoire de contacts :
1) ArrayList<Contact>
2) HashMap<Integer, Contact>

Le projet doit être simple, lisible, niveau étudiant débutant/intermédiaire, sans interface graphique, sans Maven, sans Gradle, uniquement du Java console.

Structure voulue :
- Tous les fichiers doivent être dans le dossier src/
- Ne pas utiliser de package Java pour éviter les problèmes d'import dans Eclipse.
- Le projet doit pouvoir être compilé avec :
  javac src/*.java
- Le projet doit pouvoir être lancé avec :
  java -cp src Main

Fichiers à créer :
1. Contact.java
2. RepertoireContacts.java
3. RepertoireArrayList.java
4. RepertoireHashMap.java
5. GenerateurContacts.java
6. Scenario.java
7. ResultatBenchmark.java
8. Benchmark.java
9. Main.java

Détails fonctionnels :
- Contact doit représenter un contact avec :
  - id : int
  - nom : String
  - prenom : String
  - telephone : String
  - email : String
  - categorie : String
- Les catégories possibles sont : Famille, Amis, Travail, Universite, Autre.

Interface RepertoireContacts :
Elle doit définir les méthodes :
- void ajouter(Contact contact)
- boolean supprimer(int id)
- Contact rechercher(int id)
- List<Contact> listerTous()
- Map<String, Integer> compterParCategorie()
- int taille()

Implémentation 1 :
RepertoireArrayList doit utiliser ArrayList<Contact>.
La recherche et la suppression par id doivent parcourir la liste.

Implémentation 2 :
RepertoireHashMap doit utiliser HashMap<Integer, Contact>.
La clé doit être l'id du contact.

Gestion des erreurs :
- Si on essaie d'ajouter un contact avec un id déjà existant, lancer une IllegalArgumentException.
- Si le contact est null, lancer une IllegalArgumentException.
- Pour supprimer un id inexistant, retourner false.
- Pour rechercher un id inexistant, retourner null.

Générateur :
GenerateurContacts doit générer des listes de contacts réalistes avec des ids uniques.
Il doit pouvoir générer par exemple 1000, 10000, 50000 et 100000 contacts.

Benchmark :
Le benchmark doit comparer les deux structures sur :
- ajout
- recherche par identifiant
- suppression par identifiant
- listing complet
- comptage par catégorie
- scénarios mixtes avec pourcentages d'opérations

Les mesures doivent utiliser System.nanoTime().
Il faut répéter les mesures plusieurs fois pour éviter les résultats trop aléatoires.
Il faut afficher les résultats clairement dans la console.

Scénarios mixtes à prévoir :
1) Scénario recherche intensive :
   10% ajout, 5% suppression, 70% recherche, 10% listing, 5% comptage
2) Scénario listing intensif :
   10% ajout, 5% suppression, 10% recherche, 65% listing, 10% comptage
3) Scénario équilibré :
   25% ajout, 20% suppression, 35% recherche, 10% listing, 10% comptage

Main.java :
Doit lancer automatiquement les benchmarks pour plusieurs tailles :
- 1000
- 10000
- 50000
- 100000

Important :
- Le code doit être clair et commenté.
- Le code doit rester simple et crédible pour un étudiant.
- Évite les optimisations trop avancées.
- Après avoir créé les fichiers, explique-moi comment compiler et lancer le projet.

### PROMPT 2 — Amélioration benchmark, mémoire, répétitions et CSV

Le projet Java fonctionne comme première version, mais il faut l'améliorer pour mieux respecter le sujet du devoir maison.

Consignes du devoir à respecter :
- comparer ArrayList<Contact> et HashMap<Integer, Contact>
- mesurer les temps d'exécution
- mesurer l'occupation mémoire
- utiliser plusieurs tailles de test
- répéter les mesures
- exécuter des scénarios mixtes avec pourcentages d'opérations
- présenter des résultats clairs
- garder un code simple, niveau étudiant, sans Maven, sans Gradle, sans package

Améliorations demandées :
1. Ajouter une mesure mémoire avec Runtime.
2. Répéter chaque benchmark 5 fois et calculer une moyenne.
3. Mélanger les opérations des scénarios avec Collections.shuffle.
4. Exporter les résultats dans resultats.csv.
5. Garder le projet compilable avec javac src/*.java et lançable avec java -cp src Main.
6. Ne pas rendre le code trop avancé.

### PROMPT 3 — Mise à jour GitHub

J'ai un repo GitHub pour ce projet :
https://github.com/ecliph/devoir-structures-contact

Après avoir fait toutes les modifications demandées, tu dois mettre à jour le repo GitHub avec le MCP server GitHub connecté à Antigravity.

Objectif :
- que tous les fichiers modifiés soient bien envoyés sur GitHub ;
- que les fichiers ajoutés soient aussi envoyés ;
- que je puisse ensuite faire vérifier le code par ChatGPT directement depuis GitHub.

À faire :
1. vérifier l'état Git du projet ;
2. ajouter les fichiers modifiés et les nouveaux fichiers ;
3. faire un commit clair ;
4. pousser sur la branche main du repo GitHub.

Avant de pousser :
- ne pas ajouter les fichiers .class au repo ;
- ne pas ajouter de fichiers inutiles ;
- si besoin, créer ou compléter un fichier .gitignore avec :
  *.class
  *.log
  .vscode/
  bin/
  out/

### PROMPT 4 — Réduction du benchmark

Le benchmark fonctionne mais il est trop long avec 100000 contacts.

Fais uniquement ces petites modifications rapides :
1. Dans Main.java, remplace :
int[] volumesCibles = { 1000, 10000, 50000, 100000 };
par :
int[] volumesCibles = { 1000, 10000, 50000 };

Ajoute un commentaire disant que 100000 peut être remis si la machine est assez puissante.

2. Dans README.md, corrige le titre :
remplacer "Base de Données" par "Structures de données".

3. Dans README.md, ajoute une phrase :
"Il faut attendre le message final COMPARAISON TERMINÉE avant d'utiliser le fichier resultats.csv."

4. Ne touche à rien d'autre.
5. Vérifie que ça compile avec javac src/*.java.
6. Commit et push avec le message :
"Reduit taille benchmark par defaut"

### PROMPT 5 — Création du rapport

Maintenant que le code Java fonctionne et que le fichier resultats.csv est généré correctement, je veux produire le document de résultats du devoir maison.

Matière : Structures de données en Java
Sujet : Sujet 9 - RepertoireContacts
Structures comparées :
- ArrayList<Contact>
- HashMap<Integer, Contact>

Opérations étudiées :
- ajout
- suppression par identifiant
- recherche par identifiant
- listing complet
- comptage par catégorie
- scénarios mixtes

Tailles testées :
- 1000 contacts
- 10000 contacts
- 50000 contacts

Le programme fait :
- 5 répétitions par mesure
- moyenne des résultats
- export dans resultats.csv
- mesure mémoire approximative avec Runtime

Créer rapport.md avec :
1. Introduction
2. Présentation du cas d’étude
3. Structures comparées
4. Opérations étudiées
5. Génération des données
6. Protocole expérimental
7. Résultats
8. Analyse des résultats
9. Complexités théoriques
10. Limites
11. Conclusion
12. Usage des LLM
13. Empreinte carbone
14. Annexes

Style étudiant, clair, simple.
Ne pas modifier le code Java.
Commit et push avec le message :
"Ajoute rapport de resultats"

### PROMPT 6 — Correction du style du rapport

Corriger uniquement les formulations trop exagérées ou trop IA.
Ne pas modifier les tableaux ni les résultats.
Rendre le style plus sobre, universitaire et étudiant.
Reformuler la conclusion et l'empreinte carbone.
Commit et push avec le message :
"Corrige style du rapport"

### PROMPT 7 — Génération du PDF et guide vidéo

Le document final doit être en PDF.
Créer rapport_RepertoireContacts.pdf à partir de rapport.md.
Créer video_guide.md expliquant comment faire la vidéo :
- plan minute par minute ;
- script oral ;
- fichiers à montrer ;
- commandes à taper ;
- conseils d'enregistrement ;
- checklist finale.

### PROMPT 8 — Correction mise en page PDF

Le PDF est généré mais certaines listes Markdown apparaissent mal.
Corriger rapport.md en ajoutant les lignes vides nécessaires avant les listes et tableaux.
Régénérer le PDF avec :
pandoc rapport.md -o rapport_RepertoireContacts.pdf --pdf-engine=xelatex

### PROMPT 9 — Empreinte carbone et prompts complets

Je veux améliorer une dernière fois le rapport PDF.
Objectif :
- améliorer fortement la section "Empreinte carbone de l'usage des LLM" ;
- ajouter les prompts importants complets ;
- régénérer le PDF.

L’estimation carbone doit prendre en compte :
- la conversation avec ChatGPT pour comprendre le sujet, créer les prompts, analyser les résultats et préparer le rapport ;
- les prompts envoyés à Gemini/Antigravity pour générer le code, améliorer le benchmark, créer le rapport, corriger le style, générer le PDF et produire le guide vidéo.