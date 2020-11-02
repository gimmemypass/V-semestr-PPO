package com.example.second

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.second.Models.TrainingProgram
import com.example.second.Models.TrainingProgramDetails

class ProgramListAdapter internal constructor(
 context: Context
) : RecyclerView.Adapter<ProgramListAdapter.ProgramViewHolder>(){

 private val inflater: LayoutInflater = LayoutInflater.from(context)
 private var programs = emptyList<TrainingProgramDetails>() // Cached copy of words
 private lateinit var listener : ProgramListFragment.OnItemSelected
 init {
     if (context is ProgramListFragment.OnItemSelected)
      listener = context
 }
 inner class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val programTitleView: TextView = itemView.findViewById(R.id.recycler_text_view)
  val programColorView: View = itemView.findViewById(R.id.recycler_color_view)
//  init {
//      itemView.setOnClickListener{
//       listener.onItemSelected()
//      }
//  }
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramListAdapter.ProgramViewHolder {
  val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
  return ProgramViewHolder(itemView)
 }

 override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
  val current = programs[position]
  holder.programTitleView.text = current.trainingProgram.title
  holder.programColorView.setBackgroundColor(Color.parseColor(current.trainingProgram.color))
  holder.itemView.setOnClickListener{
   listener.onItemSelected(current)
  }
 }

 internal fun setPrograms(programs: List<TrainingProgramDetails>) {
  this.programs = programs
  notifyDataSetChanged()
 }

 override fun getItemCount() = programs.size
}