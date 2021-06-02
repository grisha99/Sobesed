CREATE SCHEMA `cinema` ;

CREATE TABLE `cinema`.`films` (
  `id`       INT          NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(255) NOT NULL COMMENT 'Название фильма',
  `duration` INT UNSIGNED NOT NULL COMMENT 'Длительность фильма, в минутах',
  PRIMARY KEY (`id`))
COMMENT = 'Список фильмов';

CREATE TABLE `cinema`.`seance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `begin` INT NOT NULL COMMENT 'Время начала сеанса, в минутах от начала дня (00:00)',
  `comment` VARCHAR(255) NULL COMMENT 'Комментарий ',
  PRIMARY KEY (`id`))
COMMENT = 'Сеансы';

CREATE TABLE `cinema`.`shedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `film_id` INT NOT NULL COMMENT 'id фильма',
  `seance_id` INT NOT NULL COMMENT 'id сеанса',
  `price` DOUBLE NOT NULL COMMENT 'стоимость просмотра',
  PRIMARY KEY (`id`),
  INDEX `FK_shedule_films_idx` (`film_id` ASC) VISIBLE,
  INDEX `FK_shedule_seance_idx` (`seance_id` ASC) VISIBLE,
  CONSTRAINT `FK_shedule_films`
    FOREIGN KEY (`film_id`)
    REFERENCES `cinema`.`films` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shedule_seance`
    FOREIGN KEY (`seance_id`)
    REFERENCES `cinema`.`seance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Расписание поросмотра';

CREATE TABLE `cinema`.`tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `schedule_id` INT NOT NULL COMMENT 'id сеанса в расписании',
  `number` VARCHAR(12) NOT NULL COMMENT 'Номер билета',
  PRIMARY KEY (`id`),
  INDEX `FK_tickets_shedule_idx` (`schedule_id` ASC) VISIBLE,
  CONSTRAINT `FK_tickets_shedule`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `cinema`.`shedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Купленные билеты';


INSERT INTO `cinema`.`films` (`name`, `duration`) VALUES
('Форсаж', 120),
('На гребне волны', 90),
('Один дома', 90),
('Тачки-2', 60);

INSERT INTO `cinema`.`seance` (`begin`, `comment`) VALUES
(540, 'Сеанс в 9:00'),
(610, 'Сеанс в 10:10'),
(700, 'Сеанс в 11:40'),
(820, 'Сеанс в 13:40'),
(890, 'Сеанс в 14:50'),
(1030, 'Сеанс в 17:10'),
(1130, 'Сеанс в 18:50'),
(1200, 'Сеанс в 20:00'),
(1300, 'Сеанс в 21:40'),
(1430, 'Сеанс в 23:50');

INSERT INTO `cinema`.`shedule` (`film_id`, `seance_id`, `price`) VALUES
(4, 1, 200.00),
(3, 2, 210.00),
(2, 3, 250.00),
(3, 4, 250.00),
(1, 5, 300.00),
(4, 6, 300.00),
(2, 7, 320.00),
(3, 8, 400.00),
(1, 9, 450.00),
(2, 10, 450.00);

INSERT INTO `cinema`.`tickets` (`schedule_id`, `number`) VALUES
(1, 'FD1234'),
(1, 'FD333'),
(2, 'AS555'),
(3, 'ASD123'),
(4, '11111'),
(4, '11133'),
(5, 'KL123'),
(1, 'DF456'),
(6, 'A112112'),
(6, 'A4566'),
(7, 'ALK234'),
(8, 'KJ123123'),
(9, 'LK34534'),
(10, 'AS123'),
(7, 'ASD3333'),
(2, '777ASD'),
(3, 'QWERTY12'),
(5, 'Z111'),
(3, 'AA666');
