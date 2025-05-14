create table members(
    m_id varchar2(30) primary key,
    pass varchar2(30) not null,
    email varchar2(50),
    m_name varchar2(20) not null,
    nickname varchar2(20) not null,
    birthday varchar2(8) not null,
    gender varchar2(10) not null,
    foreignyn char not null,
    telecom varchar2(10) not null,
    phone varchar2(13) not null,
    m_date date
);

SELECT * FROM members;