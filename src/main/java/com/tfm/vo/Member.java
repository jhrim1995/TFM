package com.tfm.vo;

public class Member {
		  private String m_id ; 
		  private String pass ;
		  private String email ;
		  private String m_name ;
		  private String nickname ;
		  private String birthday ;
		  private String gender ;
		  private String foreignyn ;
		  private String telecom ;
		  private String phone ;
		  private String m_date;
		  
		  
		 public Member(){ };
		  
		 public Member(String m_id, String pass, String email, String m_name, String nickname, String birthday,	String gender, String foreignyn, String telecom, String phone, String m_date) {
			this.m_id = m_id;
			this.pass = pass;
			this.email = email;
			this.m_name = m_name;
			this.nickname = nickname;
			this.birthday = birthday;
			this.gender = gender;
			this.foreignyn = foreignyn;
			this.telecom = telecom;
			this.phone = phone;
			this.m_date = m_date;
		}

		public String getM_id() {
			return m_id;
		}

		public void setM_id(String m_id) {
			this.m_id = m_id;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getM_name() {
			return m_name;
		}

		public void setM_name(String m_name) {
			this.m_name = m_name;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getForeignyn() {
			return foreignyn;
		}

		public void setForeignyn(String foreignyn) {
			this.foreignyn = foreignyn;
		}

		public String getTelecom() {
			return telecom;
		}

		public void setTelecom(String telecom) {
			this.telecom = telecom;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getM_date() {
			return m_date;
		}

		public void setM_date(String m_date) {
			this.m_date = m_date;
		} 
		  

}
