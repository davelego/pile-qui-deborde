package pile.qui.deborde
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

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
			redirect(uri:"/")
		}
		else{
			render 'fail'
		}
	}
	
	def logout = {
		session.user = null
		redirect(uri:"/")
	}
	
	def list() {
		 def listMembers = Member.list()
		 render(view: "ListMembersView", model:[members: listMembers])
	}
	 
	def saveNewMember () {
		
		
		
		
		
		/*String fileNameToCreate = "./grails-app/views/uploads/" + request.getFile("payload").getOriginalFilename();
		System.out.println(fileNameToCreate)
		try {
		
		File file = new File(fileNameToCreate);
		request.getFile("payload").transferTo(file)
		
		} catch (Throwable e) {
		e.printStackTrace();
		}*/
		
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
	
	/* Displays the information of the logged user */
	def myAccount () {
		if (!session.user) {
			redirect(controller:"member", action:"index")
		} else {
			def currentMember = Member.get(session.user.id)
			render(view: "MyAccountMemberView", model:[member: currentMember,edit:false])
		}
	}
	
	/* Edits the information of the logged user */
	def edit () {
		def currentMember = Member.get(session.user.id)
		render(view: "MyAccountMemberView", model:[member: currentMember,edit:true])
	}
	
	def updateProfile () {
		def memberEdited 				= Member.get(params.memberToEdit)
		memberEdited.firstName 			= params.get("firstname")
		memberEdited.lastName 			= params.get("lastname") 
		memberEdited.pseudo 			= params.get("pseudo")
		memberEdited.password 			= params.get("password")
		memberEdited.email				= params.get("email")
		memberEdited.bio 				= params.get("bio")
		memberEdited.website 			= params.get("website")
		memberEdited.photoPath 			= params.get("avatar") 
		memberEdited.dateNaissance 		= params.get("birthdate")
		memberEdited.dateInscription 	= new Date()
		memberEdited.save()
		redirect(action: "myAccount")
	}
	
	
}
