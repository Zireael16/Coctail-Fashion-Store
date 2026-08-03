# Coctail Fashion Store

Coctail Fashion Store is a full-stack web-based online shopping platform built using **Java Enterprise Edition (Java EE)**. The application follows the **Model-View-Controller (MVC)** architecture to ensure a clean separation between business logic, data persistence, and the presentation layer. It provides a secure and responsive shopping experience with product browsing, user authentication, shopping cart management, and order processing.

## 🚀 Features

- **Secure User Authentication:** User registration and login with encrypted passwords using `jBCrypt`.
- **Product Catalog:** Browse products by category with detailed product information and dynamic inventory management.
- **Shopping Cart:** Session-based shopping cart with support for adding, updating, and removing products.
- **Checkout & Order Management:** Secure checkout process with JDBC transaction management to ensure data consistency during order placement.
- **Order History:** Users can view previously placed orders along with complete order details.
- **Responsive UI:** Interactive frontend developed using JSP, HTML, CSS, JavaScript, and JSTL.

---

## 🛠️ Tech Stack

### Backend
- Java EE (Servlets)
- JDBC
- MVC Architecture
- DAO Design Pattern

### Frontend
- JavaServer Pages (JSP)
- HTML5
- CSS3
- JavaScript
- JSTL

### Database
- MySQL Community Server

### Libraries
- `mysql-connector-j-9.7.0.jar`
- `jbcrypt-0.4.jar`

---

## 📁 Project Architecture

```
src
│
├── Controllers
│   ├── LoginServlet
│   ├── RegisterServlet
│   ├── ProductServlet
│   ├── CartServlet
│   ├── CheckoutServlet
│   ├── OrderServlet
│   └── LogoutServlet
│
├── model
│   ├── User
│   ├── Product
│   ├── Cart
│   ├── CartItem
│   ├── Order
│   └── OrderItem
│
├── DAO
│   ├── UserDAO
│   ├── ProductDAO
│   ├── CartDAO
│   ├── OrderDAO
│   └── DAO Implementations
│
├── util
│   └── DBConnection
│
└── webapp
    ├── JSP Pages
    ├── CSS
    ├── JavaScript
    └── WEB-INF
```

The project follows the MVC pattern:

- **Controllers** handle incoming HTTP requests.
- **Model** contains POJOs representing business entities.
- **DAO Layer** manages all database interactions using JDBC.
- **Views (JSP)** dynamically render data returned by the controllers.

---

## 💻 Core Functionalities

### User Module
- User Registration
- Secure Login
- Password Encryption
- User Profile Management

### Product Module
- View Products
- Product Details
- Category-wise Product Listing
- Search & Browse Products

### Shopping Cart
- Add to Cart
- Update Quantity
- Remove Items
- Session-Based Cart Management

### Order Module
- Checkout
- Order Placement
- Order History
- Order Details

---

## ⚙️ Local Setup

### Prerequisites

- Java Development Kit (JDK 8 or later)
- Apache Tomcat 9+
- MySQL Server
- Eclipse IDE for Enterprise Java Developers (or IntelliJ IDEA Ultimate)

---

### Database Configuration

1. Create a MySQL database.

2. Execute the SQL scripts to create the required tables:

- Users
- Products
- Orders
- OrderItems
- Categories (if applicable)

3. Configure your database credentials inside:

```
src/main/java/com/tap/util/DBConnection.java
```

```java
private static final String URL =
    "jdbc:mysql://localhost:3306/your_database";

private static final String USER =
    "your_username";

private static final String PASSWORD =
    "your_password";
```

---

## 🚀 Deployment

1. Clone the repository.

```bash
git clone https://github.com/yourusername/Coctail-Fashion-Store.git
```

2. Import the project into Eclipse as a Dynamic Web Project.

3. Configure Apache Tomcat.

4. Add the required JAR files to the project's Build Path.

5. Run the project on the Tomcat Server.

6. Open the application in your browser:

```
http://localhost:8080/ECommerceApplication/
```

---

## 📌 Key Highlights

- Developed using Java EE following the MVC architecture.
- Implemented the DAO design pattern for modular database operations.
- Used JDBC transactions to maintain data integrity during order processing.
- Session-based authentication and authorization for secure access.
- Responsive frontend using JSP, HTML, CSS, JavaScript, and JSTL.
- Object-oriented design with reusable model classes and layered architecture.

---

## 👨‍💻 Author

**Surya Ramesh**

GitHub: https://github.com/Zireael16
