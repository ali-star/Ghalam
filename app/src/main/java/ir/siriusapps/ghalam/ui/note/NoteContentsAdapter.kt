package ir.siriusapps.ghalam.ui.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.siriusapps.ghalam.databinding.NoteTextContentBinding
import ir.siriusapps.ghalam.model.ContentItem
import ir.siriusapps.ghalam.model.TextContentItem
import ir.sitiusapps.ghalam.domain.model.ContentType

class NoteContentsAdapter(private val noteViewModel: NoteViewModel) : RecyclerView.Adapter<NoteContentsAdapter.ContentViewHolder>() {

    private var items: MutableList<ContentItem> = ArrayList()

    companion object {
        public const val VT_TEXT_CONTENT = 1
        public const val VT_FILE_CONTENT_PHOTO = 2
        public const val VT_FILE_CONTENT_RECORDING = 3
        public const val VT_FILE_CONTENT_MUSIC = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return when (viewType) {
            VT_TEXT_CONTENT -> TextContentViewHolder.from(
                parent
            )

            else -> TextContentViewHolder.from(
                parent
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].contentType) {
            ContentType.TEXT -> return VT_TEXT_CONTENT
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val item = items[position]
        holder.bind(noteViewModel, item)
    }

    fun setItems(items: MutableList<ContentItem>?) {
        items?.let {
            this.items = items
            notifyDataSetChanged()
        }
    }

    abstract class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(viewModel: NoteViewModel, item: ContentItem)
    }

    class TextContentViewHolder(private val binding: NoteTextContentBinding) : ContentViewHolder(binding.root) {

        override fun bind(viewModel: NoteViewModel, item: ContentItem) {
            binding.viewmodel = viewModel
            binding.textContent = item as TextContentItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(viewGroup: ViewGroup): ContentViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = NoteTextContentBinding.inflate(inflater, viewGroup, false)
                return TextContentViewHolder(
                    binding
                )
            }
        }

    }

}