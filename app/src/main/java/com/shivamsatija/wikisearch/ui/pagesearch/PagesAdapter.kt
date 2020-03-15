package com.shivamsatija.wikisearch.ui.pagesearch

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.shivamsatija.wikisearch.R
import com.shivamsatija.wikisearch.data.model.Page
import org.w3c.dom.Text

class PagesAdapter : RecyclerView.Adapter<PagesAdapter.PageViewHolder>() {

    var data: ArrayList<Page> = arrayListOf()

    private var listener: ItemInteractionListener? = null

    fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }

    fun addData(data: List<Page>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addItemInteractionListener(interactionListener: ItemInteractionListener) {
        this.listener = interactionListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder =
        PageViewHolder(
            LayoutInflater.from(parent.context)
//                .inflate(android.R.layout.simple_list_item_1, parent, false),
                .inflate(R.layout.layout_item_search_result, parent, false),
            listener
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class PageViewHolder(
        itemView: View,
        private var listener: ItemInteractionListener?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(page: Page) {
            itemView.apply {
                findViewById<ImageView>(R.id.ivPageThumbnail).apply {
                    Glide.with(itemView)
                        .load(page.thumbnail?.source)
                        .into(this)
                }
                findViewById<TextView>(R.id.tvPageTitle).apply {
                    text = page.title
                }
                findViewById<TextView>(R.id.tvPageDescription).apply {
                    if (page.terms?.description?.size ?: 0 > 0) {
                        text = page.terms?.description!![0]
                    }
                }
                setOnClickListener {
                    page.fullurl?.let { url ->
                        listener?.onItemClick(url)
                    }
                }
            }
        }
    }

    interface ItemInteractionListener {

        fun onItemClick(url: String)
    }
}