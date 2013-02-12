package pile.qui.deborde

class LoginTagLib {
	def loginControl = {
		if(session.user){
		  out << "Hello ${session.user.pseudo} "
		  out << """[${link(action:"logout", controller:"member"){"Logout"}}]"""
    } else {
      out << """[${link(action:"index", controller:"member"){"Login"}}]"""      
		}
	  }
	
}
