**1. Executable JAR**

**Build Tool Configuration**

Ajouter 
```
<packaging>jar</packaging>
```

Dans build plugin configuration, ajouter le Buildpacks:
```
<image>
	<name>mchienduong/${project.artifactId}:latest</name>
</image>
```
     
**Build**

```
mvn clean install
```

**Execution**

```
java -jar tondeuse-0.0.1-SNAPSHOT.jar
```

**2. Docker Containerization**

**La première façon, nous pouvons utiliser les Buildpacks**

```
mvn spring-boot:build-image
```

**La seconde manière, nous pouvons écrire un fichier docker**

```
FROM openjdk:17-slim

WORKDIR /app

COPY tondeuse-0.0.1-SNAPSHOT.jar /app/tondeuse.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tondeuse.jar"]
```

**Build**

```
docker build -t tondeuse-app .
```

**Run**

```
docker run -d -p 8080:8080 tondeuse-app
```

**3.Orchestration avec Kubernetes**

Pour l'évolutivité et la facilité de gestion dans les environnements de production, envisagez de déployer l'application conteneurisée sur Kubernetes.
