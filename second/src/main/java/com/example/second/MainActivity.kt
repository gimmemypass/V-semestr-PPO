package com.example.second

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.second.Models.TrainingProgram
import com.example.second.Models.TrainingProgramDetails
import com.example.second.data.ProgramViewModel

class MainActivity : AppCompatActivity(), ProgramListFragment.OnItemSelected{
    private lateinit var programViewModel: ProgramViewModel
    private val newProgramActivityRequestCode = 1
    private lateinit var fragment : ProgramListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        fragment = supportFragmentManager.findFragmentByTag("listFragment") as ProgramListFragment
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, ProgramListFragment.newInstance(), "programList")
                .commit()
        }
    }

    override fun onItemSelected(program: TrainingProgramDetails) {
        val detailsFragment = ProgramDetailsFragment.newInstance(program)
//        var detailsFragment = TestFragment.newInstance("asd", "da")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "detailsFragment")
            .addToBackStack(null)
            .commit()
    }
}

