USE alpha_solution_pkv;

-- ========== USERS ==========
INSERT INTO user (username, email, password, role) VALUES
                                                            ('admin', 'admin@alpha.dk', '$2a$10$adminhashed123', 'ADMIN'),
                                                            ('pm_oliver', 'oliver@alpha.dk', '$2a$10$pmhashed456', 'PROJECT_MANAGER'),
                                                            ('emp_maria', 'maria@alpha.dk', '$2a$10$emphashed789', 'EMPLOYEE');

-- ========== EMPLOYEES ==========
INSERT INTO employee (user_id, first_name, last_name, position, hourly_rate) VALUES
                                                                                 (2, 'Oliver', 'Mahmoud', 'Projektleder', 450.00),
                                                                                 (3, 'Maria', 'Nielsen', 'Udvikler', 300.00);

-- ========== PROJECTS ==========
INSERT INTO project (name, description, start_date, end_date, budget, created_by) VALUES
                                                                                      ('Website Redesign', 'Redesign af Alpha Solutions corporate site.', '2025-01-10', '2025-04-20', 120000.00, 2),
                                                                                      ('Intern App', 'Udvikling af intern tidsregistreringsapp.', '2025-02-01', '2025-06-01', 200000.00, 2);

-- ========== TASKS ==========
INSERT INTO task (project_id, assigned_to, name, description, estimated_hours, actual_hours, cost, status) VALUES
                                                                                                               (1, 2, 'Frontend UI', 'Udarbejdelse af brugergrænseflade', 80, 60, 18000, 'IN_PROGRESS'),
                                                                                                               (1, 3, 'Backend API', 'Opsætning af REST API', 100, 90, 27000, 'DONE'),
                                                                                                               (2, 3, 'Database Design', 'Design af MySQL-database til app', 50, 45, 13500, 'IN_PROGRESS');