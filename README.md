![alt text](https://raw.githubusercontent.com/zalando/zalenium/master/docs/img/logo_zalenium_wide.png "zaleniumlogo")

# Content
* [Why and What](#why-and-what)
* [Technologies](#technologies)
* [Getting Started with Zalenium](#getting-started-with-zalenium)
  * [Prerequisites](#prerequisites)
  * [Setup](#setup)
  * [Run](#run)
  * [Running Tests with Docker](#running-tests-with-docker)
* [Getting Started with Test Cases](#getting-started-with-test-cases)
  * [Cloning Project from Github](#cloning-project-from-github)
  * [Installing Maven](#installing-maven)
  * [Importing Project with Maven](#importing-project-with-maven)
  * [Build and Run Test Cases](#build-and-run-test-cases)
* [Docker Run Parameters](#docker-run-parameters)
* [Checkout If Tests Are Too Slow](#checkout-if-tests-are-too-slow)

## Why and What
* This project created for study purposes and simulates user actions on **[hurriyetemlak.com](http://www.hurriyetemlak.com)** (which is a real estate advertizement website) such as checking for getting advertisement information, filter usage and taking a close look to published advertizements. 
* Project uses browser user-agent information to simulate platforms like Linux, Macintosh, Android, Chrome and Safari on screen resolutions such as 1920x1080, 1366x768, 360x640, 375x667.
* After setting up you can watch tests live at [localhost:4444/grid/live/admin](http://localhost:4444/grid/admin/live) or you can watch replays of test cases at [http://localhost:4444/dashboard](http://localhost:4444/dashboard).
* And don't forget that zalenium docker image ***has to be running*** first before you run the test cases.

## Technologies
* **IntelliJ Idea**
* **Selenium**
* **TestNG**
* **Zalenium**
* **Docker**

## Getting Started with Zalenium
* Following steps will be explaining how to setup your Zalenium environment, how to clone this project and run in on your system. (This readMe file is partially created by help of [this](https://github.com/zalando/zalenium#getting-started) documentation.)

### Prerequisites
* Docker engine running, version >= 1.11.1 (probably works with earlier versions, not tested yet).
* Make sure your docker daemon is running (e.g. docker info works without errors).

### Setup
* Pull the [docker-selenium](https://github.com/elgalu/docker-selenium) image.
```
  # Pull docker-selenium
  $ docker pull elgalu/selenium

  # Pull Zalenium
  $ docker pull dosel/zalenium
```

### Run
* Zalenium uses docker to scale on-demand, therefore we need to give it the docker.sock full access, this is known as "Docker alongside docker".
```
  $  docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start
```
If everything is fine you should see something like this in your terminal.(this image generated with "--desiredContainers 8" parameter.)
![alt text](https://i.ibb.co/vPX0Y2x/nodes.png "created nodes 8")

### Running Tests with Docker
* In case of you'd like to run tests with docker instead of maven or from IDE, docker image of tests can be found on [koulgar/hurriyettest](https://cloud.docker.com/u/koulgar/repository/docker/koulgar/hurriyettest) and can be pulled and runned with commands such as;
```
  # Pull Docker Image 
  $ docker pull koulgar/hurriyettest
  
  # Run Docker Image
  $ docker run --rm -ti --net=host koulgar/hurriyettest
```

## Getting Started with Test Cases
* This part explains how to clone this project from Github and run it on your system and in case of it's running too slow on your system will contain additional information to reduce source usage.

### Cloning Project from Github
* Go to your folder that you desire project to be in.
* Clone project from Github.
```
  $ git clone https://github.com/koulgar/HurriyetEmlakStudyCase
```
### Installing Maven
* Installing maven,
  * For Windows you can check [this](https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows) link.
  * For Ubuntu you can check [this](https://www.mkyong.com/maven/how-to-install-maven-in-ubuntu/) link.
  * For MacOs you can check [this](http://www.codebind.com/mac-osx/install-maven-mac-os/) link.

### Importing Project with Maven
* Importing project as maven project.
  * For Eclipse you can check [this](https://www.lagomframework.com/documentation/1.5.x/java/EclipseMavenInt.html) link.
  * For IntelliJ you can check [this](https://www.lagomframework.com/documentation/1.5.x/java/IntellijMaven.html) link.
  
### Build and Run Test Cases
* Go to your project folder and use command below in order to build.
```
  $ mvn clean package
```

* Run "testng.xml" file inside your IDE to run all test cases.
  * or
* Use terminal with following command,
```
  $ mvn clean test -Dsurefire.suiteXmlFiles=<Full path to your testng.xml file>
```

## Docker Run Parameters
* **--rm** - remove container automatically after it exits
* **--ti** - connect the container to terminal
* **--name** - name the container
* **-p (x):(y)** - expose port **x** externally and map to port **y**
* **-v** - create a host mapped volume inside the container
* **--privileged** - gives all the capabilities to the container and also access to the host’s devices
* **--desiredContainers** - Number of nodes/containers created on startup (2 is default value.)

## Checkout If Tests Are Too Slow
* On docker run parameters "--desiredContainers 8" is not a default parameter. In case of test process takes too long on your system you can just use fewer containers instead of "8" or just delete whole parameter for default value which is assigned as "2".
In this case you should see something like this when you run docker image.

![alt text](https://i.ibb.co/tBGbqtZ/unknown.png "created nodes 2")

* Another option is to use fewer threads in your "testng.xml" file. Threads are amount of simultaneous test process  that your system will run. Which in this project it's assigned as "8". Using fewer threads such as "3" will ease down things. You can see location of "threads" parameter below.

![alt text](https://i.ibb.co/MZdyJ7S/unknown.png "threads in testng.xml")
