package pile.qui.deborde

import java.util.Date;

class Answer extends Post {
	
	String body
	Date date

	int notationUp = 0
	int notationDown = 0
	boolean topansewer = false;
	
	static belongsTo = [question:Question, author:Member]
	
	static constraints = {
		body(blank:false, minSize: 20)
		date(blank:false)
	}
	
}
