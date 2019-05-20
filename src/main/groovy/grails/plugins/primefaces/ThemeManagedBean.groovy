package grails.plugins.primefaces

import groovy.util.logging.Slf4j
import grails.util.Holders
import com.prominic.primeface.ThemeService
import javax.faces.bean.ApplicationScoped
import javax.faces.bean.ManagedBean



/**
 * Provide some useful utilities for working with themes.
 * Use themeMB.theme to get the current theme.
 # Use themeMB.themeList to get a list of available themes for use with <p:themeSwitcher>
 */
@ManagedBean(name = "themeMB", eager = true)
@ApplicationScoped
@Slf4j
public class ThemeManagedBean {

    ThemeService themeService=Holders.grailsApplication.mainContext.getBean 'themeService'

    /**
     * The configured theme
     * Read-only.
     */ 
    String getTheme() {
        String theme = themeService.configTheme
        log.debug("ThemeService.theme = '$theme'")
        return theme
    }

    /**
     * A list of available themes.
     */
    Collection<String> getThemeList() {
        Collection<String> themeList = themeService.themeList
        log.debug("ThemeService.themeList = '${themeList.toString()}'")
        return themeList
    }

}
