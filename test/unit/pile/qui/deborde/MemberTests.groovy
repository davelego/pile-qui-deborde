package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

import java.util.Date;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
class MemberTests {

    void testConstraints() {
		 def member = new Member(
					firstName:"David",
					lastName:"CHARETTE",
					email:"dav.c@hotmail.fr",
					pseudo:"Stonkeep",
					password:"Machin",
					bio:"Je suis Stonkeep",
					website:"http://www.stonkeep.com",
					photoPath:"http://www.stonkeep.com/img/avatar.png",
					dateNaissance:Date.parse("yyyy-MM-dd", "1990-09-21"),
					dateInscription:new Date())
		 
		 mockForConstraintsTests(Member, [member])
		 
		 def memberTest = new Member()
		 assertFalse memberTest.validate()
		 assertEquals 9, memberTest.errors.errorCount
		 assertEquals "nullable", memberTest.errors["firstName"]
		 assertEquals "nullable", memberTest.errors["lastName"]
		 assertEquals "nullable", memberTest.errors["email"]
		 assertEquals "nullable", memberTest.errors["pseudo"]
		 assertEquals "nullable", memberTest.errors["password"]
		 assertEquals "nullable", memberTest.errors["dateNaissance"]
    }
}
