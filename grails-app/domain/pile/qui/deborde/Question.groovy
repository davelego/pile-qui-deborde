package pile.qui.deborde

class Question extends Post {
	
	String title
	String body
	Date date
	
	static hasMany = [answers:Answer, tags:Tag]
	
    static constraints = {
		title(blank:false, size: 10..280)
		body(blank:false, minSize: 50)
		date(blank:false)
		tags(nullable: false, minSize: 1, maxSize: 5)
    }
	
	static mapping = {
		body type: 'text'
	  }
}
