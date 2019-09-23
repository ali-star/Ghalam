package ir.siriusapps.ghalam.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.TextContent
import ir.siriusapps.ghalam.databinding.NoteItemTitleTextBinding
import ir.siriusapps.ghalam.note.NoteContentsAdapter

class NotesAdapter(private val viewModel: NotesViewModel) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var items: MutableList<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return when (viewType) {
            NoteContentsAdapter.VT_TEXT_CONTENT -> TextContentViewHolder.from(parent)
            else -> TextContentViewHolder.from(parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(viewModel, items[position])
    }

    fun setItems(items: MutableList<Note>) {
        this.items = items
        notifyDataSetChanged()
    }

    abstract class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(viewModel: NotesViewModel, item: Note)
    }

    class TextContentViewHolder(private val binding: NoteItemTitleTextBinding) : NoteViewHolder(binding.root) {

        override fun bind(viewModel: NotesViewModel, item: Note) {
            binding.viewmodel = viewModel
            binding.title = item.title
            if (item.contentList.isNotEmpty()) {
                val content = item.contentList[0]
                if (content is TextContent)
                    binding.contentText = content.text
            }
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup) : TextContentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemTitleTextBinding.inflate(layoutInflater, parent, false)
                return TextContentViewHolder(binding)
            }
        }

    }

}