

package com.prominic.primeface

import grails.gorm.transactions.Transactional

@Transactional
class ThemeService {

    public String theme="afterwork"

    def getTheme() {

        return this.theme

    }

    def setTheme(String themeName){
        this.theme=themeName
    }
}
