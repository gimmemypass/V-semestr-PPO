package com.example.second.Models

import android.graphics.Color
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TrainingProgram(
    val title: String,
    val color: String

): Serializable
{
    @PrimaryKey(autoGenerate = true) var programId: Int = 0
}
