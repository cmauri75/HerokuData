# HerokuData
Steps: 
* To begin, create a free [Heroku account](https://signup.heroku.com/)

* Then download and install the Heroku CLI.
```
curl -L https://cli-assets.heroku.com/apt/release.key | sudo apt-key add -
sudo apt-get update
sudo apt-get install heroku
```

* Download Keys using browser login integration: ``heroku login``
* Check if key exists ``heroku keys``
* If not create one: ``heroku keys:add``


* Create a spring web app (in example a simple Dataload app) check it at: https://dataload-app.herokuapp.com/data/ok

* Provision an heorku app: ``heroku create``
* If you like use a better than random name: ``heroku apps:rename DataloadApp``, keep appName tag in pom.xml aligned

* Than use maven plugin to deploy: ``mvn clean heroku:deploy`` [reference here](https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin)
This is preferrable if you want to deploy a WIP version with no need to push. But sometimes does not work

* Another way to deploy is just push on heroku repo: ``git push heroku master`` It will detect app type and automatically deploy, pay attention to branch name
This is faster

* ``heroku logs --tail`` let you stay in tail on remote log 
* ``heroku run bash`` connects to remote server
* ``heroku config`` get information about plugins
  
NB: system.properties let heroku to detect project type and deploy accordingly

## Addons
Lot available, list [here](https://elements.heroku.com/addons)

* Now add a new service, ae postgresql: ``heroku addons:create heroku-postgresql``
* ``heroku config`` gives you information about created service connect url 

This creates a service on amazon, you can connect ad user outside heroku

A shell is available by:  ``heroku pg:psql``

* Add redis addon: ``heroku addons:create redistogo:nano``

* Than put also kafka: ``heroku addons:create cloudkarafka:ducky``
Unfortunally doesn't work due to free plan

* Send mail: ``heroku addons:create mailtrap:unpaid``

## helpfull

* local start using heroku env: ``heroku local:start`` This works only if Procfile is provided. Attention: don't push it or app in heroku will crash, maybe it will be fixed in future
content is: web: java -jar target/Dataload-0.0.1-SNAPSHOT.jar
* scale number of microserviced: ``heroku ps:scale web=0``
* list of addons created: ``heroku addons``

## Pwd management
Password are stored in .env file (not pushed). Spring-boot manage it transparently like application.properties entries.

- IDE can be configured setting run configuration

- In local build/run first run ``export KEY=val`` for all properties

- In heroku use: ``heroku config:set`` A good configuration can avoid it if it uses config created by module creation

# DigitalOceanData - PaaS
This case release spring-boot-app on Digital Ocean PaaS. App will use external services by Heroku because does not provide free tier for that.

App will be loaded inside a linux virtual machine stored as fat jar app and controlled via systemrc service

* data-spring-boot.service file control it
* Create a Droplet (linux) and ssh root@ in it, in my case: 206.189.25.252
* that install java
```
sudo apt update
sudo apt install openjdk-11-jre-headless
useradd -m app
passwd app
usermod --shell /bin/bash
su - apphttps://dataload-app.herokuapp.com/data/ok
vi .bashrc
--now add exports in digitalOceanEnv.sh with password for external services
mkdir release
exit
```

In local shell:
```
scp data-spring-boot.service root@206.189.25.252:/etc/systemd/system
scp target/Dataload-0.0.1-SNAPSHOT.jar app@206.189.25.252:/home/app/release/
```

Back in remote
```
systemctl edit myservice
-- add data in digitalOceanEnv.conf
systemctl start data-spring-boot.service

systemctl status data-spring-boot.service
journalctl -u data-spring-boot.service
```

try it on: http://206.189.25.252:8080/data/ok

# DigitalOceanData - Kubernetes
Create an image, a pod and deploy in kubernates env 

* modify pom.xml in order to create docker image on build (pay attention to heroku conflict, maybe comment) and also provide Dockerfile. NB: you can local test via: 
``docker run -p8080:8080 --env-file .env example/dataload:0.0.1-SNAPSHOT``

* 

