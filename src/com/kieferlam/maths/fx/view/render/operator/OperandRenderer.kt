package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.maths.fx.view.maths.MathsInputPane
import com.kieferlam.maths.fx.view.render.measureString
import com.kieferlam.postfix.syntax.Operand
import javafx.geometry.VPos
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color

object OperandRenderer {

    fun render(op: Operand): Image {
        val canvas = Canvas()
        val g = canvas.graphicsContext2D
        g.font = OperatorRenderer.FONT
        val snapshot = SnapshotParameters()
        snapshot.fill = Color.TRANSPARENT

        var text = op.value.toDouble().toString()
        if (op.value.toDouble() == op.value.toInt().toDouble()) {
            text = op.value.toInt().toString()
        }
        val size = measureString(text, OperatorRenderer.FONT)

        canvas.width = size.width
        canvas.height = size.height

        with(g) {
            fill = Color.BLACK
            textBaseline = VPos.TOP
            fillText(text, 0.0, 0.0)
        }

        return canvas.snapshot(snapshot, null)
    }

}