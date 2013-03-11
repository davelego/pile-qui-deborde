package pile.qui.deborde

class Question extends Post {
	
	String title
	
	static hasMany = [answers:Answer, tags:Tag]
	
    static constraints = {
		title(blank:false, size: 10..280)
		body(blank:false, minSize: 50)
		date(blank:false)
		tags(nullable: false, minSize: 1, maxSize: 5)
    }
	
	static mapping = {
		sort date: "desc"
	    answers sort:'totalVote', order:'asc' , cascade: 'all-delete-orphan'
	}
}
