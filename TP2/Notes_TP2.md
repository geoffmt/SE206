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

Le problème réside dans le fait que lorsque P1 entre en section critique (L4toLk), P2 passe en état L2 juste après puisque has_cs prend la valeur 2. Ainsi, quelques tours d'horloge plus tard P2 entre en section critique puisqu'aucun test ne l'en a empêché. On a donc P1 et P2 tout deux en section critique.

Pour corriger le problème, il suffit d'empêcher P1 d'entrer en section critique si P2 est sur le point de passer à l'état L2 et donc de mettre à jour has_cs. On augmente donc le delay de 1 pour permettre à P1 d'arriver un tour plus tard à l'état L3. Ainsi, P2 mettra à jour has_cs à 2 avant que P1 n'entre en section critique. P1 reviendra donc à l'état initial via L3goto puisque has_cs!=1 (il vient d'être mis à jour par P2).


#Partie 2

## Propriété d'équité de l'algorithme de synchronisation:
Cette propriété n'est pas vérifiée puisque un processus peut reprendre la main après être sorti de sa section critique, ne laissant pas la main à l'autre processus.

## Ajout d'une brne sur le temps passé en section critique/
Si le processus P1 contient une boucle while(1) et qu'il est dans sa section critique alors le processus P2 ne pourra peut-être jamais prendre la main.
La borne limitant le temps passé en section critique ne sert en fait à rien puisque notre état L4toLk ne fait rien dans notre modèle.

## Ajout d'une contrainte de temps pour le processus sorti de section critique et retournant à L0
Il faut rajouter un état d'attente pour le processus qui vient de sortir de la section critique pour laisser le temps à l'autre processus de reprendre la main. Cette contrainte sur le retour à l'état L0 du processus venant de sortir de sa section critique est nécessaire pour que chaque processus prenne la main l'un après l'autre équitablement.

Faison une étude pour déterminer le temps pendant lequel le processus sortant de sa section critique doit attendrre avant de repasser à l'état L0
Dans le pire des cas, P2 se trouve en L0 et P1 se trouve en section critique. P2 reste au maximum 4 cycles en L0goto (exec<C) puis au maximum 4 cycles en L0 (exec<C) puis au maximum 4 cycles en L1 (exec<C) puis au maximum 4 cycles en L2 (exec<C) puis 4 cycles de waiting avant d'arriver à L3, puis au maximum 4 cycles en L3 (exec<C).
