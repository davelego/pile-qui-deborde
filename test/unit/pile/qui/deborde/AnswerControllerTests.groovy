package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

@TestFor(AnswerController)
@Mock([Answer,Question,Tag,Member,AnswerService,MemberService])
class AnswerControllerTests {

   void testSomething() {
       assertTrue(true);
    }
   
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
   
   
   Answer a = new Answer(body : "this is an answer in order to test controller",
						   author:m,
						question:q,
						date:new Date())
   
   void testAnswer() {
	   
	   q.save()
	   
	   controller.params.idquestion = q.id 
	   controller.answer()
	   assert view == '/answer/NewAnswerView'
	   assert model.question == q
   }
   
   void testSave () {
	   
	   q.save()
	   m.save()
	   t.save()
	   session.user = m
	   controller.params.idquestion = q.id
	   controller.params.body = "this is an answer in order to test the controller"
	   controller.save()
	   
	   assert response.redirectedUrl == '/question/detail/' + q.id
	   assert Answer.count() == 1
   }
   
   void testSaveFail () {
   
	   q.save()
	   t.save()
	   session.user = null
	   controller.params.idquestion = q.id
	   controller.params.body = "this is an answer in order to test the controller"
	   controller.save()
	   
	   assert view == '/answer/NewAnswerView' 
	   assert model.question == q
   }
   
   void testEdit () {
	   
	   a.save()
	   
	   controller.params.idanswer = a.id
	   controller.edit()
	   
	   assert view == '/answer/EditAnswerView'
	   assert model.answer == a
   }
   
   
   void testEditAnswer () {
	   q.save()
	   m.save()
	   t.save()
	   a.save()
	   
	   controller.params.idanswer = a.id
	   controller.params.body = "this is the new body"
	   controller.editAnswer()
	   
	   assert response.redirectedUrl == '/question/detail/' + q.id
	   assert a.body == "this is the new body"
   }
   
   void testDelete () {
	   q.save()
	   m.save()
	   t.save()
	   a.save()
	   
	   controller.params.idanswer = a.id
	   assert Answer.count() == 1
	   
	   controller.delete()
	   
	   assert response.redirectedUrl == '/question/detail/' + q.id
	   assert Answer.count() == 0
	   
   }
   
   
   void testCheck () {
	   q.save()
	   m.save()
	   t.save()
	   a.save()
	   int rep = m.reputation
	   controller.params.idanswer = a.id
	   controller.check()
	   assert a.haveHelped == true
	   assert m.reputation == rep + 3
	   assert response.redirectedUrl == '/question/detail/' + q.id  
   }
   
   void testUncheck () {
	   q.save()
	   m.save()
	   t.save()
	   a.save()
	   int rep = m.reputation
	   controller.params.idanswer = a.id
	   controller.uncheck()
	   assert a.haveHelped == false
	   assert m.reputation == rep - 3 
	   assert response.redirectedUrl == '/question/detail/' + q.id
	   
	   
   }
   
   
}
