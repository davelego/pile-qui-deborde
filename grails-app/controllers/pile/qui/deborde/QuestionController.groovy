package pile.qui.deborde
import org.springframework.web.context.request.RequestContextHolder

class QuestionController {

    def index () {
		render (view: "NewQuestionView.gsp") 
	}
	
	def list() {
		def liste = Question.list()
		render("<a href='/pile-qui-deborde'>Retour a l\'accueil</a><br/><br/>")
		if (liste.size() == 0 ) {
			render "Aucune question"
		} else {
			for (Question q : liste) {
				render "Author : " + q.author.pseudo + '<br/>'
				render "Title : "  + q.title         + '<br/>'
				render "Body : "   + q.body          + '<br/>'
				render "Date : "   + q.date          + '<br/><br/>'
			}
		}
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
}