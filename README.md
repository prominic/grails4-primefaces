# Summary

Run JSF and PrimeFaces from a Grails 4 application

## Getting Started

This plugin has not yet been submitted to the Grails repository.  For now you will need to install the plugin to your local repository.

    ./gradlew install

Then add this dependency to your project:
```
    compile "net.prominic.grails.plugins:grails4-primefaces:0.1"
```

To run with the default Tomcat container, you will also need to add these WEB-INF files:

#### src/main/webapp/WEB-INF/faces-config.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<faces-config
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd"
    version="2.2">

    <!-- required for grails4-primefaces plugin -->
    <application>
        <el-resolver>org.springframework.web.jsf.el.SpringBeanFacesELResolver</el-resolver>
    </application>

</faces-config>

```

#### src/main/webapp/WEB-INF/web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.0"
         metadata-complete="true"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">



    <!-- Required for grails4-primefaces plugin -->
    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.faces</url-pattern>
    </servlet-mapping>


</web-app>
```


### Theme

To set the theme to one of the default [community themes](https://www.primefaces.org/themes/), set the grails.plugins.primefaces.theme configuration value.  For example in grails-app/conf/application.yml:

```
grails:
    plugins:
        primefaces:
            #...
            theme: afterdark
```

If you want to experiment with the themes, you can add use a ThemeSwitcher and the themeMB bean:

```
        <p:themeSwitcher id="template" style="width:165px"  value="#{themeMB.theme}" >
            <f:selectItem itemLabel="Choose Theme" itemValue="#{null}" />
            <f:selectItems value="#{themeMB.themeList}" var="theme" itemLabel="#{theme}" itemValue="#{theme}" />
        </p:themeSwitcher>

```

If you want to add custom themes to the ThemeSwitcher, you can use the grails.plugins.primefaces.customThemes configuration value:

```
grails:
    plugins:
        primefaces:
            #...
            customThemes:
                - ultima-blue
                - ultima-blue-grey
```


### Example

You can see an example project and some demo instructions in [this project](https://github.com/prominic/grails4-primefaces-demo)

## Dependencies

This plugin was written and tested on Grails 4.0.0.M2.

## Usage

To generate the beans, use this command:

    ./grailsw run-command pf-generate-all mypackage.MyDomain
    
This will generate the following files:
* grails-app/services/mypackage/MyDomainService.groovy
* src/main/groovy/mypackage/beans/MyDomainManageBean.groovy (the ManagedBean is called "myDomainMB")
* src/main/groovy/mypackage/beans/MyDomainLazyDataModel.groovy

To allow the new beans to work, you will need to add the new package to the grails.plugins.primefaces.beans.packages property in grails-app/conf/application.yml.

```
grails:
    plugins:
        primefaces:
                beans:
                   packages: mypackage.beans
```

You can then define .xhtml files to reference these beans in:

    src/main/webapp

## Built With

* [Grails](http://grails.org/download.html)
* [Gradle](https://gradle.com/)

## Authors

* [mibesoft](https://github.com/mibesoft/primefaces) - Original code 
* [feather812002](https://github.com/feather812002) - Grails 3 and 4 Conversion
* [JoelProminic](https://github.com/JoelProminic) - Cleanup and Documentation

## License

grails4-primefaces is licensed under the Apache License 2.0 - see the [LICENSE.md](https://github.com/prominic/grails4-primefaces/blob/master/LICENSE.MD) file for details

## Acknowledgments

* This the Grails 4 version of the [grails3-primefaces plugin](https://github.com/prominic/grails3-primefaces)
* The original code was adapted from [this Grails 2.x plugin](https://github.com/mibesoft/primefaces)
