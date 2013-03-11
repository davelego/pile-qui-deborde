package pile.qui.deborde

import javax.sound.midi.ControllerEventListener;

import org.junit.*
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MemberController)
@Mock(Member)
class MemberControllerTests {

	Member m = new Member(
		firstName:"David",
		lastName:"CHARETTE",
		email:"dav.c@hotmail.fr",
		pseudo:"Stonkeep",
		password:"Machin",
		bio:"Je suis Stonkeep",
		website:"http://www.stonkeep.com",
		avatar:null,
		dateNaissance:Date.parse("yyyy-MM-dd", "1990-09-21"),
		dateInscription:new Date())
	
	
    void testSomething() {
      	assertTrue(true);
    }
	
	void testIndex () {
		controller.index()
		
		assert view == '/member/login.gsp'
	}
	
	void testRegister () {
		controller.register()	
		assert view == '/member/NewMemberView.gsp'
	}
	
	void testLogin () {
		m.save();
		
		controller.params.pseudo = m.pseudo
		controller.params.password = m.password
		
		controller.login()
		
		assert response.redirectedUrl == '/'
		assert session.user == m
	}
	
	void testLogout () {
	
		m.save() 	
		
		
		controller.params.pseudo = m.pseudo
		controller.params.password = m.password
		
		//controller.login()
		
		session.user == m
		
		controller.logout()
		
		assert session.user == null
		assert response.redirectedUrl == '/'
	}
	
	void testList () {
		m.save () 
		
		controller.list()
		
		assert view == '/member/ListMembersView'
		assert model.members == Member.list()
	}
	
	void testSaveNewMember () {
		controller.params.firstname = "Raphaël"
		controller.params.lastname = "Goncalves"
		controller.params.pseudo = "R4pHG"
		controller.params.password = "Machin"
		controller.params.repassword = "Machin"
		controller.params.email = "raphael.goncalves@live.Fr"
		controller.params.bio = "la vie est un long chemin rempli de lendemain"
		controller.params.website = "http://oportugues90.skyblog.com"
		controller.params.birthdate = new Date()
		
		controller.saveNewMember()
		
		assert response.redirectedUrl == '/member/list'
	}
	
	void testSeaveNewMemberFail () {
		controller.params.lastname = "Goncalves"
		controller.params.pseudo = "R4pHG"
		controller.params.password = "Machin"
		controller.params.repassword = "Machineeeee"
		controller.params.email = "raphael.goncalves@live.Fr"
		controller.params.bio = "la vie est un long chemin rempli de lendemain"
		controller.params.website = "http://oportugues90.skyblog.com"
		controller.params.birthdate = new Date()
		
		controller.saveNewMember()
		
		assert view == '/member/NewMemberView'
	}
	
	
	void myAccountTestFail () {
		session.user = null	
		
		controller.myAccount()
		
		assert response.redirectedUrl == '/member/index'
	}
	
	void myAccountTestOK () {
		m.save()
		session.user = m
		controller.myAccount()
		assert view == '/member/MyAccountMemberView'
		assert model.currentMember == m
	}
	
	void testEdit () {
		session.user = m
		controller.edit()
		assert view == '/member/MyAccountMemberView'
		assert model.edit == true
	}
	
	void updateTest () {
		m.save()
		session.user = m
		controller.params.lastname = "Goncalves"
		controller.params.pseudo = "R4pHG"
		controller.params.password = "Machin"
		controller.params.repassword = "Machineeeee"
		controller.params.email = "raphael.goncalves@live.Fr"
		controller.params.bio = "la vie est un long fleuve tranquille"
		controller.params.website = "http://oportugues90.skyblog.com"
		controller.params.birthdate = new Date()
		
		controller.updateProfile()
		
		assert m.bio == "la vie est un long fleuve tranquille"
		assert response.redirectedUrl == '/member/myAccount'
	}
}
