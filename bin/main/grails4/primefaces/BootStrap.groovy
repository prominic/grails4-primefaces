package grails4.primefaces

class BootStrap {

    def init = { servletContext ->
        log.info  "grails4-primefaces BootStrap start"
        grails.plugins.primefaces.PrimefacesBeans.init()
   
    }
    def destroy = {
    }
}
