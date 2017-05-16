# Koulusafka-api

Koulusafka-api provides an interface for accessing lunch menus in Finland for different restaurants. Version 1.0 currently consists of two catering services: Amica and Sodexo. UniCafe is also supported but not yet accessible due text encoding related issues.

If more comprehensive documentation exists, link to it here.

## Features

- Provides day/week lunchmenu
- Feature #2
- Feature #3

### Getting Started / Installation

All information for getting started with JHipster application is provided inside README_JHIPSTER.md.


### Installation/Dependencies

How does a user get up and running with your project? What dependencies does the project have? Aim to describe these in clear and simple steps. Provide external links

### Usage

### Restaurants
------------

* `GET /api/v1/restaurants/all` returns all restaurants - **[(example)](http://188.226.135.225:8080/api/v1/restaurants/all)**
* `GET /api/v1/restaurants/location?latitude=[latitude]&longitude=[longitude]` returns nearest restaurants for coordinates **[(example)](http://188.226.135.225:8080/api/v1/restaurants/location?latitude=60.17247&longitude=24.93790)** 
* `GET /api/v1/restaurants?brand=[brand]` returns all restaurants of a brand **[(example)](http://188.226.135.225:8080/api/v1/restaurants?brand=sodexo)**
* `GET /api/v1/restaurants?post_office=[postOffice]` returns restaurants by post office **[(example)](http://188.226.135.225:8080/api/v1/restaurants?post_office=espoo)** 
* `GET /api/v1/restaurants?postal_code=[postalCode]` returns restaurants by postal code **[(example)](http://188.226.135.225:8080/api/v1/restaurants?postal_code=02150)** 


* `GET /api/v1/restaurant?id=[id]` returns more information about restaurant - **[(example)](http://188.226.135.225:8080/api/v1/restaurant?id=1)**
* `GET /api/v1/restaurant?name=[name]` returns more information about restaurant - **[(example)](http://188.226.135.225:8080/api/v1/restaurant?name=aaltoyliopiston_kahvila)**


* `GET /api/v1/restaurants/brands` will return all brands - **[(example)](http://188.226.135.225:8080/api/v1/restaurants/brands)**





### Built With

* [JHipster](https://github.com/jhipster) - Built on top of


## License

Include a license for your project. If you need help choosing a license, use this guide: https://choosealicense.com/




