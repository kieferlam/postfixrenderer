package com.kieferlam.maths.fx.view.maths

import com.kieferlam.maths.fx.view.render.MathsRenderer
import com.kieferlam.maths.fx.view.render.measureString
import com.kieferlam.maths.fx.view.render.operator.OperatorRenderer
import com.kieferlam.postfix.exception.InvalidSyntaxException
import com.kieferlam.postfix.syntax.ExpressionEntity
import com.kieferlam.postfix.syntax.Postfix
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import tornadofx.add
import java.util.*

class MathsInputPane : Pane() {

    private val canvas = Canvas()
    private val context by lazy { canvas.graphicsContext2D }

    private var inputString = ""
    private var postfix = ArrayList<ExpressionEntity>()
    private var resultString = "NaN"
    private var mathsRender = Stack<Image>()

    private var isDrawing = false
    private val drawThread = Thread({
        isDrawing = true
        while (isDrawing) {
            if (!this.scene.window.isShowing) isDrawing = false
            draw()
            Thread.sleep(1000L)
        }
    })

    init {
        canvas.widthProperty().bind(widthProperty())
        canvas.heightProperty().bind(heightProperty())
        canvas.widthProperty().addListener { _, _, _ ->
            draw()
        }
        canvas.heightProperty().addListener { _, _, _ ->
            draw()
        }

        add(canvas)
        onMousePressed = EventHandler {
            this.requestFocus()
        }

        onKeyTyped = EventHandler {
            when (it.character) {
                "\b" -> {
                    if (inputString.isNotEmpty()) inputString = inputString.substring(0, inputString.length - 1)
                }
                "\u000D" -> {
                }
                else -> inputString += it.character
            }

            try {
                postfix = Postfix.from(inputString)
                resultString = Postfix.evaluate(postfix).value.toDouble().toString()
                Platform.runLater {
                    mathsRender = MathsRenderer.render(postfix)
                }
            } catch (syntax: InvalidSyntaxException) {
                "Syntax Error"
            } catch (e: Exception) {
                e.message!!
            }

            draw()
        }

        Platform.runLater {
            drawThread.start()
        }
    }

    fun draw() {
        with(context) {
            clearRect(0.0, 0.0, width, height)

            fill = Color.BLACK
            font = OperatorRenderer.FONT

            val displayString = inputString + " = " + resultString
            val textSize = measureString(displayString, font)
            val xScale = Math.min(width / textSize.width, 1.0)
            val yScale = Math.min(height / (textSize.height + (mathsRender.maxBy { it.height }?.height ?: 0.0)), 1.0)
            val scale = Math.min(xScale, yScale)

            save()
            translate(width * 0.5, height * 0.5)
            scale(scale * 0.8, scale * 0.8)

            fillText(displayString, -textSize.width * 0.5, -textSize.height)

            fill = Color.LIGHTGRAY
            strokeLine(-canvas.width, -100.0, canvas.width, -100.0)
            fill = Color.BLACK
            
            var x = 0.0
            mathsRender.forEach {
                drawImage(it, x - 500, -(it.height * 0.5) + 200.0)
                x += it.width
            }

            restore()

        }
    }

}