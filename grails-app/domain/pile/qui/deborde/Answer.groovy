package pile.qui.deborde

import java.util.Date;

class Answer extends Post {

	boolean haveHelped = false;
	
	static belongsTo = [question:Question]
	
	static constraints = {
		body(blank:false, minSize: 20)
		date(blank:false)
	}
}
