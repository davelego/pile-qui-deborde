package pile.qui.deborde

class Author {
	
	String firstName
	String lastName
	String email
	String bio
	String website
	
	String photoPath;
	static hasMany =  [givenAnswers:Answer, questions:Question]
	
	
    static constraints = {
		email(email:true)
		website url:true
    }
}