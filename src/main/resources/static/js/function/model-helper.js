var developer = developer || {};
developer.research = developer.research || {};

/**
 * Helper class for profile model
 * 
 * @constructor
 */
developer.research.ModelHelper = function() {
	/**
	 * @type{Array<element>}
	 * @private
	 */
	this.$formGroups_ = $('.form-group');
};

/**
 * @typedef{{firstName:string, lastName:string, dayOfBirth:string,
 *                             speciality:number, lang:Array<string>}}
 */
developer.research.ModelHelper.ProfileModelTypeDef;

/**
 * get initialized profile model object
 * 
 * @return{developer.research.ModelHelper.ProfileModelTypeDef}
 * @private
 */
developer.research.ModelHelper.prototype.getNewModelDef_ = function() {
	return {
		'firstName' : null,
		'lastName' : null,
		'dayOfBirth' : null,
		'speciality' : null,
		'lang' : []
	};
};

/**
 * generate profileModel
 * 
 * @return{developer.research.ModelHelper.ProfileModelTypeDef}
 */
developer.research.ModelHelper.prototype.generateModel = function(
		profileModel) {
	var profileModel = this.getNewModelDef_();
	this.setInputElement_(profileModel);
	this.setRadioElement_(profileModel);
	this.setCheckboxElement_(profileModel);
	return profileModel;
};

/**
 * set input form element value in profileModel
 * 
 * @param{developer.research.ModelHelper.ProfileModelTypeDef} profileModel
 */
developer.research.ModelHelper.prototype.setInputElement_ = function(
		profileModel) {
	this.$formGroups_.each(function(index, $formGroups) {
		var $inputs = $($formGroups).children('input');
		$inputs.each(function(index, $input) {
			if (profileModel.hasOwnProperty($input.name)) {
				profileModel[$input.name] = $input.value;
			} else {
				console.log('Invalid element name : ' + $input);
			}
		});
	});
};

/**
 * set radio input element value in profileModel
 * 
 * @param{developer.research.ModelHelper.ProfileModelTypeDef} profileModel
 */
developer.research.ModelHelper.prototype.setRadioElement_ = function(
		profileModel) {
	var $radioGroups = $(this.$formGroups_).children('.radio-group');
	$radioGroups.each(function(index, $radioGroup) {
		var $radios = $($radioGroup).find('input[type="radio"]');
		$radios.each(function(index, $radio) {
			if (!$radio.checked) {
				return;
			}
			if (profileModel.hasOwnProperty($radio.name)) {
				profileModel[$radio.name] = $radio.value;
			} else {
				console.log('Invalid element name : ' + $radio);
			}
		});
	});
};

/**
 * set checkbox input element value in profileModel
 * 
 * @param{developer.research.ModelHelper.ProfileModelTypeDef} profileModel
 */
developer.research.ModelHelper.prototype.setCheckboxElement_ = function(
		profileModel) {
	var $checkGroups = $(this.$formGroups_).children('.checkbox-group');
	$checkGroups.each(function(index, $checkGroup) {
		var $checks = $($checkGroup).find('input[type="checkbox"]');
		$checks.each(function(index, $check) {
			if (!$check.checked) {
				return;
			}
			if (profileModel.hasOwnProperty($check.name)) {
				profileModel[$check.name].push($check.value);
			} else {
				console.log('Invalid element name : ' + $check);
			}
		});
	});
};