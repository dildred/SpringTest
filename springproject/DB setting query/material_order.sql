/*발주 미완성(발주 회사 넣어야함)*/
/*테이블 생성 쿼리문*/
create table mat_order(
morder_name varchar(50) not null comment "발주 명",
morder_qty integer not null comment "발주 수량",
morder_date date comment "발주 날짜",
morder_bill integer comment "상품 금액",
morder_company_name varchar(50) comment "발주 회사 명",
morder_company_code varchar(50) comment "발주 회사 코드",
morder_comment varchar(100) comment "비고",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date date not null comment "등록 일자",
regist_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/
alter table mat_order add constraint pk_morder_name Primary key(morder_name, morder_date);
