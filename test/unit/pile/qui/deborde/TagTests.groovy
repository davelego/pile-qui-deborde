package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*


@TestFor(Tag)
class TagTests {

    void testConstraints () {
        def tag = new Tag(
            word:"Java",
            description: "This is the official tag for language Java")
        
        mockForConstraintsTests(Tag,[tag])
        
        def tagTest = new Tag()
        assertFalse tagTest.validate()
        assertEquals tagTest.errors.errorCount, 2
        assertEquals "nullable", tagTest.errors["word"]
        assertEquals "nullable", tagTest.errors["description"]
        
        tagTest = new Tag(word:"", description:"")
        assertFalse tagTest.validate()
        assertEquals tagTest.errors.errorCount, 2
        assertEquals "blank", tagTest.errors["word"]
        assertEquals "blank", tagTest.errors["description"]
    }
}
