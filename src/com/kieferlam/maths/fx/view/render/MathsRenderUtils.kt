package com.kieferlam.maths.fx.view.render

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.scene.text.Text
import tornadofx.*

fun measureString(string: String, font: Font): Size {
    var text = Text(string)
    text.font = font
    Scene(Group(text))
    return Size(text.layoutBounds.width, text.layoutBounds.height)
}

data class Size(val width: Double, val height: Double)