# CDI-CONFIG

Project for the management of configurations values through CDI injection

## OVERVIEW

This api try to provide an strategy for manage in a centralized way the configuration
values in a project using JCache for store the values and can do a faster querying.

## CUTOMIZATION

For create your own source of values, you must declare a ```Map<String, String>```
 produces in your project, the config initializer take this information an put
 in the cache for futures queries.
 
```java
@Produces
public Map<String, String> producesMyInitialValues(){
    Map<String, String> initialValues = new HashMap<>();
    
    initialValues.put("key1", "value1");
    initialValues.put("key2", "value2");
    
    return initialValues;
}
```

These values can be produces manually, from a file, local environment, DB, etc.

## MENTIONS

This idea was taken of the book [Real World Java EE Patterns - Adam Bien](http://realworldpatterns.com/).