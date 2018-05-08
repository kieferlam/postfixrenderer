package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.maths.fx.view.render.MathsRenderException
import com.kieferlam.maths.fx.view.render.Size
import com.kieferlam.postfix.syntax.Operator
import javafx.geometry.VPos
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeLineCap
import javafx.scene.text.Font
import java.io.File
import java.io.FileInputStream

class PowerRenderer : OperatorRenderer(Operator.POWER) {

    override fun render(operator: Operator?, vararg operands: Image): Image {
        if (operands.size < Operator.DIVIDE.numberOfParameters) throw MathsRenderException("Not enough parameters.")
        val canvas = Canvas()
        val g = canvas.graphicsContext2D
        val snapshot = SnapshotParameters()
        snapshot.fill = Color.TRANSPARENT
//        snapshot.fill = Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())

        val operandSize = Size(operands[0].width + (operands[1].width * 0.5), operands[0].height + (operands[1].height * 0.25))

        canvas.width = operandSize.width
        canvas.height = operandSize.height

        with(g) {
            font = OperatorRenderer.FONT
            fill = Color.BLACK
            textBaseline = VPos.TOP

            //Left operand
            drawImage(operands[0], 0.0, operands[1].height*0.25)

            //Super operand
            drawImage(operands[1], operands[0].width, 0.0, operands[1].width*0.5, operands[1].height*0.5)

        }

        return canvas.snapshot(snapshot, null)
    }

}
