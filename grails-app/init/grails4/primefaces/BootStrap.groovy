//Default boot starp file ,it will be execute before the application starting.
//It will loading&inital the primeface  in here
package grails4.primefaces

class BootStrap {

    def init = { servletContext ->
        log.info  "grails4-primefaces BootStrap start"
        grails.plugins.primefaces.PrimefacesBeans.init()
   
    }
    def destroy = {
    }
}
