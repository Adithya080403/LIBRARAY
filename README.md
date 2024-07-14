I have developed a digital library application using Spring Boot, JUnit, Redis, and relational databases in IntelliJ. This application features three primary models: User, Book, and Author. The relationship between the Book and Author models is defined as one-to-many, indicating that a single author can write multiple books, but each book is associated with only one author. Furthermore, the relationship between the User and Book models is many-to-many, meaning that a user can borrow multiple books, and each book can be borrowed by multiple users.

To ensure efficient development and testing, I used JUnit for unit testing, verifying the functionality and reliability of the application's components. Redis was integrated as a caching solution to enhance performance by reducing database load and speeding up data retrieval processes. Throughout the development process, IntelliJ served as the integrated development environment (IDE), providing a robust and user-friendly platform for coding, debugging, and managing the project's various aspects.
