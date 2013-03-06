package pile.qui.deborde

class Vote {

	static belongsTo = [votedPost:Post, voter:Member]
}
