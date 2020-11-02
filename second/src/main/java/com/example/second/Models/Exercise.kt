package com.example.second.Models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.SET_DEFAULT
import java.io.Serializable

@Entity
data class Exercise(
    @ForeignKey(
        entity = TrainingProgram::class,
        parentColumns = ["programId"],
        childColumns = ["programFk"],
        onDelete = CASCADE
    )
    var programFk: Int,
    @ForeignKey(
        entity = EffortType::class,
        parentColumns = ["effortTypeId"],
        childColumns = ["effortTypeFk"],
        onDelete = SET_DEFAULT
    )
    @ColumnInfo(defaultValue = "0")
    var effortTypeFk: Int,
    var name: String,
    var duration: Int

//    val effortType: EffortType
) : Serializable
{
    @PrimaryKey(autoGenerate = true) var exerciseId: Int = 0
}