package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.maths.fx.view.render.MathsRenderException
import com.kieferlam.maths.fx.view.render.Size
import com.kieferlam.maths.fx.view.render.measureString
import com.kieferlam.postfix.syntax.Operator
import com.kieferlam.postfix.syntax.operator.DivideOperator
import javafx.geometry.VPos
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeLineCap

class FractionRenderer : OperatorRenderer(Operator.DIVIDE){
    
    companion object {
        val LINE_WIDTH = 5.0
    }
    
    override fun render(operator: Operator?, vararg operands: Image): Image {
        if(operands.size < Operator.DIVIDE.numberOfParameters) throw MathsRenderException("Not enough parameters.")
        val canvas = Canvas()
        val g = canvas.graphicsContext2D
        val snapshot = SnapshotParameters()
        snapshot.fill = Color.TRANSPARENT
//        snapshot.fill = Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt())

        val operandSize = Size(operands.maxBy { it.width.toInt() }!!.width, operands.sumByDouble { it.height })

        canvas.width = operandSize.width
        canvas.height = operandSize.height
        
        with(g) {
            font = OperatorRenderer.FONT
            fill = Color.BLACK
            textBaseline = VPos.TOP
            
            var y = 0.0
            
            //Top operand
            drawImage(operands[0], (canvas.width - operands[0].width)*0.5, y)
            y += operands[0].height + 4.0
            
            //Fraction Line
            lineWidth = LINE_WIDTH
            lineCap = StrokeLineCap.ROUND
            beginPath()
            moveTo(0.0, y)
            lineTo(canvas.width, y)
            stroke()
            
            y += 4.0
            
            //Bottom operand
            drawImage(operands[1], (canvas.width - operands[1].width)*0.5, y)
            
        }

        return canvas.snapshot(snapshot, null)
    }

}