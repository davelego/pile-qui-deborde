package pile.qui.deborde

class CommentController {

    /**
     * Method to call when the user want to add a comment on a post
     * @return
     */
   	def comment () {
	   def postToComment = Post.get(params.idpost)
       
       if (postToComment instanceof Question) {
           render(view: "NewCommentView", model:[post: postToComment, type:"question"])
       }
       
       if (postToComment instanceof Answer) {
           render(view: "NewCommentView", model:[post: postToComment, type:"answer"])
       }
   	}
    
    /**
     * Method to call in order to save a new comment
     * @return
     */
    def save () {
        
        def postToComment = Post.get(params.idpost)
        
        def Comment c = new Comment(body: params.get("body"),
                                    date: new Date(),
                                    relatedPost: postToComment,
								    author: session.user)
        
        if (c.validate()) {
            c.save()
            if (postToComment instanceof Question) {
                redirect(controller:"question", action: "detail", params: [id: postToComment.id])
            } else {
            redirect(controller:"question", action: "detail", params: [id: postToComment.question.id])
            }
        }
        else {
            render(view: "NewCommentView", model:[question: postToComment, comment: c])
        }
        
    }
}
