# Weather UI

This project was generated with Spring Boot `2.2.4.RELEASE`, Java `1.8`.

## GitHub URL

[weather_api](https://github.com/dllyj86/weather_api)  
URL: <https://github.com/dllyj86/weather_api>

## Jenkins URL

[Jenkins](http://52.76.30.89:8080/job/weather_api_cicd/)  
URL: <http://52.76.30.89:8080/job/weather_api_cicd/>

If you want to visit my Jenkins, please use user name `visitor` and password `12345678`.

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

### Modules

1. app.module  
This is main module of this application.

2. custom-material.module  
This is custom config module to import Angular Material modules for UI component.

### Components

1. app.component  
Main component of this application.

2. city-dropdown.component  
This component shows dropdown for cities. User can choose any city in the dropdown to query its current weather.  

3. current-weather-table.component  
This component shows a table that contains weather information of the city.

4. error-message.component  
This is common component that shows error message in UI when service call is failed.

### Services

1. current-weather.service  
This service sends request to query cities and city's weather.

### Models

1. weather
This model keeps weather data.

### mocks

1. city-list-mock.json  
This is mock file for showing city dropdown.

2. city-weather-mock.json  
This is mock file for showing city weather.

### environments

1. environment  
This file keeps application properties for dev environment.

2. environment.prod  
This file keeps application properties for production environment.
