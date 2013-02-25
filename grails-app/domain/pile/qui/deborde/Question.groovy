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
		title(blank:false, size: 10..280)
		body(blank:false, minSize: 50)
		date(blank:false)
		tags(nullable: false, minSize: 1, maxSize: 5)
    }
}
