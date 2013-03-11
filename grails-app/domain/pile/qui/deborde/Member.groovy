package pile.qui.deborde

class Member {
	
	String firstName
	String lastName
	String email
	String pseudo
	String password
	String bio
	String website
	byte [] avatar
	String avatarType
	String role = "author"
	Date dateNaissance
	Date dateInscription
	
	int reputation = 0

	static hasMany = [givenAnswers:Answer, questions:Question, comments:Comment, votes:Vote, badges:Badge]
	
    static constraints = {
		firstName(blank:false)
		lastName(blank:false)
		pseudo(blank:false, unique:true, size: 5..15)
		password(blank:false, size: 5..15)
		email(blank:false, unique:true, email:true)
		website(url:true)
		dateNaissance(max: new Date())
		role(inList:["author", "admin"])
		avatar(nullable:true,maxSize: 16384)
		avatarType(nullable:true)
    }
	
	static mapping = {
		bio type: 'text'
	}
}