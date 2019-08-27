# Content
* [Why and What](#why-and-what)
* [Technologies](#technologies)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Setup](#setup)
  * [Run](#run)
* [Getting Test Case](#getting-test-case)
  * [Cloning Project from Github](#cloning-project-from-github)
*
## Why & What
* This project created for study purposes and simulates user actions on [hurriyetemlak.com](#http://www.hurriyetemlak.com)(which is a real estate advertizement website) such as checking for getting advertizement information, filter usage and taking a close look to published advertizements. 
* Project uses browser user-agent information to simulate platforms like Linux, Macintosh, Android, Chrome and Safari on screenresolutions such as 1920x1080, 1366x768, 360x640, 375x667.

## Technologies
* Selenium
* TestNG
* Zalenium
* Docker

## Getting Started
* Following steps will be explaining how to setup your Zalenium environment, how to clone this project and run in on your system. (This readMe file is partially created by help of [this](https://github.com/zalando/zalenium#getting-started) documentation.)

### Prerequisites
* Docker engine running, version >= 1.11.1 (probably works with earlier versions, not tested yet).
* Make sure your docker daemon is running (e.g. docker info works without errors).

### Setup
* Pull the [docker-selenium](https://github.com/elgalu/docker-selenium) image.
```
 # Pull docker-selenium
  docker pull elgalu/selenium

  # Pull Zalenium
  docker pull dosel/zalenium
```

### Run
* Zalenium uses docker to scale on-demand, therefore we need to give it the docker.sock full access, this is known as "Docker alongside docker".
```
  docker run --rm -ti --name zalenium -p 4444:4444 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v /tmp/videos:/home/seluser/videos \
    --privileged dosel/zalenium start --desiredContainers 8
```

## Getting Test Case
* This part explains how to clone this project from Github and run it on your system and in case of it's running too slow on your system will contain additional information to reduce source usage.

### Cloning Project from Github
