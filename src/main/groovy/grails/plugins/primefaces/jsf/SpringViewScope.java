package grails.plugins.primefaces.jsf;

import java.util.Map; 
import javax.faces.context.FacesContext; 
import org.springframework.beans.factory.ObjectFactory; 
import org.springframework.beans.factory.config.Scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* The class will  implements view scope for spring framework ,so that the JSF page can correct work with grails.
* When you call any bean in grails framework , 
* you can found it will call this class then add the bean into view-map of FacesContext.
* source file https://github.com/andreaminnucci/primefaces
* Modify &amp; Improve: Prominic -Bing Li
* Data: 2019-04-21
*/
 
public class SpringViewScope implements Scope {
    private static Logger log = LoggerFactory.getLogger("test.TestJavaLogging");
    
    public Object get(String name, ObjectFactory<?> objectFactory) {
        log.debug("Try to find the bean:  '"+name + "'.");
        if (FacesContext.getCurrentInstance().getViewRoot() != null) {
            Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
            if (viewMap.containsKey(name)) {
                return viewMap.get(name);
            } else {
                Object object = objectFactory.getObject();
                viewMap.put(name, object);
                return object;
            }
        } else {
            log.error("SpringViewScope get FacesContext is null ");
            return null;
        }
    }

    public Object remove(String name) {
        if (FacesContext.getCurrentInstance().getViewRoot() != null) {
            return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
        } else {
            return null;
        }
    }

    public void registerDestructionCallback(String name, Runnable callback) {
    }
    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        return null;
    }
}
