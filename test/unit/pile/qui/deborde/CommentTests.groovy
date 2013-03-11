package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

@TestFor(Comment)
@Mock([Member,Post,Comment])
class CommentTests {

    def member
    def post
    
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
        
        post = new Post(author:member)
    }
    
    void testConstraints () {
       def comment = new Comment(relatedPost:post, author:member)
       mockForConstraintsTests(Comment,[comment])
       
       def commentTest = new Comment()
       assertFalse commentTest.validate()
       assertEquals commentTest.errors.errorCount, 4
       assertEquals commentTest.errors["body"], "nullable"
       assertEquals commentTest.errors["date"], "nullable"
       assertEquals commentTest.errors["author"], "nullable"
       assertEquals commentTest.errors["relatedPost"], "nullable"
       
       commentTest = new Comment(body:"")
       assertFalse commentTest.validate()
       assertEquals commentTest.errors["body"], "blank"
    }
}
