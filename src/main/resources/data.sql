INSERT INTO projects (name, description) VALUES
('E-Commerce Website', 'A full-featured online shopping platform with payment integration and user management'),
('Task Management System', 'A collaborative tool for teams to manage projects, assign tasks, and track progress'),
('Weather Dashboard', 'A responsive web application displaying real-time weather data with forecasting capabilities'),
('Blog Platform', 'A content management system for creating, editing, and publishing blog posts with user authentication'),
('Inventory Management', 'A comprehensive system for tracking products, managing stock levels, and generating reports');

INSERT INTO users (username, email, password, role) VALUES
('admin', 'admin@example.com', 'admin123', 'ADMIN'),
('john_doe', 'john@example.com', 'password123', 'PROJECT_MANAGER'),
('jane_smith', 'jane@example.com', 'password123', 'PROJECT_MANAGER'),
('bob_johnson', 'bob@example.com', 'password123', 'EMPLOYEE'),
('alice_wilson', 'alice@example.com', 'password123', 'EMPLOYEE');

INSERT INTO tasks (name, description, project_id) VALUES
('Setup Product Database', 'Create database schema for storing product information including categories, prices, and inventory', 1),
('Implement User Authentication', 'Develop login/logout functionality with password encryption and session management', 1),
('Design Shopping Cart', 'Build interactive shopping cart with add/remove items and quantity management', 1),
('Payment Gateway Integration', 'Integrate PayPal and Stripe payment processing systems', 1),
('Order Management System', 'Create admin panel for managing orders and tracking shipments', 1),

('Create Task Board Interface', 'Design drag-and-drop Kanban board for task management', 2),
('User Role Management', 'Implement different user roles (admin, manager, member) with permissions', 2),
('Notification System', 'Build email and in-app notifications for task assignments and updates', 2),
('Time Tracking Feature', 'Add time logging functionality for tasks with reporting capabilities', 2),
('Project Analytics Dashboard', 'Create charts and metrics for project progress and team performance', 2),

('API Integration Setup', 'Connect to OpenWeatherMap API for real-time weather data', 3),
('Location-based Weather', 'Implement geolocation detection and city search functionality', 3),
('7-Day Forecast Display', 'Create responsive UI for displaying extended weather forecasts', 3),
('Weather Alerts System', 'Add severe weather warnings and notification system', 3),
('Historical Weather Data', 'Implement feature to view past weather trends and comparisons', 3),

('Rich Text Editor', 'Integrate WYSIWYG editor for creating and formatting blog posts', 4),
('Comment System', 'Build threaded commenting system with moderation features', 4),
('SEO Optimization', 'Implement meta tags, sitemaps, and URL optimization', 4),
('Social Media Integration', 'Add sharing buttons and auto-posting to social platforms', 4),
('Content Management', 'Create admin interface for managing posts, categories, and tags', 4),

('Barcode Scanner Integration', 'Implement barcode scanning for quick product identification', 5),
('Stock Level Monitoring', 'Build automated alerts for low stock and reorder points', 5),
('Supplier Management', 'Create database and interface for managing supplier information', 5),
('Inventory Reports', 'Generate comprehensive reports on stock movements and trends', 5),
('Multi-location Support', 'Add support for managing inventory across multiple warehouses', 5);