CREATE TABLE article (
	at_no	number PRIMARY KEY,
	m_id	VARCHAR2(30 char) NOT NULL,
    title VARCHAR2(100 char) NOT NULL,
    content clob NOT NULL,
    pass VARCHAR2(10 Char) NOT NULL,
    w_date TIMESTAMP NOT NULL,
    views number(5) NOT NULL,
    recm number(5)
);

select * from article;
drop table article;


DROP SEQUENCE article_seq;
CREATE SEQUENCE article_seq;

INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이01','맛집기사 제목01','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@1@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-10 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이02','맛집기사 제목02','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@2@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-11 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이03','맛집기사 제목03','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@3@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-12 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이04','맛집기사 제목04','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@4@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-13 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이05','맛집기사 제목05','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@5@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-14 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이06','맛집기사 제목06','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@6@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-15 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이07','맛집기사 제목07','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@7@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-16 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이08','맛집기사 제목08','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@8@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-17 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이09','맛집기사 제목09','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@9@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-18 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이10','맛집기사 제목10','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@10@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-19 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이01','맛집기사 제목01','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@1@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-20 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이02','맛집기사 제목02','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@2@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-21 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이03','맛집기사 제목03','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@3@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-22 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이04','맛집기사 제목04','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@4@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-23 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이05','맛집기사 제목05','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@5@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-24 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이06','맛집기사 제목06','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@6@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-25 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이07','맛집기사 제목07','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@7@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-26 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이08','맛집기사 제목08','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@8@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-27 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이09','맛집기사 제목09','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@9@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-28 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이10','맛집기사 제목10','@@@@@ || CHR(13) || CHR(10) || @@@@@@기사본문@10@@@@@@ || CHR(13) || CHR(10) || @@@@@@', 1234, '2025-05-29 10:00:00',10);

commit;

select * from article ORDER BY at_no DESC;;

-- 기본 페이지 게시 글 수
SELECT COUNT(*) FROM article;

-- 기본 페이징
SELECT * FROM (SELECT ROWNUM num, sa.* FROM (SELECT * FROM article ORDER BY at_no DESC)sa) WHERE num >= 1 AND num <= 10;

-- 검색 페이징(type = title, writer, content),  검색어
SELECT * FROM 
    (SELECT ROWNUM num, sa.* FROM 
        (SELECT * FROM article WHERE title LIKE '%@@%' ORDER BY at_no DESC)sa)
WHERE num >= 1 AND num <= 10;


/* 먼저 HR 스키마에 reply 테이블과 reply 테이블에서 사용할 SEQUENCE를 생성한다.
 * 아래의 댓글 데이터를 reply 테이블에 추가한다.
 **/
DROP TABLE at_reply;
CREATE TABLE at_reply(
	c_no NUMBER PRIMARY KEY,
    at_no NUMBER  NOT NULL,
    m_id VARCHAR2(30 CHAR) NOT NULL,
    c_con VARCHAR2(100 CHAR) NOT NULL,
	c_date TIMESTAMP NOT NULL,
	CONSTRAINT at_rfk1 FOREIGN KEY(at_no) REFERENCES article(at_no)
    --CONSTRAINT at_rfk2 FOREIGN KEY(m_id) REFERENCES members(m_id)--
);

-- 댓글 테이블의 시퀀스
DROP SEQUENCE atr_seq;
CREATE SEQUENCE atr_seq
      MAXVALUE 9999999
      INCREMENT BY 1      
      NOCACHE
      NOORDER
      NOCYCLE;

-- 댓글 데이터 추가하기 - 1번만 실행 한다. --
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 200, '댓글쓴이01', '항상 감사합니다~',  '2023-05-08 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 200, '댓글쓴이02', '항상 감사합니다~',  '2023-05-09 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 200, '댓글쓴이03', '항상 감사합니다~',  '2023-05-01 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 200, '댓글쓴이04', '항상 감사합니다~',  '2023-05-02 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 200, '댓글쓴이05', '항상 감사합니다~',  '2023-05-03 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 199, '댓글쓴이06', '항상 감사합니다~',  '2023-05-04 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 199, '댓글쓴이07', '항상 감사합니다~',  '2023-05-05 13:41:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 199, '댓글쓴이08', '항상 감사합니다~',  '2023-05-06 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 199, '댓글쓴이09', '항상 감사합니다~',  '2023-05-07 13:44:32');
INSERT INTO at_reply(c_no, at_no, m_id, c_con, c_date) VALUES(reply_seq.NEXTVAL, 199, '댓글쓴이10', '항상 감사합니다~',  '2023-05-05 13:44:32');

commit;
SELECT * FROM at_reply;


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

select * from members;
