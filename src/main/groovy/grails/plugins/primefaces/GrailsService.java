package grails.plugins.primefaces;

 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
* The class will  help bean get the service from grails framework ,it work with grails2.*.
* source file https://github.com/andreaminnucci/primefaces
* Modify &amp; Improve: Prominic -Bing Li
* Data: 2019-04-21
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GrailsService {
    String name();
}
