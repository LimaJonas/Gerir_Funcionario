create database cad_func_exemplo;
use cad_func_exemplo;
create table funcionario(
	codigo integer(11) auto_increment primary key,
	nome varchar(50),
	telefone varchar(20)
);
insert into funcionario (codigo, nome, telefone) values 
('1','Jonas Lima','13123456789'),
('2','João Da Silva','13123123123');