package pile.qui.deborde
import org.springframework.web.context.request.RequestContextHolder

class QuestionController {

    def index () {
		render (view: "NewQuestionView.gsp") 
	}
	
	def list() {
		def listQuestions = Question.list()
		render(view: "ListQuestionsView", model:[questions: listQuestions])
	}
	
	def saveNewQuestion () {
		def currentRequest = RequestContextHolder.requestAttributes
		def pseudo
		if(currentRequest) { 
		  pseudo = currentRequest.session["loggedUser"]
		  def memberList = Member.list()
		  def member = Member.findAllByPseudo(pseudo)
		  def Question q = new Question(title: params.get("title"),
										body:  params.get("body"),
										date:  new Date(),
										author: member.get(0))
  
		  if (q.validate()) {
			  q.save()
			  redirect(action: "list")
		  }
		  else {
			q.errors.rejectValue('author', 'You must be logged in order to post a question')
			render(view: "NewQuestionView", model:[question: q])
		  }
		}
	}
}