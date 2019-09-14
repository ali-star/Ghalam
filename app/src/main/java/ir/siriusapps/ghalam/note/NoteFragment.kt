package ir.siriusapps.ghalam.note

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import ir.siriusapps.ghalam.R

class NoteFragment : Fragment(R.layout.note_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            sharedElementEnterTransition = transition
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}