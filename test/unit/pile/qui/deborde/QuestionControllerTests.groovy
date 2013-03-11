package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(QuestionController)
@Mock([Question,Answer,Badge,Tag,Member,QuestionService,MemberService,TagService])

class QuestionControllerTests {

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
		dateInscription:new Date(),
		badges:[])
	
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
	
    void testSomething() {
       assertTrue(true);
    }
	
	void testIndex () {
		controller.index()
		assert view == '/question/NewQuestionView'
	}
	
	void testList() {
		q.save()
		controller.list()
		assert view == '/question/ListQuestionsView'
		assert model.questions == Question.list()
	}
	
	void testDetail () {
		q.save()
		
		controller.params.id = q.id
		controller.detail()
		
		assert model.q == q
		assert view == '/question/DetailQuestionView'
	}
	
	void testSave () {
		t.save()
		m.save()
		session.user = m
		controller.params.tags = t.word;
		controller.params.title = q.title
		controller.params.body = q.body
		
		controller.save()
		
		assert response.redirectedUrl == "/question/list"
		assert Question.count() == 1
	}
	
	void testEdit () {
		
		q.save()
		
		controller.params.idquestion = q.id
		controller.edit()
		
		assert view == '/question/EditQuestionView'
		assert model.question == q
	}
	
	
	void testEditQuestion () {
		q.save()
		m.save()
		t.save()
		
		
		controller.params.idquestion = q.id
		controller.params.body = "this is the new body that must containt 50 chars or this **** test doesn't work"
		controller.params.tags = t.word
		controller.editQuestion()
		
		assert response.redirectedUrl == '/question/detail/' + q.id
		assert q.body == "this is the new body that must containt 50 chars or this **** test doesn't work"
	}
	
	void testDelete () {
		q.save()
		m.save()
		t.save()

		
		controller.params.idquestion = q.id
		assert Question.count() == 1
		
		controller.delete()
		
		assert response.redirectedUrl == '/question/list' 
		assert Question.count() == 0
		
	}
	
	
}
