## Chef Booking System - Spring Boot Backend
### Overview
The Chef Booking System is a backend application based on Spring Boot framework designed for managing chefs, customers, bookings, and administrators.
### Features
<pre>
○ CRUD Operations: Manage chefs, customers, bookings, and administrators.
○ Interactive API Documentation: Access detailed API documentation using Swagger.
○ MySQL Integration: Automatic schema updates and data persistence.
  </pre>
### Prerequisites
<pre>
○ Java 17 or later
○ Maven 3.6.0 or later
○ MySQL Database
○ Swagger UI
  </pre>
### Getting Started
#### Clone the Repository
<pre>
git clone https://github.com/your-username/chef-booking-system.git
cd chef-booking-system
  </pre>
#### Configuration
○ Update the src/main/resources/application.properties file with your database details:
### Properties
<pre>
○ spring.datasource.url=jdbc:mysql://localhost:3306/book?ServerTimeZone=UTC
○ spring.datasource.username=root
○ spring.datasource.password=your-database-password
○ spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
○ spring.jpa.hibernate.ddl-auto=update
○ spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
○ spring.application.name=chefBooking
</pre>
### Build and Run
<pre>
Build the project:
Copy code
○ mvn clean install
</pre>
### Run the application:
<pre>
Copy code
○ mvn spring-boot:run
</pre>
### API Documentation
<pre>
Access Swagger UI at:
Copy code
○ http://localhost:8080/swagger-ui.html
</pre>
### Controllers and Endpoints
<pre>
Admin Controller
○ Create Admin: POST /admin/create - saveAdmin
○ Update Admin: PUT /admin/update - updateAdmin
○ Delete Admin: DELETE /admin/delete - deleteAdmin
○ Get All Bookings: GET /admin/allBookings - allBookings
○ View All Chefs: GET /admin/viewAll/chef/{adminId}/{sessionKey} - findAllChef
○ View All Customers: GET /admin/viewAll/customer/{adminId}/{sessionKey} - findAllCustomer
○ View Chef by ID: GET /admin/viewById/chef/{adminId}/{chefId}/{sessionKey} - findChefById
○ View Customer by ID: GET /admin/viewById/customer/{adminId}/{customerId}/{sessionKey} - findCustomerById
  <br>
    <br>
Chef Controller
○ Create Chef: POST /chef/create - saveChef
○ Update Chef: PUT /chef/update - updateChef
○ Delete Chef: DELETE /chef/delete - deleteChef
○ Handle Booking Completion: GET /chef/taskCompleted - bookingCompletionHandler
    <br>
Customer Controller
○ Register Chef Booking: POST /customer/chefBooking/{sessionKey} - registerChefBooking
○ Get All Bookings by Customer ID: GET /customer/chefBooking/{customerId}/{sessionKey} - allBookingByCustomerId
○ Cancel Booking: DELETE /customer/cancelBooking/{customerId}/{sessionKey} - deleteBooking
○ Generate Bill: GET /customer/generateBill/{customerId}/{chefBookingId}/{sessionKey} - generateBillHandler
○ Create Customer: POST /customer/create - saveCustomer
○ Update Customer: PUT /customer/update - updateCustomer
○ Delete Customer: DELETE /customer/delete/{customerId}/{sessionKey} - deleteCustomer
    <br>
Login Controllers
○ Admin Login: POST /loginAdmin - logIn
○ Admin Logout: GET /logoutAdmin - logout
○ Chef Login: POST /loginChef - logIn
○ Chef Logout: GET /logoutChef - logout
○ Customer Login: POST /loginCustomer - logIn
○ Customer Logout: GET /logoutCustomer - logout

</pre>
### License
This project is licensed under the MIT License. See the LICENSE file for details.

### Contact
For any questions or feedback, you can reach out to utsav45.jn@gmail.com.
