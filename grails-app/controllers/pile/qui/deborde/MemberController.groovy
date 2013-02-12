package pile.qui.deborde

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
		
		def listMember = Member.executeQuery("Select id from Member where pseudo = ${pseudo} and password = ${password};")
		
		if( !listMember.empty ) {
			render 'yay bro !'
		}
	}
	
	def list() {
		 def liste = Member.list()
		 if (liste.size() == 0) {
		 	render('Aucun membre dans la base')
		 }
		 else {
			 for (Member m : liste) {
				 render 'Pseudo :'             + m.pseudo		   + '<br/>'
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
		
		def Member m = new Member(firstName: params.get("firstname"), lastName: params.get("lastname"), pseudo: params.get("pseudo"), password: params.get("password"),
								  email: params.get("email"), bio: params.get("bio"), website: params.get("website"),
								  photoPath: params.get("avatar"), dateNaissance: params.get("birthdate"),
								  dateInscription: new Date());
							  
		/* Validation des informations du nouveau membre par rapport aux contraintres */  
		if (m.validate()) {
			
			/* Verification de l'egalite des deux mots de passe */
			if (params.get("password").equals(params.get("repassword"))) {
				m.save()
				redirect(action: "list")
			}
			else {
				render "Les deux mots de passe saisis sont différents"
			}
			
		} else {
			render "Certains champs sont vides"
		}
	}
}
