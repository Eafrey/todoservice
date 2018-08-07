CREATE TABLE todo (
  id int PRIMARY KEY AUTO_INCREMENT,
  content TEXT,
  complete BOOLEAN,
  readonly BOOLEAN,
  visible BOOLEAN,
  deleted BOOLEAN,
  date DATETIME,
  create_by int
)