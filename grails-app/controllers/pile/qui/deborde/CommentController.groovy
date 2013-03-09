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
    
    /**
     * Called when the author or the admin want to edit an existing comment
     * @return
     */
    def edit () {
        
        def commentToEdit = Comment.get(params.idcomment)
        render(view: "EditCommentView", model:[comment: commentToEdit])
    }
	
	/**
	 * deletes the comment
	 * 
	 * @return
	 */
	def delete () {
		def com = Comment.get(params.idcomment)
		def postid = com.relatedPost.id
		com.delete()
		redirect(controller:"question", action: "detail", params: [id: postid])
	}
    
    /**
     * Persist the edition of the comment in the database
     * @return
     */
    def editComment () {
        def commentToEdit = Comment.get(params.idcomment)
        commentToEdit.body = params.get("body")
        commentToEdit.save()
        redirect(controller:"question", action: "detail", params: [id: commentToEdit.relatedPost.id])
    }
}
