package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.maths.fx.view.render.MathsRenderException
import com.kieferlam.maths.fx.view.render.Size
import com.kieferlam.maths.fx.view.render.measureString
import com.kieferlam.postfix.syntax.Operator
import com.kieferlam.postfix.syntax.operator.function.FunctionOperator
import javafx.geometry.VPos
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.paint.Color

class DefaultFunctionRenderer : OperatorRenderer(FunctionOperator.ABSOLUTE) {

    override fun render(operator: Operator?, vararg operands: Image): Image {
        if (operator == null) throw MathsRenderException("Operator is null.")
        if(operands.size < operator.numberOfParameters) throw MathsRenderException("Not enough parameters.")
        val canvas = Canvas()
        val g = canvas.graphicsContext2D
        val snapshot = SnapshotParameters()
        snapshot.fill = Color.TRANSPARENT
//        snapshot.fill = Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())

        val operatorSize = measureString(operator.symbol + "()", FONT)

        val operandSize = Size(operands.sumBy { it.width.toInt() }.toDouble() , operands.maxBy { it.height.toInt() }!!.height)

        canvas.width = operatorSize.width + operandSize.width
        canvas.height = operandSize.height

        var operandIndex = 0
        var drawX = 0.0

        with(g) {
            font = OperatorRenderer.FONT
            fill = Color.BLACK
            textBaseline = VPos.TOP
            if(operator.numberOfParameters > 1){
                drawImage(operands[operandIndex], drawX, (canvas.height - operands[operandIndex].height)*0.5)
                drawX += operands[operandIndex].width
                operandIndex++
            }
            fillText(operator.symbol, drawX, ((canvas.height - operatorSize.height) * 0.5) - 15.0)
            drawX += operatorSize.width
            drawImage(operands[operandIndex], drawX, (canvas.height - operands[operandIndex].height)*0.5)
            operandIndex++
        }

        return canvas.snapshot(snapshot, null)
    }

}