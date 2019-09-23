package ir.siriusapps.ghalam.notes

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.android.support.DaggerFragment
import ir.siriusapps.ghalam.EventObserver
import ir.siriusapps.ghalam.R
import ir.siriusapps.ghalam.databinding.NotesFragmentBinding
import ir.siriusapps.sview.Utils
import kotlinx.android.synthetic.main.notes_fragment.*
import kotlinx.android.synthetic.main.notes_fragment.newNoteLayout
import kotlinx.android.synthetic.main.notes_fragment.view.*
import javax.inject.Inject

class NotesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NotesViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: NotesFragmentBinding

    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_fragment, container, false)
        viewDataBinding = NotesFragmentBinding.bind(view).apply {
            viewmodel = viewModel
        }

        view.notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        view.notesRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val space = Utils.dipToPix(8)
                outRect.left = space
                outRect.top = space
                outRect.right = space
                outRect.bottom =space
            }

        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = NotesAdapter(viewModel)

        notesRecyclerView.adapter = adapter

        viewModel.newNoteEvent.observe(this, EventObserver {
            val extras = FragmentNavigatorExtras(newNoteLayout to "tr1")
            findNavController().navigate(
                R.id.action_notesFragment_to_noteFragment,
                null,
                null,
                extras)
        })

        viewModel.notesLiveData.observe(this) {
            adapter.setItems(it)
        }

        viewModel.start()
    }

}