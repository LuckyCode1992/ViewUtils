

package com.justcode.hxl.viewutil.recycleview_util.core


data class Type<T>(
  val clazz: Class<out T>,
  val binder: ItemViewBinder<T, *>,
  val linker: Linker<T>
)
