package pile.qui.deborde

class Question {
	
	String title
	String body
	int notationUp = 0
	int notationDown = 0 
	Date date
	
	static hasMany = [answers:Answer, comments:Comment, tags:Tag]
	static belongsTo = [author:Member]
	
    static constraints = {
		title(blank:false)
		body(blank:false)
		date(blank:false)
    }
}
