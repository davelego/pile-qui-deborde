package pile.qui.deborde

class TagService {

    def checkTags (String tags) {
		
		def  tagsArray = tags.split(" ");
		def listTags = []
		for (String tag : tagsArray) {
		   if (Tag.findAllByWord(tag)) {
				listTags.add(Tag.findAllByWord(tag)[0])
		   } else {
			   listTags = null;
			   break;
		   }
		}
		
		return listTags
    }
    
    def save (Tag t) {
        t.save()
    }
}
