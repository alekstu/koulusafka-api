# Koulusafka-api

Koulusafka-api provides an interface for accessing lunch menus in Finland for different restaurants. Version 1.0 currently consists of two catering services: Amica and Sodexo. UniCafe is also supported but not yet accessible due text encoding related issues.


### Getting Started / Installation

All information for getting started with JHipster application is provided inside README_JHIPSTER.md.

## Usage

### Restaurants
------------

* `GET /api/v1/restaurants`
  * `/all` returns all restaurants - **[(example)](http://koulusafka.fi/api/v1/restaurants/all)**
  * `/brands` returns available brands - **[(example)](http://koulusafka.fi/api/v1/restaurants/brands)**
  * `/location` returns nearby restaurants - **[(example)](http://koulusafka.fi/api/v1/restaurants/location?latitude=60.17247&longitude=24.93790)**
    * Params:
       * `?latitude=`[__latitude__] -- _double_ (_required_)
       * `&longitude=`[__longitude__] -- _double_ (_required_)
   * `?brand=[brand]` returns all restaurants of a brand **[(example)](http://koulusafka.fi/api/v1/restaurants?brand=sodexo)**
   * `?post_office=[postOffice]` returns restaurants by post office **[(example)](http://koulusafka.fi/api/v1/restaurants?post_office=espoo)** 
   * `?postal_code=[postalCode]` returns restaurants by postal code **[(example)](http://koulusafka.fi/api/v1/restaurants?postal_code=02150)** 




* `GET /api/v1/restaurant` returns more information about restaurant
  * Params:
    * `?id=`[**restaurantId**] -- _integer_ (__id__ or __name__ _required_) **[(example)](http://koulusafka.fi/api/v1/restaurant?id=1)**
    * `?name=`[**restaurantName**] -- _string_ (__id__ or __name__ _required_) **[(example)](http://koulusafka.fi/api/v1/restaurant?name=aaltoyliopiston_kahvila)**



### Menus
------------
* `GET /api/v1/menu/restaurant` returns menu for restaurant
  * Params:
    * `?id=`[**restaurantId**] -- _integer_ (__id__ or __name__ _required_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1)**
    * `?name=`[**restaurantName**] -- _string_ (__id__ or __name__ _required_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?name=aaltoyliopiston_kahvila)**
    * `&type=`[**type**] -- _string_ (_optional_)
      * __week__ -- returns this weeks menu (_default_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&type=week)**
      * __today__ -- returns today's menu **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&type=today)**
    * `&lang=`[**language**] - _string_ (_optional_)
      * __fi__ -- returns menu in Finnish (_default_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&lang=fi)**
      * __en__ -- returns menu in English  **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&lang=en)**
      * __sv__ -- returns menu in Swedish  [if not available, returns menu in English] **[(example)](http://koulusafka.fi/api/v1/menu/restaurant?id=1&lang=sv)**
      
* `GET /api/v1/menu/restaurants` returns multiple menus for restaurants
  * Params:
    * `?id=`[**restaurantId**] -- _integer_ (_required_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1)**
    * `&id=`[**restaurantId**] -- _integer_ (_optional_) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2)**
       * returns additional menu for another restaurant
       * can be used multiple times **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2&id=3)**
    * `&type=`[**type**] -- _string_ (_optional_, see above) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2&type=today)**
    * `&lang=`[**language**] - _string_ (_optional_, see above) **[(example)](http://koulusafka.fi/api/v1/menu/restaurants?id=1&id=2&type=today&lang=en)**



### Todo

- Some coordinates are incorrect
- Add UniCafe


### Built With

* [JHipster](https://github.com/jhipster) - Built on top of JHipster 4.0.6






