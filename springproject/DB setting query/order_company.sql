/*발주 회사 테이블*/
/*테이블 생성 쿼리문*/
create table order_company(
company_name varchar(50) comment "발주 회사 명",
company_cd varchar(50) comment "발주 회사 코드",
company_tel varchar(20) comment "발주 회사 전화 번호",
company_address varchar(50) comment "발주 회사 주소",
company_comment varchar(100) comment "비고",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date date not null comment "등록 일자",
regist_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;


/*기본키 설정 쿼리문*/
alter table order_company add constraint pk_company_cd Primary key(company_cd);

insert into order_company values ('sampleName','sample01','sampleTel','sampleAddr','sampleCom','0',now(),'SYSTEM',now(),'SYSTEM','0');
commit;