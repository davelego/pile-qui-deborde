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
			}
			render ' logged user : ' + currentRequest.session["loggedUser"]
		}
	}
	
	def list() {
		 def liste = Member.list()
		 render("<a href='/pile-qui-deborde'>Retour a l\'accueil</a><br/><br/>")
		 if (liste.size() == 0) {
		 	render('Aucun membre dans la base')
		 }
		 else {
			 for (Member m : liste) {
				 render 'Pseudo : '            + m.pseudo		   + '<br/>'
				 render 'Password : '		   + m.password        + '<br/>'
				 render 'First name  : '       + m.firstName       + '<br/>'
				 render 'Last name  : '        + m.lastName        + '<br/>'
				 render 'Email  : '            + m.email           + '<br/>'
				 render 'Bio  : '              + m.bio             + '<br/>'
				 render 'Website  : '          + m.website         + '<br/>'
				 render 'Avatar : '            + m.photoPath       + '<br/>'
				 render 'Birth date  : '       + m.dateNaissance   + '<br/>'
				 render 'Inscription date  : ' + m.dateInscription + '<br/><br/>'
			 }
		 }
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
