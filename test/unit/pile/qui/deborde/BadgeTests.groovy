package pile.qui.deborde

import org.junit.*
import grails.test.mixin.*

@TestFor(Badge)
class BadgeTests {

    void testConstraints () {
       def badge = new Badge(name:Badge.BN_REPUT_10)
       mockForConstraintsTests(Badge,[badge])
       
       def badgeTest = new Badge()
       assertFalse badgeTest.validate()
       assertEquals badgeTest.errors.errorCount, 1
       assertEquals "nullable", badgeTest.errors["name"]
       
       badgeTest = new Badge(name:"")
       assertFalse badgeTest.validate()
       assertEquals badgeTest.errors.errorCount, 1
       assertEquals "blank", badgeTest.errors["name"]
    }
}
