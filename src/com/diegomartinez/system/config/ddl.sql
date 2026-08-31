drop database if exists auditoria_usuario_producto_2_in4av;
create database auditoria_usuario_producto_2_in4av;
use auditoria_usuario_producto_2_in4av;

create table Users(
	name varchar(50) not null check(length(name) <= 50),
    lastname varchar(50) not null check (length(lastname) <=50),
    email varchar(50) not null check (length(email)<= 50),
    user varchar(25) not null check(length(user)<= 25),
    password varchar(35)not null check(length(password)<= 35),
    id_user varchar(36) not null,
    constraint pk_users primary key(id_user)
);

delimiter $$
	create procedure sp_create_users ( in name_P varchar(50),  in lastname_P varchar(50),
										in email_P varchar (50),	
										in user_P varchar (25),
										in password_P varchar(35
										))
	begin
		insert into Users (name,lastname,email,user,password,id_user)
        values (name_p,lastname_p, email_p, user_p,password_p,uuid()); 
	end $$
delimiter ;

delimiter $$
	create procedure sp_login_user ( in user_P varchar(25), in password_P varchar(35))
	begin
		select * from Users where user = user_P and password = password_P;
	end $$
delimiter ;

# call sp_create_users ("a","a","a@","a","a123");

select*from Users;