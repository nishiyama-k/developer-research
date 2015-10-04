package jp.co.developer.research.dao.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.developer.research.entity.Profile;

/**
 * Singleton storage for mock 
 *
 */
public class MockProfileStorage {

	private static MockProfileStorage instance = new MockProfileStorage();

	private Map<String, Profile> cache = new HashMap<>();

	private MockProfileStorage() {
	}

	/**
	 * When use this, access from here.
	 * 
	 * @return
	 */
	public static MockProfileStorage getInstance() {
		return instance;
	}

	/**
	 * put profile 
	 * 
	 * @param profile
	 * @return profileNo
	 */
	public String put(Profile profile) {
		instance.cache.put(profile.getProfileNo(), profile);
		return profile.getProfileNo();
	}

	/**
	 * get profile from profileNo
	 * 
	 * @param profileNo
	 * @return if not exist, return null
	 */
	public Profile get(String profileNo) {
		return instance.cache.get(profileNo);
	}
}
