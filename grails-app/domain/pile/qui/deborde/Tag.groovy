package pile.qui.deborde

class Tag {

	String word
	static int nbUsed = 0
	String description
	
    static constraints = {
		word(blank:false)
		description(blank:false)
    }
	
	static mapping = {
		description type: 'text'
	}
}
