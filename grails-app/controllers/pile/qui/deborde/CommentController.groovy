package pile.qui.deborde

class CommentController {

    /**
     * Method to call when the user want to add a comment on a question
     * @return
     */
   	def commentQuestion () {
	   def questionToComment = Question.get(params.idquestion)
       render(view: "NewCommentView", model:[question: questionToComment])
   	}
      
    /**
     * Method to call when the user want to add a comment on an answer
     * @return
     */
    def commentAnswer () {
        
        
    }
    
    /**
     * Metho to call in order to save a new comment
     * @return
     */
    def save () {
        
        def questionToComment = Question.get(params.idquestion)
        
        def Comment c = new Comment(body: params.get("body"),
                                    date: new Date(),
								    author: session.user)
        
        if (c.validate()) {
            c.save()
            redirect(controller:"question", action: "detail", params: [id: questionToComment.id])
        }
        else {
            render(view: "NewCommentView", model:[question: questionToComment, comment: c])
        }
        
    }
}
