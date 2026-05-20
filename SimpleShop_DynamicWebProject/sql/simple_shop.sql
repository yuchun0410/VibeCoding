DROP DATABASE IF EXISTS simple_shop;
CREATE DATABASE simple_shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE simple_shop;

CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(64) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    image_url VARCHAR(255)
);

INSERT INTO members(username, password_hash, name, email, role) VALUES
('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', '系統管理員', 'admin@codebyx.local', 'ADMIN'),
('test', 'ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae', '測試會員', 'test@codebyx.local', 'USER');

INSERT INTO products(name, description, price, stock, image_url) VALUES
('Java 17 課程包', '適合 Java 初學者到認證準備的教學包。', 1990.00, 20, 'assets/img/java17.svg'),
('Spring Boot 實戰包', '後端 RESTful API、JPA、MySQL 實戰教材。', 2990.00, 15, 'assets/img/springboot.svg'),
('MySQL 資料庫入門', '資料庫設計、SQL、CRUD 實務範例。', 1290.00, 30, 'assets/img/mysql.svg'),
('Web 前端基礎包', 'HTML、CSS、JSP 表單與 Bootstrap 基礎。', 990.00, 25, 'assets/img/frontend.svg'),
('AI 工具入門包', 'AI 工具與開發流程的基礎實作教材。', 2490.00, 10, 'assets/img/aitools.svg'),
('CodeByX VIP Bundle', 'Java + Web + Database 整合範例包。', 4990.00, 5, 'assets/img/vip.svg');

-- 測試帳號：
-- admin / admin123
-- test  / test123
