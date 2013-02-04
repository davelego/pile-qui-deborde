package pile.qui.deborde

class QuestionController {

    def index () {
		render (view: "NewQuestionView.gsp") 
	}
	
	def list() {
		def liste = Question.list()
		if (liste.size() == 0 ) {
			render "Aucune question"
		} else {
			for (Question q : liste) {
				render "Title : " + q.title + '<br/>'
				render "Body : "  + q.body  + '<br/>'
				render "Date : "  + q.date  + '<br/><br/>'
			}
		}
	}
	
	def saveNewQuestion () {
		
		def Question q = new Question(title: params.get("title"),
									  body:  params.get("body"),
									  date:  new Date());
						
		if (q.validate()) {
			q.save()
			redirect(action: "list")
		} else {
			render "Certains champs sont vides"
		}
	}
}
