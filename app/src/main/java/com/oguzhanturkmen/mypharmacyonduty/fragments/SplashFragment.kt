package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentPharmacyBinding
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentSplashBinding
import kotlinx.android.synthetic.main.my_toolbar.*

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timer = object : CountDownTimer(2250,1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val action = SplashFragmentDirections.actionSplashFragmentToPharmacyFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
        timer.start()


    }
}