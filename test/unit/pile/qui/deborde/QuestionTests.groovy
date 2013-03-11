package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*


@TestFor(Question)
@Mock([Tag,Member,Question])
class QuestionTests {
    
    Member member
    Tag tag
    
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
    }

    void testConstraints () {
       def question = new Question (
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
       
       mockForConstraintsTests(Question,[question])
       
       def questionTest = new Question()
       assertFalse questionTest.validate()
       assertEquals questionTest.errors.errorCount, 5
       assertEquals questionTest.errors["author"], "nullable"
       assertEquals questionTest.errors["title"], "nullable"
       assertEquals questionTest.errors["body"], "nullable"
       assertEquals questionTest.errors["tags"], "nullable"
       
       questionTest = new Question(body:"", title:"")
       assertFalse questionTest.validate()
       assertEquals questionTest.errors["title"], "blank"
       assertEquals questionTest.errors["body"], "blank"
       
       questionTest = new Question(body:"ab", title:"ab", tags:[])
       assertFalse questionTest.validate()
       assertEquals questionTest.errors["title"], "size"
       assertEquals questionTest.errors["body"], "minSize"
       assertEquals questionTest.errors["tags"], "minSize"
    }
}
