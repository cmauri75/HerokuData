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


* Create a spring web app (in example a simple Dataload app)


* Provision an heorku app: ``heroku create``
* If you like use a better than random name: ``heroku apps:rename DataloadApp``, keep appName tag in pom.xml aligned

* Than use maven plugin to deploy: ``mvn clean heroku:deploy`` [reference here](https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin)
This is preferrable if you want to deploy a WIP version with no need to push. But sometimes does not work

* Another way to deploy is just push on heroku repo: ``git push heroku master`` It will detect app type and automatically deploy, pay attention to branch name
This is faster

* ``heroku logs --tail`` let you stay in tail on remote log 
* ``heroku run bash`` connects to remote server
  
NB: system.properties let heroku to detect project type and deploy accordingly

## Addons
Lot available, list [here](https://elements.heroku.com/addons)

* Now add a new service, ae postgresql: ``heroku addons:create heroku-postgresql``
* ``heroku config`` gives you information about created service connect url 
This creates a service on amazon, you can connect ad user outside heroku

* Now add redis addon: ``heroku addons:create redistogo:nano``