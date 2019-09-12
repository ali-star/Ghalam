package ir.siriusapps.ghalam.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ir.siriusapps.ghalam.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Handler().postDelayed({
            Navigation.findNavController(view!!).navigate(R.id.action_splashFragment_to_notesFragment)
        }, 1500)

    }

}