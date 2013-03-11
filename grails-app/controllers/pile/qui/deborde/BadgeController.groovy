package pile.qui.deborde

class BadgeController {

    //static scaffold = true
    
    def list () {
        def listBadgesTmp = Badge.list()
        def listBadges = []
        for (Badge b1 : listBadgesTmp) {
           def isIn = false
           for (Badge b2 : listBadges) {
               if (b1.name == b2.name) {
                   isIn = true
               }
           }
           
           if (!isIn) {
               listBadges.add(b1)
           }
        }
        
        render(view: "ListBadgeView", model:[badges: listBadges])
    }
}
