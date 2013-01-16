package pile.qui.deborde

class Answer {
	
	String body 
	int notationUp = 0
	int notationDown = 0
	boolean topansewer = false;
	
	//author of the Answer
	static belongsTo = [author:Author]
	
    static constraints = {
    }
}
