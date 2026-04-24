# Guide de Présentation Vidéo - RepertoireContacts

Ce guide a été spécifiquement conçu pour t'aider à enregistrer ta présentation vidéo de 5 minutes. Suis ce script et ce plan étape par étape.

## A. Objectif de la vidéo
L'objectif est d'expliquer rapidement au correcteur :
- Le problème traité (le répertoire de contacts).
- Le lancement des expérimentations.
- Quelques résultats significatifs prouvant que le projet fonctionne.
- Ta conclusion sur le choix entre `ArrayList` et `HashMap`.

## B. Préparation avant enregistrement
Ouvre à l'écran les éléments suivants pour être prêt à naviguer rapidement :
1. Ton dossier de projet dans Antigravity ou VS Code.
2. Le fichier `README.md`.
3. Le fichier `rapport.md`.
4. Le code `src/Contact.java`.
5. Le code `src/RepertoireContacts.java`.
6. Le code `src/RepertoireArrayList.java`.
7. Le code `src/RepertoireHashMap.java`.
8. Le code `src/Benchmark.java`.
9. Le **terminal** ouvert.
10. Le fichier de traces `resultats.csv`.

## C. Commandes à préparer
Garde ces commandes prêtes ou tape-les en direct lors de la vidéo :
```bash
cd ~/OneDrive/Documents/Master-ICO/S2_ICO/structure_de_donnees/devoir_maison/devoir-structures-contact
rm src/*.class
javac src/*.java
java -cp src Main
```
*Il faut absolument attendre le message `COMPARAISON TERMINÉE`.*

## D. Plan minute par minute
- **0:00 à 0:30** : introduction du sujet
- **0:30 à 1:15** : présentation des structures
- **1:15 à 2:10** : présentation rapide du code
- **2:10 à 3:00** : lancement du programme
- **3:00 à 4:20** : lecture des résultats principaux
- **4:20 à 5:00** : conclusion

## E. Script oral complet

- "Mon sujet est RepertoireContacts."
- "Je compare `ArrayList<Contact>` et `HashMap<Integer, Contact>`."
- "Je teste l'ajout, la suppression, la recherche par identifiant, le listing complet et le comptage par catégorie."
- "Les tailles testées sont 1000, 10000 et 50000 contacts."
- "Chaque mesure est répétée 5 fois."
- "HashMap est meilleure pour la recherche et la suppression par identifiant."
- "ArrayList est plus intéressante pour le listing complet et la mémoire."
- "Le choix dépend donc de l'usage."

## F. Ce que je dois montrer à l'écran
- **README.md** pour présenter le projet.
- **Contact.java** pour montrer l'objet métier.
- **RepertoireContacts.java** pour montrer l'interface.
- **RepertoireArrayList.java** pour montrer la version ArrayList.
- **RepertoireHashMap.java** pour montrer la version HashMap.
- **Benchmark.java** pour montrer les tailles et les scénarios.
- **Terminal** pour compiler et lancer.
- **resultats.csv ou rapport.md** pour montrer les résultats.
- **rapport.md conclusion** pour finir.

## G. Conseils d'enregistrement
- Zoomer l'écran.
- Parler lentement.
- Ne pas lire tout le code.
- Montrer seulement les parties importantes.
- Éviter de dépasser 5 minutes.
- Si le programme prend trop de temps, montrer directement `resultats.csv` déjà généré après avoir expliqué la commande.

## H. Checklist finale avant rendu
- [ ] `src/` avec le code Java
- [ ] `README.md`
- [ ] `rapport_RepertoireContacts.pdf`
- [ ] éventuellement `rapport.md`
- [ ] éventuellement `resultats.csv`
- [ ] vidéo de présentation
- [ ] prompts LLM déjà inclus dans le rapport
