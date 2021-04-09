# END-tech-test-app


## Resume 
This application gets a product list from an API endpoint.

## Screens 
 ![Tela de erro](/pictures/home_screen_top.png)
 ![Tela de erro](/pictures/home_screen_bottom.png)
 ![Tela de erro](/pictures/shimmer.png)
 ![Tela de erro](/pictures/empty_error_screen.png)
 ![Tela de erro](/pictures/error_screen.png)

## Automation
Ktlint - the task validates whether the code standard complies with the lint. Use the `./gradlew ktlint` command to validate.

KtlintFormat - this task modifies the code so that it follows the lint pattern. Use the `./gradlew ktlintFormat` command to adjust the code.

## Continuous Integration
**GitHub CI**

CI tool that allows the creation of customized workflows for repositories on GitHub.

**Workflows**

In this application, two workflows were created. The first for `Master` branch and the second for `general` branches. The following are descriptions of the workflows.

Master - run Ktlint and APK generation.

General - run Ktlint.

## Architecture
I tried to follow the concepts of MVI and Clean Architecture, so I divided the project into:

* **app module**: module that is started when user opens the application. It contais the Apllication class which starts dependency injection;

* **presentation module**: where everything related to Views is (activity, fragment, adapters and View Model);

* **data module**: the data layer contains all the code necessary to retrieve the data;

* **domain module**: the business logic layer contains models, use cases, repository interface, action and result (MVI) to communicate with the data module;

* **core module**: it has some extensions, MVI interfaces and utils that are used by the application;

## Main dependencies
**Koin** - _dependence injection_
 <p> Library chosen for its simple implementation. As a negative point, there is some loss of performance when compared to other competitors, such as Dagger. There is no significant loss for this application. </p>

**RxJava** - _dealing with asynchronism_
 <p> RxJava is java implementation for Reactive programming (Event Based Programming) which in simple terms means that one part of your code would fire an Event(a button click, successful api response etc) while some other part of code would observe and react to that particular event asynchronously(updating view, handling the api result etc). </p>

**Retrofit** - _HTTP requirements_
 <p> Retrofit is the most widespread library for handling HTTP requests, in addition to being easy to implement. </p>
 
 **Shimmer** - _animation_
 <p> The Facebook library allows you to introduce shimmering animations in a simple way. It is widely used to signal the loading of some content. </p>
 
 ## TODO
 
 I would like to implement: 
  
  - SplashActivity.
  
 I would like to study and implement: 
 
 - Dark theme;
 - Unit test;
 - Handle no internet connection displaying a snackbar;
 - Animation;
 - ViewPager.
