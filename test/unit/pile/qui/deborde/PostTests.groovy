package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

@TestFor(Post)
@Mock([Post,Member,Tag])
class PostTests {

    Member member
    
    void setUp () {
        member = new Member(
            firstName:"David",
            lastName:"CHARETTE",
            email:"dav.c@hotmail.fr",
            pseudo:"Stonkeep",
            password:"Machin",
            bio:"Je suis Stonkeep",
            website:"http://www.stonkeep.com",
            dateNaissance:Date.parse("yyyy-MM-dd", "1990-09-21"),
            dateInscription:new Date())
    }
    
    void testConstraints() {
        
        def post = new Post(author:member)
        mockForConstraintsTests(Post, [post])
        
        def postTest = new Post()
        assertFalse postTest.validate()
        assertEquals "nullable", postTest.errors["author"]
    }
}
