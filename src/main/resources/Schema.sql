-- ======================================================
-- DATABASE SCHEMA – Alpha Solution PKV
-- ======================================================

CREATE DATABASE IF NOT EXISTS alpha_solution_pkv
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE alpha_solution_pkv;

-- ========== USER ==========
CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) UNIQUE NOT NULL,
                      email VARCHAR(100) UNIQUE NOT NULL,
                      password_hash VARCHAR(255) NOT NULL,
                      role ENUM('ADMIN', 'PROJECT_MANAGER', 'EMPLOYEE') DEFAULT 'EMPLOYEE',
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========== EMPLOYEE ==========
CREATE TABLE employee (
                          employee_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          position VARCHAR(50),
                          hourly_rate DECIMAL(8,2),
                          FOREIGN KEY (user_id) REFERENCES user(user_id)
                              ON DELETE SET NULL
);

-- ========== PROJECT ==========
CREATE TABLE project (
                         project_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         description TEXT,
                         start_date DATE,
                         end_date DATE,
                         budget DECIMAL(12,2),
                         created_by INT,
                         FOREIGN KEY (created_by) REFERENCES user(user_id)
                             ON DELETE SET NULL
);

-- ========== TASK ==========
CREATE TABLE task (
                      task_id INT AUTO_INCREMENT PRIMARY KEY,
                      project_id INT NOT NULL,
                      assigned_to INT,
                      name VARCHAR(100) NOT NULL,
                      description TEXT,
                      estimated_hours DECIMAL(6,2),
                      actual_hours DECIMAL(6,2),
                      cost DECIMAL(12,2),
                      status ENUM('PENDING', 'IN_PROGRESS', 'DONE') DEFAULT 'PENDING',
                      FOREIGN KEY (project_id) REFERENCES project(project_id)
                          ON DELETE CASCADE,
                      FOREIGN KEY (assigned_to) REFERENCES employee(employee_id)
                          ON DELETE SET NULL
);
