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
		
		def listMember = Member.executeQuery("Select pseudo from Member where pseudo = '${pseudo}' and password = '${password}'")
		
		if( !listMember.empty ) {
			def currentRequest = RequestContextHolder.requestAttributes
			if(currentRequest) { // we have been called from a web request processing thread
			  // currentRequest is an instance of GrailsWebRequest
			  currentRequest.session["loggedUser"] = listMember[0];
			  log.error(currentRequest.session["loggedUser"]);
			}
			render ' logged user : ' + currentRequest.session["loggedUser"]
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
