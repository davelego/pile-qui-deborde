package pile.qui.deborde
import org.springframework.web.context.request.RequestContextHolder

class MemberController {

    def index () {
		render (view: "login.gsp") 
	}
	
	def register () {
		render (view: "NewMemberView.gsp")
	}
	
	def login () {
		
		def pseudo = params.get("pseudo")
		def password = params.get("password")
		
		def user = Member.findByPseudoAndPassword(pseudo,password)
		if( user ) {
			session.user = user
			render ' logged user : ' + session.user
		}
	}
	
	def list() {
		 def listMembers = Member.list()
		 render(view: "ListMembersView", model:[members: listMembers])
	}
	 
	def saveNewMember () {
		
		def Member m = new Member(firstName: params.get("firstname"), 
								  lastName: params.get("lastname"), 
								  pseudo: params.get("pseudo"), 
								  password: params.get("password"),
								  email: params.get("email"), 
								  bio: params.get("bio"), 
								  website: params.get("website"),
								  photoPath: params.get("avatar"), 
								  dateNaissance: params.get("birthdate"),
								  dateInscription: new Date());
		
		// def Member m = new Member(params) Ne fonctionne pas ?
							  
		/* Validation des informations du nouveau membre par rapport aux contraintres */  
		if (m.validate()) {
			
			/* Verification de l'egalite des deux mots de passe */
			if (params.get("password").equals(params.get("repassword"))) {
				m.save()
				redirect(action: "list")
			}
			else {
				m.errors.rejectValue("password", 'Both password fields must be the same')
				render(view: "NewMemberView", model:[member: m])
			}
			
		} else {
			render(view: "NewMemberView", model:[member: m])
		}
	}
}
