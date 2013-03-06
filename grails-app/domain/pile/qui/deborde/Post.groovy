package pile.qui.deborde

class Post {
    
    static hasMany =  [comments:Comment, votes:Vote]
	static belongsTo = [author:Member]
}
