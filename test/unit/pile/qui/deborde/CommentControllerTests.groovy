package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CommentController)
@Mock([Comment,Question,Tag,Member,Answer,Post])
class CommentControllerTests {

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
	
	
	Answer a = new Answer(body : "this is an answer in order to test controller and other speech ",
						 author:m,
						 question:q,
						 date:new Date())
	
	Comment cAnswer = new Comment(body : "this is a comment for an answer",
								  relatedPost : a,
								  date : new Date(),
								  author : m)
	
	Comment cQuestion = new Comment(body : "this is a comment for an question",
		relatedPost : q,
		date : new Date(),
		author : m)
	
    void testSomething() {
       assertTrue(true);
    }
	
	void testCommentQuestion () {
		
		q.save()
		a.save()
		m.save()
		t.save()
		
		controller.params.idpost = q.id 
		
		controller.comment()
		
		assert view == '/comment/NewCommentView'
		assert model.post.id == q.id
		assert model.type == 'question'
		
		
	}
	
	void testCommentAnswer () {
		
		q.save()
		a.save()
		m.save()
		t.save()

		controller.params.idpost = a.id
		
		controller.comment()
		
		assert view == '/comment/NewCommentView'
		assert model.post.id == a.id
		assert model.type == 'answer'
		
		
	}
	
	void testSaveQuestion () {
		
		q.save()
		m.save()
		t.save()
		session.user = m
		controller.params.idpost = q.id
		controller.params.body = "this is an answer in order to test the controller"
		controller.save()
		
		assert response.redirectedUrl == '/question/detail/' + q.id
		assert Comment.count() == 1
	}
	
	void testSaveQuestionFail () {
	
		q.save()
		session.user = null
		controller.params.idpost = q.id
		controller.params.body = "this is an answer in order to test the controller"
		controller.save()
		
		assert view == '/comment/NewCommentView'

	}
	
	void testSaveAnswer () {
		
		q.save()
		a.save()
		m.save()
		t.save()
		session.user = m
		controller.params.idpost = a.id
		controller.params.body = "this is an answer in order to test the controller"
		controller.save()
		
		assert response.redirectedUrl == '/question/detail/' + q.id
		assert Comment.count() == 1
	}
	
	void testSaveAnswerFail () {
	
		q.save()
		t.save()
		session.user = null
		controller.params.idpost = a.id
		controller.params.body = "this is an answer in order to test the controller"
		controller.save()
		
		assert view == '/comment/NewCommentView'
	}
	
	void testEdit () {
		
		cAnswer.save()
		
		controller.params.idcomment = cAnswer.id
		controller.edit()
		
		assert view == '/comment/EditCommentView'
		assert model.comment == cAnswer
	}
	
	
	void testEditComment () {
		q.save()
		a.save()
		cAnswer.save()
		
		controller.params.idcomment = cAnswer.id
		controller.params.idquestion = q.id
		controller.params.body = "this is the new body"
		controller.editComment()
		
		assert response.redirectedUrl == '/question/detail/' + q.id
		assert cAnswer.body == "this is the new body"
	}
	
	void testDelete () {
		q.save()
		a.save()
		cAnswer.save()
		
		controller.params.idcomment = cAnswer.id
		controller.params.idquestion = q.id
		
		assert Comment.count() == 1
		
		controller.delete()
		
		assert response.redirectedUrl == '/question/detail/' + q.id
		assert Comment.count() == 0
		
	}
	
}
