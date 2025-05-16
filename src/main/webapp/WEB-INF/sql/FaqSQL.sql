-- 게시글 번호, 제목, 내용, 글쓴이, 조회수
-- no, title, writer, content, read_count
DROP TABLE faqSQL;
CREATE TABLE faqSQL(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(30 CHAR) NOT NULL,
  writer VARCHAR2(20 CHAR) NOT NULL,
  content VARCHAR2(1000 CHAR) NOT NULL,
  read_count NUMBER(5) NOT NULL
);

DROP SEQUENCE faqSQL_seq;
CREATE SEQUENCE faqSQL_seq;


INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'공지 건','관리자','내용',15);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질의응답','관리자','내용',53);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문질문질문질문질문질문질문질문질문질문질문질문','관리자','내용',35);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문질문질문질문질문질문질문질문질문질문질문질문','관리자','내용',103);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'장문의 질문 건','관리자','내용',42);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'장문의 질문 건','관리자','내용',36);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'장문의 질문 건','관리자','내용',82);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',132);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',97);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',64);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',15);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',9);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',79);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',38);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'질문 건','관리자','내용',46);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'메인페이지 건','관리자','내용',16);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'종종 화면이 안보일 때','관리자','내용',35);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'타 지역 맛집 건','관리자','내용',81);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'댓글이 안써짐','관리자','내용',77);
INSERT INTO faqSQL (no,title,writer,content,read_count) VALUES (faqSQL_seq.NEXTVAL,'회원가입이 안돼요','관리자','내용',162);
COMMIT;
SELECT * FROM faqSQL ORDER BY no DESC;