package com.kieferlam.maths.fx.view

import com.kieferlam.maths.fx.view.maths.MathsInputPane
import com.kieferlam.maths.fx.view.menu.GlobalMenuBar
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import tornadofx.*

class RootView : View(){
    override val root = BorderPane()
    
    init{
        root.top = GlobalMenuBar()
        root.center = MathsInputPane()
    }

}