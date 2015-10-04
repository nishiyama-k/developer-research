package jp.co.developer.research.service.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import jp.co.developer.research.validate.AbstractBusinessValidator;
import jp.co.developer.research.vo.ProfileVo;
import jp.co.developer.research.vo.ValidateMessage;

/**
 * Check validate of Profile except BasicValidator
 *
 */
public class BirthdayValidator extends AbstractBusinessValidator<ProfileVo> {

	@Override
	public List<ValidateMessage> validate(ProfileVo profile) {
		List<ValidateMessage> errorList = new ArrayList<>();
		checkFuture(profile).ifPresent(e -> errorList.add(e));
		return errorList;
	}

	/**
	 * Check dayOfBirth is future<br>
	 * null check is already done at BasicValidate, so ignore the null check
	 * here.
	 * 
	 * @param profile
	 * @return
	 */
	private Optional<ValidateMessage> checkFuture(ProfileVo profile) {
		if (profile.getDayOfBirth() != null) {
			if (profile.getDayOfBirth().compareTo(Calendar.getInstance()) > 0) {
				// TODO ValidateMessage's keyshould be operated same as
				// BasicValidator, and message also same.
				return Optional.of(ValidateMessage.of("dayOfBirth", "Day of Birth is future."));
			}
		}
		return Optional.ofNullable(null);
	}
}
