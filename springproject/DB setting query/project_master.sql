/*마스터 테이블*/
/*테이블 생성 쿼리문*/
create table proj_master(
m_cd varchar(50) not null comment "마스터 코드",
m_select1 varchar(20) comment "마스터 식별 1",
m_select2 varchar(20) comment "마스터 식별 2",
m_select3 varchar(20) comment "마스터 식별 3",
m_value varchar(2000) not null comment "마스터 식별 값",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date date not null comment "등록 일자",
regist_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/
alter table proj_master add constraint pk_m_cd Primary key(m_cd,m_select1,m_select2,m_select3);

/*마스터 처리 입력문*/
/*알바생 시급*/
insert into proj_master(m_cd,m_select1,m_select2,m_select3,m_value,regist_date,regist_user,update_date,update_user)
values('W001','SALARY','ALBA','','9000',now(),'SYSTEM',now(),'SYSTEM');
/*정직원 기본 월급(신입기준)*/
insert into proj_master(m_cd,m_select1,m_select2,m_select3,m_value,regist_date,regist_user,update_date,update_user)
values('W001','SALARY','EMPL','','2000000',now(),'SYSTEM',now(),'SYSTEM');
