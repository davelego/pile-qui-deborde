package pile.qui.deborde
import java.util.logging.Logger;

import org.springframework.web.context.request.RequestContextHolder

class QuestionController {

	def questionService
	def memberService

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
	
	def detail() {
		def questionToDetail = Question.get(params.id)
		def listAnswers = Answer.findAllByQuestion(questionToDetail, [sort:'totalVote', order:'desc'])
		render(view: "DetailQuestionView", model:[q:questionToDetail, answers:listAnswers])
	}
	
	/* Save in database a new question posted by a member */
	def save () {
		def currentRequest = RequestContextHolder.requestAttributes
		
		if (currentRequest) {
		  def tags = params.get("tags");
		  tags = tags.toString();
		  def  tagsArray = tags.split(" ");
		  def listTags = []
		  for (String tag : tagsArray) {
			 if(Tag.findAllByWord(tag)) {
			  	listTags.add(Tag.findAllByWord(tag)[0])
			 }
			 else{
				print tag
				listTags = null;
			 	break;
			 }
		  }
		  def Question q = new Question(title: params.get("title"),
										body:  params.get("body"),
										date:  new Date(),
										author: session.user,
										tags: listTags)
  
		  if (q.validate()) {
			  q.save()
			  
			  /* Reputation reward for the author */
			  Member currentMember = Member.get(session.user.id)
			  currentMember.reputation += 10
			  memberService.checkReputation(currentMember)
			  for (Tag t : q.tags) {
				  memberService.checkTags(currentMember, t.word)
			  }
			  currentMember.save()
			  
			  redirect(action: "list")
		  }
		  else {
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
	
	/* Call from the edit page, when the user validate the changes. This  */
	/* method actually apply the modifications of the question in database */
	def editQuestion () {
		def questionEdited = Question.get(params.idquestion)
		questionEdited.body = params.get("body")
		
		def tags = params.get("tags");
		tags = tags.toString();
		def  tagsArray = tags.split(" ");
		def listTags = []
		for (String tag : tagsArray) {
		   if (Tag.findAllByWord(tag)) {
				listTags.add(Tag.findAllByWord(tag)[0])
		   } else {
			  listTags = null;
			  break;
		   }
		}
		
		questionEdited.tags = listTags
		if (questionEdited.validate()) {
			questionEdited.save()
			redirect(action:"detail", params: [id: questionEdited.id])
		} else {
			questionEdited.errors.rejectValue('tags',"0ne of your tags doesn\'t exists")
			render(view: "EditQuestionView", model:[question: questionEdited])
		}
	}
}