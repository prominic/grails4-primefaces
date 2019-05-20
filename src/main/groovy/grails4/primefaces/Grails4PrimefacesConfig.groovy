package grails4.primefaces

import grails.core.GrailsApplication
import grails.core.support.GrailsApplicationAware

import grails.plugins.primefaces.ThemeService

import javax.servlet.DispatcherType
import javax.servlet.ServletContext
import javax.servlet.ServletException
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean

import com.sun.faces.config.ConfigureListener
import com.sun.faces.config.FacesInitializer

import com.sun.el.ExpressionFactoryImpl

import groovy.util.logging.Slf4j
import grails.util.Holders
 
/**
* The class will defined the all configuration for JSF page,and inistal the primeface framework in here.
* The grails framwork will loading these configuraiton from this class ,rather than loading them from web.xml
* Author: Prominic -Bing Li
* Data: 2019-04-21
*/
@Slf4j
class Grails4PrimeFacesConfig  implements GrailsApplicationAware{
    GrailsApplication grailsApplication
    def servletContext =Holders.getServletContext()

    public void setGrailsApplication(GrailsApplication grailsApplication){
        this.grailsApplication=grailsApplication
    }


    @Bean
    public ServletContextInitializer grails4PrimefacesInitializer() {
        return new ServletContextInitializer() {
            @Override
            void onStartup(ServletContext servletContext) throws ServletException {
                log.info "******* grails4-primefaces Initializer Started **********"

                // Theme
                ThemeService themeService=Holders.grailsApplication.mainContext.getBean 'themeService'
                def theme = themeService.configTheme
                if(theme){
                     log.info "Using configured theme:  '$theme'"
                     servletContext.setInitParameter("primefaces.THEME", theme);
                }
                else {
                    log.info('Using default theme.')
                }

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
