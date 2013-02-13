package pile.qui.deborde
import org.springframework.web.context.request.RequestContextHolder

class QuestionController {

	def questionService

	/* Main method of the controller */
    def index () {
		render (view: "NewQuestionView") 
	}
	
	def beforeInterceptor = [action:this.&auth, except:"list"]
	
	/* Allow the authentification */
	def auth() {
		if (!session.user) {
			redirect(controller:"member", action:"index")
			return false
		}
	}
	
	/* List all the questions in database */
	def list() {
		def listQuestions = Question.list()
		render(view: "ListQuestionsView", model:[questions: listQuestions])
	}
	
	/* Save in database a new question posted by a member */
	def save () {
		def currentRequest = RequestContextHolder.requestAttributes
		
		if (currentRequest) {
			
		  def Question q = new Question(title: params.get("title"),
										body:  params.get("body"),
										date:  new Date(),
										author: session.user)
  
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
	
	/* Call from the list of the question, and redirect the user to a page */
	/* where he can edit his own question                                  */
	def edit () {
		def questionToEdit = Question.get(params.idquestion)
		render(view: "EditQuestionView", model:[question: questionToEdit])
	}
	
	/* Call from the edit page, when the user validate the changes. This  */
	/* method actualy apply the modifications of the question in database */
	def editQuestion () {
		def questionEdited = Question.get(params.idquestion)
		questionEdited.body = params.get("body")
		questionEdited.save()
		redirect(action: "list")
	}
}