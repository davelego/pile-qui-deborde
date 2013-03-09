package pile.qui.deborde

class QuestionService {

	/**
	 * Perform the save of the new question in database
	 * @param q
	 * @return
	 */
    def save (Question q) {
		q.save()
	}
}
