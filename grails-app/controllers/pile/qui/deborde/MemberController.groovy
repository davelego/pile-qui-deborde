package pile.qui.deborde

class MemberController {

    def index () {
		render (view: "NewMemberView.gsp") 
	}
	
	def saveNewMember () {
		render params
	}
}
