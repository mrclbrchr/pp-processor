# pp-processor

## Docker

You can generate docker images from all 3 projects on github referring to the practical project.
https://github.com/mrclbrchr/pp-processor

https://github.com/mrclbrchr/pp-datastream

https://github.com/mrclbrchr/pp-detector

To do so, just go inside the project folder and run:

```cli
mvn spring-boot:build-image
```

Afterwards you can run:

```cli
docker-compose up
```

Which will start consul, hivemq, the datastream, dataprocessor and protector.
