

### Create the parent project
```bash
mvn archetype:generate -DgroupId=com.demo -DartifactId=parent-demo-gcloud-project
```

### Move to the parent project
```bash
cd parent-demo-gcloud-project
```

### Edit the pom.xml 
```bash
vim pom.xml
```

### Add the  tag packaging with the value pom
```xml
 <packaging>pom</packaging>
```

### inside the parent project create a moduel call logging
```bash
mvn archetype:generate -DgroupId=com.demo.logging -DartifactId=logging
```
