package ir.siriusapps.ghalam.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.siriusapps.ghalam.databinding.NoteItemTitleTextBinding
import ir.siriusapps.ghalam.model.NoteItem
import ir.siriusapps.ghalam.model.TextContentItem
import ir.siriusapps.ghalam.ui.note.NoteContentsAdapter

class NotesAdapter(private val viewModel: NotesViewModel) :
    PagedListAdapter<NoteItem, NotesAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<NoteItem>() {
            override fun areItemsTheSame(oldConcert: NoteItem,
                                         newConcert: NoteItem) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: NoteItem,
                                            newConcert: NoteItem) = oldConcert.toString() == newConcert.toString()
        }
    }

    private var items: MutableList<NoteItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return when (viewType) {
            NoteContentsAdapter.VT_TEXT_CONTENT -> TextContentViewHolder.from(
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

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(viewModel, items[position])
    }

    fun setItems(items: MutableList<NoteItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    abstract class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(viewModel: NotesViewModel, item: NoteItem)
    }

    class TextContentViewHolder(private val binding: NoteItemTitleTextBinding) : NoteViewHolder(binding.root) {

        override fun bind(viewModel: NotesViewModel, item: NoteItem) {
            binding.viewmodel = viewModel
            binding.note = item
            if (item.contentList.isNotEmpty()) {
                val content = item.contentList[0]
                if (content is TextContentItem)
                    binding.contentText = content.text
            }
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup) : TextContentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemTitleTextBinding.inflate(layoutInflater, parent, false)
                return TextContentViewHolder(
                    binding
                )
            }
        }

    }

}