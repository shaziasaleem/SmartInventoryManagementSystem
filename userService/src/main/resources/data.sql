-- Insert roles
INSERT INTO roles (name) VALUES 
('ADMIN'),
('USER');

-- Insert permissions
INSERT INTO permissions (name) VALUES 
('VIEW_ALL'),
	('VIEW_PRODUCT'),
	('MANAGE_PRODUCT'),
	('VIEW_ORDER'),
	('MANAGE_ORDER'),
	('MANAGE_INVENTORY');

-- Map roles to permissions
-- ADMIN gets all permissions
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p WHERE r.name = 'ADMIN';

-- USER gets 
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p
WHERE r.name = 'USER' AND p.name IN ('VIEW_PRODUCT', 'MANAGE_PRODUCT','VIEW_ORDER','MANAGE_INVENTORY');

-- Insert sample users
INSERT INTO users (username, email,user_password, role_id)
SELECT 'admin_user', 'admin@example.com','$2a$12$k.15UKLKYwLAmRjzILh7COJVmKiBg5tERGhU2pyj6dnj2EdkJRonG', r.id FROM roles r WHERE r.name = 'ADMIN';

INSERT INTO users (username, email,user_password, role_id)
SELECT 'shazia', 'user@example.com', '$2a$12$k.15UKLKYwLAmRjzILh7COJVmKiBg5tERGhU2pyj6dnj2EdkJRonG',r.id FROM roles r WHERE r.name = 'USER';
