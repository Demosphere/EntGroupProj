CREATE TABLE `sites` (
  `site_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`site_id`),
  UNIQUE KEY `sites_site_id_uindex` (`site_id`)
);

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_id` int(11) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `user_role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_user_id_uindex` (`user_id`),
  KEY `users_sites_site_id_fk` (`site_id`),
  CONSTRAINT `users_sites_site_id_fk` FOREIGN KEY (`site_id`) REFERENCES `sites` (`site_id`)
);
