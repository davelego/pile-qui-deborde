package pile.qui.deborde

class TagController {

	/* Main method of the controller */
	def index () {
		render (view: "NewTagsView")
	}
	
	
	/* List all the questions in database */
	def list() {
		def listTags = Tag.list()
		render(view: "ListTagView", model:[tags: listTags])
	}
	

	/* Save in database a new question posted by a member */
	def save () {

		def Tag t = new Tag(word: params.get("word"),
				description:  params.get("description"))

		if (t.validate()) {
			t.save()
			redirect(action: "list")
		}
		else {
			//t.errors.rejectValue('author', 'You must be logged in order to post a question')
			render(view: "NewTagsView", model:[tag: t])
		}
	}

	
}
