package grails4.primefaces
import org.springframework.web.WebApplicationInitializer
import javax.servlet.ServletContext

import com.sun.faces.config.ConfigureListener


/**
* The class will force the container add the configuration listener ,
* so that it can loading the primeface configuration.
* Author: Prominic - Bing Li
* Data: 2019-04-21
*/
class Grails4PrimeFacesWebAppInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext container) {
        container.addListener(new ConfigureListener());
    }

}
