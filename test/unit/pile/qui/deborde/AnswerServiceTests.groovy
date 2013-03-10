package pile.qui.deborde

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*


@TestFor(AnswerService)
@Mock([Answer,Question, Tag, Member])
class AnswerServiceTests {

    void testSomething() {
        assertEquals Answer.getAll().size(), 0
        
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
        
        Tag t = new Tag(
            word:"Java",
            description: "This is the official tag for language Java")
        
        Question q = new Question (
            title:"Why is backface-visibility hidden not working in IE10 when perspective is " +
                  "applied to parent elements ?",
            body:"Ok, so here's another IE10 glitch. The problem is that applying perspective " +
                 "on parent elements breaks the backface-visibility hidden setting. Please see " +
                 "this fiddle: http://jsfiddle.net/2y4eA. " +
                 "When you hover over the red square it rotates by 180° on the x-axis, and even " +
                 "though the backface-visibility is set to hidden, the backface is shown, at least " +
                 "until the 180° is reached, then it disappears. Remove the perspective property, " +
                 "and you'll see that it works as expected, the backface isn't visible at all, but " +
                 "ofcourse the 3d effect is lost.",
            date: Date.parse("yyyy-MM-dd", "2013-03-07"),
            author:m,
            tags:[t])
        
        Answer a = new Answer (
            body:"I came up against this glitch too and it is definitely a glitch." +
                 "The workaround is to apply the perspective transform on the child element. " +
                 "I updated your fiddle here: http://jsfiddle.net/jMe2c/",
            date: Date.parse("yyyy-MM-dd", "2013-03-07"),
            author:m,
            question:q)
        
        service.save(a)
        assertEquals Answer.getAll().size(), 1
    }
}
