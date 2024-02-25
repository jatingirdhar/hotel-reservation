# Rest API for Hotel Booking Application

This is a spring boot application for an online hotel booking application, developed using core java and the spring framework.The application utilizes a MySQL database for data storage and CRUD operations.

## Project Details
- In this project the data will be automatically inserted data in customer, hotel , user and room tables.This will automatically be inserted through import.sql file.
- The database tables will be automatically created on server startup.
- This contains set of API's for fetching hotel list, hotel details and reservation realted API.

## API Details
- hotel/list :- This will return the list of available hotels.
- hotel/detail/id/{id} - This will return hotel detail along with room details.
- reservation/book - To book the reservation
- reservation/id/{id} - To get reservation details
- reservation/id/{id}/cancel - To cancel the reservation

In Headers  user basic auth
username-test
password - 1234

User will be already created in database.


## Installation & Run
- To run this API server, you should update the database configuration inside the application.properties file which is present in the src/main/resources folder.
- Update the port number, username and password as per your local database configuration.
  spring.datasource.url=jdbc:mysql://localhost:3306/hotel
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.datasource.username=your_username_here
  spring.datasource.password=your_password_here

## API Root Endpoint

http://localhost:8080/


