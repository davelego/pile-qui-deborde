package pile.qui.deborde

class Tag {

	String mot
	static int nbUsed = 0
	String description
	
    static constraints = {
		mot(blank:false)
		description(blank:false)
    }
}
