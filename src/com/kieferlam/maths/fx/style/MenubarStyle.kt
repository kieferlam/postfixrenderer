package com.kieferlam.maths.fx.style

import tornadofx.*

class MenubarStyle : Stylesheet(){
    init{
        menuBar{
            padding = box(0.0.px)
        }
        menu{
            padding = box(5.0.px, 8.0.px)
        }
        menuItem{
            padding = box(100.0.px)
        }
    }
}