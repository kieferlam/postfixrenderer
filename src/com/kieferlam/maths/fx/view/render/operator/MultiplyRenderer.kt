package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.maths.fx.view.render.MathsRenderException
import com.kieferlam.maths.fx.view.render.Size
import com.kieferlam.maths.fx.view.render.measureString
import com.kieferlam.postfix.syntax.Operator
import javafx.geometry.VPos
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.paint.Color

class MultiplyRenderer : OperatorRenderer(Operator.TIMES) {

    override fun render(operator: Operator?, vararg operands: Image): Image {
        if(operands.size < Operator.TIMES.numberOfParameters) throw MathsRenderException("Not enough parameters.")
        val canvas = Canvas()
        val g = canvas.graphicsContext2D
        val snapshot = SnapshotParameters()
        snapshot.fill = Color.TRANSPARENT
//        snapshot.fill = Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())

        val operatorSize = measureString("\u00D7", FONT)

        val operandSize = Size(operands.sumBy { it.width.toInt() }.toDouble(), operands.maxBy { it.height.toInt() }!!.height)

        canvas.width = operatorSize.width + operandSize.width
        canvas.height = operandSize.height

        var drawX = 0.0

        with(g) {
            font = OperatorRenderer.FONT
            fill = Color.BLACK
            textBaseline = VPos.TOP
            drawImage(operands[0], drawX, (canvas.height - operands[0].height)*0.5)
            drawX += operands[0].width
            fillText("\u00D7", drawX, (canvas.height - operatorSize.height) * 0.5)
            drawX += operatorSize.width
            drawImage(operands[1], drawX, (canvas.height - operands[1].height)*0.5)
        }

        return canvas.snapshot(snapshot, null)
    }

}