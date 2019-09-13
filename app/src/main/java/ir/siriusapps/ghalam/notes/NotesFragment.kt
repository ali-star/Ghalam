package ir.siriusapps.ghalam.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import ir.siriusapps.ghalam.EventObserver
import ir.siriusapps.ghalam.R
import ir.siriusapps.ghalam.databinding.NotesFragmentBinding
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
        viewDataBinding = NotesFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.newNoteEvent.observe(this, EventObserver {
            findNavController().navigate(R.id.action_notesFragment_to_noteFragment)
        })
    }

}