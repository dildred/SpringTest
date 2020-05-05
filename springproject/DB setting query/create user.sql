create database project_emp;
create user 'emp' identified by 'test';
grant all privileges on project_emp.*to 'emp';
flush privileges;