# Bootstrap - Testing framework

## 1. Set up a new project

1. Clone bootstrap project to a new directory
   
    ```bash
    git clone <bootstrap git address> <project_name>
    ```
   
    Example:
   
    ```bash
    git clone git@gitlab.cleevio.cz:testing/bootstrap-testing.git driverama-testing
    ```

1. Enter project directory

    ```bash
    cd <project_name>
    ```

   Example:

    ```bash
    cd driverama-testing
    ```

1. Remove bootstrap repository

    ```bash
    rm -rf .git
    ```

1. Initialize a project repository

    ```bash
    git init
    git remote add origin <project git address>
    ```

   Example:

    ```bash
    git init
    git remote add origin git@gitlab.cleevio.cz:testing/driverama-testing.git
    ```

## 2. Configure project in IDE

1. Copy application.properties.example to application.properties in

    ```
    src/main/java/resources/application.properties
    ```

    and update configuration properties

    ```properties
    url=http://project.example.com // project web page URL
    
    browser.close=true // Close browser window after the test
    browser.headless=false // Run tests without UI
    browser.driver=firefox // Driver to use
    
    browser.width=1920 // Resize browser width after initialization
    browser.height=1080 // Resize browser height after initialization

    environment=devel // Define environment

    allure.project=<project name> // Allure reporting project name

    ```
   
1. Find in files "bootstrap" and change it to <project_name>

## 3. Allure set up
1. Go to the postman and open allure workspace
2. Create a new project with **_POST /projects_** request

    
Note! Result will be shown after first deployment

``` 
https://allure.cleevio.dev/allure-docker-service/projects/<project_name>/reports/latest/index.html#
```

