package pile.qui.deborde

import org.springframework.dao.DataIntegrityViolationException

class AnswerController {

	/* Call from the list of the questions and redirect the user on a page */
	/* where he can answer to the selected question                        */
	def answer () {
		def questionToAnswer = Question.get(params.idquestion)
		render(view: "NewAnswerView", model:[question: questionToAnswer])
	}
	
	/* List all the answers about a given question */
	def list () {
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
	
	/* Save the answer in the database */
	def save () {
		
		def questionToAnswer = Question.get(params.idquestion)
		
		def Answer a = new Answer(body: params.get("body"),
							      date: new Date(),
								  question: questionToAnswer,
								  author: questionToAnswer.author)
		
		if (a.validate()) {
			a.save()
			redirect(action: "list", params: [idquestion: questionToAnswer.id])
		}
		else {
			render(view: "NewAnswerView", model:[question: questionToAnswer, answer: a])
		}
		
	}
	
//	static scaffold = true

//    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
//
//    def index() {
//        redirect(action: "list", params: params)
//    }
//
//    def list(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        [answerInstanceList: Answer.list(params), answerInstanceTotal: Answer.count()]
//    }
//
//    def create() {		
//        [answerInstance: new Answer(params)]
//    }
//
//    def save() {
//        def answerInstance = new Answer(params)
//		//answerInstance.question = Question.get(params.question.id)
//		log.fatal("Answer:" + answerInstance.question)
//        if (!answerInstance.save(flush: true)) {
//            render(view: "create", model: [answerInstance: answerInstance])
//            return
//        }
//
//        flash.message = message(code: 'default.created.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
//        redirect(action: "show", id: answerInstance.id)
//    }
//
//    def show(Long id) {
//        def answerInstance = Answer.get(id)
//        if (!answerInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [answerInstance: answerInstance]
//    }
//
//    def edit(Long id) {
//        def answerInstance = Answer.get(id)
//        if (!answerInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
//            redirect(action: "list")
//            return
//        }
//
//        [answerInstance: answerInstance]
//    }
//
//    def update(Long id, Long version) {
//        def answerInstance = Answer.get(id)
//        if (!answerInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
//            redirect(action: "list")
//            return
//        }
//
//        if (version != null) {
//            if (answerInstance.version > version) {
//                answerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
//                          [message(code: 'answer.label', default: 'Answer')] as Object[],
//                          "Another user has updated this Answer while you were editing")
//                render(view: "edit", model: [answerInstance: answerInstance])
//                return
//            }
//        }
//
//        answerInstance.properties = params
//
//        if (!answerInstance.save(flush: true)) {
//            render(view: "edit", model: [answerInstance: answerInstance])
//            return
//        }
//
//        flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.label', default: 'Answer'), answerInstance.id])
//        redirect(action: "show", id: answerInstance.id)
//    }
//
//    def delete(Long id) {
//        def answerInstance = Answer.get(id)
//        if (!answerInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), id])
//            redirect(action: "list")
//            return
//        }
//
//        try {
//            answerInstance.delete(flush: true)
//            flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
//            redirect(action: "list")
//        }
//        catch (DataIntegrityViolationException e) {
//            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), id])
//            redirect(action: "show", id: id)
//        }
//    }
}
