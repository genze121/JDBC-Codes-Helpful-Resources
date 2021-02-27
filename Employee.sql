create database Employee;
use Employee;

create table Emp
(eno int,
ename varchar(25),
salary int);

insert into Emp(eno,ename,salary) values(1,'HI',2000);
insert into Emp(eno,ename,salary) values(2,'TI',4000);
insert into Emp(eno,ename,salary) values(3,'SI',6000);
insert into Emp(eno,ename,salary) values(4,'JI',8000);

select * from Emp;
