package com.nirwashh.android.sportclub

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import com.nirwashh.android.sportclub.data.ClubContentProvider.Companion.CONTENT_URI
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_FIRST_NAME
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_GENDER
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_LAST_NAME
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_SPORT
import com.nirwashh.android.sportclub.data.DatabaseManager
import com.nirwashh.android.sportclub.databinding.FragmentAddMemberBinding
import com.nirwashh.android.sportclub.model.Member

class AddMemberFragment : BaseFragment() {
    private var _binding: FragmentAddMemberBinding? = null
    private val b get() = _binding!!
    private var gender = ""
    private lateinit var dbManager: DatabaseManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbManager = DatabaseManager(context)

    }

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
    ): View {
        _binding = FragmentAddMemberBinding.inflate(layoutInflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createSpinner()
        b.btnSave.setOnClickListener {
            //saveMember()
            insertMember()
            hideKeyboard(requireContext(), b.edGroup)
        }
        b.btnDelete.setOnClickListener {
            val firstName = b.edFirstName.text.toString()
            val member = dbManager.getMember(firstName)
            dbManager.deleteMember(member)
        }


    }

    private fun saveMember() {
        val firstName = b.edFirstName.text.toString().trim()
        val lastName = b.edLastName.text.toString().trim()
        val sportGroup = b.edGroup.text.toString().trim()
        val gender = b.spinner.selectedItem.toString()
        dbManager.addMember(Member(firstName = firstName, lastName = lastName, gender = gender, sport = sportGroup))
    }
    private fun insertMember() {
        val firstName = b.edFirstName.text.toString().trim()
        val lastName = b.edLastName.text.toString().trim()
        val sportGroup = b.edGroup.text.toString().trim()
        val gender = b.spinner.selectedItem.toString()
        val contentValues = ContentValues()
        contentValues.put(KEY_FIRST_NAME, firstName)
        contentValues.put(KEY_LAST_NAME, lastName)
        contentValues.put(KEY_SPORT, sportGroup)
        contentValues.put(KEY_GENDER, gender)
        val uri = requireActivity().contentResolver.insert(CONTENT_URI, contentValues)
        if (uri == null) {
            Toast.makeText(context, "Incorrect URI", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
        }
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
                    gender = selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    gender = "non"
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}