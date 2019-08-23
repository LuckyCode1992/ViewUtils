

package com.justcode.hxl.viewutil.recycleview_util.core

import android.support.annotation.CheckResult


interface OneToManyFlow<T> {

  /**
   * Sets some item view binders to the item type.
   *
   * @param binders the item view binders
   * @return end flow operator
   */
  @CheckResult
  fun to(vararg binders: ItemViewBinder<T, *>): OneToManyEndpoint<T>
}
