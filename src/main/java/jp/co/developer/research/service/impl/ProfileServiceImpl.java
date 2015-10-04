package jp.co.developer.research.service.impl;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.developer.research.dao.ProfileDao;
import jp.co.developer.research.entity.Profile;
import jp.co.developer.research.service.ProfileService;
import jp.co.developer.research.service.ValidatorService;
import jp.co.developer.research.service.validator.BirthdayValidator;
import jp.co.developer.research.util.BirthdayUtil;
import jp.co.developer.research.validate.ValidatorException;
import jp.co.developer.research.vo.ProfileVo;

@Service
public class ProfileServiceImpl implements ProfileService {

	private ValidatorService validator;
	private ProfileDao dao;
	
	@Autowired
	public ProfileServiceImpl(ValidatorService validator, ProfileDao dao) {
		this.validator = validator;
		this.dao = dao;
	}
	
	@Override
	public String register(ProfileVo profile) throws ValidatorException{
		validator.validate(profile, Arrays.asList(new BirthdayValidator()));
		return dao.insert(generateEntityFromProfileVo(profile));
	}
	
	/**
	 * Convert to Profile entity in order to pass dao.
	 * 
	 * @param profile
	 * @return
	 */
	private Profile generateEntityFromProfileVo(ProfileVo profile){
		Profile entity = new Profile();
		entity.setProfileNo(UUID.randomUUID().toString());//TODO temporary
		entity.setFirstName(profile.getFirstName());
		entity.setLastName(profile.getLastName());
		entity.setDayOfBirth(BirthdayUtil.trimTime(profile.getDayOfBirth()));
		entity.setAge(BirthdayUtil.calculateAge(profile.getDayOfBirth()));
		entity.setSpeciality(profile.getSpeciality());
		entity.setLang(profile.getLang());
		return entity;
	}

	@Override
	public Optional<Profile> find(String profileNo) {
		return Optional.ofNullable(dao.find(profileNo));
	}

}
