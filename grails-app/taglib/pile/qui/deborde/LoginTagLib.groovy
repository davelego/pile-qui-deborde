package pile.qui.deborde

class LoginTagLib {
	def loginControl = {
		if(session.user){
		  def msg = message(code: "default.label.hello")
		  out << "${msg} ${session.user.pseudo} "
		  out << """[${link(action:"logout", controller:"member"){"Logout"}}]"""
    } else {
      out << """[${link(action:"index", controller:"member"){"Login"}}]"""      
		}
	  }
	
}
