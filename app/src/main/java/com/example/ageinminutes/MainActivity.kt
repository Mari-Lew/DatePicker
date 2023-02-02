package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var tvSelectedDate : TextView? = null
    var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener{
           clickDatePicker()
        }
    }

    fun clickDatePicker()
    {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{ view, selectedYear, selectedMonth, selectedDayofMonth ->
            var selectedDate = "${selectedMonth+1}/$selectedDayofMonth/$selectedYear"
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
            //selected date
            val theDate = sdf.parse(selectedDate)
            val dateInMin = theDate.time / 60000 //date property gives us
            // what day it is today
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val curDateMins = currentDate.time / 60000

            val minDifference = curDateMins - dateInMin

            tvAgeInMinutes?.text = minDifference.toString()

        },
            year,
            month,
            day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()

        Toast.makeText(this,"Button pressed", Toast.LENGTH_LONG).show()
    }
}