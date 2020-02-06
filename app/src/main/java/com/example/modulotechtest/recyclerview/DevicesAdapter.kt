package com.example.modulotechtest.recyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modulotechtest.R
import com.example.modulotechtest.model.device.Devices
import com.example.modulotechtest.model.device.Heater
import com.example.modulotechtest.model.device.Light
import com.example.modulotechtest.model.device.RollerShutter
import kotlinx.android.synthetic.main.heater_item.view.*
import kotlinx.android.synthetic.main.light_item.view.*
import kotlinx.android.synthetic.main.roller_shutter_item.view.*

class DevicesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var devices = listOf<Devices>()
        set(devices) {
            field = devices
            notifyDataSetChanged()
        }
    var listener: OnItemClickListener? = null

    override fun getItemViewType(position: Int) = devices[position].getType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Devices.TYPE_LIGHT -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.light_item, parent, false)
                return LightHolder(itemView)
            }
            Devices.TYPE_Heater -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.heater_item, parent, false)
                return HeaterHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.roller_shutter_item, parent, false)
                return RollerShutterHolder(itemView)
            }

        }
    }

    override fun getItemCount() = devices.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            Devices.TYPE_LIGHT -> {
                (holder as (LightHolder)).bind(position)
            }
            Devices.TYPE_Heater -> {
                (holder as (HeaterHolder)).bind(position)
            }
            Devices.TYPE_ROLLER_SHUTTER -> {
                (holder as (RollerShutterHolder)).bind(position)
            }
        }
    }

    inner class LightHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewName = itemView.light_device_name
        private val textViewMode = itemView.light_device_mode
        private val textViewIntensity = itemView.light_device_intensity
        private val lightModeIcon = itemView.light_mode_icon

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position) {
                    listener?.onItemClick(devices[position])
                }
            }
        }
        // binding the view to device
        fun bind(position: Int) {
            val light = devices[position] as Light
            textViewName.text = light.deviceName
            textViewName.isSelected = true
            val text = "${textViewMode.resources.getString(R.string.device_mode)} ${light.mode}"
            //textViewMode.text = "Device mode : ${light.mode}"
            textViewMode.text = text
            textViewIntensity.text = light.intensity.toString()
            if (light.mode == "ON") {
                lightModeIcon.setImageResource(R.drawable.on)
            } else {
                lightModeIcon.setImageResource(R.drawable.stop)
            }
        }


    }

    inner class HeaterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewName = itemView.heater_device_name
        private val textViewMode = itemView.heater_device_mode
        private val textViewTemperature = itemView.heater_device_temperature
        private val modeIcon = itemView.mode_icon
        val text = itemView.resources.getString(R.string.device_mode)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position) {
                    listener?.onItemClick(devices[position])
                }
            }
        }

        fun bind(position: Int) {
            val heater = devices[position] as Heater
            textViewName.text = heater.deviceName
            textViewName.isSelected = true
            val text = "${textViewMode.resources.getString(R.string.device_mode)} ${heater.mode}"
            textViewMode.text = text
            //textViewMode.text = "Device mode : ${heater.mode}"
            textViewTemperature.text = heater.temperature.toString()
            if (heater.mode == "ON")
                modeIcon.setImageResource(R.drawable.on)
            else
                modeIcon.setImageResource(R.drawable.stop)


        }


    }

    inner class RollerShutterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewName = itemView.roller_shutter_name
        private val textViewPosition = itemView.roller_shutter_position

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position) {
                    listener?.onItemClick(devices[position])
                }
            }
        }

        fun bind(position: Int) {
            val rollerShutter = devices[position] as RollerShutter
            textViewName.text = rollerShutter.deviceName
            textViewPosition.text = rollerShutter.position.toString()
        }

    }

    interface OnItemClickListener {
        fun onItemClick(devices: Devices)
    }


}