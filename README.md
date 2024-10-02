
# Chef Booking System - Spring Boot Backend

## Overview
The **Chef Booking System** is a backend service built with the Spring Boot framework. It provides APIs for managing chefs, customers, bookings, and administrators. The platform allows users to interact with the system securely and efficiently, offering seamless CRUD operations, user authentication, and more.

## Features
- **CRUD Operations**: Manage chefs, customers, bookings, and administrators through RESTful APIs.
- **Swagger Integration**: Access interactive API documentation with Swagger UI.
- **MySQL Database**: Ensures data persistence and automatic schema updates.
- **Exception Handling**: Robust handling of errors to ensure system reliability.

## Prerequisites
- Java 17 or later
- Maven 3.6.0 or later
- MySQL Database
- Swagger UI

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/your-username/chef-booking-system.git
cd chef-booking-system
```

### Configuration
Update the `src/main/resources/application.properties` file with your MySQL database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your-database-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.application.name=chefBooking
```

### Build and Run

#### Build the Project
```bash
mvn clean install
```

#### Run the Application
```bash
mvn spring-boot:run
```

### API Documentation
Access Swagger UI for detailed API documentation:
```bash
http://localhost:8080/swagger-ui.html
```

## Controllers and Endpoints

### Admin Controller
- **Create Admin**: `POST /admin/create`
- **Update Admin**: `PUT /admin/update`
- **Delete Admin**: `DELETE /admin/delete`
- **Get All Bookings**: `GET /admin/allBookings`
- **View All Chefs**: `GET /admin/viewAll/chef/{adminId}/{sessionKey}`
- **View All Customers**: `GET /admin/viewAll/customer/{adminId}/{sessionKey}`
- **View Chef by ID**: `GET /admin/viewById/chef/{adminId}/{chefId}/{sessionKey}`
- **View Customer by ID**: `GET /admin/viewById/customer/{adminId}/{customerId}/{sessionKey}`

### Chef Controller
- **Create Chef**: `POST /chef/create`
- **Update Chef**: `PUT /chef/update`
- **Delete Chef**: `DELETE /chef/delete`
- **Handle Booking Completion**: `GET /chef/taskCompleted`

### Customer Controller
- **Register Chef Booking**: `POST /customer/chefBooking/{sessionKey}`
- **Get All Bookings by Customer ID**: `GET /customer/chefBooking/{customerId}/{sessionKey}`
- **Cancel Booking**: `DELETE /customer/cancelBooking/{customerId}/{sessionKey}`
- **Generate Bill**: `GET /customer/generateBill/{customerId}/{chefBookingId}/{sessionKey}`
- **Create Customer**: `POST /customer/create`
- **Update Customer**: `PUT /customer/update`
- **Delete Customer**: `DELETE /customer/delete/{customerId}/{sessionKey}`

### Login Controllers
- **Admin Login**: `POST /loginAdmin`
- **Admin Logout**: `GET /logoutAdmin`
- **Chef Login**: `POST /loginChef`
- **Chef Logout**: `GET /logoutChef`
- **Customer Login**: `POST /loginCustomer`
- **Customer Logout**: `GET /logoutCustomer`

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any questions or feedback, contact: **[utsav45.jn@gmail.com](mailto:utsav45.jn@gmail.com)**
