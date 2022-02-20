create SEQUENCE seq01;

create table
if not exists
users (
    id Number(2, 0) default seq01.nextval,
    firstname varchar(30),
    lastname varchar(30),
    primary_school varchar(30),
    junior_high_school varchar(30),
    high_school varchar(30)
);

insert into
users values (
    seq01.nextval,'Taiki','Takayanagi', 'Miho_DaiNi_Shogakkou', 'Shimizu_DaiGo_TyuGakkou', 'Shimizu_Nishi_Koukou'
);

insert into
users values (
    seq01.nextval,'Rika','Takayama', 'Hukishima_Shogakkou', 'Hukishima_TyuGakkou', 'Toukai_ShouGyou'
);

insert into
users values (
    seq01.nextval,'Kenjyurou','Yamamoto', 'Hukishima_Shogakkou', 'Hukishima_TyuGakkou', 'Toukai_ShouGyou'
);
