EasyShop Capstone

Description
The EasyShop Capstone is an E-Commerce API that powers the backend of an online shopping platform. It's a RESTful service that lets both administrators and regular users manage products, categories, and user information securely. The system also includes user authentication and different roles to ensure safe access to the app.
This project has key features like managing categories, handling products, and authenticating users. It's built using Java, Spring Boot, and MySQL for storing and managing the data.
Key Features
	• Category Management: Lets admins add and manage product categories.
	• Product Management: Users can browse, search, and manage product information.
	• User Authentication: Keeps the API secure with role-based access for both users and admins.
Database Management
The app uses MySQL to store and manage data. Some key things to know:
	• CRUD Operations: The app supports basic actions like creating, reading, updating, and deleting products, categories, and users.
	• Query Filtering: It uses WHERE clauses in SQL to filter data when needed.
	• Database Setup: MySQL is set up for the app, and you'll find the connection settings in the application.properties file.
RESTful API Design
This API follows the basic principles of REST:
	• GET: Retrieve data (e.g., fetch products or categories).
	• POST: Add new records (e.g., create new products or categories).
	• PUT: Update existing records (e.g., update product details).
	• DELETE: Delete records (e.g., remove a product).
The app uses the correct HTTP status codes for different actions and provides helpful error messages when something goes wrong. We used Postman to test the API and make sure all the endpoints were working properly.


Front End


Postman Requests




![image](https://github.com/user-attachments/assets/522bdc7e-1522-441d-aa40-b46443c27123)
