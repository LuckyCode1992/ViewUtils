package com.justcode.hxl.viewutil.recycleview_util.core

import android.support.annotation.CheckResult
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup


import java.util.concurrent.CopyOnWriteArrayList
import kotlin.reflect.KClass


open class MultiTypeAdapter @JvmOverloads constructor(
        /**
         * Sets and updates the items atomically and safely. It is recommended to use this method
         * to update the items with a new wrapper list or consider using [CopyOnWriteArrayList].
         *
         * Note: If you want to refresh the list views after setting items, you should
         * call [RecyclerView.Adapter.notifyDataSetChanged] by yourself.
         *
         * @since v2.4.1
         */
        open var items: List<Any> = emptyList(),
        open val initialCapacity: Int = 0,
        open var types: Types = MutableTypes(initialCapacity)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Registers a type class and its item view binder. If you have registered the class,
     * it will override the original binder(s). Note that the method is non-thread-safe
     * so that you should not use it in concurrent operation.
     *
     * Note that the method should not be called after
     * [RecyclerView.setAdapter], or you have to call the setAdapter
     * again.
     *
     * @param clazz the class of a item
     * @param binder the item view binder
     * @param T the item data type
     * */
    fun <T> register(clazz: Class<T>, binder: ItemViewBinder<T, *>) {
        unregisterAllTypesIfNeeded(clazz)
        register(Type(clazz, binder, DefaultLinker()))
    }

    inline fun <reified T : Any> register(binder: ItemViewBinder<T, *>) {
        register(T::class.java, binder)
    }

    fun <T : Any> register(clazz: KClass<T>, binder: ItemViewBinder<T, *>) {
        register(clazz.java, binder)
    }

    internal fun <T> register(type: Type<T>) {
        types.register(type)
        type.binder._adapter = this
    }

    /**
     * Registers a type class to multiple item view binders. If you have registered the
     * class, it will override the original binder(s). Note that the method is non-thread-safe
     * so that you should not use it in concurrent operation.
     *
     * Note that the method should not be called after
     * [RecyclerView.setAdapter], or you have to call the setAdapter again.
     *
     * @param clazz the class of a item
     * @param <T> the item data type
     * @return [OneToManyFlow] for setting the binders
     * @see [register]
     */
    @CheckResult
    fun <T> register(clazz: Class<T>): OneToManyFlow<T> {
        unregisterAllTypesIfNeeded(clazz)
        return OneToManyBuilder(this, clazz)
    }

    @CheckResult
    fun <T : Any> register(clazz: KClass<T>): OneToManyFlow<T> {
        return register(clazz.java)
    }

    /**
     * Registers all of the contents in the specified [Types]. If you have registered a
     * class, it will override the original binder(s). Note that the method is non-thread-safe
     * so that you should not use it in concurrent operation.
     *
     * Note that the method should not be called after
     * [RecyclerView.setAdapter], or you have to call the setAdapter
     * again.
     *
     * @param types a [Types] containing contents to be added to this adapter inner [Types]
     * @see [register]
     * @see [register]
     */
    fun registerAll(types: Types) {
        val size = types.size
        for (i in 0 until size) {
            val type = types.getType<Any>(i)
            unregisterAllTypesIfNeeded(type.clazz)
            register(type)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return indexInTypesOf(position, items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binder = types.getType<Any>(indexViewType).binder
        return binder.onCreateViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, position, emptyList())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = items[position]
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    override fun getItemCount(): Int = items.size

    /**
     * Called to return the stable ID for the item, and passes the event to its associated binder.
     *
     * @param position Adapter position to query
     * @return the stable ID of the item at position
     * @see ItemViewBinder.getItemId
     * @see RecyclerView.Adapter.setHasStableIds
     * @since v3.2.0
     */
    override fun getItemId(position: Int): Long {
        val item = items[position]
        val itemViewType = getItemViewType(position)
        return types.getType<Any>(itemViewType).binder.getItemId(item)
    }

    /**
     * Called when a view created by this adapter has been recycled, and passes the event to its
     * associated binder.
     *
     * @param holder The ViewHolder for the view being recycled
     * @see RecyclerView.Adapter.onViewRecycled
     * @see ItemViewBinder.onViewRecycled
     */
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        getOutBinderByViewHolder(holder).onViewRecycled(holder)
    }

    /**
     * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
     * due to its transient state, and passes the event to its associated item view binder.
     *
     * @param holder The ViewHolder containing the View that could not be recycled due to its
     * transient state.
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns `true`, RecyclerView *will ignore* the transient state of
     * the View and recycle it regardless. If this method returns `false`,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     * @see RecyclerView.Adapter.onFailedToRecycleView
     * @see ItemViewBinder.onFailedToRecycleView
     */
    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return getOutBinderByViewHolder(holder).onFailedToRecycleView(holder)
    }

    /**
     * Called when a view created by this adapter has been attached to a window, and passes the
     * event to its associated item view binder.
     *
     * @param holder Holder of the view being attached
     * @see RecyclerView.Adapter.onViewAttachedToWindow
     * @see ItemViewBinder.onViewAttachedToWindow
     */
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        getOutBinderByViewHolder(holder).onViewAttachedToWindow(holder)
    }

    /**
     * Called when a view created by this adapter has been detached from its window, and passes
     * the event to its associated item view binder.
     *
     * @param holder Holder of the view being detached
     * @see RecyclerView.Adapter.onViewDetachedFromWindow
     * @see ItemViewBinder.onViewDetachedFromWindow
     */
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        getOutBinderByViewHolder(holder).onViewDetachedFromWindow(holder)
    }

    private fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        @Suppress("UNCHECKED_CAST")
        return types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    @Throws(BinderNotFoundException::class)
    internal fun indexInTypesOf(position: Int, item: Any): Int {
        val index = types.firstIndexOf(item.javaClass)
        if (index != -1) {
            val linker = types.getType<Any>(index).linker
            return index + linker.index(position, item)
        }
        throw BinderNotFoundException(item.javaClass)
    }

    private fun unregisterAllTypesIfNeeded(clazz: Class<*>) {
        if (types.unregister(clazz)) {
            Log.w(TAG, "The type ${clazz.simpleName} you originally registered is now overwritten.")
        }
    }

    companion object {
        private const val TAG = "MultiTypeAdapter"
    }

    /**
     * 更新列表
     */
    fun update(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()

    }

}
