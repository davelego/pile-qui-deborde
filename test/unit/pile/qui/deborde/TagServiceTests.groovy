package pile.qui.deborde

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*


@TestFor(TagService)
@Mock(Tag)
class TagServiceTests {

    void testCheckTags () {
        assertNull service.checkTags("")
        
        def t = new Tag(word:"Java", description:"This is the official JAVA tag")
        service.save(t)
        assertNotNull service.checkTags(t.word)
        assertNull service.checkTags("C++")
    }
    
    void testSave () {
        assertEquals Tag.getAll().size(), 0
        
        def t = new Tag(word:"Java", description:"This is the official JAVA tag")
        service.save(t)
        assertEquals Tag.getAll().size(), 1
    }
}
