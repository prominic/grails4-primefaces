package grails4.primefaces
import grails.core.GrailsApplication
import grails.core.support.GrailsApplicationAware

import javax.servlet.DispatcherType
import javax.servlet.ServletContext
import javax.servlet.ServletException
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean

import com.sun.faces.config.ConfigureListener
import com.sun.faces.config.FacesInitializer

import com.sun.el.ExpressionFactoryImpl

import groovy.util.logging.Slf4j
 
/**
 * Created by featger812002@163.com on 2019/4/03.
 */
@Slf4j
class Grails4PrimeFacesConfig  implements GrailsApplicationAware{
    GrailsApplication grailsApplication

    public void setGrailsApplication(GrailsApplication grailsApplication){
        this.grailsApplication=grailsApplication
    }


    @Bean
    public ServletContextInitializer grails4PrimefacesInitializer() {
        return new ServletContextInitializer() {
            @Override
            void onStartup(ServletContext servletContext) throws ServletException {
                log.info "******* grails4-primefaces Initializer Started **********"
                servletContext.setInitParameter("primefaces.THEME", "start");
                servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");
                servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
                servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/applicationContext.xml");
                servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
                //servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");

                servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
                servletContext.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");

                servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");


                FacesInitializer facesInitializer = new FacesInitializer();
                Set<Class<?>> clazz = new HashSet<Class<?>>();
                clazz.add(Grails4PrimeFacesConfig.class);
                facesInitializer.onStartup(clazz, servletContext);

                servletContext.addListener(new ConfigureListener())
                log.info "******* grails4-primefaces Initializer Completed **********"
            }
        }
    }


}
