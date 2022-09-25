# Mashe

Simple, fast, and easy to use Event Handling for Java.

## Usage

```java
import hyro.mashe.Mashe;
import hyro.mashe.types.Event;
import hyro.mashe.annotations.Listen;

public class Example {
    public static void main(String[] args) {
        // Create a new mashe
        new Mashe();
        
        // Register a listener (you can also use annotations)
        Mashe.getInstance().register(ExampleEvent.class, (event) -> {
            System.out.println("Hello World!");
        });

        // Register events with Listener annotation
        new ExampleV2().start();
        
        // Post an event
        Mashe.getInstance().fire(new ExampleEvent());
    }
}

class ExampleV2 {
    public void start() {
        Mashe.getInstance().register(this);
    }

    @Listen
    public void onExampleEvent(ExampleEvent event) {
        System.out.println("Hello World with annotations!");
    }
}

class ExampleEvent extends Event {
    // ...
}
```

## Imports

### Maven

```xml
<distributionManagement>
    <repository>
        <id>github</id>
        <name>mashe repository</name>
        <url>https://maven.pkg.github.com/xHyroM/mashe</url>
    </repository>
</distributionManagement>

<dependency>
    <groupId>com.xhyrom</groupId>
    <artifactId>mashe</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Gradle

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/xHyroM/mashe")
    }
}

dependencies {
    implementation 'com.xhyrom:mashe:0.1.0'
}
```

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/xHyroM/mashe")
    }
}

dependencies {
    implementation("com.xhyrom:mashe:0.1.0")
}
```
