package jp.co.developer.research.vo;

import java.util.Calendar;
import java.util.List;

import jp.co.developer.research.validate.annotation.BasicValidate;

@BasicValidate
public class ProfileVo {

	@BasicValidate(required = true, label="First Name")
	private String firstName;

	@BasicValidate(required = true, label="Last Name")
	private String lastName;

	@BasicValidate(required = true, label="Day of birth")
	private Calendar dayOfBirth;

	@BasicValidate(required = true, label="Speciality")
	private Integer speciality;

	@BasicValidate(required = true, label="Experience in languages")
	private List<String> lang;

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
		return "ProfileVo [firstName=" + firstName + ", lastName=" + lastName + ", dayOfBirth=" + dayOfBirth
				+ ", speciality=" + speciality + ", lang=" + lang + "]";
	}
}
