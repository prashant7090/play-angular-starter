# --- !Ups
create table users(

id 			bigint auto_increment not null,
email		varchar(100) not null,
name		varchar(100) not null,
mobile		varchar(20),
password 	varchar(255),

constraint dl_user primary key(id)

);


# --- !Downs
SET FOREIGN_KEY_CHECKS=0;

drop table if exists users;

SET FOREIGN_KEY_CHECKS=1;