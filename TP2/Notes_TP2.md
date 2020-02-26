Ne pas de faire de test and set sur has_cs.

Algorithme de Fischer

Les deux processus se demandent si la variable est nulle.
Si oui, ils essaient de l'attribuer avec leur identifiant.
Donc has_cs devient myID ou attend (si qqun l'a déjà demandé).

Fischer propose une double vérification basée sur le temps : on attend un certain délai pour faire ça :
si has_cs vaut myID on continue, s'il vaut autre chose on repart à la case départ.

## Algorithme de Fischer

### Question 7

Avant correction : delay = 3

La propriété vérifiant que deux processus distincts ne peuvent pas être tous les deux dans la même section n'est pas vérifiée.
