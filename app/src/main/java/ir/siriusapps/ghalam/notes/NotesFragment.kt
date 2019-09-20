package ir.siriusapps.ghalam.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import ir.siriusapps.ghalam.EventObserver
import ir.siriusapps.ghalam.R
import ir.siriusapps.ghalam.databinding.NotesFragmentBinding
import kotlinx.android.synthetic.main.notes_fragment.*
import javax.inject.Inject

class NotesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NotesViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: NotesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_fragment, container, false)
        viewDataBinding = NotesFragmentBinding.bind(view).apply {
            viewmodel = viewModel
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.newNoteEvent.observe(this, EventObserver {
            val extras = FragmentNavigatorExtras(newNoteLayout to "tr1")
            findNavController().navigate(
                R.id.action_notesFragment_to_noteFragment,
                null,
                null,
                extras)
        })
    }

}