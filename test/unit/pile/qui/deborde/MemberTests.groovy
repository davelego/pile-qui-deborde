package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

import java.util.Date;

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
            dateNaissance:Date.parse("yyyy-MM-dd", "1990-09-21"),
            dateInscription:new Date())
 
         mockForConstraintsTests(Member, [member])
		 
		 def memberTest = new Member()
		 assertFalse memberTest.validate()
		 assertEquals "nullable", memberTest.errors["firstName"]
		 assertEquals "nullable", memberTest.errors["lastName"]
		 assertEquals "nullable", memberTest.errors["email"]
		 assertEquals "nullable", memberTest.errors["pseudo"]
		 assertEquals "nullable", memberTest.errors["password"]
		 assertEquals "nullable", memberTest.errors["dateNaissance"]
         
         memberTest = new Member (firstName:"",
                                  lastName:"",
                                  email:"",
                                  pseudo:"",
                                  password:"")
         
         assertFalse memberTest.validate()
         assertEquals "blank", memberTest.errors["firstName"]
         assertEquals "blank", memberTest.errors["lastName"]
         assertEquals "blank", memberTest.errors["email"]
         assertEquals "blank", memberTest.errors["pseudo"]
         assertEquals "blank", memberTest.errors["password"]
         
         memberTest = new Member (pseudo:"ab", password:"cd")
         assertFalse memberTest.validate()
         assertEquals "size", memberTest.errors["pseudo"]
         assertEquals "size", memberTest.errors["password"]
         
         memberTest = new Member (pseudo:"abcdefghijklmnopqrstuvwxyz", password:"abcdefghijklmnopqrstuvwxyz")
         assertFalse memberTest.validate()
         assertEquals "size", memberTest.errors["pseudo"]
         assertEquals "size", memberTest.errors["password"]
         
         memberTest = new Member (email:"isnotanemail")
         assertFalse memberTest.validate()
         assertEquals "email", memberTest.errors["email"]
         
         memberTest = new Member (website:"isnotanurl")
         assertFalse memberTest.validate()
         assertEquals "url", memberTest.errors["website"]
         
         memberTest = new Member (pseudo:"david", email:"dav.c@hotmail.fr")
         def memberTest2 = new Member (pseudo:"david", email:"dav.c@hotmail.fr")
         assertFalse memberTest.validate()
         assertFalse memberTest2.validate()
         assertEquals "unique", memberTest2.errors["email"]
         assertEquals "unique", memberTest2.errors["email"]
         
         memberTest = new Member (dateNaissance:Date.parse("yyyy-MM-dd", "2015-09-21"))
         assertFalse memberTest.validate()
         assertEquals "max", memberTest.errors["dateNaissance"]
    }
}
