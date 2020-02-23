# Weather UI

This project was generated with Spring Boot `2.2.4.RELEASE`, Java `1.8`.

## GitHub URL

[weather_api](https://github.com/dllyj86/weather_api)  
URL: <https://github.com/dllyj86/weather_api>

## Jenkins URL

[Jenkins](http://52.76.30.89:8080/job/weather_api_cicd/)  
URL: <http://52.76.30.89:8080/job/weather_api_cicd/>

If you want to visit my Jenkins, please check user name and password in email.

**Notice:**  
If you want to run the build, please inform me in advance. I need to change my EC2 from t2.micro to a larger type or the Jenkins will crash.

## Deployment

I use AWS CodeDeploy to make the deployment. The application is running in AWS EC2. Successful Jenkins build will trigger the deployment automatically.

Screenshots:  

![deployment group config](https://jimmy-demo-static-files.s3-ap-southeast-1.amazonaws.com/api+1.PNG)

![deployment records](https://jimmy-demo-static-files.s3-ap-southeast-1.amazonaws.com/api+2.PNG)

## Server information

**Production API endpoint:**  
Getting city list:  
<http://52.76.30.89:8888/api/weather/citylist>

Getting weather for Sydney:  
<http://52.76.30.89:8888/api/weather/current?city=Sydney>  
You can change the value of `city` parameter to `Melbourne` or `Wollongong`.

**Notice:**  
This application was deployed in AWS Singapore region.

## Local Build and Run

You can also build this application in your local and run it locally.

Steps:  

1. Go to scripts folder of this project  

2. Run `local_maven_build_and_run.sh` or `local_maven_build_and_run.cmd`

3. The script will build and run the application in local  
The application will consume port 8888.

Screenshot:  
![Screenshot](https://jimmy-demo-static-files.s3-ap-southeast-1.amazonaws.com/local+1.PNG)

## Code structure

### common package

It keeps all common stuff.

1. WeatherApiClient  
Http client for calling weather API.

2. CallingApiService  
This service extends Task class. It has `run` method that is called by WorkflowScheduler.  
This service calls WeatherApiClient.

3. CityListService  
This service returns city name list to controller directly. The city name list is injected with `cityNameList` bean in `workflow-config.xml`.

4. ConditionCodesXmlLoader  
This class has `Map<String, String> conditionCodesMap` proprty to keep the mapping of weather condition code and description. When the map is required first, this class loads `weather-condition-codes.xml` file to get the data and keep the data in the map and return the map.

    <font color="#dc143c">**Design Pattern:**</font>  
    This class use a `"Singleton Pattern"`. If conditionCodesMap has data (not null), return it directly.

5. WorkflowScheduler
This class has `Map<String, List<Task>> workflowStore`. It keeps the mapping of `workflow name` and `workflow's tasks`.  
The data of workflowStore was injected by the framework. The configuration of the injection is is `workflow-config.xml`.

    <font color="#dc143c">**Design Pattern:**</font>  
    WorkflowScheduler uses `"command pattern"`. All tasks extends `abstract "Task" class` so all of them has `"run"` method. WorkflowScheduler will loop the task list and call all tasks' run method one by one to complete the whole process.

6. Task
This class is abstract class. It is parent class of all "tasks". "run" method will be called by WorkflowScheduler. This class offers some common method to set and get "UI query data" and "Backend Query data" from workflowInputData for convenient.

7. AppConfig  
This class is for application configuration. It configures CORS setting.

### current package

It keeps classes for query city name list and city weather.

1. CurrentWeatherController  
It is the only controller of this application. It handles the request mapping.

2. CurrentWeatherService  
This service is for querying city weather. It pass `workflow name` to WorkflowScheduler so the scheduler could call related tasks to complete the whole process.

3. ParsingCurrentWeatherRequestService  
It get "UI query data" and put it to workflowInputMap as "backend query data". Subsequent tasks can get the "backend query data" by default key easily.

4. CurrentWeatherResponseConverter  
It converts API response data to application model.

### resources

1. application.properties  
It keeps API connection information, application port and CORS config.

2. weather-condition-codes.xml
It keeps the mapping data of weather condition code and description.

3. workflow-config.xml
It keeps the configuration of workflow and tasks. It also keeps the city name list which is used to show city name list in UI dropdown.

### scripts folder

It keeps scripts (clean, stop, startup) that are run by AWS CodeDeploy.

local_maven_build_and_run.bat and sh are for building and running the application in local.

**Notice:**  
You have to install Java and Maven in your local and the related path enviornment and other configuration must be setup correctly.

### diagrams folder

It keeps all class diagrams and sequence diagrams of this application.

### Other files

1. WeatherApiApplication  
It's main entry of this application.

2. appspec.yml
It is configuration of AWS CodeDeploy.

3. gradle related files are useless. They are generated by the scaffold.

4. settings.xml lists Ali maven repositories. If your network is too slow to connect with central maven repository, you can use this settings file.

### Screenshots

![screenshot 1](https://jimmy-demo-static-files.s3-ap-southeast-1.amazonaws.com/api+3.PNG)

![screenshot 2](https://jimmy-demo-static-files.s3-ap-southeast-1.amazonaws.com/api+4.PNG)

## Reusable and extenable

1. WorkflowScheduler and tasks make the application reusable.  
When you want to implement new process, you just need to create new tasks for specific logic. If there is existing task that meets your sepecific requirement, you can reuse it in your workflow. The tasks make up new workflow that helps you to complete your process.  

    For example:  
    You want to query the air quality for Sydney. You can reuse below existing tasks:  

    ParsingCurrentWeatherRequestService  
    CallingApiService  

    You only need to create one new task:  

    CurrentAirQualityConverter (it gets air quality from API response and put it in model)

2. If you want to add new city to the dropdown, you only need to add the new city name in workflow-config.xml. It's very convenient.
