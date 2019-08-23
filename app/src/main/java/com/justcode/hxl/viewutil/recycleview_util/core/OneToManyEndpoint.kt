

package com.justcode.hxl.viewutil.recycleview_util.core

import kotlin.reflect.KClass


interface OneToManyEndpoint<T> {

  /**
   * Sets a linker to link the items and binders by array index.
   *
   * @param linker the row linker
   * @see Linker
   */
  fun withLinker(linker: Linker<T>)

  fun withLinker(linker: (position: Int, item: T) -> Int) {
    withLinker(object : Linker<T> {
      override fun index(position: Int, item: T): Int {
        return linker(position, item)
      }
    })
  }

  /**
   * Sets a class linker to link the items and binders by the class instance of binders.
   *
   * @param javaClassLinker the class linker
   * @see JavaClassLinker
   */
  fun withJavaClassLinker(javaClassLinker: JavaClassLinker<T>)

  private fun withJavaClassLinker(classLinker: (position: Int, item: T) -> Class<out ItemViewBinder<T, *>>) {
    withJavaClassLinker(object : JavaClassLinker<T> {
      override fun index(position: Int, item: T): Class<out ItemViewBinder<T, *>> {
        return classLinker(position, item)
      }
    })
  }

  fun withKotlinClassLinker(classLinker: KotlinClassLinker<T>) {
    withJavaClassLinker { position, item -> classLinker.index(position, item).java }
  }

  fun withKotlinClassLinker(classLinker: (position: Int, item: T) -> KClass<out ItemViewBinder<T, *>>) {
    withJavaClassLinker { position, item -> classLinker(position, item).java }
  }
}
