package pile.qui.deborde

class PostController {

	def memberService
	def voteService
	def postService
	
	/**
	 * Called when a user add a positive vote for a post
	 * @return
	 */
    def voteUp () {
		def postVoted = Post.get(params.idpost)
		def memberWhoVotes = session.user
		boolean haveAlreadyVoted = voteService.checkIfAlreadyVoted(postVoted,memberWhoVotes)
		
		/* If the member haven't already voted */
		if (!haveAlreadyVoted) {
			/* Add one vote to the post */
			postService.incTotalVote(postVoted)
			postService.save(postVoted)
			
			/* Add a vote in database in order to memorize the relation voter - post */
			def Vote v = new Vote (votedPost:postVoted, voter: memberWhoVotes)
			if (v.validate()) {
				voteService.save(v)
			}
			/* Add a reputation point to the author of the original post */
			Member author = Member.get(postVoted.author.id)
			memberService.updateReputation(author, 1)
			memberService.checkReputation(author)
			memberService.save(author)
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
		boolean haveAlreadyVoted = voteService.checkIfAlreadyVoted(postVoted, memberWhoVotes)
		
		/* If the member haven't already voted */
		if (!haveAlreadyVoted) {
			/* Add one vote to the post */
			postService.decTotalVote(postVoted)
			postService.save(postVoted)
			
			/* Add a vote in database in order to memorize the relation voter - post */
			def Vote v = new Vote (votedPost:postVoted, voter: memberWhoVotes)
			if (v.validate()) {
				voteService.save(v)
			}
			/* Add a reputation point to the author of the original post */
			Member author = Member.get(postVoted.author.id)
			memberService.updateReputation(author, -1)
			memberService.checkReputation(author)
			memberService.save(author)
		}
			
		render postVoted.totalVote
	}
}
