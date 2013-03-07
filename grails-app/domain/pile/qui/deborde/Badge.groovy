package pile.qui.deborde

class Badge {

	public static final String BN_REPUT_10    = "newborn"
	public static final String BN_REPUT_100   = "n00b"
	public static final String BN_REPUT_1000  = "disciple"
	public static final String BN_REPUT_5000  = "master"
	public static final String BN_REPUT_10000 = "warrior"
	
	String name
	
    static constraints = {
		name(blank: false)
    }
}
