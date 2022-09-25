# Mashe

Simple, fast, and easy to use Event Handling for Java.

## Usage

```java
import hyro.mashe.Mashe;
import hyro.mashe.types.Event;
import hyro.mashe.annotations.Listen;

public class Example {
    public Mashe mashe;
    public static void main(String[] args) {
        // Create a new mashe
        mashe = new Mashe();
        
        // Register a listener (you can also use annotations)
        mashe.register(ExampleEvent.class, (event) -> {
            System.out.println("Hello World!");
        });

        // Register events with Listener annotation
        new ExampleV2().start(mashe);
        
        // Post an event
        mashe.fire(new ExampleEvent());
    }
}

class ExampleV2 {
    public void start(Mashe mashe) {
        mashe.register(this);
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
    <groupId>xhyrom.packages</groupId>
    <artifactId>mashe</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Gradle

#### Groovy

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/xHyroM/mashe")
    }
}

dependencies {
    implementation 'xhyrom.packages:mashe:0.1.0'
}
```

#### Kotlin

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/xHyroM/mashe")
    }
}

dependencies {
    implementation("xhyrom.packages:mashe:0.1.0")
}
```
