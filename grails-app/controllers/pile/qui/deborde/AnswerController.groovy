package pile.qui.deborde

import org.springframework.dao.DataIntegrityViolationException

class AnswerController {
	
	def memberService
	def answerService

	/* Call from the list of the questions and redirect the user on a page */
	/* where he can answer to the selected question                        */
	def answer () {
		def questionToAnswer = Question.get(params.idquestion)
		render(view: "NewAnswerView", model:[question: questionToAnswer])
	}
	
	/* List all the answers about a given question */
	def list () {
        print "params.idquestion = " + params.idquestion
		def q = Question.get(params.idquestion)
		def listAnswers = q.answers
		
		render("<a href=\"${createLink(uri: '/', absolute: true)}\"><- Return to main page</a><br/><br/>")
		
		if (listAnswers.size() == 0) {
			render("Pas de reponse pour cette question !")
			
		} else {
		
			for (Answer a : listAnswers) {
				render("Body : " + a.body + "<br/>")
				render("Date : " + a.date + "<br/><br/>")
			}
		}
	}
	
	/**
	 * Called when we have to save a new answer
	 * @return
	 */
	def save () {
		
		def questionToAnswer = Question.get(params.idquestion)
		
		def Answer a = new Answer(body: params.get("body"),
							      date: new Date(),
								  question: questionToAnswer,
								  author: session.user)
		
		if (a.validate()) {
			answerService.save(a)
			Member currentMember = Member.get(session.user.id)
			memberService.updateReputation(currentMember, 5)
			memberService.checkReputation(currentMember)
			memberService.save(currentMember)
			redirect(controller:"question", action: "detail", params: [id: questionToAnswer.id])
		} else {
			render(view: "NewAnswerView", model:[question: questionToAnswer, answer: a])
		}
	}
    
    /**
     * Allow the author of the question, or the admin, to edit an answer already posted
     * @return
     */
    def edit() {
        def answerToEdit = Answer.get(params.idanswer)
        render(view: "EditAnswerView", model:[answer: answerToEdit])
    }
	
	/**
	 * deletes the answer (only for admin and owner) - manages cascade
	 * 
	 * @return
	 */
	def delete () {
		def answer = Answer.get(params.idanswer)
		def questionid = answer.question.id
		answer.delete()
		redirect(controller:"question", action:"detail", params: [id: questionid])
	}
	
    /**
     * Call the service to persist the modification
     * @return
     */
    def editAnswer () {
        
        def answerEdited = Answer.get(params.idanswer)
        answerEdited.body = params.get("body")
		answerService.save(answerEdited)
		redirect(controller:"question", action:"detail", params: [id: answerEdited.question.id])
    }
	
	/**
	 * Called when the author of the related question mark this answer
	 * @return
	 */
	def check () {
		def answerToCheck = Answer.get(params.idanswer)
		answerToCheck.haveHelped = true
		answerService.save(answerToCheck)
		// Reputation reward for the author of the answer
		Member answerAuthor = Member.get(answerToCheck.author.id)
		memberService.updateReputation(answerAuthor, 3)
		memberService.checkReputation(answerAuthor)
		memberService.save(answerAuthor)
		redirect(controller:"question", action:"detail", params: [id: answerToCheck.question.id])
	}
	
	/**
	 * Called when the author of the related question unmark this answer
	 * @return
	 */
	def uncheck () {
		def answerToUncheck = Answer.get(params.idanswer)
		answerToUncheck.haveHelped = false
		answerService.save(answerToUncheck)
		// Reputation reward for the author of the answer
		Member answerAuthor = Member.get(answerToUncheck.author.id)
		memberService.updateReputation(answerAuthor, -3)
		memberService.checkReputation(answerAuthor)
		memberService.save(answerAuthor)
		redirect(controller:"question", action:"detail", params: [id: answerToUncheck.question.id])
	}
}
