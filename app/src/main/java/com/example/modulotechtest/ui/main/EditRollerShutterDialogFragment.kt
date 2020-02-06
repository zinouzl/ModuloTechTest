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
import kotlinx.android.synthetic.main.dialog_fragment_roller_shutter.*

class EditRollerShutterDialogFragment : DialogFragment() {


    private var listener: DeviceUpdatedListener? = null

    companion object {
        private const val ROLLER_SHUTTER_ID = "ROLLER_SHUTTER_ID"
        private const val ROLLER_SHUTTER_NAME = "ROLLER_SHUTTER_NAME"
        private const val ROLLER_SHUTTER_POSITION = "ROLLER_SHUTTER_POSITION"

        fun newInstance(
            id: Int,
            name: String,
            position: Int
        ): EditRollerShutterDialogFragment {
            val mFragment = EditRollerShutterDialogFragment()
            val args = Bundle()
            args.putInt(ROLLER_SHUTTER_ID, id)
            args.putString(ROLLER_SHUTTER_NAME, name)
            args.putInt(ROLLER_SHUTTER_POSITION, position)
            mFragment.arguments = args
            return mFragment

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.dialog_fragment_roller_shutter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getInt(ROLLER_SHUTTER_ID)
        val name = arguments!!.getString(ROLLER_SHUTTER_NAME)!!

        val intensity = arguments!!.getInt(ROLLER_SHUTTER_POSITION)
        device_name_input_roller_shutter.setText(name)




        device_seek_bar_roller_shutter.setProgress(intensity.toFloat())

        action_ok_roller_shutter.setOnClickListener {


            if (dataChanged(
                    name,
                    device_name_input_roller_shutter.text.toString(),
                    intensity,
                    device_seek_bar_roller_shutter.progress
                )
            ) {
                listener?.rollerShutterUpdated(
                    id,
                    device_name_input_roller_shutter.text.toString(),
                    device_seek_bar_roller_shutter.progress
                )
            }
            dialog?.dismiss()
        }
        action_cancel_roller_shutter.setOnClickListener { dialog?.dismiss() }
    }

    private fun dataChanged(
        name: String,
        newName: String,
        position: Int,
        newPosition: Int
    ): Boolean {
        return (name != newName) or (position != newPosition)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? HomeFragment
    }
}