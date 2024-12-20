# EasyShop Capstone

## Description

The EasyShop Capstone is an E-Commerce API that powers the backend of an online shopping platform. It's a RESTful service that lets both administrators and regular users manage products, categories, and user information securely. The system also includes user authentication and different roles to ensure safe access to the app.

This project has key features like managing categories, handling products, and authenticating users. It's built using Java, Spring Boot, and MySQL for storing and managing the data.

## Key Features

- **Category Management**: Lets admins add and manage product categories.
- **Product Management**: Users can browse, search, and manage product information.
- **User Authentication**: Keeps the API secure with role-based access for both users and admins.

## Database Management

The app uses MySQL to store and manage data. Some key things to know:

- **CRUD Operations**: The app supports basic actions like creating, reading, updating, and deleting products, categories, and users.
- **Query Filtering**: It uses `WHERE` clauses in SQL to filter data when needed.
- **Database Setup**: MySQL is set up for the app, and you'll find the connection settings in the `application.properties` file.

## RESTful API Design

This API follows the basic principles of REST:

- **GET**: Retrieve data (e.g., fetch products or categories).
- **POST**: Add new records (e.g., create new products or categories).
- **PUT**: Update existing records (e.g., update product details).
- **DELETE**: Delete records (e.g., remove a product).

The app uses the correct HTTP status codes for different actions and provides helpful error messages when something goes wrong. We used Postman to test the API and make sure all the endpoints were working properly.

## Front End

![Front End](https://github.com/user-attachments/assets/f516cf5c-01bc-4c61-8356-a1c412674d74)

## Postman Requests

Here are some examples of the Postman requests used for testing:

![Postman Request 1](https://github.com/user-attachments/assets/a142a44c-3433-41f5-b62a-caf866c0fe18)

![Postman Request 2](https://github.com/user-attachments/assets/55ff6224-8b18-4fdc-aa02-abf2e35cef2b)


