-- 게시글 번호, 제목, 내용, 글쓴이, 날짜, 조회수, 비밀번호
-- no, title, writer, content, reg_date, read_count, pass
DROP TABLE faqSQL;
CREATE TABLE faqSQL(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(30 CHAR) NOT NULL,
  writer VARCHAR2(20 CHAR) NOT NULL,
  content VARCHAR2(1000 CHAR) NOT NULL,
  reg_date TIMESTAMP NOT NULL,
  read_count NUMBER(5) NOT NULL,
  pass VARCHAR2(20 CHAR) NOT NULL
);

DROP SEQUENCE faqSQL_seq;
CREATE SEQUENCE faqSQL_seq;


-- 페이징 처리를 위해 아래 SQL 쿼리를 COMMIT까지 30번 실행해 테이블에 데이터를 추가한다. 
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'공지 건','관리자','내용','2023-04-27 01:44:58',15,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질의응답','관리자','내용','2023-04-27 05:43:38',53,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문질문질문질문질문질문질문질문질문질문질문질문','관리자','내용','2023-04-27 05:44:32',35,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문질문질문질문질문질문질문질문질문질문질문질문','관리자','내용','2023-04-27 05:44:32',103,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'장문의 질문 건','관리자','내용','2023-04-27 05:44:32',42,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'장문의 질문 건','관리자','내용','2023-04-27 16:30:41',36,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'장문의 질문 건','관리자','내용','2023-04-27 05:03:52',82,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 05:44:32',132,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 03:37:44',97,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 05:04:23',64,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 05:44:32',15,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 05:44:32',9,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 05:44:32',79,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 05:44:32',38,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용','2023-04-27 04:59:15',46,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'메인페이지 건','관리자','내용','2023-04-27 03:40:43',16,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'종종 화면이 안보일 때','관리자','내용','2023-04-27 04:58:45',35,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'타 지역 맛집 건','관리자','내용','2023-04-27 05:44:32',81,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'댓글이 안써짐','관리자','내용','2023-04-27 05:50:21',77,'1234');
INSERT INTO faqSQL (no,title,writer,content,reg_date,read_count,pass) VALUES (faqSQL_seq.NEXTVAL,'회원가입이 안돼요','관리자','내용','2023-04-27 05:44:32',162,'1234');
COMMIT;
SELECT * FROM faqSQL ORDER BY no DESC;