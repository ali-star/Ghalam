package ir.siriusapps.ghalam.ui.notes

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.android.support.DaggerFragment
import ir.siriusapps.ghalam.EventObserver
import ir.siriusapps.ghalam.R
import ir.siriusapps.ghalam.databinding.NotesFragmentBinding
import ir.siriusapps.sview.Utils
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

        adapter = NotesAdapter(viewModel)

        viewDataBinding = NotesFragmentBinding.bind(view).apply {
            lifecycleOwner = this@NotesFragment.viewLifecycleOwner
            viewmodel = viewModel

            notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            notesRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {

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
                    outRect.bottom = space
                }

            })

            // Setting top padding of the notesRecyclerView
            listTitleTextView.viewTreeObserver.addOnGlobalLayoutListener (object : ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    listTitleTextView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    notesRecyclerView.setPadding(
                        notesRecyclerView.paddingLeft,
                        Utils.dipToPix(88) + listTitleTextView.height,
                        notesRecyclerView.paddingRight,
                        notesRecyclerView.paddingBottom
                    )
                }
            })

            notesRecyclerView.adapter = adapter
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.newNoteEvent.observe(viewLifecycleOwner, EventObserver {
            val extras = FragmentNavigatorExtras(viewDataBinding.newNoteLayout to "tr1")
            findNavController().navigate(
                R.id.action_notesFragment_to_noteFragment,
                null,
                null,
                extras)
        })

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.openNoteEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val destination =
                    NotesFragmentDirections.actionNotesFragmentToNoteFragment(
                        it
                    )
                val extras = FragmentNavigatorExtras(viewDataBinding.newNoteLayout to "tr1")
                findNavController().navigate(destination, extras)
            }
        }
    }

}