package pile.qui.deborde

class Badge {

	String nom
	String condition
	
    static constraints = {
		nom(blank: false)
		condition(blank: false)
    }
}
