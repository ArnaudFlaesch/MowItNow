# MowItNow

[![CI](https://github.com/ArnaudFlaesch/MowItNow/actions/workflows/ci.yml/badge.svg)](https://github.com/ArnaudFlaesch/MowItNow/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ArnaudFlaesch_MowItNow&metric=alert_status)](https://sonarcloud.io/dashboard?id=ArnaudFlaesch_MowItNow)
[![codecov](https://codecov.io/gh/ArnaudFlaesch/MowItNow/branch/master/graph/badge.svg)](https://codecov.io/gh/ArnaudFlaesch/MowItNow)

# Fonctionnement

Au lancement du programme, l'utilisateur doit choisir un fichier dans lequel sont
renseignés les informations nécessaires au fonctionnement de l'application : la première
ligne doit contenir les dimensions de la carte et le reste du fichier doit contenir les informations
relatives à la position et aux actions des tondeuses.
Une fois le fichier sélectionné, la simulation démarre et les tondeuses se déplacent
en exécutant chacune tour à tour l'ensemble de leurs actions avant que la suivante se déplace et termine en
affichant sa position et sa direction.

Une fois que la dernière tondeuse a terminé de se déplacer, l'application s'arrête.

# Lancer l'application

* Installer Gradle 7
* Cloner le projet
  
* Exécuter la commande <code>gradlew run</code>
* Ou exécuter la commande <code>gradlew assemble</code> et exécuter la commande
  <code>java -jar mowitnow-1.0.jar</code> dans le répertoire build/libs.
  
# Lancer les tests

<code>gradlew test</code>

