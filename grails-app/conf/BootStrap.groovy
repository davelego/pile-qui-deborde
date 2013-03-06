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
			
		// Mock tag
		Tag t = new Tag(
			word:"Java",
			description: "This is the official tag for language Java")
		if (!t.save()){
			log.error "Error on TAG"
			log.error "${t.errors}"
		}
			
		// Mock question
		def listTags = []
		listTags.add(t)
		Question q = new Question (
			title:"Ou va le monde ?",
			body:"Bonjour à tous. Voilà, je me pose cette question depuis maintenant environ 5 ans."
				+ "Je ne trouve la réponse nul part, même sur jeuxvideo.com, alors je la pose ici."
				+ "Merci de me répondre dans les plus bref délais. Cordialement, Stonkeep.",
			date: new Date(),
			author:m1,
			tags:listTags)
		if (!q.save()){
			log.error "Error on QUESTION"
			log.error "${q.errors}"
		}
		
		// Mock answer
		Answer a = new Answer (
			body:"Bonjour Stonkeep. Alors pour être franc, même si ça va te paraître relativement hors propos," +
			     "je dirai à peu près nul part ... Bisous.",
			date: new Date(),
			author:m2,
			question:q)
		if (!a.save()){
			log.error "Error on ANSWER"
			log.error "${a.errors}"
		}
    }
		
		
    def destroy = {
    }
}
