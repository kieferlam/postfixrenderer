package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.maths.fx.view.render.MathsRenderException
import com.kieferlam.maths.fx.view.render.Size
import com.kieferlam.postfix.syntax.Operator
import com.kieferlam.postfix.syntax.operator.function.FunctionOperator
import javafx.geometry.VPos
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin

class SquareRootRenderer : OperatorRenderer(FunctionOperator.SQRT){

    companion object {
        val LINE_WIDTH = 5.0
    }

    override fun render(operator: Operator?, vararg operands: Image): Image {
        if(operands.size < FunctionOperator.SQRT.numberOfParameters) throw MathsRenderException("Not enough parameters.")
        val canvas = Canvas()
        val g = canvas.graphicsContext2D
        val snapshot = SnapshotParameters()
        snapshot.fill = Color.TRANSPARENT
//        snapshot.fill = Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())

        val operandSize = Size(operands[0].width + 50.0, operands[0].height + 20.0)

        canvas.width = operandSize.width
        canvas.height = operandSize.height

        with(g) {
            font = OperatorRenderer.FONT
            fill = Color.BLACK
            textBaseline = VPos.TOP
            lineWidth = LINE_WIDTH
            lineCap = StrokeLineCap.ROUND
            lineJoin = StrokeLineJoin.MITER

            beginPath()
            moveTo(0.0, canvas.height - 50.0)
            lineTo(14.0, canvas.height - 60.0)
            lineTo(30.0, canvas.height - 4.0)
            lineTo(50.0, 4.0)
            lineTo(canvas.width, 4.0)
            stroke()

            drawImage(operands[0], 50.0, 8.0)
        }

        return canvas.snapshot(snapshot, null)
    }

}