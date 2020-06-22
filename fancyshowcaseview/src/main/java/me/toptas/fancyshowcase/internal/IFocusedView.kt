package me.toptas.fancyshowcase.internal

import android.view.View
import me.toptas.fancyshowcase.ext.globalLayoutListener

internal interface IFocusedView {
    fun width(): Int
    fun height(): Int
    fun getLocationInWindow(viewPoint: IntArray): IntArray
    fun waitForLayout(onLayout: () -> Unit)

    fun cantFocus() = width() == 0 && height() == 0

    fun id(): Int
}

internal class FocusedView(private val view: View) : IFocusedView {

    override fun width() = view.width

    override fun height() = view.height

    override fun getLocationInWindow(viewPoint: IntArray): IntArray {
        view.getLocationInWindow(viewPoint)
        return viewPoint
    }

    override fun waitForLayout(onLayout: () -> Unit) {
        view.globalLayoutListener { onLayout() }
    }

    override fun id(): Int = view.id

    fun view() = view

}

internal data class CircleCenter(var x: Int, var y: Int)

internal data class AutoTextPosition(var bottomMargin: Int = 0, var topMargin: Int = 0, var height: Int = 0)