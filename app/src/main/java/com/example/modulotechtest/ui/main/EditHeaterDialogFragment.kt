package com.example.modulotechtest.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.modulotechtest.R
import com.example.modulotechtest.ui.main.home.HomeFragment
import com.example.modulotechtest.utils.DeviceUpdatedListener
import kotlinx.android.synthetic.main.dialog_fragment_heater.*

class EditHeaterDialogFragment : DialogFragment() {


    private var listener: DeviceUpdatedListener? = null

    companion object {
        private const val HEATER_ID = "HEATER_ID"
        private const val HEATER_NAME = "HEATER_NAME"
        private const val HEATER_MODE = "HEATER_MODE"
        private const val HEATER_TEMPERATURE = "HEATER_TEMPERATURE"

        fun newInstance(
            id: Int,
            name: String,
            mode: String,
            temperature: Double
        ): EditHeaterDialogFragment {
            val mFragment = EditHeaterDialogFragment()
            val args = Bundle()
            args.putInt(HEATER_ID, id)
            args.putString(HEATER_NAME, name)
            args.putString(HEATER_MODE, mode)
            args.putDouble(HEATER_TEMPERATURE, temperature)
            mFragment.arguments = args
            return mFragment

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.dialog_fragment_heater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt(HEATER_ID)
        val name = arguments!!.getString(HEATER_NAME)!!
        val checked = arguments!!.getString(HEATER_MODE) == "ON"
        val temperature = arguments!!.getDouble(HEATER_TEMPERATURE)
        device_name_input_heater.setText(name)
        device_mode_switch_heater.isChecked = checked
        device_number_picker_heater.maxValue = 42
        device_number_picker_heater.minValue = 0
        device_number_picker_heater.displayedValues =
            resources.getStringArray(R.array.temperature_array)


        device_number_picker_heater.value = ((temperature - 7) * 2).toInt()

        action_ok_heater.setOnClickListener {
            var mode = "OFF"
            if (device_mode_switch_heater.isChecked) {
                mode = "ON"
            }
            val temperaturePicker = ((device_number_picker_heater.value / 2F) + 7).toDouble()
            if (dataChanged(
                    name,
                    device_name_input_heater.text.toString(),
                    checked,
                    device_mode_switch_heater.isChecked,
                    temperature,
                    temperaturePicker
                )
            ) {
                listener?.heaterUpdated(
                    id,
                    device_name_input_heater.text.toString(),
                    mode,
                    temperaturePicker
                )
            }
            dialog?.dismiss()
        }
        action_cancel_heater.setOnClickListener { dialog?.dismiss() }
    }

    private fun dataChanged(
        name: String,
        newName: String,
        checked: Boolean,
        newChecked: Boolean,
        temperature: Double,
        newTemperature: Double
    ): Boolean {
        return (name != newName) or (checked != newChecked) or (temperature != newTemperature)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? HomeFragment
    }
}