package pile.qui.deborde

class VoteService {

	/**
	 * Check if a post have already been voted by a member
	 * @param p post 
	 * @param m member
	 * @return true of fale
	 */
    def checkIfAlreadyVoted (Post p, Member m) {
		
		boolean haveAlreadyVoted = false
		
		for (Vote v : p.votes) {
			if (v.voter.id == m.id) {
				haveAlreadyVoted = true
			}
		}
		
		return haveAlreadyVoted
    }
	
	/**
	 * Actually save the new vote in DB
	 * @param v Vote
	 * @return
	 */
	def save (Vote v) {
		v.save()
	}
}
