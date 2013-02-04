package pile.qui.deborde

class MemberController {

    def index () {
		render (view: "NewMemberView.gsp") 
	}
	
	def list() {
		 def liste = Member.list()
		 if (liste.size() == 0) {
		 	render('Aucun membre dans la base')
		 }
		 else {
			 for (Member m : liste) {
				 render 'First name  : '      + m.firstName        + '<br/>'
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
		
		def Member m = new Member(firstName: params.get("firstname"), lastName: params.get("lastname"),
								  email: params.get("email"), bio: params.get("bio"), website: params.get("website"),
								  photoPath: params.get("avatar"), dateNaissance: params.get("birthdate"),
								  dateInscription: new Date());
							  
		if (m.validate()) {
			m.save()
			redirect(action: "list")
		} else {
			render "Certains champs sont vides"
		}
	}
}
