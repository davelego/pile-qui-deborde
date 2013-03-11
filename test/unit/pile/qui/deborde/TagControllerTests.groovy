package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TagController)
@Mock(Tag)
class TagControllerTests {
	
	Tag t = new Tag(
		word:"Java",
		description: "This is the official tag for language Java")
	
    void testSomething() {
       assertTrue(true);
    }
	
	
	void testIndex () {
		controller.index()
		assert view == "/tag/NewTagsView"
	}
	
	
	void testList() {
		t.save()
		controller.list()
		assert view == '/tag/ListTagView'
		assert model.tags == Tag.list()
	}
	

	def save () {
		controller.params.word = t.word
		controller.params.description = t.description
		
		assert response.redirectedUrl == '/tag/list'
		
	}
}
