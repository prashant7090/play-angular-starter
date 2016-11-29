# --- !Ups

alter table users add token varchar(20);


# --- !Downs

SET FOREIGN_KEY_CHECKS = 0;

alter table users drop column token;

SET FOREIGN_KEY_CHECKS = 1;