package pile.qui.deborde

class Post {
    
	int totalVote = 0
	
    static hasMany =  [comments:Comment, votes:Vote]
	static belongsTo = [author:Member]
}
