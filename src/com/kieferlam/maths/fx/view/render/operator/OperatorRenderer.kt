package com.kieferlam.maths.fx.view.render.operator

import com.kieferlam.postfix.syntax.Operand
import com.kieferlam.postfix.syntax.Operator
import com.kieferlam.postfix.syntax.operator.function.FunctionOperator
import javafx.scene.SnapshotParameters
import javafx.scene.image.Image
import javafx.scene.text.Font
import java.io.File
import java.io.FileInputStream

abstract class OperatorRenderer(private val operator: Operator) {

    companion object {
        val RENDER_WIDTH = 1280.0
        val RENDER_HEIGHT = 720.0
        val FONT: Font by lazy { Font.loadFont(FileInputStream(File("./cmunrm.ttf")), 192.0) }

        private val RENDERERS = arrayOf(
                DefaultRenderer(),
                MinusRenderer(),
                MultiplyRenderer(),
                FractionRenderer(),
                PowerRenderer(),
                SquareRootRenderer()
        )

        fun getRenderer(op: Operator): OperatorRenderer {
            return (1 until RENDERERS.size)
                    .firstOrNull { RENDERERS[it].isOperatorRenderer(op) }
                    ?.let { RENDERERS[it] }
                    ?: RENDERERS[0]
        }

    }

    fun isOperatorRenderer(op: Operator): Boolean = op == operator

    abstract fun render(operator: Operator? = null, vararg operands: Image): Image

}