package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

@TestFor(MemberService)
@Mock([Member, Badge, Tag, Question])
class MemberServiceTests {

    void testCheckReputation () {
        def m = new Member(badges: [])
        
        service.updateReputation(m,0)
        assertNull m.badges.asList()[0]
        
        service.updateReputation(m,10)
        service.checkReputation(m)
        assertNotNull m.badges.asList()[0]
        assertEquals m.badges.asList()[0].name, Badge.BN_REPUT_10
        
        service.updateReputation(m,100)
        service.checkReputation(m)
        assertNotNull m.badges.asList()[1]
        assertEquals m.badges.asList()[1].name, Badge.BN_REPUT_100
        
        service.updateReputation(m,1000)
        service.checkReputation(m)
        assertNotNull m.badges.asList()[2]
        assertEquals m.badges.asList()[2].name, Badge.BN_REPUT_1000
        
        service.updateReputation(m,5000)
        service.checkReputation(m)
        assertNotNull m.badges.asList()[3]
        assertEquals m.badges.asList()[3].name, Badge.BN_REPUT_5000
        
        service.updateReputation(m,10000)
        service.checkReputation(m)
        assertNotNull m.badges.asList()[4]
        assertEquals m.badges.asList()[4].name, Badge.BN_REPUT_10000
    }
    
    void testUpdateReputation () {
        
        def m = new Member()
        assertEquals m.reputation, 0
        
        service.updateReputation(m,25)
        assertEquals m.reputation, 25
    }
    
    void testHaveBadge () {
        def m = new Member(badges:[])
        def b = new Badge(name:Badge.BN_REPUT_10) 
        
        assertFalse service.haveBadge(m, b.name)
        
        service.updateReputation(m,10)
        service.checkReputation(m)
        assertTrue service.haveBadge(m, b.name)
    }
    
    void testCheckTags () {
        def t = new Tag(word:"Java")
        
        def m = new Member(badges:[])
        service.checkTags(m,t.word)
        assertNull m.badges.asList()[0]
        
        def q1 = new Question(tags:[t])
        def q2 = new Question(tags:[t])
        def q3 = new Question(tags:[t])
        def q4 = new Question(tags:[t])
        def q5 = new Question(tags:[t])
        
        m = new Member(badges:[], questions:[q1, q2, q3, q4, q5])
        service.checkTags(m,t.word)
        assertNotNull m.badges.asList()[0]
        assertEquals m.badges.asList()[0].name, "ExpertOf" + t.word
    }
    
    void testSave () {
        assertEquals Member.getAll().size(), 0
        
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
        
        service.save(m)
        assertEquals Member.getAll().size(), 1
        assertNotNull Member.getAll().asList()[0]
    }
}
