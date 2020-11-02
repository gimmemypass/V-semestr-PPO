package com.example.second.data

import androidx.lifecycle.LiveData
import com.example.second.Models.Exercise
import com.example.second.Models.ExerciseDetails
import com.example.second.Models.TrainingProgram
import com.example.second.Models.TrainingProgramDetails

class ProgramRepository(private val programDao : ProgramDAO) {
    val allPrograms : LiveData<List<TrainingProgramDetails>> = programDao.getPrograms()

    suspend fun insertProgram(program : TrainingProgram){
        programDao.insertProgram(program)
    }

}