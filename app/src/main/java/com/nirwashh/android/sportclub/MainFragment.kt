package com.nirwashh.android.sportclub

import android.database.Cursor
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nirwashh.android.sportclub.data.ClubContentProvider.Companion.CONTENT_URI
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_FIRST_NAME
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_GENDER
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_ID
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_LAST_NAME
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_SPORT
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.TABLE_NAME
import com.nirwashh.android.sportclub.databinding.FragmentMainBinding
import com.nirwashh.android.sportclub.model.Member

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

    override fun onStart() {
        super.onStart()
        displayData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayData() {
        val projection = arrayOf(KEY_ID, KEY_FIRST_NAME, KEY_LAST_NAME, KEY_GENDER, KEY_SPORT)
        val cursor: Cursor? = requireActivity().contentResolver.query(CONTENT_URI, projection, null, null, null)

        val members = ""
        val text = "All members\n\n" +
                "$KEY_ID $KEY_FIRST_NAME $KEY_LAST_NAME $KEY_GENDER $KEY_SPORT".plus(members)
        with(cursor) {
            while (this?.moveToNext() == true) {
                val memberID = cursor?.getString(cursor.getColumnIndexOrThrow(KEY_ID))?.toInt()
                val firstName = cursor?.getString(cursor.getColumnIndexOrThrow(KEY_FIRST_NAME))
                val lastName = cursor?.getString(cursor.getColumnIndexOrThrow(KEY_LAST_NAME))
                val gender = cursor?.getString(cursor.getColumnIndexOrThrow(KEY_GENDER))
                val sportGroup = cursor?.getString(cursor.getColumnIndexOrThrow(KEY_SPORT))
                val member = "\n" +
                        "$memberID $firstName $lastName $gender $sportGroup"
                members.plus(member)

            }
        }
        cursor?.close()

        b.tvDisplayData.text = text

    }

}