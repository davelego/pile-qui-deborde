import java.util.Date;

import pile.qui.deborde.Answer
import pile.qui.deborde.Member
import pile.qui.deborde.Question
import pile.qui.deborde.Tag

class BootStrap {

    def init = { servletContext ->
		
		// First mock member
		Member m1 = new Member(
			firstName:"David",
			lastName:"CHARETTE",
			email:"dav.c@hotmail.fr",
			pseudo:"Stonkeep",
			password:"Machin",
			bio:"Je suis Stonkeep",
			website:"http://www.stonkeep.com",
			photoPath:"http://www.stonkeep.com/img/avatar.png",
			dateNaissance:Date.parse("yyyy-MM-dd", "1990-09-21"),
			dateInscription:new Date())
		
		if (!m1.save()){
		    log.error "Error on USER1"
		    log.error "${m1.errors}"
		}
			
		// Second mock member
		Member m2 = new Member(
			firstName:"Raphaël",
			lastName:"GONCALVES",
			email:"goncalvr@poste.isima.fr",
			pseudo:"Superapha",
			password:"Machin",
			bio:"Je suis Superapha",
			website:"http://www.superapha.com",
			photoPath:"http://www.superapha.com/img/avatar.png",
			dateNaissance:Date.parse("yyyy-MM-dd", "1990-09-19"),
			dateInscription:new Date())
		
		if (!m2.save()){
			log.error "Error on USER2"
			log.error "${m2.errors}"
		}
			
		// Mock tags
		Tag t1 = new Tag(
			word:"Java",
			description: "This is the official tag for language Java")
		if (!t1.save()){
			log.error "Error on TAG"
			log.error "${t1.errors}"
		}
		
		Tag t2 = new Tag(
			word:"Grails",
			description: "This is the official tag for the Grails framework")
		if (!t2.save()){
			log.error "Error on TAG"
			log.error "${t2.errors}"
		}
		
		Tag t3 = new Tag(
			word:"jQuery",
			description: "This is the official tag for the jQuery framework")
		if (!t3.save()){
			log.error "Error on TAG"
			log.error "${t3.errors}"
		}
		
		
		Tag t4 = new Tag(
			word:"Eclipse",
			description: "This is the official tag for the Eclipse IDE")
		if (!t4.save()){
			log.error "Error on TAG"
			log.error "${t4.errors}"
		}
		
		Tag t5 = new Tag(
			word:"Git",
			description: "This is the official tag for the git tool")
		if (!t5.save()){
			log.error "Error on TAG"
			log.error "${t5.errors}"
		}
			
		// Mock questions
		def listTags = []
		listTags.add(t1)
		listTags.add(t2)
		listTags.add(t3)
		Question q1 = new Question (
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
			author:m1,
			tags:listTags)
		if (!q1.save()){
			log.error "Error on QUESTION"
			log.error "${q1.errors}"
		}
		
		listTags = []
		listTags.add(t5)
		Question q2 = new Question (
			title:"Starting a new user session from a service ?",
			body:"I have the following problem: " +
				 "From a service I need to start an application in a user session. " +
				 "No human user log on that machine, since it is a server. " + 
				 "Launched application must have a session != 0.",
			date: Date.parse("yyyy-MM-dd", "2012-03-09"),
			author:m2,
			tags:listTags)
		if (!q2.save()){
			log.error "Error on QUESTION"
			log.error "${q2.errors}"
		}
		
		listTags = []
		listTags.add(t3)
		listTags.add(t5)
		Question q3 = new Question (
			title:"Android add MMS to database ?",
			body:"I want to add some MMS messages into my device database. " +
				 "I've the following code but it doesn't work at all. No entry are added " + 
				 "into the native app ...",
			date: Date.parse("yyyy-MM-dd", "2013-03-09"),
			author:m2,
			tags:listTags)
		if (!q3.save()){
			log.error "Error on QUESTION"
			log.error "${q2.errors}"
		}
		
		// Mock answers
		Answer a1 = new Answer (
			body:"I came up against this glitch too and it is definitely a glitch." +
				 "The workaround is to apply the perspective transform on the child element. " +
				 "I updated your fiddle here: http://jsfiddle.net/jMe2c/",
			date: Date.parse("yyyy-MM-dd", "2013-03-07"),
			author:m2,
			question:q1)
		if (!a1.save()){
			log.error "Error on ANSWER"
			log.error "${a1.errors}"
		}
		
		Answer a2 = new Answer (
			body:"A workaround that i would imagine is adding a transition for opacity that " +
				 "has 0 timing and has a delay of half of your perspective transition.",
			date: Date.parse("yyyy-MM-dd", "2013-03-08"),
			author:m1,
			question:q1)
		if (!a2.save()){
			log.error "Error on ANSWER"
			log.error "${a2.errors}"
		}
    }
		
		
    def destroy = {
    }
}
