# MatchTimeConverter
Converter that can take a String representing match time in one format, and convert it to a
String representing match time in another format.

Input

[period] minutes:seconds.milliseconds

Output

normalTimeMinutes:normalTimeSeconds - period
## To run the project
```
$ git clone https://github.com/Jonatans2014/MatchTimeConverter
$ cd MatchTimeConverter-master
$ mvn install
After building:
$ cd target
$ java -jar matchTimeConverter.jar
Else by entering a file name as following:
$ java -jar matchTimeConverter.jar location/input.txt
```

## To test the project
```
$ cd MatchTimeConverter-master
$ mvn test
```