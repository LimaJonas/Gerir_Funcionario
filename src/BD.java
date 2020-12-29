/*
BANCO DE DADOS ======

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

*/

import java.sql.*;

public class BD {
    public Connection connection = null;
    private final String DRIVER = "org.gjt.mm.mysql.Driver";
    private final String DBNAME = "cad_func_exemplo";
    private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    private final String LOGIN = "root";
    private final String SENHA = "";
    
    public boolean getConnection(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou");
            return true;
        } catch (ClassNotFoundException erro){
            System.out.println("Driver não encontrado\n "+ erro.toString());
            return false;
        } catch (SQLException erro){
            System.out.println("Problemas de comunicação com o Banco de dados\n "+ erro.toString());
            return false;
        }
    }
    
    public void close(){
        try{
            connection.close();
            System.out.println("Desconectou");
        } catch (SQLException erro){
            System.out.println("Problemas de comunicação com o Banco de dados\n "+ erro.toString());
        }
    }
}