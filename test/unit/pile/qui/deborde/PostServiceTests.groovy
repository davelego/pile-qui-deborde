package pile.qui.deborde

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*


@TestFor(PostService)
@Mock([Post,Member])
class PostServiceTests {
    
    void testIncTotalVote () {
        def p = new Post()
        assertEquals p.totalVote, 0
        
        service.incTotalVote(p)
        assertEquals p.totalVote, 1
    }
    
    void testDecTotalVote () {
        def p = new Post()
        assertEquals p.totalVote, 0
        
        service.decTotalVote(p)
        assertEquals p.totalVote, -1
    }
    
    void testSave () {
        assertEquals Post.getAll().size(), 0
        
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
        
        Post p = new Post (author:m, date: new Date())
        service.save(p)
        assertEquals Post.getAll().size(), 1
    }
}
