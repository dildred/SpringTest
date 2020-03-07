/*UTF-8형식으로 저장할 것*/
create database sppro;
create user 'sppro'@'localhost' identified by 'test';
grant all privileges on sppro.*to 'sppro'@'localhost';
flush privileges;