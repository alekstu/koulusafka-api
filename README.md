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

## Usage

### Restaurants
------------

* `GET /api/v1/restaurants/all` returns all restaurants - **[(example)](http://koulusafka.fi/api/v1/restaurants/all)**
* `GET /api/v1/restaurants/location?latitude=[latitude]&longitude=[longitude]` returns nearest restaurants for coordinates **[(example)](http://koulusafka.fi/api/v1/restaurants/location?latitude=60.17247&longitude=24.93790)** 
* `GET /api/v1/restaurants?brand=[brand]` returns all restaurants of a brand **[(example)](http://koulusafka.fi/api/v1/restaurants?brand=sodexo)**
* `GET /api/v1/restaurants?post_office=[postOffice]` returns restaurants by post office **[(example)](http://koulusafka.fi/api/v1/restaurants?post_office=espoo)** 
* `GET /api/v1/restaurants?postal_code=[postalCode]` returns restaurants by postal code **[(example)](http://koulusafka.fi/api/v1/restaurants?postal_code=02150)** 


* `GET /api/v1/restaurant?id=[id]` returns more information about restaurant - **[(example)](http://koulusafka.fi/api/v1/restaurant?id=1)**
* `GET /api/v1/restaurant?name=[name]` returns more information about restaurant - **[(example)](http://koulusafka.fi/api/v1/restaurant?name=aaltoyliopiston_kahvila)**


* `GET /api/v1/restaurants/brands` will return available brands - **[(example)](http://koulusafka.fi/api/v1/restaurants/brands)**

### Menus
------------
* `GET /api/v1/menu/restaurant` returns menu for restaurant
  * Params:
    * ?id=[**restaurantId**] -- _integer_ (__id__ or __name__ _required_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1)**
    * ?name=[**restaurantName**] -- _string_ (__id__ or __name__ _required_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?name=aaltoyliopiston_kahvila)**
    * &type=[**type**] -- _string_ (_optional_)
      * __week__ -- returns this weeks menu (_default_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&type=week)**
      * __today__ -- returns today's menu **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&type=today)**
    * &lang=[**language**] - _string_ (_optional_)
      * __fi__ -- returns menu in Finnish (_default_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&lang=fi)**
      * __en__ -- returns menu in English  **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&lang=en)**
      * __sv__ -- returns menu in Swedish  [if not available, returns menu in English] **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&lang=sv)**
      
* `GET /api/v1/menu/restaurants` returns multiple menus for restaurants
  * Params:
    * ?id=[**restaurantId**] -- _integer_ (_required_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1)**
    * &id=[**restaurantId**] -- _integer_ (_optional_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2)**
       * returns additional menu for another restaurant
       * can be used multiple times **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2&id=3)**
    * &type=[**type**] -- _string_ (_optional_, see above) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2&type=today)**
    * &lang=[**language**] - _string_ (_optional_, see above) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2&type=today&lang=en)**



### Built With

* [JHipster](https://github.com/jhipster) - Built on top of


## License

Include a license for your project. If you need help choosing a license, use this guide: https://choosealicense.com/




