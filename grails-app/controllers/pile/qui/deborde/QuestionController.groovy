package pile.qui.deborde
import java.util.logging.Logger;

import org.springframework.web.context.request.RequestContextHolder

class QuestionController {

	def questionService
	def memberService
	def tagService

	/* Main method of the controller */
    def index () {
		render (view: "NewQuestionView") 
	}
	
	def beforeInterceptor = [action:this.&auth, except:'list']
	
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
	
	/**
	 * Give the detail of a question
	 * @return
	 */
	def detail() {
		def questionToDetail = Question.get(params.id)
		def listAnswers = Answer.findAllByQuestion(questionToDetail, [sort:'totalVote', order:'desc'])
		render(view: "DetailQuestionView", model:[q:questionToDetail, answers:listAnswers])
	}
	
	/* Save in database a new question posted by a member */
	def save () {
		def currentRequest = RequestContextHolder.requestAttributes
		
		if (currentRequest) {
		  def tags = params.get("tags").toString();
		  def listTags = tagService.checkTags(tags)
		  print listTags
		  def Question q = new Question(title: params.get("title"),
										body:  params.get("body"),
										date:  new Date(),
										author: session.user,
										tags: listTags)
  
		  if (q.validate()) {
			  questionService.save(q)
			  
			  /* Reputation reward for the author */
			  Member currentMember = session.user
			  memberService.updateReputation(currentMember, 10)
			  memberService.checkReputation(currentMember)
			  for (Tag t : q.tags) {
				  memberService.checkTags(currentMember, t.word)
			  }
			  memberService.save(currentMember)
			  redirect(action: "list")
		  }
		  else {
			  q.errors.each {
				  print it
			  }
			q.errors.rejectValue('tags','one of your tags is wrong')
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
	
	def delete () {
		def question = Question.get(params.idquestion)
		question.delete()
		redirect(action: "list")
	}
	
	/* Call from the edit page, when the user validate the changes. This  */
	/* method actually apply the modifications of the question in database */
	def editQuestion () {
		def questionEdited = Question.get(params.idquestion)
		questionEdited.body = params.get("body")
		
		def tags = params.get("tags").toString();
		def listTags = tagService.checkTags(tags)
		questionEdited.tags = listTags
		
		if (questionEdited.validate()) {
			questionService.save(questionEdited)
			redirect(action:"detail", params: [id: questionEdited.id])
		} else {
			print questionEdited.errors.each {
				print it
			}
			questionEdited.errors.rejectValue('tags',"0ne of your tags doesn\'t exists")
			render(view: "EditQuestionView", model:[question: questionEdited])
		}
	}
	
	def questionByTags () {
		def c = Question.createCriteria()
		def word = Tag.get(params.id).word;

		def results = c.list {
			tags {
				inList('word', word)
			}
		}
		render(view:"QuestionByTagView", model:[questions: results, tagWord:word])
	}
}