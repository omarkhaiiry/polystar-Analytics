# analytics
Analytics
analyse text file and get the top repeated words and its count

------------------------------------------

#### System
* Maven >= 4.0.0
* JDK > 8

------------------------------------------

#### IDE setup
* Clone the repo and open pom.xml file with intelliJ, and automatically it will download all the needed dependencies
* Click on "Run" from the upper main menu and select "Edit Configurations"
* Press on the PLUS button to add new configuration and select "Application"
* Give the application any name you want
* Choose the Main class for the app -> For this project it will be "com.polystar.analytics.AnalyticsApplication"
* On "Use class module" select the application "Analytics"

------------------------------------------

# env vars
The values below are the env var name with the default value in case of it is not provided

------------------------------------------

#### Server
* PORT:8080
* TIME_ZONE:Africa/Cairo

------------------------------------------

###Websocket 
you can connect on the websocket on  ws://localhost:8080/top-word-analytics 
with body of { line: anytext }

### HTTP
Gets Top 5 words from local files
http://localhost:8080/api/analytics/most-repeated-words?filesPaths=&topWordsCount=<5>
  
  
#######Need to depoloy https://github.com/omarkhaiiry/polystar-publisher first  then you can use this api 
http://localhost:8080/api/analytics/read-count?filesPaths=<textFilepath>,<AnotherTextFilePath>

  
