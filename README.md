# tourist-core

A Java library for intercepting and tracking method execution using AspectJ, with a "tourist" metaphor where method calls are "tours" and execution details are captured as "shots" on a "camera roll".

## Overview

Tourist-core provides an AOP-based framework for monitoring method execution flows. It uses AspectJ to intercept method calls and capture execution data through a camera/shot metaphor:

- **Tourist**: The main aspect that intercepts method calls
- **Camera**: Captures execution details ("shots") during method execution
- **CameraRoll**: Stores the sequence of shots taken during a tour
- **Tour**: Represents a single method execution context
- **Shot**: A single captured piece of information with timestamp

## Features

- **Method Execution Tracking**: Automatically intercepts and tracks method calls
- **Nested Call Support**: Handles nested method invocations with proper context management
- **Event System**: Fires events for tour lifecycle (started, ended, failed)
- **Conditional Execution**: Only tracks methods that meet specified conditions
- **Thread-Safe**: Uses ThreadLocal for isolated tracking per thread
- **Extensible**: Pluggable event listeners and camera roll implementations

## Requirements

- Java 6 or higher
- AspectJ Runtime 1.7.4
- JUnit 4.12 (for tests)
- EasyMock 3.4 (for tests)

## Core Components

### Tourist API

```java
// Get the tourist's camera
Camera camera = tourist.getCamera();

// Take a shot during method execution
if (camera.isOn()) {
    camera.shot("Important event occurred");
}
```

### Event Listeners

Implement `TourEventListener` or extend `TourEventListenerAdapter`:

```java
public class MyListener extends TourEventListenerAdapter {
    @Override
    public void onTourStarted(Tour tour) {
        // Handle tour start
    }
    
    @Override
    public void onTourEnded(Tour tour) {
        // Access shots taken during the tour
        for (Shot shot : tour.getCameraRoll().getShotList()) {
            System.out.println(shot.getPicture());
        }
    }
}
```

### Tour Events

- `TOURIST_TRAVEL_STARTED`: First method in call chain intercepted
- `TOUR_STARTED`: Any intercepted method begins execution
- `TOUR_ENDED`: Method completes successfully
- `TOUR_FAILED`: Method throws an exception
- `TOURIST_TRAVEL_ENDED`: Last method in call chain completes

### Conditions

Control when to start tracking:

```java
public class HasClassAnnotationCondition implements Condition {
    @Override
    public boolean check(ProceedingJoinPoint proceedingJoinPoint) {
        // Return true to enable tracking for this method
        return targetClassHasAnnotation();
    }
}
```

## Configuration

Spring XML configuration example:

```xml
<bean id="tourist-parent" class="io.tourist.core.api.TouristImpl" abstract="true">
    <property name="cameraRollFactory">
        <bean class="io.tourist.core.api.CameraRollFactoryImpl" />
    </property>
    <property name="tourEventListenerSet">
        <set>
            <!-- Add your event listeners here -->
        </set>
    </property>
    <property name="condition">
        <bean class="io.tourist.core.condition.HasClassAnnotationCondition">
            <property name="clazz" value="com.example.YourAnnotation"/>
        </bean>
    </property>
</bean>
```

## Example: Shot Printer

The included `ShotPrinterTourEventListener` prints execution traces:

```
-- START TRAVEL --
outerMethod():
    innerMethod():
        SHOT #1: Database connection established
        SHOT #2: Query executed
    SHOT #1: Processing results
    SHOT #2: Sending notification
-- END TRAVEL --
```

## Building

```bash
mvn clean install
```

## Testing

```bash
mvn test
```

## Architecture

- **api**: Core interfaces and implementations (Tourist, Camera, Tour, Shot, CameraRoll)
- **condition**: Conditional execution logic
- **event**: Event system for tour lifecycle tracking

## Thread Safety

Tourist-core uses `ThreadLocal` storage for:
- Tour stacks (for nested method tracking)
- Camera instances
- String builders (in event listeners)

This ensures complete isolation between concurrent executions.

## License

See project documentation for licensing information.
