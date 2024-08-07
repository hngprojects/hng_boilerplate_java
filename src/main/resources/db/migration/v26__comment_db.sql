CREATE TABLE comments(
comment_id VAR(36) PRIMARY KEY,
user_id VARCHAR(36), NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
comment VARCHAR(1000), NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id)
);