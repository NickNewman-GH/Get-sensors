package com.example.getsensors

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sensors = get_sensors()
        val spinner: Spinner = findViewById(R.id.spinner)
        val listView: ListView = findViewById(R.id.listView)
        val list: ArrayList<String> = arrayListOf("abd", "adsd")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                list.clear()
                list.addAll(sensors[id.toInt()])
                adapter.notifyDataSetChanged()
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }
    }

    fun get_sensors(): Array<MutableList<String>> {
        val sensorTypes = arrayOf(intArrayOf(13,5,2,14,6,12), intArrayOf(1,35,11,15,20,9,4,16,36,30,8,17,29,19,18,10), intArrayOf(34,31,21))
        val sensorList = arrayOf(mutableListOf<String>(), mutableListOf<String>(), mutableListOf<String>(), mutableListOf<String>())
        val sensManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensManager.getSensorList(Sensor.TYPE_ALL).forEach{
            var is_type = false
            for (i in 0..2){
                if (sensorTypes[i].contains(it.type)){
                    sensorList[i].add(it.name)
                    is_type = true
                    break
                }
            }
            if (!is_type)
                sensorList[3].add(it.name)
        }
        return sensorList
    }
}