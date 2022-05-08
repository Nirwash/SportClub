package com.nirwashh.android.sportclub

import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.nirwashh.android.sportclub.databinding.FragmentAddMemberBinding

class AddMemberFragment : BaseFragment() {
    private var _binding: FragmentAddMemberBinding? = null
    private val b get() = _binding!!
    private var gender = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.slide_out_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMemberBinding.inflate(layoutInflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createSpinner()
    }

    private fun createSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.array_gender,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        b.spinner.apply {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGender = parent?.selectedItemPosition.toString()
                    if (!TextUtils.isEmpty(selectedGender)) {
                        gender = when (selectedGender) {
                            "Male" -> 1
                            "Female" -> 2
                            else -> 0
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    gender = 0
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}