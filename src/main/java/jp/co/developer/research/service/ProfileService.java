package jp.co.developer.research.service;

import java.util.Optional;

import jp.co.developer.research.entity.Profile;
import jp.co.developer.research.validate.ValidatorException;
import jp.co.developer.research.vo.ProfileVo;

/**
 * Service for Profile
 *
 */
public interface ProfileService {

	/**
	 * Register profile data<br>
	 * If the profile has validation error, profile is not registered.
	 * 
	 * @param profile
	 * @return profileNo : key of profile
	 * @throws ValidatorException
	 */
	public String register(ProfileVo profile) throws ValidatorException;
	
	/**
	 * Find profile
	 * 
	 * @param profileNo
	 * @return nullable
	 */
	public Optional<Profile> find(String profileNo);
	
}
