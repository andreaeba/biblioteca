<h1>📚 Library Management System</h1>


<h2>📖 Description</h2>
    <p>This is a library management system built with <strong>Java</strong>, <strong>Spring Data JPA</strong>, <strong>Spring Boot</strong>, <strong>Thymeleaf</strong>, and <strong>MySQL</strong>. It allows managing books, authors, publishers, and users with different roles (<strong>ADMIN</strong> and <strong>USER</strong>).</p>

<h3>🚀 Key Features</h3>
    <ul>
        <li><strong>Book Management:</strong> Add, edit, and delete books from the library.</li>
        <li><strong>Author & Publisher Management:</strong> Manage authors and publishers' information.</li>
        <img src="https://github.com/user-attachments/assets/386436ee-bbb8-49b0-abaa-56baafdee7a0"/>
        <li><strong>User Management:</strong> Create and edit users with different roles.</li>
        <img src="https://github.com/user-attachments/assets/b2beee9c-713c-4ec1-946a-eb6bc89a0f2a"/>
        <li><strong>Authentication & Authorization:</strong> Role-based access control (ADMIN vs USER).</li>
        <img src="https://github.com/user-attachments/assets/7bb47446-d49e-4d98-86c0-e81ef59abdc2"/>
        <li><strong>Friendly UI:</strong> Built with <strong>Thymeleaf</strong> for an intuitive experience.</li>
    </ul>

 <h3>🛠️ Technologies Used</h3>
    <ul>
        <li><strong>Backend:</strong> Spring Boot, Spring Security, JPA/Hibernate</li>
        <li><strong>Frontend:</strong> Thymeleaf, Bootstrap</li>
        <li><strong>Database:</strong> MySQL</li>
        <li><strong>Tools:</strong> Maven</li>
    </ul>

 <h3>⚡ Installation & Setup</h3>
    <h4>🔹 1. Clone the Repository</h4>
    <pre><code>git clone https://github.com/tu-usuario/library-management.git
cd library-management</code></pre>

<h4>🔹 2. Set up the Database</h4>
    <p>Create the database in MySQL:</p>
    <pre><code>CREATE DATABASE library_management;</code></pre>
    <p>Then, configure the <code>application.properties</code> file with your MySQL credentials.</p>

<h4>🔹 3. Run the Project</h4>
    <pre><code>mvn spring-boot:run</code></pre>
    <p>Access the app at: <a href="http://localhost:8080">http://localhost:8080</a></p>


<h3>👥 Roles & Permissions</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Role</th>
                <th>Permissions</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><strong>ADMIN</strong></td>
                <td>Create, edit, and delete books, authors, publishers, and users</td>
            </tr>
            <tr>
                <td><strong>USER</strong></td>
                <td>View books, authors and publishers</td>
            </tr>
        </tbody>
    </table>

<h3>💡 Roadmap - Upcoming Features</h3>
    <ul>
        <li>Integrate REST API 📡</li>
        <li>Implement book borrowing system 📚</li>
        <li>UI improvements with React🎨</li>
    </ul>

<h3>🤝 Contributing</h3>
    <p>Contributions are welcome! Feel free to fork, improve the code, and open a pull request.</p>

<h3>📩 Contact</h3>
    <p>If you have any questions or suggestions, feel free to contact me on <a href="https://www.linkedin.com/in/andrea-bermudez-alvarez/" target="_blank">LinkedIn</a>.</p>
