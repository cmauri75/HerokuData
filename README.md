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
