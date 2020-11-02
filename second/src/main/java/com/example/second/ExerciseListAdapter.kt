package com.example.second

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.second.Models.ExerciseDetails
import com.example.second.Models.TrainingProgram
import com.example.second.Models.TrainingProgramDetails

class ExerciseListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<ExerciseDetails>() // Cached copy of words

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseItemTitle: TextView = itemView.findViewById(R.id.textTitle)
        val exerciseItemDuration: TextView = itemView.findViewById(R.id.textDuration)
        val exerciseItemEF: TextView = itemView.findViewById(R.id.textEffortType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListAdapter.ExerciseViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseListAdapter.ExerciseViewHolder, position: Int) {
        val current = exercises[position]
        holder.exerciseItemTitle.text = current.exercise.name
        holder.exerciseItemDuration.text = current.exercise.duration.toString()
        holder.exerciseItemEF.text = current.effortType.type
    }

    internal fun setExercises(exercises: List<ExerciseDetails>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount() = exercises.size
}
