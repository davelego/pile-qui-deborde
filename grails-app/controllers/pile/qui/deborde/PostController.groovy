package pile.qui.deborde

class PostController {

	/**
	 * Called when a user add a positive vote for a post
	 * @return
	 */
    def voteUp () {
		def postVoted = Post.get(params.idpost)
		def memberWhoVotes = session.user
		
		/* For each vote */
		boolean haveAlreadyVoted = false
		for (Vote v : postVoted.votes) {
			if (v.voter.id == memberWhoVotes.id) {
				haveAlreadyVoted = true
			}
		}
		
		/* If the member haven't already voted */
		if (!haveAlreadyVoted) {
			postVoted.totalVote ++
			postVoted.save()
			def Vote v = new Vote (votedPost:postVoted, voter: memberWhoVotes)
			if (v.validate()) {
				v.save()
			}
		}
			
		render postVoted.totalVote
	}
	
	/**
	 * Called when a user add a negative vote for a post
	 * @return
	 */
	def voteDown () {
		def postVoted = Post.get(params.idpost)
		def memberWhoVotes = session.user
		
		/* For each vote */
		boolean haveAlreadyVoted = false
		for (Vote v : postVoted.votes) {
			if (v.voter.id == memberWhoVotes.id) {
				haveAlreadyVoted = true
			}
		}
		
		/* If the member haven't already voted */
		if (!haveAlreadyVoted) {
			postVoted.totalVote --
			postVoted.save()
			def Vote v = new Vote (votedPost:postVoted, voter: memberWhoVotes)
			if (v.validate()) {
				v.save()
			}
		}
			
		render postVoted.totalVote
	}
}
