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
        Mashe mashe = new Mashe();
        
        // Register a listener (you can also use annotations)
        mashe.register(ExampleEvent.class, (event) -> {
            System.out.println("Hello World!");
        });

        // Register events with Listen annotation
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
With [jitpack](https://jitpack.io/#xHyroM/mashe)

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.xHyroM</groupId>
    <artifactId>mashe</artifactId>
    <version>v0.1.1</version>
</dependency>
```

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.xHyroM:mashe:v0.1.1'
}
```
