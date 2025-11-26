DELETE FROM projects;
DELETE FROM users;

INSERT INTO projects (name, description) VALUES
('E-Commerce Website', 'A full-featured online shopping platform with payment integration and user management'),
('Task Management System', 'A collaborative tool for teams to manage projects, assign tasks, and track progress'),
('Weather Dashboard', 'A responsive web application displaying real-time weather data with forecasting capabilities'),
('Blog Platform', 'A content management system for creating, editing, and publishing blog posts with user authentication'),
('Inventory Management', 'A comprehensive system for tracking products, managing stock levels, and generating reports');

INSERT INTO users (username, email, password) VALUES
('admin', 'admin@example.com', 'admin123'),
('john_doe', 'john@example.com', 'password123'),
('jane_smith', 'jane@example.com', 'mypassword'),
('bob_johnson', 'bob@example.com', 'bobsecure'),
('alice_wilson', 'alice@example.com', 'alicepass');