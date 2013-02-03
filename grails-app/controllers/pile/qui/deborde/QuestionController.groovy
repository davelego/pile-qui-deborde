package pile.qui.deborde

class QuestionController {

    def index () {
		render (view: "NewQuestionView.gsp") 
	}
	
	def saveNewQuestion () {
		render params
	}
}
