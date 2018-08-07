CREATE TABLE task (
 id INT PRIMARY key AUTO_INCREMENT,
 task TEXT,
 todo_id INT,
 FOREIGN KEY (todo_id) REFERENCES todo(id)
)