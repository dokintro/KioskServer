DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS student_has_purchased;


CREATE TABLE IF NOT EXISTS users (
  `idUser` int(255) NOT NULL AUTO_INCREMENT UNIQUE,
  `nameUser` varchar(100) NOT NULL,
  `RFIDUSER` varchar(255) NOT NULL,
  `userIsAdmin` tinyint(1) NOT NULL DEFAULT '0'
);

CREATE TABLE IF NOT EXISTS products (
  `idProduct` int(255) NOT NULL AUTO_INCREMENT UNIQUE,
  `nameProduct` varchar(100) NOT NULL,
  `priceProduct` int(11) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '0'
);

CREATE TABLE student_has_purchased (
  `users_idUser` int(255) NOT NULL,
  `users_RFIDUser` VARCHAR(255) NOT NULL,
  `products_idProduct` int(255) NOT NULL,
  `amountBought` int(255) NOT NULL
);

ALTER TABLE `student_has_purchased` ADD CONSTRAINT `student_has_purchased_fk0` FOREIGN KEY (`users_idUser`) REFERENCES `Users`(`idUser`);

ALTER TABLE `student_has_purchased` ADD CONSTRAINT `student_has_purchased_fk1` FOREIGN KEY (`products_idProduct`) REFERENCES `Products`(`idProduct`);
