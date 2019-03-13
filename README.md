# Mater

Pre-requisites:
- Java 8 (JDK) installed
- Redis server up and running

To build the jar run following command (after unzipping and navigating inside) :
```sh
./gradlew build
```

This will create a jar file (metar.jar) at location build/libs.
To run the jar :
```sh
cd build/libs/
java -jar metar.jar
```


That's it. Server is now up and running. Navigate to your browser or better any REST API client, and try : http://localhost:8080/metar/ping

## Debugging
1. The server starts by default on port 8080, in case you want to change it (something else running on 8080) refer to next section to change default port.
2. By default redis is expected to be running on localhost:6379, that too without any authentication. To change again refer next section.
3. Still any issue, contact at rajatgl17@gmail.com

## Configure properties
You can configure properties by editing file src/main/resources/application.properties file, before building jar (can do later as well, but not discussed here)

List of properties:
- server.port=8080
- mater.url=https://tgftp.nws.noaa.gov/data/observations/metar/stations/{{scode}}.TXT
- mater.cache.time.seconds=300
- spring.redis.host=localhost
- spring.redis.port=6379
- spring.redis.password=

