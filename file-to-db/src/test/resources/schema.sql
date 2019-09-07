DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author` TEXT NULL,
  `title` TEXT NULL,
  `publisher` TEXT NULL,
  `ISBN` TEXT NULL,
  PRIMARY KEY (`id`));
