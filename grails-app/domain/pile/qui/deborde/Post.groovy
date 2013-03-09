package pile.qui.deborde

class Post {
    
	int totalVote = 0
	Date date
	
    static hasMany =  [comments:Comment, votes:Vote]
	static belongsTo = [author:Member]
	static mapping = {
		comments cascade: 'all-delete-orphan'
	}
}
