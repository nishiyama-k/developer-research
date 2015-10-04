package jp.co.developer.research.response;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import jp.co.developer.research.entity.Profile;

public class FinishResponseParam {

	public String name;
	public String birthday;
	public String age;
	public String speciality;
	public String languages;

	public static FinishResponseParam of(Profile p) {
		return new FinishResponseParam(p);
	}

	private FinishResponseParam(Profile p) {
		this.name = p.getFirstName() + ' ' + p.getLastName();
		this.birthday = getBirthday(p.getDayOfBirth());
		this.age = String.valueOf(p.getAge());
		this.speciality = getSpeciality(p.getSpeciality());
		this.languages = getLang(p.getLang());

	}

	/**
	 * TODO Locale is fixed in ENGLISH.
	 * 
	 * @param cal
	 * @return
	 */
	private String getBirthday(Calendar cal) {
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
		return dateFormatter.format(cal.getTime());
	}

	/**
	 * TODO remove literal and define type in anywhere
	 * 
	 * @param type
	 * @return
	 */
	private String getSpeciality(int type) {
		String str = "";
		switch (type) {
		case 1:
			str = "Front-end";
			break;
		case 2:
			str = "Back-end";
			break;
		case 10:
			str = "Full-stack";
			break;
		}
		return str;
	}

	/**
	 * TODO remove literal and define langCd in anywhere
	 * 
	 * @param langs
	 * @return
	 */
	private String getLang(List<String> langs) {
		StringBuffer sb = new StringBuffer();
		for (String langCd : langs) {
			switch (langCd) {
			case "001":
				sb.append("C# / ");
				break;
			case "100":
				sb.append("HTML / ");
				break;
			case "110":
				sb.append("JavaScript / ");
				break;
			case "200":
				sb.append("SQL / ");
				break;
			}

		}
		String str = sb.toString();
		// langs must has some value, so ignore IndexOutOfBoundsException
		// remove last " / "
		return StringUtils.substring(str, 0, str.lastIndexOf(str) - 3);
	}
}
