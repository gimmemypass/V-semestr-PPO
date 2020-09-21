package com.example.first

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button0
import kotlinx.android.synthetic.main.activity_main.button1
import kotlinx.android.synthetic.main.activity_main.button2
import kotlinx.android.synthetic.main.activity_main.button3
import kotlinx.android.synthetic.main.activity_main.button4
import kotlinx.android.synthetic.main.activity_main.button5
import kotlinx.android.synthetic.main.activity_main.button6
import kotlinx.android.synthetic.main.activity_main.button7
import kotlinx.android.synthetic.main.activity_main.button8
import kotlinx.android.synthetic.main.activity_main.button9
import kotlinx.android.synthetic.main.activity_main.buttonDelete
import kotlinx.android.synthetic.main.activity_main.buttonTranslate
import kotlinx.android.synthetic.main.activity_main.noImeEditText
import kotlinx.android.synthetic.main.keyboard.*
import kotlinx.android.synthetic.main.text_layout.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private val units = arrayOf<String>("дюйм в мм", "мм в дюйм", "миля в метры", "метры в мили", "ярд в см", "см в ярды")
    private var mToIn : Boolean = true
    private var coef : Double = getCoef(0)
    private lateinit var clipboard : ClipboardManager
    private lateinit var unitManager : UnitManager
    private lateinit var adapterCategory : ArrayAdapter<String>
    private lateinit var adapterFirst: ArrayAdapter<String>
    private lateinit var adapterSecond: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        setUnits()
        setSpinners()
        //buttons
        val numberBtn = arrayOfNulls<Button>(10)
        numberBtn[0] = button0
        numberBtn[1] = button1
        numberBtn[2] = button2
        numberBtn[3] = button3
        numberBtn[4] = button4
        numberBtn[5] = button5
        numberBtn[6] = button6
        numberBtn[7] = button7
        numberBtn[8] = button8
        numberBtn[9] = button9
        for (i in numberBtn.indices){
            numberBtn[i]?.setOnClickListener(this)
        }
        buttonTranslate.setOnClickListener(this)
        buttonDelete.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
        buttonTextCopy.setOnClickListener(this)
        buttonEditCopy.setOnClickListener(this)
        buttonSwap.setOnClickListener(this)

    }

//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        focusedEdit = v as NoImeEditText
//        return false
//    }

    override fun onClick(v: View?) {
        when(v){
            button0 -> {
                noImeEditText.append("0");
            }
            button1 -> {
                noImeEditText.append("1");
            }
            button2 -> {
                noImeEditText.append("2");
            }
            button3 -> {
                noImeEditText.append("3");
            }
            button4 -> {
                noImeEditText.append("4");
            }
            button5 -> {
                noImeEditText.append("5");
            }
            button6 -> {
                noImeEditText.append("6");
            }
            button7 -> {
                noImeEditText.append("7");
            }
            button8 -> {
                noImeEditText.append("8");
            }
            button9 -> {
                noImeEditText.append("9");
            }
            buttonTranslate -> {
//                Toast.makeText(this, "translated", Toast.LENGTH_SHORT).show()
                //textView.text = translateUnits()
                var value : Double = 0.0
                val text = noImeEditText.text.toString()
                if(text.isNotEmpty()){
                    value = text.toDouble()
                }
                textView.text = unitManager.translate(value, spinnerFirstUnits.selectedItem.toString(),spinnerSecondUnits.selectedItem.toString(), spinnerCategory.selectedItemPosition, mToIn).toString()
            }
            buttonDelete -> {
                val length = noImeEditText.text?.length
                if(length != null && length > 0){
                    noImeEditText.text?.delete(length-1, length)
                }
            }
            buttonDot -> {
                noImeEditText.append(".");
            }
            buttonTextCopy -> {
                val clip : ClipData = ClipData.newPlainText("textField", textView.text.toString())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, textView.text.toString() + " copied", Toast.LENGTH_SHORT).show()
            }
            buttonEditCopy -> {
                val clip : ClipData = ClipData.newPlainText("editField", noImeEditText.text.toString())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, noImeEditText.text.toString() + " copied", Toast.LENGTH_SHORT).show()
            }
            buttonSwap -> {
                mToIn = !mToIn
                if(mToIn){
                    spinnerFirstUnits.adapter = adapterFirst
                    spinnerSecondUnits.adapter = adapterSecond
                }
                else{
                    spinnerFirstUnits.adapter = adapterSecond
                    spinnerSecondUnits.adapter = adapterFirst
                }
                adapterFirst.notifyDataSetChanged()
                adapterSecond.notifyDataSetChanged()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            when(parent){
                spinnerCategory -> {
                    mToIn = true
                    adapterFirst.clear()
                    adapterFirst.addAll(unitManager.getCategoriesFirstUnits(position))
                    adapterFirst.notifyDataSetChanged()
                    adapterSecond.clear()
                    adapterSecond.addAll(unitManager.getCategoriesSecondUnits(position))
                    adapterSecond.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getCoef(id : Int) : Double {
       //"дюйм в мм", "мм в дюйм", "миля в метры", "метры в мили", "ярд в см", "см в ярды"
        when(id){
            0 -> return 25.4
            1 -> return 1/25.4
            2 -> return 1609.0
            3 -> return 1/1609.0
            4 -> return 91.44
            5 -> return 1/91.44

        }
        return 0.0
    }

    private fun translateUnits() : String {
        var value : Double = 0.0
        val text = noImeEditText.text.toString()
        if(text.isNotEmpty()){
            value = text.toDouble()
        }
        return (value * coef).toString()
    }

    private fun setUnits(){
        unitManager = UnitManager()
        var tempCat : UnitCategory = UnitCategory("distance", "mm", "in", 0.03937)
        tempCat.addFirstSetUnit("cm", 10.0)
        tempCat.addFirstSetUnit("m", 1000.0)
        tempCat.addSecondSetUnit("ft", 12.0)
        tempCat.addSecondSetUnit("yd", 36.0)
        unitManager.addCategory(tempCat)
        tempCat = UnitCategory("weight", "g", "oz", 0.0353)
        tempCat.addFirstSetUnit("kg", 1000.0)
        tempCat.addFirstSetUnit("mg", 0.001)
        tempCat.addSecondSetUnit("lb", 16.0)
        tempCat.addSecondSetUnit("stone", 224.0)
        unitManager.addCategory(tempCat)
        tempCat  = UnitCategory("area", "sq cm", "sq in", 0.1550)
        tempCat.addFirstSetUnit("sq m", 10000.0)
        tempCat.addFirstSetUnit("sq mm", 0.01)
        tempCat.addSecondSetUnit("sq ft", 144.0)
        tempCat.addSecondSetUnit("sq yd", 1296.0)
        unitManager.addCategory(tempCat)
    }
    private fun setSpinners(){
        adapterCategory = ArrayAdapter<String>(this, R.layout.spinner_item, unitManager.getCategories())
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapterCategory
        spinnerCategory.prompt = "Category"
        spinnerCategory.setSelection(0)
        spinnerCategory.onItemSelectedListener = this
        adapterFirst = ArrayAdapter<String>(this, R.layout.spinner_item, unitManager.getCategoriesFirstUnits(spinnerCategory.selectedItemPosition))
        adapterFirst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFirstUnits.adapter = adapterFirst
        spinnerFirstUnits.prompt = "First"
        spinnerFirstUnits.setSelection(0)
        spinnerFirstUnits.onItemSelectedListener = this
        adapterSecond = ArrayAdapter<String>(this, R.layout.spinner_item, unitManager.getCategoriesSecondUnits(spinnerCategory.selectedItemPosition))
        adapterSecond.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSecondUnits.adapter = adapterSecond
        spinnerSecondUnits.prompt = "Second"
        spinnerSecondUnits.setSelection(0)
        spinnerSecondUnits.onItemSelectedListener = this
    }

}


