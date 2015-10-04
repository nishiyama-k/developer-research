package jp.co.developer.research.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * TODO DataType of dayOfBirth and lang should be considered with thinking of DB table column design.
 *
 */
public class Profile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String profileNo;
	private String firstName;
	private String lastName;
	private Calendar dayOfBirth;
	private Integer age;
	private Integer speciality;
	private List<String> lang;

	public String getProfileNo() {
		return profileNo;
	}

	public void setProfileNo(String profileNo) {
		this.profileNo = profileNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Calendar getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Calendar dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}

	public List<String> getLang() {
		return lang;
	}

	public void setLang(List<String> lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "Profile [profileNo=" + profileNo + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dayOfBirth=" + dayOfBirth + ", age=" + age + ", speciality=" + speciality + ", lang=" + lang + "]";
	}

}
