/*발주 미완성(발주 회사 넣어야함)*/
/*테이블 생성 쿼리문*/
create table mat_order(
order_cd varchar(50) not null comment "발주 번호",
mat_no varchar(50) not null comment "재료 번호",
mat_name varchar(50) not null comment "발주 재료명",
order_qty integer not null comment "발주 수량",
order_date date comment "발주 날짜",
order_bill integer comment "상품 금액",
company_cd varchar(50) comment "발주 회사 코드",
order_comment varchar(100) comment "비고",
del_flg varchar(1) not null default '0' comment "삭제 플래그",
regist_date date not null comment "등록 일자",
regist_user varchar(50) not null comment "등록 사용자",
update_date date not null comment "변경 일자",
update_user varchar(50) not null comment "변경 사용자",
update_cnt integer default 0 comment "변경 횟수"
) engine=innodb charset=utf8mb4;

/*기본키 설정 쿼리문*/
alter table mat_order add constraint pk_order_cd Primary key(order_cd, mat_no, mat_name);
alter table mat_order add constraint fk_company_cd foreign key(company_cd) references order_company(company_cd);
alter table mat_order add constraint fk_mat_no foreign key(mat_no, mat_name) references material(mat_no, mat_name);
/*패치했으므로 drop table해주세요*/
drop table mat_order;
