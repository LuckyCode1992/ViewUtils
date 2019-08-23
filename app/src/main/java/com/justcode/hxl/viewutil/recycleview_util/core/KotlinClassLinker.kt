
package com.justcode.hxl.viewutil.recycleview_util.core

import kotlin.reflect.KClass


interface KotlinClassLinker<T> {

  /**
   * Returns the class of your registered binders for your item.
   *
   * @param position The position in items
   * @param item The item
   * @return The index of your registered binders
   * @see OneToManyEndpoint.withJavaClassLinker
   */
  fun index(position: Int, item: T): KClass<out ItemViewBinder<T, *>>
}
