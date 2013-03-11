package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*


@TestFor(Answer)
@Mock([Tag,Member,Question])
class AnswerTests {
    
    Member member
    Tag tag
    Question question

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
        
        tag = new Tag(
            word:"Java",
            description: "This is the official tag for language Java")
        
        question = new Question (
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
            author:member,
            tags:[tag])
    }
    
    void testConstraints() {
        def answer = new Answer (
                            body:"I came up against this glitch too and it is definitely a glitch." +
                                 "The workaround is to apply the perspective transform on the child element. " +
                                 "I updated your fiddle here: http://jsfiddle.net/jMe2c/",
                            date: Date.parse("yyyy-MM-dd", "2013-03-07"),
                            author:member,
                            question:question)
        mockForConstraintsTests(Answer,[answer])
        
        def answerTest = new Answer()
        assertFalse answerTest.validate()
        assertEquals answerTest.errors.errorCount, 4
        assertEquals answerTest.errors["author"], "nullable"
        assertEquals answerTest.errors["question"], "nullable"
        assertEquals answerTest.errors["body"], "nullable"
        assertEquals answerTest.errors["date"], "nullable"
        
        answerTest = new Answer(body:"")
        assertFalse answerTest.validate()
        assertEquals answerTest.errors["body"], "blank"
        
        answerTest = new Answer(body:"ab")
        assertFalse answerTest.validate()
        assertEquals answerTest.errors["body"], "minSize"
    }
}
