# Dashery Clothing Query [![Build Status](https://travis-ci.org/Cowbacca/dashery-clothing-query.svg?branch=master)](https://travis-ci.org/Cowbacca/dashery-clothing-query) [![codecov.io](https://codecov.io/github/Cowbacca/dashery-clothing-query/coverage.svg?branch=master)](https://codecov.io/github/Cowbacca/dashery-clothing-query?branch=master)

Finds clothes with given tags.

## Usage
GET the */clothing/search?={category}:{value},{category}:{value}* endpoint to be given a JSON list of [Clothing objects](https://github.com/Cowbacca/dashery-clothing-query/blob/master/src/main/java/uk/co/dashery/data/Clothing.java) which match the values for the given categories..

## Deployment

Standard Spring Boot deployment.  Requires the following properties to be present in the environment, whether via yml/properties file or environment variable:

```
spring:
  cloud:
    config:
      profile: #e.g. will get values from the dashery-autocomplete-{profile}.properties file from the config server.
      uri: #the uri of the config server, e.g.  http://dashery-config-server.herokuapp.com 
      username: #username to access the config server.
      password: #password to access the config server.
```
