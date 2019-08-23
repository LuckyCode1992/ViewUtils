

package com.justcode.hxl.viewutil.recycleview_util.core




internal class ClassLinkerBridge<T> private constructor(
  private val javaClassLinker: JavaClassLinker<T>,
  private val binders: Array<ItemViewBinder<T, *>>
) : Linker<T> {

  override fun index(position: Int, item: T): Int {
    val indexedClass = javaClassLinker.index(position, item)
    val index = binders.indexOfFirst { it.javaClass == indexedClass }
    if (index != -1) return index
    throw IndexOutOfBoundsException(
      "The binders'(${binders.contentToString()}) you registered do not contain this ${indexedClass.name}."
    )
  }

  companion object {
    fun <T> toLinker(
      javaClassLinker: JavaClassLinker<T>,
      binders: Array<ItemViewBinder<T, *>>
    ): Linker<T> {
      return ClassLinkerBridge(javaClassLinker, binders)
    }
  }
}
