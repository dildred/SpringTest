/*직원 일단 완성*/
/*테이블 생성 쿼리문*/
create table staff(
staff_number varchar(50) not null comment "직원 번호",
staff_pw varchar(50) not null comment "직원 비밀 번호",
staff_name varchar(20) not null comment "직원 이름",
staff_age integer not null comment "직원 나이",
staff_tel varchar(20) not null comment "직원 전화번호",
staff_id_num varchar(20) not null comment "직원 주민 등록 번호",
staff_email varchar(100) not null comment "직원 이메일",
staff_end_degree varchar(10) comment "직원 최종 학력",
staff_dep varchar(20) comment "직원 부서",
staff_level varchar(10) comment "직원 직급",
staff_pay varchar(20) comment "직원 급여",
staff_photo_file varchar(200) comment "직원 증명 사진 파일명",
staff_address varchar(200) comment "직원 주소",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
staff_date date not null comment "등록 일자",
staff_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/
alter table staff add constraint pk_staff_number_name Primary key(staff_number, staff_name);

/*유니크 키 설정 쿼리문*/
alter table staff add constraint un_staff_id_num unique(staff_id_num);
