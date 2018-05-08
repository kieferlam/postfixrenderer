package com.kieferlam.maths.fx.view.render

import com.kieferlam.maths.fx.view.render.operator.OperandRenderer
import com.kieferlam.maths.fx.view.render.operator.OperatorRenderer
import com.kieferlam.postfix.syntax.ExpressionEntity
import com.kieferlam.postfix.syntax.Operand
import com.kieferlam.postfix.syntax.Operator
import com.kieferlam.postfix.syntax.operator.function.FunctionOperator
import javafx.scene.SnapshotParameters
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import java.util.*
import kotlin.collections.ArrayList

object MathsRenderer {

    fun render(expression: ArrayList<ExpressionEntity>): Stack<Image> {
        var stack = Stack<Image>()
        for (e in expression) {
            if (e.isOperator) {
                val numOfOperands = (e as Operator).numberOfParameters
                if (stack.size < numOfOperands) throw MathsRenderException("Not enough operands (operator requires $numOfOperands operands).")
                val operandImgs = ArrayList<Image>()
                for (i in 0 until numOfOperands) {
                    operandImgs.add(stack.pop())
                }
                stack.push(OperatorRenderer.getRenderer(e).render(e, *operandImgs.toTypedArray().reversedArray()))
            } else {
                stack.push(OperandRenderer.render(e as Operand))
            }
        }
        return stack
    }

}