package com.example.kotlinexample2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class FragmentOne:Fragment() {
    //retrieve the view model in the activity scope
    private val viewModel:UserViewModel by activityViewModels()
//
    private lateinit var  editTextName:EditText
    private lateinit var editTextZipcode: EditText
    private lateinit var button:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? { //? means something can be null

        val view = inflater.inflate(R.layout.fragment_one, container, false)
        editTextName=view.findViewById(R.id.editText_name);
        editTextZipcode = view.findViewById(R.id.editText_zip)
        button = view.findViewById(R.id.button_submit)

        //when button is clicked I want to update the
        // user ifnoration in the view model with the dagta entererd

        //if you can do it in the app, do it in the app
        button.setOnClickListener {

            //it in this context, in this case it is the view
            //create our new userinfo
            val userInfo= UserInformation(editTextName.text.toString(), editTextZipcode.text.toString())
            viewModel.setInformation(userInfo)

        }
        return view;
    }

}