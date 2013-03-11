package pile.qui.deborde

import java.util.Date;

class Comment {
	
	String body
	Date date

	static belongsTo = [relatedPost:Post, author:Member]
	
	static constraints = {
		body(blank:false)
	}
	
	static mapping = {
		body type: 'text'
	}
}
