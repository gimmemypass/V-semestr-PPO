package com.example.second

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.second.Models.Exercise
import com.example.second.Models.TrainingProgram
import com.example.second.Models.TrainingProgramDetails
import com.example.second.data.ExerciseViewModel
import com.example.second.presentation.EditExerciseDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_program_details.*
import org.w3c.dom.Text


class ProgramDetailsFragment : Fragment(), EditExerciseDialog.OnUpdated {

    private lateinit var mContext: Context
    private lateinit var exerciseViewModel : ExerciseViewModel

    private lateinit var textTitle : TextView
    private lateinit var countExercises : TextView
    private lateinit var exerciseRecyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_program_details, container, false)
        textTitle = view.findViewById(R.id.text_title)
        countExercises = view.findViewById(R.id.count_exercises)
        val program = arguments?.getSerializable(PROGMODEL) as TrainingProgramDetails
        Toast.makeText(activity, program.trainingProgram.programId.toString() , Toast.LENGTH_SHORT).show()
        textTitle.text = program.trainingProgram.title
        countExercises.text = program.exercises.size.toString()
        ///////recyclerView
        exerciseRecyclerView = view.findViewById(R.id.exercise_recyclerview)
        val adapter = ExerciseListAdapter(mContext)
        exerciseRecyclerView.adapter = adapter
        exerciseRecyclerView.layoutManager = LinearLayoutManager(context)
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        exerciseViewModel.searchExercisesByProgramId(program.trainingProgram.programId)
        exerciseViewModel.exercises.observe(viewLifecycleOwner, Observer { exercises ->
            exercises?.let {adapter.setExercises(it)}
        })
        //////////////fab
        addButton = view.findViewById(R.id.fab)
        addButton.setOnClickListener{
//            TODO("call modal view to create")
            val newExercise = Exercise(program.trainingProgram.programId, 0,"", 30000)
            val id = exerciseViewModel.insertExercise(newExercise)
            newExercise.exerciseId = id
            val dialog = EditExerciseDialog(newExercise, this)
            dialog.show(requireActivity().supportFragmentManager, "dialog")
        }
        return view
    }

    companion object {
        private const val PROGMODEL = "model"

        @JvmStatic
        fun newInstance(program : TrainingProgramDetails) : ProgramDetailsFragment {
            val args = Bundle().apply {
                putSerializable(PROGMODEL, program)
            }
            val fragment = ProgramDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onUpdated(exercise: Exercise) {
        exerciseViewModel.updateExercise(exercise)
    }
}