package com.bitcamp.op.member.model;

import org.springframework.web.multipart.MultipartFile;

public class MemberInfo {

	private String userid;
	private String password;
	private String name;
	private String birthday;
	private boolean gender;
	private String email;
	private String phone;
	private String save_id;
	private MultipartFile photoFile;
	private String photo;

	public MemberInfo() {

	}

	
	public MemberInfo(String userid, String password, String name, String birthday, boolean gender, String email,
			String phone, String save_id, MultipartFile photoFile, String photo) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.save_id = save_id;
		this.photoFile = photoFile;
		this.photo = photo;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public boolean isGender() {
		return gender;
	}


	public void setGender(boolean gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSave_id() {
		return save_id;
	}


	public void setSave_id(String save_id) {
		this.save_id = save_id;
	}


	public MultipartFile getPhotoFile() {
		return photoFile;
	}


	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public boolean isMatchPassword(String pw) {

		return password.equals(pw) ? true : false;
	}


	@Override
	public String toString() {
		return "MemberInfo [userid=" + userid + ", password=" + password + ", name=" + name + ", birthday=" + birthday
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", save_id=" + save_id
				+ ", photoFile=" + photoFile + ", photo=" + photo + "]";
	}

	
}
