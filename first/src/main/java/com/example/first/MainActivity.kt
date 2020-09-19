package com.example.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //buttons
        val numberBtn = arrayOfNulls<Button>(10)
        numberBtn[0] = findViewById(R.id.button0)
        numberBtn[1] = findViewById(R.id.button1)
        numberBtn[2] = findViewById(R.id.button2)
        numberBtn[3] = findViewById(R.id.button3)
        numberBtn[4] = findViewById(R.id.button4)
        numberBtn[5] = findViewById(R.id.button5)
        numberBtn[6] = findViewById(R.id.button6)
        numberBtn[7] = findViewById(R.id.button7)
        numberBtn[8] = findViewById(R.id.button8)
        numberBtn[9] = findViewById(R.id.button9)
//        for (i in numberBtn.indices){
//            numberBtn[i].setOnClickListener()
//        }
        val buttonTranslate: Button = findViewById(R.id.buttonTranslate);
        buttonTranslate.setOnClickListener{
            Toast.makeText(this, "translated", Toast.LENGTH_SHORT).show()

        }
        //edits
        val edit1: EditText = findViewById(R.id.editTextNumberDecimal);
        val edit2: EditText = findViewById(R.id.editTextNumberDecimal2);
    }
}
