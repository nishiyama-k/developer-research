package jp.co.developer.research.dao;

import jp.co.developer.research.entity.Profile;

/**
 * Data access interface of Profile
 *
 */
public interface ProfileDao {
	
	/**
	 * insert profile to storage
	 * 
	 * @param profile should be perfectly no error
	 * @return profileNo : key of profile
	 */
	String insert(Profile profile);
	
	/**
	 * find profile
	 * 
	 * @param profileNo
	 * @return
	 */
	Profile find(String profileNo);
}

