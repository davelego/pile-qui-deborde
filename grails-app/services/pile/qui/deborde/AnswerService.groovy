package pile.qui.deborde

class AnswerService {

	/**
	 * Actually save the new question in DB
	 * @param a the new question to save
	 * @return
	 */
    def save (Answer a) {
		a.save()
    }
}
