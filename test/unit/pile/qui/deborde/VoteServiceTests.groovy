package pile.qui.deborde

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*


@TestFor(VoteService)
@Mock([Vote,Member,Post])
class VoteServiceTests {

    void testCheckIfAlreadyVoted () {
        def m = new Member(
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
        
        def p = new Post (author:m, date: new Date())
        assertFalse service.checkIfAlreadyVoted(p,m)
        
        def v = new Vote(votedPost:p, voter:m)
        p = new Post (author:m, date: new Date(), votes:[v])
        assertTrue service.checkIfAlreadyVoted(p,m)
    }
    
    void testSave () {
        assertEquals Vote.getAll().size(), 0
        
        def m = new Member(
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
        def p = new Post (author:m, date: new Date())  
        def v = new Vote(votedPost:p, voter:m)
        service.save(v)
        assertEquals Vote.getAll().size(), 1
    }
}
