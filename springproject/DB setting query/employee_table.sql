/*직원 일단 완성*/
/*테이블 생성 쿼리문*/
create table employee(
emp_no varchar(50) not null comment "직원 번호",
emp_pw varchar(50) not null comment "직원 비밀 번호",
emp_name varchar(20) not null comment "직원 이름",
emp_age integer not null comment "직원 나이",
emp_tel varchar(20) not null comment "직원 전화번호",
emp_id_num varchar(20) not null comment "직원 주민 등록 번호", 
emp_end_degree varchar(10) comment "직원 최종 학력",
emp_level varchar(10) comment "직원 직급",
emp_family_file varchar(200) comment "직원 가족 관계 증명서 파일명",
emp_info_file varchar(200) comment "직원 등본 파일명",
emp_photo_file varchar(200) comment "직원 증명 사진 파일명",
emp_address varchar(200) comment "직원 주소",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date date not null comment "등록 일자",
regist_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/
alter table employee add constraint pk_emp_no_name Primary key(emp_no, emp_name);

/*유니크 키 설정 쿼리문*/
alter table employee add constraint un_emp_id_num unique(emp_id_num);
