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
		
		def Member m = new Member(firstName: params.get("firstname"),
			lastName: params.get("lastname"),
			pseudo: params.get("pseudo"),
			password: params.get("password"),
			email: params.get("email"),
			bio: params.get("bio"),
			website: params.get("website"),
			avatar:null,
			dateNaissance: params.get("birthdate"),
			dateInscription: new Date());
		
		// Get the avatar file from the multi-part request 
		def f = request.getFile('avatar')
		
		// List of OK mime-types 
		def okcontents = ['image/png', 'image/jpeg', 'image/gif'] 
		
		if (! okcontents.contains(f.getContentType())) { 
			flash.message = "Avatar must be one of: ${okcontents}" 
			render(view:'select_avatar', model:[user:user]) 
			return; 
		}
		
		// Save the image and mime type 
		m.avatar = f.getBytes() 
		m.avatarType = f.getContentType() 
		log.info("File uploaded: " + m.avatarType)
		
			
							  
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
		memberEdited.dateNaissance 		= params.get("birthdate")
		memberEdited.dateInscription 	= new Date()
		
		// Get the avatar file from the multi-part request
		def f = request.getFile('avatar')
		
		// List of OK mime-types
		def okcontents = ['image/png', 'image/jpeg', 'image/gif']
		
		if (! okcontents.contains(f.getContentType())) {
			flash.message = "Avatar must be one of: ${okcontents}"
			render(view: "MyAccountMemberView", model:[member: Member.get(params.memberToEdit)])
			return;
		}
		
		// Save the image and mime type
		memberEdited.avatar = f.getBytes()
		memberEdited.avatarType = f.getContentType()
		log.info("File uploaded: " + memberEdited.avatarType)
		
		/* Validation des informations du nouveau membre par rapport aux contraintres */
		if (memberEdited.validate()) {
			
			/* Verification de l'egalite des deux mots de passe */
			if (params.get("password").equals(params.get("repassword"))) {
				memberEdited.save()
				redirect(action: "myAccount")
			}
			else {
				memberEdited.errors.rejectValue("password", 'Both password fields must be the same')
				render(view: "MyAccountMemberView", model:[member: Member.get(params.memberToEdit),edit:true])
			}
			
		} else {
			render(view: "MyAccountMemberView", model:[member: Member.get(params.memberToEdit)])
		}
	}
	
	
	def avatar_image = {
		def avatarUser = Member.get(params.id)
		if (!avatarUser || !avatarUser.avatar || !avatarUser.avatarType) {
		  response.sendError(404)
		  return;
		}
		response.setContentType(avatarUser.avatarType)
		response.setContentLength(avatarUser.avatar.size())
		OutputStream out = response.getOutputStream();
		out.write(avatarUser.avatar);
		out.close();
	  }
}
