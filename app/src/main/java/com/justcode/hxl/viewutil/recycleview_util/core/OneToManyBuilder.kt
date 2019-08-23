

package com.justcode.hxl.viewutil.recycleview_util.core

import android.support.annotation.CheckResult


internal class OneToManyBuilder<T>(
  private val adapter: MultiTypeAdapter,
  private val clazz: Class<T>
) : OneToManyFlow<T>, OneToManyEndpoint<T> {

  private var binders: Array<ItemViewBinder<T, *>>? = null

  @SafeVarargs
  @CheckResult(suggest = "#withLinker(Linker)")
  override fun to(vararg binders: ItemViewBinder<T, *>) = apply {
    @Suppress("UNCHECKED_CAST")
    this.binders = binders as Array<ItemViewBinder<T, *>>
  }

  override fun withLinker(linker: Linker<T>) {
    doRegister(linker)
  }

  override fun withJavaClassLinker(javaClassLinker: JavaClassLinker<T>) {
    withLinker(ClassLinkerBridge.toLinker(javaClassLinker, binders!!))
  }

  private fun doRegister(linker: Linker<T>) {
    for (binder in binders!!) {
      adapter.register(Type(clazz, binder, linker))
    }
  }
}
