package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(IndexController)
@Mock([Question,Tag,Member])
class IndexControllerTests {
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
    void testIndex() {
		q.save()
		controller.index()
		assert view == '/index/index'
		assert model.questions == Question.list()
		
    }
}
