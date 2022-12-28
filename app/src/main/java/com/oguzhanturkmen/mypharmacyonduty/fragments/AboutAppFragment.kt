package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentAboutAppBinding
import kotlinx.android.synthetic.main.fragment_about_app.*
import kotlinx.android.synthetic.main.my_toolbar.*
import kotlinx.android.synthetic.main.my_toolbar.view.*

class AboutAppFragment : Fragment() {
    private var _binding: FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutAppBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendMail()
        apiToClick()
    }

    fun sendMail() {
        binding.emailText.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("oguzhn.1132@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Subject")
                putExtra(Intent.EXTRA_TEXT, "Body")
            }
            startActivity(Intent.createChooser(emailIntent, "Send email"))
        }
    }

    fun apiToClick() {
        binding.apiText.setOnClickListener {
            goToApi("https://www.nosyapi.com/api/nobetci-eczane")
        }
    }

    fun goToApi(s: String) {
        val uri = Uri.parse(s)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
