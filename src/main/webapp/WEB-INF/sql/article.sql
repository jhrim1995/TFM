CREATE TABLE article (
	at_no	number PRIMARY KEY,
	m_id	VARCHAR2(30 char) NOT NULL,
    title VARCHAR2(100 char) NOT NULL,
    content clob NOT NULL,
    pass VARCHAR2(10 Char) NOT NULL,
    w_date TIMESTAMP NOT NULL,
    views number(5) NOT NULL
);

select * from article;
drop table article;


DROP SEQUENCE article_seq;
CREATE SEQUENCE article_seq;

INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이01','맛집기사 제목01','@@@@@@@@@@@기사본문@1@@@@@@@@@@@@', 1234, '2025-05-10 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이02','맛집기사 제목02','@@@@@@@@@@@기사본문@2@@@@@@@@@@@@', 1234, '2025-05-11 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이03','맛집기사 제목03','@@@@@@@@@@@기사본문@3@@@@@@@@@@@@', 1234, '2025-05-12 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이04','맛집기사 제목04','@@@@@@@@@@@기사본문@4@@@@@@@@@@@@', 1234, '2025-05-13 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이05','맛집기사 제목05','@@@@@@@@@@@기사본문@5@@@@@@@@@@@@', 1234, '2025-05-14 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이06','맛집기사 제목06','@@@@@@@@@@@기사본문@6@@@@@@@@@@@@', 1234, '2025-05-15 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이07','맛집기사 제목07','@@@@@@@@@@@기사본문@7@@@@@@@@@@@@', 1234, '2025-05-16 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이08','맛집기사 제목08','@@@@@@@@@@@기사본문@8@@@@@@@@@@@@', 1234, '2025-05-17 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이09','맛집기사 제목09','@@@@@@@@@@@기사본문@9@@@@@@@@@@@@', 1234, '2025-05-18 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이10','맛집기사 제목10','@@@@@@@@@@@기사본문@10@@@@@@@@@@@@', 1234, '2025-05-19 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이01','맛집기사 제목01','@@@@@@@@@@@기사본문@1@@@@@@@@@@@@', 1234, '2025-05-20 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이02','맛집기사 제목02','@@@@@@@@@@@기사본문@2@@@@@@@@@@@@', 1234, '2025-05-21 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이03','맛집기사 제목03','@@@@@@@@@@@기사본문@3@@@@@@@@@@@@', 1234, '2025-05-22 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이04','맛집기사 제목04','@@@@@@@@@@@기사본문@4@@@@@@@@@@@@', 1234, '2025-05-23 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이05','맛집기사 제목05','@@@@@@@@@@@기사본문@5@@@@@@@@@@@@', 1234, '2025-05-24 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이06','맛집기사 제목06','@@@@@@@@@@@기사본문@6@@@@@@@@@@@@', 1234, '2025-05-25 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이07','맛집기사 제목07','@@@@@@@@@@@기사본문@7@@@@@@@@@@@@', 1234, '2025-05-26 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이08','맛집기사 제목08','@@@@@@@@@@@기사본문@8@@@@@@@@@@@@', 1234, '2025-05-27 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이09','맛집기사 제목09','@@@@@@@@@@@기사본문@9@@@@@@@@@@@@', 1234, '2025-05-28 10:00:00',10);
INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL,'글쓴이10','맛집기사 제목10','@@@@@@@@@@@기사본문@10@@@@@@@@@@@@', 1234, '2025-05-29 10:00:00',10);

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
  
