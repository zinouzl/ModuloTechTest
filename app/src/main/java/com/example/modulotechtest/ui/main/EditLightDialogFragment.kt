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
import kotlinx.android.synthetic.main.dialog_fragment_light.*

class EditLightDialogFragment : DialogFragment() {


    private var listener: DeviceUpdatedListener? = null

    companion object {
        private const val LIGHT_ID = "LIGHT_ID"
        private const val LIGHT_NAME = "LIGHT_NAME"
        private const val LIGHT_MODE = "LIGHT_MODE"
        private const val LIGHT_INTENSITY = "LIGHT_INTENSITY"

        fun newInstance(
            id: Int,
            name: String,
            mode: String,
            intensity: Int
        ): EditLightDialogFragment {
            val mFragment = EditLightDialogFragment()
            val args = Bundle()
            args.putInt(LIGHT_ID, id)
            args.putString(LIGHT_NAME, name)
            args.putString(LIGHT_MODE, mode)
            args.putInt(LIGHT_INTENSITY, intensity)
            mFragment.arguments = args
            return mFragment

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.dialog_fragment_light, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt(LIGHT_ID)
        val name = arguments!!.getString(LIGHT_NAME)!!
        val checked = arguments!!.getString(LIGHT_MODE) == "ON"
        val intensity = arguments!!.getInt(LIGHT_INTENSITY)
        device_name_input_light.setText(name)
        device_mode_switch_light.isChecked = checked



        device_seek_bar_light.setProgress(intensity.toFloat())

        action_ok_light.setOnClickListener {
            var mode = "OFF"
            if (device_mode_switch_light.isChecked) {
                mode = "ON"
            }

            if (dataChanged(
                    name,
                    device_name_input_light.text.toString(),
                    checked,
                    device_mode_switch_light.isChecked,
                    intensity,
                    device_seek_bar_light.progress
                )
            ) {
                listener?.lightUpdated(
                    id,
                    device_name_input_light.text.toString(),
                    mode,
                    device_seek_bar_light.progress
                )
            }
            dialog?.dismiss()
        }
        action_cancel_light.setOnClickListener { dialog?.dismiss() }
    }

    private fun dataChanged(
        name: String,
        newName: String,
        checked: Boolean,
        newChecked: Boolean,
        intensity: Int,
        newIntensity: Int
    ): Boolean {
        return (name != newName) or (checked != newChecked) or (intensity != newIntensity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? HomeFragment
    }
}