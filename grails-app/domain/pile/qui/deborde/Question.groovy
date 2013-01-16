package pile.qui.deborde

class Question {
	
	String title
	String body
	
	int notationUp = 0
	int notationDown = 0 
	
	//List<Answer> answers
	//List<Comment> comments
	//List<Tags> tags
	static hasMany = [answers:Answer, comments:Comment, tags:Tags]
	
	//Author author
	static belongsTo = [author:Author]
	
    static constraints = {
    }
}
