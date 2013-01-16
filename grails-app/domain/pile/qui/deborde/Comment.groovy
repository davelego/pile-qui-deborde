package pile.qui.deborde

class Comment {
	
	String body
	
	//Author author
	static belongsTo = [author:Author]
	
    static constraints = {
    }
}
