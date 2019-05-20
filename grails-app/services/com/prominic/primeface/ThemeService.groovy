package com.prominic.primeface

import grails.gorm.transactions.Transactional

import javax.annotation.PostConstruct

/**
 *
 */
@Transactional
class ThemeService {
    def grailsApplication

    public String configTheme=null
    public Collection<String> themeList = [
        'afterdark',
        'afternoon',
        'afterwork',
        'black-tie',
        'blitzer',
        'bluesky',
        'bootstrap',
        'casablanca',
        'cruze',
        'cupertino',
        'dark-hive',
        'delta',
        'dot-luv',
        'eggplant',
        'excite-bike',
        'flick',
        'glass-x',
        'home',
        'hot-sneaks',
        'humanity',
        'le-frog',
        'midnight',
        'mint-choc',
        'overcast',
        'pepper-grinder',
        'redmond',
        'rocket',
        'sam',
        'smoothness',
        'south-street',
        'start',
        'sunny',
        'swanky-purse',
        'trontastic',
        'ui-darkness',
        'ui-lightness',
        'vader',
    ]

    /**
     * Initialize the configured theme
     */ 
    @PostConstruct
    def init () {
        log.info 'Initializing theme service.'
        this.configTheme = grailsApplication.config.getProperty('grails.plugins.primefaces.theme')

        def additionalThemes = grailsApplication.config.getProperty('grails.plugins.primefaces.customThemes')
        if (additionalThemes) {
            if (additionalThemes instanceof String) {
                String additionalThemesStr = (String) additionalThemes
                if (additionalThemesStr.indexOf(',') < 0) {
                    // this is a single string
                    log.info "Adding one custom theme:  '$additionalThemesStr'"
                    themeList.add((String)additionalThemesStr)
                }
                else {
                    // the themes were listed in a single, comma-separated string
                    log.info "Adding multiple custom theme (passed as single string):  '$additionalThemesStr'"
                    additionalThemesStr.split(',').each {curTheme ->
                        themeList.add(curTheme.trim())
                    }
                }
            }
            else if (additionalThemes instanceof Collection) {
                log.info "Adding multiple custom theme:  '$additionalThemes.toString() '"
                additionalThemes.each {curTheme ->
                    themeList.add(curTheme.toString())
                }
            }
            else {
                log.error("Invalid configuration for customThemes.")
            }
        }
        log.debug('No value for customThemes.  Use the default list')
    }

}
