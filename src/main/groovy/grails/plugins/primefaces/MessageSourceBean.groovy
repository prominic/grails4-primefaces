package grails.plugins.primefaces

import org.springframework.validation.ObjectError

import java.util.logging.Logger;

import static grails.plugins.primefaces.WebApplicationUtils.getSession;
import java.util.*;
import javax.el.BeanELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import javax.faces.bean.ApplicationScoped;
@ManagedBean(name = "message", eager = true)
@ApplicationScoped
public class MessageSourceBean extends BeanELResolver {
    private Logger log = Logger.getLogger(MessageSourceBean.class);

    private MessageSource messageSource;
    public MessageSourceBean() {
        messageSource = WebApplicationUtils.getBean("messageSource");
    }

    public void test(){
        println "this is test "
    }

    public String i18n(String code, String... args) {
        log.debug "Code:  $code"
        // println "args:"+args.toString()
        try {
            if (args.length == 0)
                return messageSource.getMessage(code, null, getLocale());
            else
                return messageSource.getMessage(code, args, getLocale());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return code;
        }
    }
    public String i18n(String code) {
         log.debug "Code:  $code"
        // println "args:"+args.toString()
        try {
            return messageSource.getMessage(code, null, getLocale());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return code;
        }
    }
    public Locale getLocale() {
        Locale locale;
        HttpSession session = getSession();
        //System.out.println(":"+"LOCALE_SESSION_ATTRIBUTE_NAME");
        if (session.getAttribute("LOCALE_SESSION_ATTRIBUTE_NAME") == null) {
            session.setAttribute("LOCALE_SESSION_ATTRIBUTE_NAME", "en");
        }
        locale = new Locale(session.getAttribute("LOCALE_SESSION_ATTRIBUTE_NAME").toString());
        return locale;
    }

    public void setLocale(String lang) {
        HttpSession session = getSession();
        session.setAttribute("LOCALE_SESSION_ATTRIBUTE_NAME", lang);
    }

    public String getErrorMessage(ObjectError error) {
//        String code = String.format("%s.%s.%s", fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
//        return messageSource.getMessage(code, fieldError.getRejectedValue(), fieldError.getDefaultMessage(), getLocale());
        return messageSource.getMessage(error.getCode(), error.getArguments(), error.getDefaultMessage(), getLocale());
    }



    private void pfMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        try {
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void infoPF(String summaryCode, String detailCode, String... args) {
        String summary = summaryCode != null && !summaryCode.equals("") ? i18n(summaryCode, args) : "";
        String detail = detailCode != null && !detailCode.equals("") ? i18n(detailCode, args) : "";
        pfMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }
    public void infoMessagePF(String summaryMessage, String detailMessage, String... args) {
        pfMessage(FacesMessage.SEVERITY_INFO, summaryMessage, detailMessage);
    }

    public void warningPF(String summaryCode, String detailCode, String... args) {
        String summary = summaryCode != null && !summaryCode.equals("") ? i18n(summaryCode, args) : "";
        String detail = detailCode != null && !detailCode.equals("") ? i18n(detailCode, args) : "";
        pfMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }
    public void warningMessagePF(String summaryMessage, String detailMessage) {
        pfMessage(FacesMessage.SEVERITY_WARN, summaryMessage, detailMessage);
    }

    public void errorPF(String summaryCode, String detailCode, String... args) {
        String summary = summaryCode != null && !summaryCode.equals("") ? i18n(summaryCode, args) : "";
        String detail = detailCode != null && !detailCode.equals("") ? i18n(detailCode, args) : "";
        pfMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }
    public void errorMessagePF(String summaryMessage, String detailMessage) {
        pfMessage(FacesMessage.SEVERITY_ERROR, summaryMessage, detailMessage);
    }
}
