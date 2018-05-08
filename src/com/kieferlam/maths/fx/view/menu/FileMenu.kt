package com.kieferlam.maths.fx.view.menu

import javafx.scene.control.Menu
import javafx.scene.control.MenuItem

class FileMenu : Menu("File") {
    init {
        items.addAll(
                New(),
                Open()
        )
    }
}

private class New : MenuItem("New") {
    init{
        setOnAction {

        }
    }
}

private class Open : MenuItem("Open") {
    init{
        setOnAction {

        }
    }
}