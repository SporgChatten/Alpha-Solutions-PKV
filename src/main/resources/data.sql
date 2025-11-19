-- ========== USERS ==========
INSERT INTO user (username, email, password, role) VALUES
('admin', 'admin@alphasolutions.com', 'hashed_pw_admin', 'ADMIN'),
('manager1', 'manager1@alphasolutions.com', 'hashed_pw_mgr1', 'MANAGER'),
('employee_jens', 'jens@alphasolutions.com', 'hashed_pw_jens', 'EMPLOYEE'),
('employee_anna', 'anna@alphasolutions.com', 'hashed_pw_anna', 'EMPLOYEE'),
('employee_peter', 'peter@alphasolutions.com', 'hashed_pw_peter', 'EMPLOYEE');

-- ========== EMPLOYEES ==========
INSERT INTO employee (user_id, first_name, last_name, hourly_rate) VALUES
(3, 'Jens', 'Hansen', 350.00),
(4, 'Anna', 'Nielsen', 300.00),
(5, 'Peter', 'Jørgensen', 320.00);

-- ========== PROJECTS ==========
INSERT INTO project (name, description, start_date, end_date, budget, created_by) VALUES
('Kundeportal', 'Udvikling af portal til kunder', '2025-01-01', '2025-04-30', 250000.00, 2),
('Intern Tool', 'Internt værktøj til tidsregistrering', '2025-02-15', '2025-05-15', 150000.00, 2),
('Marketing Kampagne', 'Digital kampagne for nye produkter', '2025-03-01', '2025-06-01', 100000.00, 2);

-- ========== TASKS ==========
INSERT INTO task (project_id, assigned_to, name, description, estimated_hours, actual_hours, cost, status) VALUES
(1, 1, 'Backend API', 'Implementering af REST endpoints', 80.00, 60.00, 21000.00, 'IN_PROGRESS'),
(1, 2, 'UI Mockups', 'Design af frontend wireframes', 40.00, 20.00, 6000.00, 'IN_PROGRESS'),
(2, 3, 'Database Setup', 'Konfiguration af schema og migrering', 25.00, 25.00, 8000.00, 'DONE'),
(2, 1, 'Dashboard Layout', 'Grundlæggende layout til frontend', 30.00, 0.00, 0.00, 'PENDING'),
(3, 2, 'Marketing Materialer', 'Design af bannere og sociale medier', 35.00, 0.00, 0.00, 'PENDING'),
(3, 3, 'Analyse & Reports', 'Udarbejdelse af rapporter for kampagnen', 20.00, 5.00, 1600.00, 'IN_PROGRESS');