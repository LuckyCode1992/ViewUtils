
package com.justcode.hxl.viewutil.recycleview_util.core


interface Linker<T> {

  /**
   * Returns the index of your registered binders for your item. The result should be in range of
   * `[0, one-to-multiple-binders.length)`.
   *
   * Note: The argument of [OneToManyFlow.to] is the
   * one-to-multiple-binders.
   *
   * @param position The position in items
   * @param item The data item
   * @return The index of your registered binders
   * @see OneToManyFlow.to
   * @see OneToManyEndpoint.withLinker
   */
  @android.support.annotation.IntRange(from = 0)
  fun index(position: Int, item: T): Int
}
