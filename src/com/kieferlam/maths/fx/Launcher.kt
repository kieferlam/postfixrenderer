package com.kieferlam.maths.fx

import com.kieferlam.maths.fx.style.MenubarStyle
import com.kieferlam.maths.fx.view.RootView
import javafx.stage.Stage
import tornadofx.*

class Launcher : App(RootView::class){
    
    init{
        reloadStylesheetsOnFocus()
    }
    
    override fun start(stage: Stage) {
        stage.width = 1280.0
        stage.height = 720.0
        stage.title = "Maths"
        super.start(stage)
    }
}