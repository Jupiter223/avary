CREATE DATABASE `avary` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `avary_info` (
  `id` varchar(45) NOT NULL,
  `ring` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `species` varchar(20) DEFAULT NULL,
  `death` tinyint DEFAULT '0',
  `dead_date` date DEFAULT NULL,
  `out_status` tinyint DEFAULT '0',
  `out_date` date DEFAULT NULL,
  `other_info` varchar(255) DEFAULT NULL,
  `card_pic` varchar(255) DEFAULT NULL,
  `avary_pic` varchar(255) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `couple_id` varchar(45) DEFAULT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `calendar` (
  `id` varchar(45) NOT NULL,
  `date` date DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `card` (
  `id` varchar(45) NOT NULL,
  `ring` varchar(45) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `couple_info` (
  `id` varchar(45) NOT NULL,
  `male_id` varchar(45) DEFAULT NULL,
  `female_id` varchar(45) DEFAULT NULL,
  `other_info` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `egg_info` (
  `id` varchar(45) NOT NULL,
  `parent_id` varchar(45) NOT NULL,
  `parent_nickname` varchar(255) DEFAULT NULL,
  `parent_location` varchar(255) DEFAULT NULL,
  `species` varchar(45) DEFAULT NULL,
  `nest` int NOT NULL,
  `count` int NOT NULL,
  `birthday` date DEFAULT NULL,
  `fertilization` tinyint DEFAULT NULL,
  `hatch` tinyint DEFAULT NULL,
  `hatch_date` date DEFAULT NULL,
  `other_info` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `nestling` (
  `id` varchar(45) NOT NULL,
  `parent_id` varchar(255) NOT NULL,
  `parent_location` varchar(45) DEFAULT NULL,
  `parent_nickname` varchar(45) DEFAULT NULL,
  `speices` varchar(45) DEFAULT NULL,
  `nest` int NOT NULL,
  `count` int NOT NULL,
  `birthday` date DEFAULT NULL,
  `ring` int DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `other_info` varchar(255) DEFAULT NULL,
  `is_dead` tinyint DEFAULT NULL,
  `is_out` tinyint DEFAULT NULL,
  `is_transfer` tinyint DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `stock` (
  `id` varchar(45) NOT NULL,
  `item_id` varchar(45) DEFAULT NULL,
  `stock_number` int DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `stock_change` (
  `id` varchar(45) NOT NULL,
  `item_id` varchar(45) DEFAULT NULL,
  `number` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `stock_item` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `todos` (
  `id` varchar(45) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `creat_date` date DEFAULT NULL,
  `alert_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `avatar` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1565572871071735814 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
