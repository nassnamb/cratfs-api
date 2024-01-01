# cratfs-api
crafts Management API

This application is powered by Spring boot and React Js

## Installation

first clone the repo
```
git clone https://github.com/nassnamb/cratfs-api.git
```

Then install the backend api
```
cd crafts
mvn clean install
```

## Run App
  In application.yml file, override home path to Import config files (for DB credentials per example):
``` 
  home:
    path: PATH_TO_CREDENTIALS_FOLDER
``` 
```
Run CraftsApplication
```
you will see following output in console :
>Tomcat started on port(s): 8080 (http) with context path '' 
>Started CraftsApplication in 7.892 seconds

go to swagger : http://localhost:8080/swagger-ui/index.html
