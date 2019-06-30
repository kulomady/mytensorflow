package com.mine.mytensorflow


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_classification.*

class ClassificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        options.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                Toast.makeText(context, "" + options.selectedItem ,Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>){
            }
        }
    }


}
