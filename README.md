# Grocery Store Console Application

- --
GroceryStoreApp project provides a simple console application which contains Cart and Order objects.
Cart objects have Product list as an abstract layer, this layer extended by `Bread`, `Beer` and `Vegetable` objects.
The business rules for the discount strategy are applied in the `DiscountStrategy` interface and all strategies are differentiated by Strategy Design Pattern.

# Architecture
- --
- Maven
- Java Object Oriented Arhitecture
- Strategy Design Pattern
- Polymorphism
- JUnit & Mockito
- SL4J Logger

# Prerequisites
- ---
 - Java JDK 1.8
 - Maven

# Run & Build
- --
To build and run `GroceryStoreApp` application you can follow the belor directions;
```sh
$ cd GroceryStore
$ mvn clean install exec:java
```
