package ir.siriusapps.ghalam.ui.note

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import ir.siriusapps.ghalam.R
import ir.siriusapps.ghalam.databinding.NoteFragmentBinding
import kotlinx.android.synthetic.main.note_fragment.*
import kotlinx.android.synthetic.main.note_fragment.view.*
import javax.inject.Inject

class NoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NoteViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: NoteFragmentBinding

    private lateinit var adapter: NoteContentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.note_fragment, container, false)

        viewDataBinding = NoteFragmentBinding.bind(view).apply {
            viewmodel = viewModel
        }

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        view.noteContentsRecyclerView.layoutManager = LinearLayoutManager(context)
        view.noteContentsRecyclerView.setHasFixedSize(false)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            sharedElementEnterTransition = transition
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args =
            NoteFragmentArgs.fromBundle(
                requireArguments()
            )
        viewModel.start(args.noteLocalId)

        adapter = NoteContentsAdapter(viewModel)
        noteContentsRecyclerView.adapter = adapter

        viewModel.noteLiveData.observe(viewLifecycleOwner) {
            adapter.setItems(it.contentList)
        }

        micButton.setOnClickListener {
            viewModel.saveNote()
        }
    }

}