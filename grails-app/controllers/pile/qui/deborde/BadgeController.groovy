package pile.qui.deborde

class BadgeController {

    //static scaffold = true
    
    def list () {
        def listBadges = Badge.list()
        render(view: "ListBadgeView", model:[badges: listBadges])
    }
}
