var developer = developer || {};
developer.research = developer.research || {};

/**
 * Validator functions
 * 
 * @constructor
 */
developer.research.Validator = function() {

	/**
	 * Used only client side validation
	 * 
	 * @type{Array<developer.research.Validator.ErrorTypeDef>}
	 */
	this.errors_ = null;
	/**
	 * Used only client side validation
	 * 
	 * @type{developer.research.ModelHelper.ProfileModelTypeDef}
	 */
	this.profileModel_ = null;
	/**
	 * @type{boolean}
	 */
	this.isAlreadyErrorPanelShown_ = false;
};

/**
 * @type{{key:string, message:string}}
 */
developer.research.Validator.ErrorTypeDef;

/**
 * validate<br>
 * this client side validate maybe is not needed, since same validates are done
 * in server side. However, this is maybe needed as consider of speed.<br>
 * TODO label should be managed under DB
 * 
 * @param{developer.research.ModelHelper.ProfileModelTypeDef} profileModel
 * @return{Array<developer.research.Validator.ErrorTypeDef>}
 */
developer.research.Validator.prototype.validate = function(profileModel) {
	this.errors_ = [];
	this.profileModel_ = profileModel;
	this.checkRequired_('firstName', 'First Name');
	this.checkRequired_('lastName', 'Last Name');
	// birthday over future is checked in server side.
	this.checkRequired_('dayOfBirth', 'Day of birth');
	this.checkRequired_('speciality', 'Speciality');
	this.checkRequired_('lang', 'Experience in languages');
	return this.errors_;
};

/**
 * check the value is mandatory TODO message should be managed under DB
 * 
 * @param{string} key profileModel's key
 * @param{string} label for validate message
 * @private
 */
developer.research.Validator.prototype.checkRequired_ = function(key, label) {
	var value = this.profileModel_[key];
	if (value === null || value.length === 0) {
		this.errors_.push({
			'key' : key,
			'message' : label + ' is required. Please enter it.'
		});
	}
};

/**
 * show error panel
 * 
 * @param{Array<developer.research.Validator.ErrorTypeDef>} profileModel
 * @private
 */
developer.research.Validator.prototype.showErrorPanel = function(errorList) {
	this.errorList_ = errorList;
	var df = document.createDocumentFragment();
	for (var i = 0; i < this.errorList_.length; i++) {
		var $h = document.createElement('h6');
		$h.innerHTML = this.errorList_[i]['message'];
		df.appendChild($h);
	}
	$('#error-panel .panel-body').empty();
	$('#error-panel .panel-body').append(df);
	if (!this.isAlreadyErrorPanelShown_) {
		$('#error-panel').slideDown(500, "linear");
		this.isAlreadyErrorPanelShown_ = true;
	}
};
