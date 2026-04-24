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
- **`HashMap<Integer, Contact>`** : une table de hachage associant une clé à une valeur. Ici, la clé utilisée est logiquement l'`id` du contact.

## 4. Opérations étudiées
Les fonctionnalités mesurées sont les suivantes :
- L'ajout d'un contact (en vérifiant d'abord que son l'identifiant n'existe pas déjà).
- La suppression d'un contact par son identifiant.
- La recherche d'un contact par son identifiant.
- Le listing complet (retourner tous les contacts).
- Le comptage des contacts groupés par catégorie.

## 5. Génération des données
Afin d'obtenir des données variées pour les tests, un générateur crée automatiquement des profils de contacts.
- Les catégories possibles (attribuées aléatoirement) sont : Famille, Amis, Travail, Universite, Autre.
- Les identifiants générés sont uniques et incrémentaux, ainsi que les numéros de téléphone et emails, assurant la diversité du répertoire.

## 6. Protocole expérimental
Afin de limiter les variations imprévisibles de la machine :
- **Tailles testées :** 1000, 10000 et 50000 contacts.
- **Répétitions :** Chaque mesure est exécutée 5 fois et la valeur affichée est une moyenne.
- **Outils de mesure :** Le chronométrage repose sur `System.nanoTime()` converti par la suite en millisecondes. L'estimation de consommation mémoire exploite la classe standard `Runtime`.
- **Scénarios mixtes :** De véritables scénarios mélangent en désordre tous les types d'opérations (via `Collections.shuffle`).
- Chaque run du benchmark procède automatiquement à un export CSV dans le fichier `resultats.csv`.

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
D'après nos données empiriques :
- **Recherche par identifiant :** La `HashMap` est beaucoup plus efficace. Elle conserve des temps quasi nuls avec une échelle grandissante. L'`ArrayList` flanche rapidement face à la volumétrie.
- **Suppression par identifiant :** La `HashMap` est également ici considérablement plus efficace.
- **Listing Complet :** C'est à ce moment précis que la tendance s'inverse : l'`ArrayList` est meilleure et beaucoup plus rapide que l'autre composant matériellement fragmenté.
- **Ajout initial :** Grosse faiblesse dans notre implémentation : l'ajout dans la liste `ArrayList` est très coûteux. En effet, elle intègre une sécurité qui vérifie l'unicité de l'identifiant pour interdire les doublons, ce qui force une recherche linéaire systématique avant d'autoriser chaque ajout. 
- **Consommation Mémoire :** Sans surprise, allouer une architecture par un tableau est hyper efficace. La mémoire prise par une `HashMap` est significativement plus lourde et consommatrice (presque dix fois plus sur les grands volumes observés).

## 9. Lien avec les complexités théoriques
Tout ceci respecte la théorie structurelle. Notamment de façon simplifiée :

**ArrayList :**
- Recherche : `O(n)`
- Suppression par id : `O(n)` (nécessite le décalage potentiel des éléments)
- Listing : `O(n)`
- C'est de fait une disposition très simple.

**HashMap :**
- Recherche : `O(1)` (en moyenne, l'index est identifié de façon statique)
- Suppression : `O(1)` (en moyenne)
- Listing : `O(n)` (implique un appel plus long pour restituer les valeurs cachées derrière des clés sur divers espaces hachés)
- Mémoire : structure plus coûteuse (liée aux calculs d'empreinte et noeuds stockés).

## 10. Limites de l'expérience
L'évaluation se heurte néanmoins à des limites purement structurelles du langage ou de l'environnement :
- Les mesures restent très dépendantes de la charge de la machine hôte.
- Le chronométrage `System.nanoTime()` est suffisant pour le cadre d'un devoir étudiant tel que celui-ci, mais moins rigide et mathématique qu'un benchmark avancé spécialisé (JMH).
- Une mesure précise et figée de la RAM est quasi utopique vue l'imprévisibilité de la JVM et de l'intermédiaire du garbage collector.
- Le test ultime des 100000 contacts a été retiré de ce rapport : bien trop lourd (notamment à l'ajout) pour beaucoup d'équipements de nos laboratoires étudiants partagés.
- Les chronos recensés ci-dessus peuvent donc légèrement varier même sur la même machine.

## 11. Conclusion
Le choix final dépend uniquement du type d'usage. Nos tests révèlent d'une part qu'une **HashMap** est l'outil inévitable si les contacts doivent faire l'objet de très régulières fouilles ou modifications précises ciblant l'identifiant. Mais d'autre part, si c'est la dimension "Annuaire Global de Consultation" qui opère, où l'utilisateur ne fera que scroller, lister la totalité, sans chercher et surtout en faisant attention à l'intégrité de la RAM globale du système : dans ce cas l'**ArrayList** sera paradoxalement l'option la plus intelligente.

## 12. Usage des LLM
- Date de contribution : avril 2026.
- Modèles exploités : ChatGPT et la console de développement assistée locale (Gemini/Antigravity).
- Usages : aide conceptuelle, production d'exemples de bases, amélioration générale du process de benchmark, accompagnement aux analyses des temps, compilation mathématique du CSV final, aide à la syntaxe et formulation de la rédaction.
- Validation : Le code Java final a été éprouvé, testé, débogué et optimisé de façon locale par le développeur originel et de multiples exécutions valides et répétitives se sont effectuées avant la rédaction.

## 13. Empreinte carbone de l'usage des LLM
Déterminer un coût d'empreinte absolue pour de simples invites ne reste que pur fantasme aléatoire ; une estimation complète dépend intimement du modèle, de l'optimisation des centres serveurs, de l'activité ou de l'heure des calculs, et du calibrage général de l'infrastructure. Cependant, la prudence nous pousse à une mesure qualitative en avouant qu'un usage modéré via de nombreuses itérations de programmation à larges instructions (prompts) suscite un impact non nul de la part globale mais largement amoindri, cadré et proportionnel s'il vient servir d'outil universitaire final modéré à une seule machine.

## 14. Annexes

### Prompt #1
> "Je dois réaliser un devoir maison en Java pour un cours de Structures de données. Sujet 9 : RepertoireContacts. Opérations typiques : ajout, suppression, recherche par identifiant, listing complet [...]"

### Prompt #2
> "Je veux que tu modifies/améliores le projet existant... Mesures Répétées et Moyennes (5 fois), Mesure de RAM via Runtime, Melange réel de Scénarios, et format de sortie purement CSV..."

### Prompt #3
> "Le benchmark fonctionne mais il est trop long avec 100000 contacts. Ajoute un mode par défaut réduit et mets le plus gros chiffre en commentaire optionnel..."
