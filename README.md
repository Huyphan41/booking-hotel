# Hotel Booking API

- [Hotel booking API](#hotel-booking-api)
    * [Overview](#overview)
- [Explore REST APIs](#explore-rest-apis)
    * [Hotel](#hotel)
        + [Get all available hotels](#get-hotels-by-destination)
    * [booking](#booking)
        + [Create booking](#create-booking)
        + [Get booking](#get-booking)
- [Database](#database)
    * [hotel](#hotel)
    * [booking](#hotel_booking)
- [Testing](#testing)
- [Tech Stack](#tech-stack)

## Overview

- This project is a Hotel booking `RESTful API` designed to **query hotels by destination and associated bookings.**
- The user can search hotels by destination, make bookings to a specific hotel, get user's booking detail as well as hotels.
- The Hotel-booking-API follows a standard Hexagonal architecture.


# Explore REST APIs

##  Hotel

| Method | Endpoint                                  | Description                                                 | Valid API Calls                                        |
| ------ |-------------------------------------------|-------------------------------------------------------------|--------------------------------------------------------|
| GET | /v1/hotels?destination={from} | Get hotels with pagination filter by specified destination. | [Get hotels by destination](#get-hotels-by-destination) |

### Get hotels with pagination filter by destination
- Description: Gets hotels with pagination by specified destination.
- Request Body:
    - Path Variables destination, in String format

##  Booking

| Method | Endpoint                 | Description | Valid API Calls |
| ------ |--------------------------| ----------- | ------------------------- |
| POST | /v1/hotels/bookings      | Creates a hotel booking with an existing hotel id | [Create booking](#create-booking) |
| GET | /v1/hotels/bookings/{id} | Get an exsiting user specified Hotel booking  | [Get booking](#get-booking) |


### Create booking
- Description: Creates a hotel booking with an existing hotel id.
- Validation (Throws an `RuntimeException` when):
    - The hotel object ID does not exist (The foreign key does not exist)
- Request Body:
  ```json 
    {
      "hotel_id": "023df4cc-498b-42ab-a453-04441b229a08",
      "user_id": "f8e7956d-8b6c-4246-b53a-5bcf319a6a7d"
      "start_date": "YYYY-MM-DD",
      "end_date": "YYYY-MM-DD"
    } 
  ```

### Get booking
- Description: Get an existing user specified Hotel booking
- Validation (Throws an `RuntimeException` when):
    - ID does not exist
- Request Body:
    -  Path variable UUID String "id"

# Database
- The database structure contains two tables, a `hotel` table that contains the hotel object.
- And a `hotel_booking` table that contains bookings objects and is associated with the `hotel` table through the `hotel table's Id` by storing it as a foreign key in its "hotel Id" value.
- The table structures and values are as follows:

## hotel
- Id: UUID (Primary Key)
- Name: VarChar
- Destination: VarChar
- Address: VarChar

## hotel_booking
-  Id: UUID (Primary Key)
-  Hotel Id: UUID (Foreign Key)
-  Start Date: Date YYYY-MM-DD
-  End Date: Date YYYY-MM-DD
-  Number of Guests: Integer
-  Status: VarChar [ PENDING, CONFIRMED, CANCELLED ]


# Testing
- I used Mockito is several unit tests.
- More elaborate descriptions of the tests and their functionalities can be found in the test folder within this project.


# Tech Stack

- API Creation:
    - Java
    - Postgresql
    - SpringBoot
    - Hibernate
    - JPA
    - Lombok
- Testing:
    - Assertj core
    - Mockito
- User Input Testing:
    - Swagger UI
    - Postman