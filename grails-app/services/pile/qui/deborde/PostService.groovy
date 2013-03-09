package pile.qui.deborde

class PostService {

    def incTotalVote (Post p) {
		p.totalVote ++
	}
	
	def decTotalVote (Post p) {
		p.totalVote --
	}
	
	def save (Post p) {
		p.save()
	}
}
