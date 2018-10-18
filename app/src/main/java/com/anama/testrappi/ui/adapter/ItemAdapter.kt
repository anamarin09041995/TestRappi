package com.anama.testrappi.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.anama.testrappi.R
import com.anama.testrappi.data.model.Item
import com.anama.testrappi.databinding.TemplateItemBinding
import com.anama.testrappi.util.inflate
import io.reactivex.subjects.PublishSubject

class ItemAdapter<T: Item> : RecyclerView.Adapter<ItemAdapter.ItemHolder>(){

    val clickItem = PublishSubject.create<T>()
    val scrollEnd = PublishSubject.create<Unit>()

    var items: MutableList<T> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding!!.item = items[position]
        holder.binding.clickItem= clickItem
        if(position == items.size - 1 ) scrollEnd.onNext(Unit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder
            = ItemHolder(parent.inflate(R.layout.template_item))

    override fun getItemCount(): Int = items.size

    fun addItems(items:List<T>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: TemplateItemBinding? = DataBindingUtil.bind(itemView)
    }
}