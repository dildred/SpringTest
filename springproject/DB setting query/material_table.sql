/*테이블 생성 쿼리문*/
create table material(
mat_no varchar(50) not null comment "재료 번호",
mat_name varchar(50) not null comment "재료 이름",
weight_unit varchar(11) comment "재료 중량단위",
mat_status varchar(50) comment "재료 분류",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date date not null comment "등록 일자",
regist_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/
alter table material add constraint pk_mat_no_name Primary key(mat_no, mat_name);

/*유니크 키 설정 쿼리문*/
