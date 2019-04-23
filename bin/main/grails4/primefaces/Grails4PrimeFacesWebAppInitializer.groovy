package grails4.primefaces
import org.springframework.web.WebApplicationInitializer
import javax.servlet.ServletContext

import com.sun.faces.config.ConfigureListener


/**
 * Created by prominic2 on 2018/4/20.
 */
class Grails4PrimeFacesWebAppInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext container) {
        container.addListener(new ConfigureListener());
    }

}
