package jp.co.developer.research.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.developer.research.entity.Profile;
import jp.co.developer.research.response.FinishResponseParam;
import jp.co.developer.research.response.ResponseStatusCode;
import jp.co.developer.research.response.SubmitResponseParam;
import jp.co.developer.research.service.ProfileService;
import jp.co.developer.research.validate.ValidatorException;
import jp.co.developer.research.vo.ProfileVo;

@Controller
public class MainController {

	private static String INPUT_VIEW = "input";
	private static String FINISH_VIEW = "finish";
	
	private ProfileService service;

	@Autowired
	public MainController(ProfileService service) {
		this.service = service;
	}

	/**
	 * input page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({"/", "/index"})
	public String input(Model model) {
		return INPUT_VIEW;
	}

	/**
	 * submit profile
	 * 
	 * @param profileJson
	 * @param model
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public String submit(@RequestBody String profileJson, Model model) throws JsonProcessingException {
		SubmitResponseParam response = new SubmitResponseParam();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ProfileVo profile = mapper.readValue(profileJson, ProfileVo.class);

			String profileNo = service.register(profile);

			response.setStatus(ResponseStatusCode.SUCCESS);
			response.setResult(profileNo);

		} catch (ValidatorException e) {
			response.setStatus(ResponseStatusCode.VALIDATION_ERROR);
			response.setResult(e.getErrorList());

		} catch (Exception e1) {
			e1.printStackTrace();
			response.setStatus(ResponseStatusCode.RUNTIME_ERROR);
			response.setResult(e1.getMessage());
		}
		return response.toJson();
	}

	/**
	 * finish page
	 * 
	 * @param profileNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/finish")
	public String finish(@RequestParam String profileNo, Model model) {
		Optional<Profile> optional = service.find(profileNo);
		optional.ifPresent(p -> {
			FinishResponseParam param = FinishResponseParam.of(p);
			model.addAttribute("name", param.name);
			model.addAttribute("birthday", param.birthday);
			model.addAttribute("age", param.age);
			model.addAttribute("speciality", param.speciality);
			model.addAttribute("languages", param.languages);
		});
		return FINISH_VIEW;
	}
}
