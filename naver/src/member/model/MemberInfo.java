package member.model;

public class MemberInfo {

	private String userid;
	private String password;
	private String name;
	private String birthday;
	private boolean gender;
	private String email;
	private String phone;
	private String save_id;
	private String photo;

	public MemberInfo() {

	}

	
	public MemberInfo(String userid, String password, String name, String birthday, boolean gender, String email,
			String phone, String save_id, String photo) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.save_id = save_id;
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


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@Override
	public String toString() {
		return "MemberInfo [userid=" + userid + ", password=" + password + ", name=" + name + ", birthday=" + birthday
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", save_id=" + save_id + ", photo="
				+ photo + "]";
	}
	
	
	

}
