CREATE TABLE article (
	at_no	number NOT NULL,
	m_id	varchar2(15)	NOT NULL,
	title	varchar2(100)	NULL,
	content	clob	NULL,
	p_date	date	NULL,
	views	number,
	fv	number
);

select * from article;
drop table article;


DROP SEQUENCE article_seq;
CREATE SEQUENCE article_seq;

INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이01','맛집기사 제목01','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-10 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),10,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이02','맛집기사 제목02','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-11 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),11,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이03','맛집기사 제목03','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-12 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),12,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이04','맛집기사 제목04','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-13 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),13,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이05','맛집기사 제목05','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-14 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),14,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이06','맛집기사 제목06','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-15 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),15,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이07','맛집기사 제목07','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-16 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),16,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이08','맛집기사 제목08','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-17 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),17,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이09','맛집기사 제목09','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-18 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),18,5);
INSERT INTO article (at_no, m_id, title, content, p_date, views, fv) VALUES (article_seq.NEXTVAL,'글쓴이10','맛집기사 제목10','@@@@@@@@@@@기사본문@@@@@@@@@@@@@',TO_TIMESTAMP('2025-05-19 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),19,5);

commit;

-- 기본 페이지 게시 글 수
SELECT COUNT(*) FROM article;

-- 기본 페이징
SELECT * FROM 
    (SELECT ROWNUM num, sj.* FROM 
        (SELECT * FROM article ORDER BY no DESC)sj)
WHERE num >= 1 AND num <= 10;

-- 검색 페이징(type = title, writer, content),  검색어
SELECT * FROM 
    (SELECT ROWNUM num, sj.* FROM 
        (SELECT * FROM article WHERE title LIKE '%@@%' ORDER BY no DESC)sj)
WHERE num >= 1 AND num <= 10;
  

select * from 
    (select rownum num, sub.* from
        (select * from jspbbs order by no desc) sub)
where num >= 41 and num <= 50;