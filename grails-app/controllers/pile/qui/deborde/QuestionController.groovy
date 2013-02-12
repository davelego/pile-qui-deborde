package pile.qui.deborde
import org.springframework.web.context.request.RequestContextHolder

class QuestionController {


    def index () {
		render (view: "NewQuestionView") 
	}
	
	def beforeInterceptor = [action:this.&auth, except:"list"]
	
	def auth() {
		if (!session.user) {
			redirect(controller:"member", action:"index")
			return false
		}
	}
		
	def list() {

		def listQuestions = Question.list()
		render(view: "ListQuestionsView", model:[questions: listQuestions])
	}
	
	def saveNewQuestion () {
		def currentRequest = RequestContextHolder.requestAttributes
		
		if(currentRequest) {
			
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
	
	def edit () {
		def questionToEdit = Question.get(params.idquestion)
		render(view: "EditQuestionView", model:[question: questionToEdit])
	}
	
	def editQuestion () {
		def questionEdited = Question.get(params.idquestion)
		questionEdited.body = params.get("body")
		questionEdited.save()
		redirect(action: "list")
	}
}