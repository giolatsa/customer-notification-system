-- V3__Insert_default_admin_user.sql

-- Insert default admin user with password admin123 (hashed using BCrypt)
INSERT INTO users (username, password, role) VALUES
    ('admin', '$2a$10$D9w3PIe2DLcOlF5J7zkFduwPV9XgjNUDmkOv9FyHZztpD0t1nDYXi', 'ADMIN');
