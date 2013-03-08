package pile.qui.deborde

class IndexController {

	/**
	 * First method called at the beginning of the app
	 * @return
	 */
    def index() { 
		render(view:"index", model:[questions:Question.list()])
	}
}
