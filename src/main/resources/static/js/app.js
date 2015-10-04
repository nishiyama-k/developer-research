var developer = developer || {};
developer.research = developer.research || {};

/**
 * Main application class
 * 
 * JavaScript doc in accordance with Closure Compiler 
 * @see https://developers.google.com/closure/compiler/docs/js-for-compiler#types
 * @constructor
 */
developer.research.App = function() {
	/**
	 * @type{developer.research.ModelHelper}
	 * @private
	 */
	this.modelHelper_ = new developer.research.ModelHelper();
	/**
	 * @type{developer.research.Validator}
	 * @private
	 */
	this.validator_ = new developer.research.Validator();

	this.bindEvent_();
};

/**
 * bind event
 * 
 * @private
 */
developer.research.App.prototype.bindEvent_ = function() {
	$('button[type="submit"]').click($.proxy(this.onClickSubmitBtn_, this));
};

/**
 * handle click event at submit
 * 
 * @private
 */
developer.research.App.prototype.onClickSubmitBtn_ = function() {
	var model = this.modelHelper_.generateModel();
	var errors = this.validator_.validate(model);
	
	// If you want to try server side validation, please comment out this block; 
	if (errors.length > 0){
		var errors = this.validator_.showErrorPanel(errors);
		return;
	}
	
	$.ajax({
		headers : {
			'Content-Type' : 'application/json'
		},
		'type' : 'POST',
		'url' : 'submit',
		'data' : JSON.stringify(model),
		'success' : $.proxy(this.onSubmitResult_, this),
	});
};

/**
 * handle submit result
 * 
 * @param{string} profileNo
 * @private
 */
developer.research.App.prototype.onSubmitResult_ = function(response) {
	var res = JSON.parse(response);
	var result = res['result'];
	switch (res.status) {
	case developer.research.Util.SubmitResponseCode.SUCCESS:
		var profileNo = result;
		document.location.href = "finish?profileNo=" + profileNo;
		break;
	case developer.research.Util.SubmitResponseCode.VALIDATION_ERROR:
		this.validator_.showErrorPanel(result);
		break;
	case developer.research.Util.SubmitResponseCode.RUNTIME_ERROR:
		var errorMsg_ = [ {
			'key' : null,
			'message' : 'Error occured. Please inform administrator.'
		} ];
		this.validator_.showErrorPanel(errorMsg_);
		throw new Error(result);
		break;
	default:
		throw new Error('Invalid status code:' + res.status);
	};
};
