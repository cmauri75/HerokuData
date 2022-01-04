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
* Another way to deploy is just push on heroku repo: ``git push heroku master`` It will detect app type and automatically deploy, pay attention to branch name
* 