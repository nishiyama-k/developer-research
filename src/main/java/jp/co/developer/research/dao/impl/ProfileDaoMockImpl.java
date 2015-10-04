package jp.co.developer.research.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jp.co.developer.research.dao.ProfileDao;
import jp.co.developer.research.entity.Profile;

/**
 * Mock implementation
 *
 */
@Component
public class ProfileDaoMockImpl implements ProfileDao {

	private final Logger logger = LoggerFactory.getLogger(ProfileDaoMockImpl.class);

	@Override
	public String insert(Profile profile) {
		logger.info("Profile is registered: {}", profile.toString());
		return MockProfileStorage.getInstance().put(profile);
	}

	@Override
	public Profile find(String profileNo) {
		return MockProfileStorage.getInstance().get(profileNo);
	}

}
