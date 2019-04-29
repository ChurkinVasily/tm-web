// -- Дамп Таблицы Projects
CREATE TABLE `projects` (
	`id` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`timeFinish` VARCHAR(255) NULL DEFAULT NULL,
	`timeStart` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

// -- Дамп таблицы Tasks
CREATE TABLE `projects` (
	`id` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`timeFinish` VARCHAR(255) NULL DEFAULT NULL,
	`timeStart` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

// -- Дамп таблицы Users
CREATE TABLE `users` (
	`id` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`password` VARCHAR(255) NULL DEFAULT NULL,
	`role` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

// -- Дамп таблицы Sessions (В приложении не используется)
CREATE TABLE `sessions` (
	`id` VARCHAR(255) NOT NULL,
	`signature` VARCHAR(255) NULL DEFAULT NULL,
	`userId` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
