package grails.plugins.primefaces;

import grails.util.Holders;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
/**
* The class privode some utils function for bean.。
* That mean it can work with grails i118 message file.
* source file https://github.com/andreaminnucci/primefaces
* Modify & Improve: Prominic -Bing Li
* Data: 2019-04-21
*/

public class WebApplicationUtils {

    public static <T> T getBean(String beanName) {
        return (T) Holders.getApplicationContext().getBean(beanName);
    }

    public static HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpSession) facesContext.getExternalContext().getSession(true);
    }
    
    public static HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }
    
}