package com.kieferlam.maths.fx.view.menu

import com.kieferlam.maths.fx.style.MenubarStyle
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import tornadofx.*

class GlobalMenuBar : MenuBar(){
    init{
        menus.addAll(
                FileMenu()
        )
    }

    override fun getUserAgentStylesheet(): String = MenubarStyle().base64URL.toExternalForm()
}