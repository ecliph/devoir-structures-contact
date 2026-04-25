# Rapport - Devoir Maison : RepertoireContacts

## 1. Introduction
L'objectif de ce devoir maison est de comparer expérimentalement deux structures de données en Java pour la gestion d'un répertoire de contacts. Il s'agit de mettre en évidence les avantages et les inconvénients de chaque structure selon la nature des opérations effectuées (recherches, ajouts, listings).

## 2. Présentation du cas d'étude
Le sujet s'articule autour d'un répertoire de contacts (Sujet 9 : RepertoireContacts). Pour cela, une classe `Contact` a été implémentée avec les attributs suivants :

- `id` (identifiant entier unique)
- `nom`
- `prenom`
- `telephone`
- `email`
- `categorie`

## 3. Structures comparées
Pour stocker et manipuler ces contacts, deux structures de la bibliothèque standard Java ont été utilisées :

- **`ArrayList<Contact>`** : un tableau redimensionnable qui stocke les objets de manière séquentielle.
- **`HashMap<Integer, Contact>`** : une table de hachage associant une clé à une valeur. Ici, la clé utilisée est l'`id` du contact.

## 4. Opérations étudiées
Les fonctionnalités mesurées sont les suivantes :

- L'ajout d'un contact (en vérifiant d'abord que son identifiant n'existe pas déjà).
- La suppression d'un contact par son identifiant.
- La recherche d'un contact par son identifiant.
- Le listing complet (retourner tous les contacts).
- Le comptage des contacts groupés par catégorie.

## 5. Génération des données
Afin d'obtenir des données variées pour les tests, un générateur crée automatiquement des profils de contacts.

- Les catégories possibles (attribuées aléatoirement) sont : Famille, Amis, Travail, Universite, Autre.
- Les identifiants générés sont uniques et incrémentaux, assurant la validité des mesures, ainsi que des noms, numéros de téléphone et emails aléatoires.

## 6. Protocole expérimental
Afin d'encadrer l'expérimentation :

- **Tailles testées :** 1000, 10000 et 50000 contacts.
- **Répétitions :** Chaque mesure est exécutée 5 fois et la valeur affichée est une moyenne.
- **Outils de mesure :** Le chronométrage repose sur `System.nanoTime()` converti par la suite en millisecondes. L'estimation de consommation mémoire exploite la classe standard `Runtime`.
- **Scénarios mixtes :** Des scénarios mélangent en désordre tous les types d'opérations (via `Collections.shuffle`).
- Chaque exécution du benchmark procède à un export CSV dans le fichier `resultats.csv`.

## 7. Résultats expérimentaux

*(Valeurs moyennées sur 5 itérations)*

### 7.1. Recherche, Suppression et Ajout (en ms)

| Taille | Structure | Ajout Total | Recherche (1000) | Suppression (1000) |
| --- | --- | --- | --- | --- |
| 1000 | ArrayList | 2.97 | 2.27 | 1.40 |
| 1000 | HashMap | 0.56 | 0.67 | 0.17 |
| 10000 | ArrayList | 36.11 | 10.47 | 3.66 |
| 10000 | HashMap | 0.68 | 0.49 | 0.05 |
| 50000 | ArrayList | 1944.30 | 54.21 | 47.12 |
| 50000 | HashMap | 3.92 | 0.61 | 0.08 |

### 7.2. Listing complet et Comptage (en ms)

| Taille | Structure | Listing Complet | Comptage Catégorie |
| --- | --- | --- | --- |
| 1000 | ArrayList | 0.02 | 0.15 |
| 1000 | HashMap | 0.10 | 0.30 |
| 10000 | ArrayList | 0.02 | 0.38 |
| 10000 | HashMap | 0.09 | 0.53 |
| 50000 | ArrayList | 0.02 | 0.64 |
| 50000 | HashMap | 0.63 | 2.45 |

### 7.3. Scénarios Mixtes (en ms)

| Taille | Structure | Recherche Intensive | Listing Intensif | Scénario Équilibré |
| --- | --- | --- | --- | --- |
| 1000 | ArrayList | 5.33 | 5.55 | 3.47 |
| 1000 | HashMap | 4.80 | 9.70 | 5.06 |
| 10000 | ArrayList | 13.91 | 20.60 | 19.45 |
| 10000 | HashMap | 18.47 | 39.72 | 19.02 |
| 50000 | ArrayList | 73.48 | 104.94 | 105.03 |
| 50000 | HashMap | 62.17 | 172.68 | 79.72 |

### 7.4. Empreinte Mémoire Estimée

| Taille | Structure | Mémoire (Octets) | Mémoire (Ko) |
| --- | --- | --- | --- |
| 1000 | ArrayList | 0* | 0* |
| 1000 | HashMap | 37 409 | 36 |
| 10000 | ArrayList | 0* | 0* |
| 10000 | HashMap | 543 520 | 530 |
| 50000 | ArrayList | 284 576 | 277 |
| 50000 | HashMap | 2 922 272 | 2853 |

*\* Note : Les mesures à 0 octet pour certaines petites tailles d'ArrayList proviennent probablement des limites de précision avec `Runtime` et des opérations asynchrones de la JVM.*

## 8. Analyse des résultats
D'après nos données expérimentales :

- **Recherche par identifiant :** La `HashMap` est nettement plus rapide. Elle conserve des temps très bas, alors que l'`ArrayList` voit ses temps d'exécution se dégrader fortement avec la volumétrie.
- **Suppression par identifiant :** La `HashMap` est également ici nettement plus rapide pour cette opération.
- **Listing Complet :** Dans ce cas, la tendance s'inverse : l'`ArrayList` est plus rapide grâce à sa structure linéaire simple.
- **Ajout initial :** L'ajout dans l'`ArrayList` est très coûteux ici. L'implémentation vérifie l'unicité de l'identifiant avant d'ajouter chaque contact, ce qui impose une recherche linéaire répétée sur des structures de plus en plus grandes.
- **Consommation Mémoire :** La structure tabulaire de l'`ArrayList` est peu coûteuse en RAM. En revanche, la `HashMap` consomme beaucoup plus de mémoire (près de 2,8 Mo contre environ 277 Ko pour la liste à 50000 éléments).

## 9. Lien avec les complexités théoriques
Nos mesures correspondent bien aux complexités algorithmiques théoriques :

**ArrayList :**

- Recherche : `O(n)` (recherche linéaire en parcourant le tableau).
- Suppression par id : `O(n)` (demande parfois de décaler les éléments dans le tableau).
- Listing : `O(n)` (parcours direct de l'ensemble de la liste, très léger en mémoire).

**HashMap :**

- Recherche et Suppression par id : `O(1)` en moyenne (accès direct calculé depuis le hash de la clé).
- Listing : `O(n)` (implique de parcourir toute la table et ses buckets internes, ce qui prend un peu plus d'instructions qu'un tableau contigu).
- Mémoire : Structure plus coûteuse (liée à la sauvegarde en objets `Map.Entry`, le maintien des pointeurs et des clés).

## 10. Limites de l'expérience
Il est important de garder en tête certaines limites lors de l'analyse :

- Les mesures sont dépendantes de la charge de la machine au moment du calcul.
- Le chronométrage `System.nanoTime()` est parfaitement adapté pour ce devoir étudiant, mais reste moins exact qu'un outil de micro-benchmarking avancé tel que JMH.
- L'estimation de la mémoire disponible est approximative à cause de la gestion automatique par la JVM et son ramasse-miettes (Garbage Collector).
- La taille extrême de 100000 contacts n'est pas testée par défaut pour éviter un temps d'exécution trop long sur de petites machines de prêt.
- Les résultats peuvent donc varier légèrement selon l'exécution et l'environnement matériel.

## 11. Conclusion
HashMap est préférable lorsque les opérations principales sont la recherche ou la suppression par identifiant. ArrayList reste intéressante lorsque l'objectif principal est de parcourir l'ensemble des contacts avec une structure simple et peu coûteuse en mémoire.

## 12. Usage des LLM

- Date : avril 2026.
- LLM utilisés : ChatGPT et Gemini/Antigravity.
- Usages : aide à la conception de l'architecture, élaboration du benchmark, ajout de la gestion CSV, compréhension des écarts de performance et aide à la rédaction de ce rapport.
- Le code a été programmé, compilé et testé localement par l'étudiant.

## 13. Empreinte carbone de l'usage des LLM

L'estimation exacte de l'empreinte carbone d'un LLM est complexe. Elle dépend du modèle, du nombre de tokens traités, de l'infrastructure et de la source de l'électricité (mix énergétique) du centre de données. Des études récentes évaluent l'empreinte énergétique d'une requête générative classique entre **0,1 gCO2e** (requête texte courte sur des serveurs efficaces avec forte énergie renouvelable) et **2 à 5 gCO2e** (limite haute pour des modèles lourds ou requêtes complexes) [1][2].

### 13.1. Protocole d’estimation chiffrée

Pour estimer l’empreinte carbone de l’usage des LLM, nous avons retenu les hypothèses suivantes :

- Nombre de prompts structurants : 9.
- Échanges secondaires : environ 10 à 15.
- Total estimé : environ 20 à 25 requêtes LLM significatives.
- Plusieurs prompts étaient longs, notamment ceux liés à la génération du code, à la rédaction du rapport et aux corrections successives.
- L’estimation ne correspond pas à une mesure directe : elle donne seulement un ordre de grandeur.

Pour ce projet, l’usage des LLM peut être estimé à environ 20 à 25 requêtes significatives, dont plusieurs prompts longs. En retenant une hypothèse basse de 0,1 gCO2e par requête et une hypothèse haute de 2 gCO2e par requête, on obtient une fourchette indicative d’environ 2 à 50 gCO2e.

Cette valeur reste faible à l’échelle d’un projet universitaire, mais elle n’est pas nulle. Elle rappelle l’intérêt de limiter les requêtes inutiles et de préparer des prompts plus précis.

*(Sources documentaires : [1] Estimates of LLM query CO2 impact based on independent data center assessments, smartly.ai ; [2] Synthèses sur la consommation des requêtes IA, Substack/Seedling.earth 2024)*

Les sources utilisées donnent des ordres de grandeur indicatifs. Elles ne permettent pas de mesurer précisément l’empreinte réelle des requêtes effectuées dans ce projet.

## 14. Annexes

Les prompts complets sont fournis dans le fichier `prompts_llm.md`.
Ce fichier doit être rendu avec le rapport, car il contient l'ensemble des prompts structurants du projet.