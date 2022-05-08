package com.nirwashh.android.sportclub

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nirwashh.android.sportclub.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {
    private var _binding: FragmentMainBinding? = null
    private val b get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_right)
        enterTransition = inflater.inflateTransition(R.transition.slide_out_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.btnAddPerson.setOnClickListener {
            val fragment = AddMemberFragment()
            fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("AddMemberFragment").commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}