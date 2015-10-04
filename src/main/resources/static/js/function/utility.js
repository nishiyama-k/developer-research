var developer = developer || {};
developer.research = developer.research || {};

developer.research.Util = function() {

};

/**
 * Submit ResponseCode<br>
 * @see jp.co.developer.research.response.ResponseStatusCode
 * 
 * @enum {number}
 */
developer.research.Util.SubmitResponseCode = {
	SUCCESS : 100,
	VALIDATION_ERROR : 200,
	RUNTIME_ERROR : 999
};
