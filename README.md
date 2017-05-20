# java-LiveTrains

Rest server for interfacing with the mobile application. Information is downloaded from [Api Warszawa][1] and stored on [AWS][2] database. 
The server provides information about Warsaw tram network. This server allows for decreasing amount of calls to API.
The project was created with **Spring Boot** framework.

[Application][3] is deployed to [Heroku][4].

[1]: https://api.um.warszawa.pl/
[2]: awseducate.com/
[3]: https://still-reef-32346.herokuapp.com/stop/100604
[4]: https://www.heroku.com/

## Build: 

Instead of building service to a classic WAR file, the app is build to JAR file which makes it easy to ship, version, and most important deploy.

### Build with Maven:

To build the JAR file with Maven clone repository and after that in the same folder as `pom.xml` use:
```
./mvnw clean package
```
After that you can run the JAR file:
```
java -jar target/LiveTrains-0.0.2.jar
```
### Build with Eclipse IDE:

Select option `File -> Open Projects from File System...` and press `Finish`.
After that you can select `Run as... -> Java Application` on class containing main function or 
just build the JAR file with `Run as... -> Maven Build` and select goal to `clean package`.
To run JAR file:
```
java -jar target/LiveTrains-0.0.2.jar
```

## Shared resources:

### `/stop/{id}`
**returns:** Object `Stop` 
- Stop with given {id}

### `/stop/{id}/lines`
**returns:** List of Objects `Line` 
- List of all lines stopping at a given stop

### `/stop/{id}/lines/info`
**returns:** List of Objects `Timetable`
- List of upcoming arrivals according to schedule for every line

### `/stop/{id}/lines/info/distance`
**returns:** `Map<String, Double>`
- Map with keys being line numbers and values are distances from scheduled tram to given stop in kilometers

### `/stop/{id}/lines/info/position`
**returns:** List of Objects `Train`
- Every train with line number is in list of lines stopping at this stop

### `/{id}/line/{lineId}/timetable`
**returns:** List of objects `Timetable`
- Full timetable for the given line specified by {lineId} and stop with given {id}

### `/delay/{timetablePosition}`
**returns:** Object `Delay`
- Delay for timetable position specifide by {timetablePosition}

***

### `/updateTimetable`
- Forces update to Timetable database
### `/truncateTimetable`
- Deletes everything from Timetable database
### `/runningTrains`
- All currently running trams
### `/updateTimetable/line/{lineId}/stop/{stopId}`
- Forces update to Timetable for given stopId and lineId
### `/history`
- Shows info is train history being saved to database
### `/history/t`
- Switches saving history