Les ressources du projet ainsi que le contre rendu du projet se trouvent dans le dossier res.
le code source du projet se trouve dans le dossier src.

Pour executer le programme indépendament de tout IDE ou de toute librairie a installer sur celui-ci :
 - ouvrir l'invite de commande 
 - cd pathToProject/Snorkunking/out/artifacts/Snorkunking_jar (chemin ver le jar ex�cutable)

Puis executer cette commande pour executer le jar :
 - Sous Linux / Mac  : java -jar -Djava.library.path=slick Snorkunking.jar
 - Sous Windows :      java -jar -Djava.library.path=slick -Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL=true Snorkunking.jar
