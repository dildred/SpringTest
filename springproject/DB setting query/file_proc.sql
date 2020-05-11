/*테이블 생성 쿼리문*/
create table file_proc(
file_proc_seq integer not null auto_increment comment "파일 처리 시퀀스",
file_origin_name varchar(300) not null comment "원래 파일 명",
file_store_name varchar(300) not null comment "저장할 파일 명",
file_directory varchar(300) not null comment "파일 저장 위치",
file_size varchar(300) not null comment "파일 사이즈",
proc_method varchar(300) not null comment "처리 요청 메소드명",
error_code longtext not null comment "에러 코드",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date timestamp default now() not null comment "등록 일자",
regist_user varchar(50) default 'SYSTEM' not null comment "등록 사용자",
update_date timestamp default now() not null comment "변경 일자",
update_user varchar(50) default 'SYSTEM' not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수",
primary key(file_proc_seq, file_store_name)
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/

/*유니크 키 설정 쿼리문*/
