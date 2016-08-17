# --- !Ups

alter table users add role varchar(20);


# --- !Downs

SET FOREIGN_KEY_CHECKS = 0;

alter table users drop column role;

SET FOREIGN_KEY_CHECKS = 1;