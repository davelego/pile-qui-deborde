package pile.qui.deborde

class MemberService {

	/**
	 * Check if the current reputation of the member can bring him
	 * a new badge
	 * @param m The member to check the reputation
	 * @return
	 */
    def checkReputation (Member m) {
		
		def rep = m.reputation
		
		if (rep >= 10 && !haveBadge(m, Badge.BN_REPUT_10)) {
			def b = new Badge (name: Badge.BN_REPUT_10)
			m.badges.add(b)
		}
		
		if (rep >= 100 && !haveBadge(m, Badge.BN_REPUT_100)) {
			def b = new Badge (name: Badge.BN_REPUT_100)
			m.badges.add(b)
		}
		
		if (rep >= 1000 && !haveBadge(m, Badge.BN_REPUT_1000)) {
			def b = new Badge (name: Badge.BN_REPUT_1000)
			m.badges.add(b)
		}
		
		if (rep >= 5000 && !haveBadge(m, Badge.BN_REPUT_5000)) {
			def b = new Badge (name: Badge.BN_REPUT_5000)
			m.badges.add(b)
		}
		
		if (rep >= 10000 && !haveBadge(m, Badge.BN_REPUT_10000)) {
			def b = new Badge (name: Badge.BN_REPUT_10000)
			m.badges.add(b)
		}
    }
	
	/**
	 * Check if a user had post enough (5) questions related to a given tag.
	 * If so, the user can win a new badge
	 * @param m The member to check
	 * @param tagWord The name of the given tag
	 * @return
	 */
	def checkTags (Member m, String tagWord) {
		
		def nbSameTags = 0
		/* for each question of the member */
		for (Question q : m.questions) {
			/* for each tag of the question */
			for (Tag t : q.tags) {
				if (t.word == tagWord) {
					nbSameTags ++
				}
			}
			
		}
		
		if (nbSameTags >= 5 && !haveBadge(m, tagWord)) {
			def b = new Badge(name: "ExpertOf" + tagWord)
			m.badges.add(b)
		}
	}
	
	/**
	 * Check if the member already have or not a tag
	 * @param m Member to check
	 * @param badgeName Name of the badge to check
	 * @return True or False
	 */
	def haveBadge (Member m, String badgeName) {
		
		boolean res = false
		
		for (Badge b : m.badges) {
			if (b.name == badgeName) {
				res = true
			}
		}
		
		return res
	}
}
